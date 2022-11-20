package com.laptrinhjavaweb.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.laptrinhjavaweb.model.respone.BuildingSearchRespone;
import com.laptrinhjavaweb.service.BuildingService;

@RestController
@RequestMapping("/api/building")
public class BuilingAPI {

	@Autowired
	BuildingService buildingService;

	@GetMapping
	public List<BuildingSearchRespone> findBuilding(@RequestParam Map<String, String> params,@RequestParam(required = false) List<String> renttypes) {
		List<BuildingSearchRespone> results = buildingService.findBuilding(params,renttypes);
		return results;
	}
}
