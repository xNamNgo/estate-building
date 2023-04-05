package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;
import com.laptrinhjavaweb.dto.respone.StaffResponeDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService implements IBuildingService {
    private final UploadFileUtils uploadFileUtils;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    BuildingConverter buildingConverter;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RentAreaRepository rentAreaRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository, BuildingConverter buildingConverter,
                           UploadFileUtils uploadFileUtils) {
        this.buildingRepository = buildingRepository;
        this.buildingConverter = buildingConverter;
        this.uploadFileUtils = uploadFileUtils;
    }

    /**
     * Load data ra table
     *
     * @param model : object chứa các field tìm kiếm
     * @return danh sách đã tìm được .
     */
    @Override
    public List<BuildingRequestDTO> loadBuilding(Pageable page, BuildingSearchDTO model) {
        List<BuildingRequestDTO> result = new ArrayList<>();

        // convert ObjectDTO sang ObjectBuider để truyền xuống repository => Giải quyết bài toán nhiều tham số
        BuildingSearchBuilder builder = buildingConverter.convertToBuildingSearchBuilder(model);
        List<BuildingEntity> buildingEntities = buildingRepository.findByCondition(page, builder);
        for (BuildingEntity item : buildingEntities) {
            BuildingRequestDTO buildingRequestDTO = buildingConverter.convertToDTO(item);
            result.add(buildingRequestDTO);
        }
        return result;
    }

    @Override
    public int countTotalItems(BuildingSearchDTO model) {
        BuildingSearchBuilder builder = buildingConverter.convertToBuildingSearchBuilder(model);
        return buildingRepository.countTotalItem(builder);
    }


    /*
     * - Lấy danh sách tất cả nhân viên
     * - kiểm tra nhân viên có nằm trong building không  - nếu có thì "checked"
     * */
    @Override
    public ResponeDTO getListStaff(Long buildingId) {
        // result
        List<StaffResponeDTO> data = new ArrayList<>();

        // danh sách tất cả nhân viên
        List<UserEntity> userList = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        // danh sách nhân viên đã được giao tòa nhà .
        // 1 building - n user , nên sẽ tìm n user bằng 1 building - cụ thể là building id.
        // building(1) có 2 user (2,3) nên cần load ra ds user id 2,3
        List<UserEntity> userAssignmentList = userRepository.findByBuildingId(buildingId);

        // output ở client : load all danh sách nhân viên có role STAFF.
        // nhân viên nào quản lý thì "checked" -
        for (UserEntity user : userList) {
            StaffResponeDTO staffResponeDTO = new StaffResponeDTO();
            staffResponeDTO.setStaffId(user.getId());
            staffResponeDTO.setFullName(user.getFullName());

            // user được giao tòa nhà thì checked
            boolean isAssigned = false;
            for (UserEntity userAssignment : userAssignmentList) {
                if (user.getId() == userAssignment.getId()) {
                    isAssigned = true;
                    break;
                }
            }

            // check
            staffResponeDTO.setChecked(isAssigned ? "checked" : "");

            data.add(staffResponeDTO);
        }
        return new ResponeDTO(data, "success", "");
    }

    // Save - Update building
    @Override
    public BuildingRequestDTO save(BuildingRequestDTO buildingRequestDTO) {
        BuildingEntity buildingEntity = buildingConverter.covertToBuildingEntity(buildingRequestDTO);
        Long buildingId = buildingRequestDTO.getId();

        //  Nếu đã tồn tại 1 building thì sẽ UPDATE.
        if (buildingId != null) {
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
                                                             .orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setImage(foundBuilding.getImage());

            // tránh bị mất data assignment thì phải set cho buliding entity.
            List<UserEntity> assignment = foundBuilding.getUsers();
            if (assignment.size() != 0) {
                buildingEntity.setUsers(assignment);
            }

        }
        saveThumbnail(buildingRequestDTO, buildingEntity);

        // "400,500,600" parse to Int [400,500,600] - Integer
        String rentAreaStr = buildingRequestDTO.getRentArea();
        if (rentAreaStr != null) {
            for (String value : rentAreaStr.split(",")) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                Integer valueParse = Integer.parseInt(value);
                rentAreaEntity.setValue(valueParse);
                rentAreaEntity.setBuilding(buildingEntity);
                buildingEntity.addRentArea(rentAreaEntity);
            }
        }


        return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
    }

    private void saveThumbnail(BuildingRequestDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (null != buildingDTO.getImageBase64()) {
            if (null != buildingEntity.getImage()) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            byte[] bytes = Base64.decodeBase64(buildingDTO.getImageBase64().getBytes());
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

    // delete building
    @Override
    public void delete(long[] idList) {
        // xóa bảng FK trước rồi mới xóa bảng building
        // Sử dụng cascade nên khi xóa building thì sẽ tự động xóa các bản reference đến building.
        // riêng @ManyToMany không cần cascade để xóa bảng trung gian .
        if (idList.length > 0) {
            Long count = buildingRepository.countByIdIn(idList);
            if (count != idList.length) {
                throw new NotFoundException("Không tìm thấy tòa nhà hợp lệ!");
            }
            buildingRepository.deleteByIdIn(idList);
        }
    }

    @Override
    public BuildingRequestDTO findById(Long id) {
        BuildingRequestDTO result = new BuildingRequestDTO();
        if (id != null) {
            result = buildingConverter.convertToDTO(buildingRepository.findOneById(id));
        }
        return result;
    }


    // Giao tòa nhà cho nhân viên quản lý
    public ResponeDTO saveAssignmentBuilding(AssignmentBuildingRequestDTO request) {
        // data từ client truyền xuống.
        Long buildingId = request.getBuildingId();
        long[] staffIdList = request.getStaffIdList();
        BuildingEntity buildingEntity = buildingRepository.findOneById(buildingId);
        List<UserEntity> userEntities = userRepository.findByIdIn(staffIdList);
        buildingEntity.setUsers(userEntities);
        buildingRepository.save(buildingEntity);
        return new ResponeDTO(null, "success", "Giao tòa nhà thành công!");
    }
}

