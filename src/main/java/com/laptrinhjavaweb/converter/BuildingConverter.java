package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingSearchRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchRespone;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.utils.GetDistrictUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchRespone convertToDTO(BuildingEntity entity) {
        BuildingSearchRespone result = modelMapper.map(entity, BuildingSearchRespone.class);

        // output "Địa chỉ"
        Map<String, String> districts = GetDistrictUtils.getDistrictMap();
        String street = entity.getStreet();
        String district = districts.get(entity.getDistrict());
        String ward = entity.getWard();
        result.setAddress(street + ", " + ward + ", " + district);

        // output Diện tích trống
        List<String> rentAreas = new ArrayList<>();
        for (RentAreaEntity item : entity.getRentAreas()) {
            rentAreas.add(item.getValue().toString());
        }
        result.setRentArea(String.join(",", rentAreas));


        return result;
    }

    // builder pattern
    public BuildingSearchBuilder convertToBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder().setName(buildingSearchRequest.getName())
                                                                          .setFloorArea(buildingSearchRequest.getFloorArea())
                                                                          .setDistrict(buildingSearchRequest.getDistrict())
                                                                          .setWard(buildingSearchRequest.getWard())
                                                                          .setStreet(buildingSearchRequest.getStreet())
                                                                          .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
                                                                          .setDirection(buildingSearchRequest.getDirection())
                                                                          .setLevel(buildingSearchRequest.getLevel())
                                                                          .setRentAreaFrom(buildingSearchRequest.getRentAreaFrom())
                                                                          .setRentAreaTo(buildingSearchRequest.getRentAreaTo())
                                                                          .setCostRentFrom(buildingSearchRequest.getCostRentFrom())
                                                                          .setCostRentTo(buildingSearchRequest.getCostRentTo())
                                                                          .setManagerName(buildingSearchRequest.getManagerName())
                                                                          .setPhoneNumber(buildingSearchRequest.getPhoneNumber())
                                                                          .setStaffId(buildingSearchRequest.getStaffId())
                                                                          .setTypes(buildingSearchRequest.getTypes()) // loại tòa nhà [TANG_TRET,NGUYEN_CAN,NOI_THAT]
                                                                          .build();
        return result;
    }

    public BuildingEntity covertToBuildingEntity(BuildingSearchRespone buildingSearchRespone) {
        BuildingEntity buildingEntity = modelMapper.map(buildingSearchRespone, BuildingEntity.class);

        String type = String.join(",", buildingSearchRespone.getTypes());
        buildingEntity.setType(type);


        return buildingEntity;
    }

    public RentAreaEntity covertToRentAreaEntity(BuildingEntity buildingEntity,String strRentArea){
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        rentAreaEntity.setBuilding(buildingEntity);
        rentAreaEntity.setValue(Integer.parseInt(strRentArea));
        return rentAreaEntity;
    }

}
