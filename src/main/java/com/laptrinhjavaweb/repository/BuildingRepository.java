package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepository extends BuildingRepositoryCustom, JpaRepository<BuildingEntity, Long> {
    void deleteByIdIn(long[] idList);
    BuildingEntity findOneById(Long buildingId);
    Long countByIdIn(long[] idList);

}
