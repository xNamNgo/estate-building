package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.respone.BuildingRequestDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.impl.BuildingService;
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

    public BuildingRequestDTO convertToDTO(BuildingEntity entity) {
        BuildingRequestDTO result = modelMapper.map(entity, BuildingRequestDTO.class);

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
    public BuildingSearchBuilder convertToBuildingSearchBuilder(BuildingSearchDTO buildingSearchDTO) {
        BuildingSearchBuilder result = new BuildingSearchBuilder.Builder().setName(buildingSearchDTO.getName()) // tên tòa nhà
                                                                          .setFloorArea(buildingSearchDTO.getFloorArea()) // diện tích sàn
                                                                          .setDistrict(buildingSearchDTO.getDistrict()) // quận
                                                                          .setWard(buildingSearchDTO.getWard()) // phường
                                                                          .setStreet(buildingSearchDTO.getStreet()) // đường
                                                                          .setNumberOfBasement(buildingSearchDTO.getNumberOfBasement()) // số tầng hầm
                                                                          .setDirection(buildingSearchDTO.getDirection()) // hướng
                                                                          .setLevel(buildingSearchDTO.getLevel()) // hạng
                                                                          .setRentAreaFrom(buildingSearchDTO.getRentAreaFrom()) // diện tích từ
                                                                          .setRentAreaTo(buildingSearchDTO.getRentAreaTo()) // diện tích đến
                                                                          .setCostRentFrom(buildingSearchDTO.getCostRentFrom()) // giá thuê từ
                                                                          .setCostRentTo(buildingSearchDTO.getCostRentTo()) // giá thuê đến
                                                                          .setManagerName(buildingSearchDTO.getManagerName()) // tên quản lý
                                                                          .setPhoneNumber(buildingSearchDTO.getPhoneNumber()) // số điện thoại quản lý
                                                                          .setStaffId(buildingSearchDTO.getStaffId()) // nhân viên phụ trách
                                                                          .setTypes(buildingSearchDTO.getTypes()) // loại tòa nhà [TANG_TRET,NGUYEN_CAN,NOI_THAT]
                                                                          .build();
        return result;
    }

    public BuildingEntity covertToBuildingEntity(BuildingRequestDTO buildingRequestDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingRequestDTO, BuildingEntity.class);

        String type = String.join(",", buildingRequestDTO.getTypes());
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
