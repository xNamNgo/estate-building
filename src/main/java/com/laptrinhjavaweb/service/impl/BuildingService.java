package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRespone;
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
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<BuildingSearchRespone> loadBuilding(Pageable page, BuildingSearchRequest model) {
        List<BuildingSearchRespone> result = new ArrayList<>();
        BuildingSearchBuilder builder = buildingConverter.convertToBuildingSearchBuilder(model);
        List<BuildingEntity> buildingEntities = buildingRepository.findByCondition(page, builder);

        // convert từ entity -> dto
        for (BuildingEntity item : buildingEntities) {
            BuildingSearchRespone buildingSearchRespone = buildingConverter.convertToDTO(item);
            result.add(buildingSearchRespone);
        }
        return result;
    }

    @Override
    public int countTotalItems(BuildingSearchRequest model) {
        BuildingSearchBuilder builder = buildingConverter.convertToBuildingSearchBuilder(model);
        return buildingRepository.countTotalItem(builder);
    }

    // Save - Update building
    @Override
    @Transactional
    public BuildingSearchRespone save(BuildingSearchRespone buildingSearchRespone) {
        BuildingEntity buildingEntity = buildingConverter.covertToBuildingEntity(buildingSearchRespone);
        Long buildingId = buildingSearchRespone.getId();

        //  Nếu đã tồn tại 1 building thì UPDATE.
        if (buildingId != null) {
            BuildingEntity foundBuilding = buildingRepository.findById(buildingId).orElseThrow(() -> new NotFoundException("Building not found!"));
            buildingEntity.setImage(foundBuilding.getImage());

            // tránh bị mất data assignment thì phải set cho building entity.
            List<UserEntity> assignment = foundBuilding.getUsers();
            if (assignment.size() != 0) {
                buildingEntity.setUsers(assignment);
            }
        }
        saveThumbnail(buildingSearchRespone, buildingEntity);
        saveRentArea(buildingSearchRespone, buildingEntity);
        return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
    }


    /**
     * 1 building - n rentarea
     * Saves rent areas from the provided BuildingSearchRespone into the given BuildingEntity.
     *
     * @param buildingSearchRespone The BuildingSearchRespone containing rent area information.
     * @param buildingEntity        The BuildingEntity to which the rent areas will be saved.
     */
    private void saveRentArea(BuildingSearchRespone buildingSearchRespone, BuildingEntity buildingEntity) {
        String rentAreaString = buildingSearchRespone.getRentArea();
        if (rentAreaString != null) {
            for (String value : rentAreaString.split(",")) {
                RentAreaEntity currentRentArea = new RentAreaEntity();
                Integer parsedValue = Integer.parseInt(value);
                currentRentArea.setValue(parsedValue);
                currentRentArea.setBuilding(buildingEntity);
                //cascade
                buildingEntity.addRentArea(currentRentArea);
            }
        }
    }

    private void saveThumbnail(BuildingSearchRespone buildingDTO, BuildingEntity buildingEntity) {
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

    /**
     * xóa bảng FK trước rồi mới xóa bảng building
     * Sử dụng cascade nên khi xóa building thì sẽ tự động xóa các bản reference đến building.
     * riêng @ManyToMany không cần cascade để xóa bảng trung gian vì đã được  sẵn .
     *
     * @param idList list id building được tick vào.
     */
    @Override
    @Transactional
    public void delete(long[] idList) {
        if (idList.length > 0) {
            Long count = buildingRepository.countByIdIn(idList);
            if (count != idList.length) {
                throw new NotFoundException("Không tìm thấy tòa nhà hợp lệ!");
            }
            buildingRepository.deleteByIdIn(idList);
        }
    }

    /**
     * Load trang insert/update building .
     *
     * @param id : building id
     * @return thông tin của tòa nhà , nếu không có thì sẽ là null .
     */
    @Override
    public BuildingSearchRespone findById(Long id) {
        BuildingSearchRespone result = new BuildingSearchRespone();
        if (id != null) {
            result = buildingConverter.convertToDTO(buildingRepository.findOneById(id));
        }
        return result;
    }

}

