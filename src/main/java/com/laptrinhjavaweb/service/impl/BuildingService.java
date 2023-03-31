package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequestDTO;
import com.laptrinhjavaweb.dto.request.BuildingListRequestDTO;
import com.laptrinhjavaweb.dto.respone.BuildingRequestDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;
import com.laptrinhjavaweb.dto.respone.StaffResponeDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.enums.TypeEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingService implements IBuildingService {
    private final BuildingRepository buildingRepository;
    private final BuildingConverter buildingConverter;
    private final UploadFileUtils uploadFileUtils;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository, BuildingConverter buildingConverter,
                           UploadFileUtils uploadFileUtils) {
        this.buildingRepository = buildingRepository;
        this.buildingConverter = buildingConverter;
        this.uploadFileUtils = uploadFileUtils;
    }

    @Autowired
    UserRepository userRepository;
    @Autowired
    RentAreaRepository rentAreaRepository;

    /**
     * Load data ra table
     *
     * @param buildingSearchDTO : object chứa các field tìm kiếm
     * @return danh sách đã tìm được .
     */
    @Override
    public List<BuildingRequestDTO> loadBuilding(BuildingSearchDTO buildingSearchDTO) {
        List<BuildingRequestDTO> result = new ArrayList<>();

        // convert ObjectDTO sang ObjectBuider để truyền xuống repository => Giải quyết bài toán nhiều tham số
        BuildingSearchBuilder buildingSearchBuilder = buildingConverter.convertToBuildingSearchBuilder(buildingSearchDTO);
        List<BuildingEntity> buildingEntities = buildingRepository.findByCondition(buildingSearchBuilder);
        for (BuildingEntity item : buildingEntities) {
            BuildingRequestDTO buildingRequestDTO = buildingConverter.convertToDTO(item);
            result.add(buildingRequestDTO);
        }
        return result;
    }


    @Override
    public Map<String, String> getDistrictMap() {
        Map<String, String> districts = new HashMap<>();
        for (DistrictEnum item : DistrictEnum.values()) {
            districts.put(item.toString(), item.getValue());
        }
        return districts;
    }

    @Override
    public Map<String, String> getTypeMap() {
        Map<String, String> types = new HashMap<>();
        for (TypeEnum item : TypeEnum.values()) {
            types.put(item.toString(), item.getValue());
        }
        return types;
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
    public void save(BuildingRequestDTO buildingRequestDTO) {
        BuildingEntity buildingEntity = buildingConverter.covertToBuildingEntity(buildingRequestDTO);
        Long buildingId = buildingRequestDTO.getId();

        // update
        if (buildingId != null) {
            buildingEntity.getRentAreas().clear();
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId)
                                                             .orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setImage(foundBuilding.getImage());
        }
        saveThumbnail(buildingRequestDTO, buildingEntity);

        // "400,500,600" parse to Int [400,500,600] - Integer
        for (String value : buildingRequestDTO.getRentArea().split(",")) {
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            Integer valueParse = Integer.parseInt(value);
            rentAreaEntity.setValue(valueParse);
            rentAreaEntity.setBuilding(buildingEntity);
            buildingEntity.addRentArea(rentAreaEntity);
        }
        buildingRepository.save(buildingEntity);
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
    public void delete(BuildingListRequestDTO buildingListRequestDTO) {
        // xóa bảng FK trước rồi mới xóa bảng building
        // Sử dụng cascade nên khi xóa building thì sẽ tự động xóa các bản reference đến building.
        // riêng @ManyToMany không cần cascade để xóa bảng trung gian .
        for (Long id : buildingListRequestDTO.getIdList()) {
            buildingRepository.deleteById(id);
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
        List<Long> staffIdList = request.getStaffIdList();

        BuildingEntity buildingEntity = buildingRepository.findOneById(buildingId);
        buildingEntity.getUsers().clear(); // xóa hết danh sách nhân viên quản lý hiện tại .

        // thêm nhân viên quản lý vào building.
        for (Long id : staffIdList) {
            UserEntity user = userRepository.findOneById(id);
            buildingEntity.getUsers().add(user);
        }
        buildingRepository.save(buildingEntity);
        return new ResponeDTO(null, "success", "Giao tòa nhà thành công!");
    }
}

