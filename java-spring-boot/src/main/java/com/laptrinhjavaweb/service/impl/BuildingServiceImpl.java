package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.model.respone.BuildingSearchRespone;
import com.laptrinhjavaweb.repository.BuildingRenttypeRepository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	BuildingRepository buildingRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	RentAreaRepository rentArearepository;

	@Autowired
	BuildingRenttypeRepository buildingRenttypeRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<BuildingSearchRespone> findBuilding(Map<String, String> params) {
		List<BuildingSearchRespone> results = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findBuilding(params);
		for (BuildingEntity entity : buildingEntities) {
			BuildingSearchRespone dto = new BuildingSearchRespone();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setFloorArea(entity.getFloorArea());
			dto.setNumberOfBasement(entity.getNumberOfBasement());
			dto.setDirection(entity.getDirection());
			dto.setLevel(entity.getLevel());
			dto.setRentPrice(entity.getRentPrice());

			// address
			String address = getDistrictNameById(entity.getDistrictId()) + " - " + entity.getWard() + " - "
					+ entity.getStreet();
			dto.setAddress(address);

			// rentarea
			List<Integer> rentAreaInt = rentArearepository.getRentAreaById(entity.getId());
			;
			List<String> rentAreaStr = new ArrayList<>();
			if (rentAreaInt.size() > 0) {
				for (Integer value : rentAreaInt) {
					rentAreaStr.add(value.toString());
				}
				String rentArea = String.join(",", rentAreaStr); // result
				dto.setRentArea(rentArea);
			}

			// building renttype
			String buildingRenttype = buildingRepository.getBuildingRenttype(entity.getId());
			dto.setBuildingRenttype(buildingRenttype);

			// staff name
			List<UserEntity> userEntities = userRepository.getStaffInfoByBuildingId(entity.getId());
			if (userEntities.size() > 0 || userEntities != null) {
				for (UserEntity userEntity : userEntities) {
					dto.setStaffName(userEntity.getFullName());
					dto.setStaffPhoneNumber(userEntity.getPhone( ));
				}
			}

			// final
			results.add(dto);
		}
		return results;
	}

	private String getDistrictNameById(Long districtId) {
		String districtValue = districtRepository.getDistrictNameById(districtId);
		return districtValue;
	}

}
