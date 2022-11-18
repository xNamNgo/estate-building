package com.laptrinhjavaweb.repository;

import java.util.List;

public interface BuildingRenttypeRepository {
	List<Long> getRenttypeId(Long buildingId);
}
