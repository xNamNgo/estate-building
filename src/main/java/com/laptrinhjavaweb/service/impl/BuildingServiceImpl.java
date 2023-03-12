package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.respone.BuildingResponeDTO;
import com.laptrinhjavaweb.dto.respone.ResponeDTO;
import com.laptrinhjavaweb.dto.respone.StaffResponeDTO;
import com.laptrinhjavaweb.entity.AssignmentBuildingEntity;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.DistrictEnum;
import com.laptrinhjavaweb.enums.TypeEnum;
import com.laptrinhjavaweb.repository.AssignmentBuildingRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RentAreaRepository rentAreaRepository;

    @Autowired
    BuildingConverter buildingConverter;

    @Override
    public List<BuildingResponeDTO> loadBuilding(BuildingSearchDTO buildingSearchDTO) {
        List<BuildingResponeDTO> result = new ArrayList<>();
        BuildingSearchBuilder buildingSearchBuilder = buildingConverter.convertToBuildingSearchBuilder(buildingSearchDTO);
        List<BuildingEntity> buildingEntities = buildingRepository.findByCondition(buildingSearchBuilder);
        for (BuildingEntity item : buildingEntities) {
            BuildingResponeDTO buildingResponeDTO = buildingConverter.convertToDTO(item);
            result.add(buildingResponeDTO);
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


    @Override
    public ResponeDTO getListStaff(Long buildingId) {
        // danh sách nhân viên đang quản lý tòa nhà id .
        List<AssignmentBuildingEntity> assignmentBuildingEntityList = assignmentBuildingRepository.findByBuildingId(buildingId);

        // danh sách tất cả nhân viên
        List<UserEntity> userEntityList = userRepository.findByStatusAndRoles_Code(1, "STAFF");

        List<StaffResponeDTO> data = new ArrayList<>();

        for (UserEntity user : userEntityList) {
            StaffResponeDTO staffResponeDTO = new StaffResponeDTO();
            staffResponeDTO.setStaffId(user.getId());
            staffResponeDTO.setFullName(user.getFullName());

            // kiểm tra user đã được giao tòa nhà hay chưa
            boolean isAssigned = false;
            for (AssignmentBuildingEntity assignment : assignmentBuildingEntityList) {
                if (user.getId() == assignment.getStaff().getId()) {
                    isAssigned = true;
                    break;
                }
            }
            staffResponeDTO.setChecked(isAssigned ? "checked" : "");

            // thêm vào data list.
            data.add(staffResponeDTO);
        }
        return new ResponeDTO(data, "success", "");
    }

    @Override
    public void save(BuildingResponeDTO buildingResponeDTO) {
        BuildingEntity buildingEntity = buildingConverter.covertToBuildingEntity(buildingResponeDTO);
        buildingRepository.save(buildingEntity);

        rentAreaRepository.deleteByBuilding_Id(buildingEntity.getId());
        String strRentArea = buildingResponeDTO.getRentArea();
        if (!strRentArea.equals("")) {
            for (String item : strRentArea.split(",")) {
                RentAreaEntity rentAreaEntity = buildingConverter.covertToRentAreaEntity(buildingEntity, item);
                rentAreaRepository.save(rentAreaEntity);
            }
        }
    }

    @Override
    public void delete(List<Long> idList){
        // xóa bảng rent_area trước rồi mới xóa bảng building
        for(Long id : idList){
            rentAreaRepository.deleteByBuilding_Id(id);
            buildingRepository.deleteById(id);
        }
    }

    @Override
    public BuildingResponeDTO findById(Long id){
        BuildingResponeDTO result = new BuildingResponeDTO();
        if(id != null){
            result = buildingConverter.convertToDTO(buildingRepository.findById(id));
        }
        return result;
    }


}
