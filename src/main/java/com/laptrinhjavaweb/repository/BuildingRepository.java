package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.entity.BuildingEntity;

public interface BuildingRepository {
	public List<BuildingEntity> findBuilding(Map<String,String> params,List<String> renttypes);
	public String getBuildingRenttype(Long buildingId);

}
