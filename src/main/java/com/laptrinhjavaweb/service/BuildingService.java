package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.model.respone.BuildingSearchRespone;

public interface BuildingService {
	public List<BuildingSearchRespone> findBuilding(Map<String,String> params,List<String> renttypes);
}
