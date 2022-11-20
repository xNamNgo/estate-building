package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.model.respone.BuildingSearchRespone;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public class BuildingConverter {
	public static BuildingSearchRespone convertToDTO(BuildingEntity entity) {
		BuildingSearchRespone dto = new BuildingSearchRespone();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setFloorArea(entity.getFloorArea());
		dto.setNumberOfBasement(entity.getNumberOfBasement());
		dto.setDirection(entity.getDirection());
		dto.setLevel(entity.getLevel());
		dto.setRentPrice(entity.getRentPrice());
		return dto;
		
	}

}
