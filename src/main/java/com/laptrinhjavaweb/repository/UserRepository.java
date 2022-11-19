package com.laptrinhjavaweb.repository;

import java.util.List;

import com.laptrinhjavaweb.repository.entity.UserEntity;

public interface UserRepository {
	public List<UserEntity> getStaffInfoByBuildingId(Long buildingId);
}
