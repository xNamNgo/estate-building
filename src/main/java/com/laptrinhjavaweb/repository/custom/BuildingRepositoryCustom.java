package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface BuildingRepositoryCustom {
    //đặt tên theo rule: XRepositoryCustom
    List<BuildingEntity> findByCondition(BuildingSearchBuilder builder);
}

