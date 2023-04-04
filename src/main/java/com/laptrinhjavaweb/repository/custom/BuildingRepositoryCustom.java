package com.laptrinhjavaweb.repository.custom;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface BuildingRepositoryCustom {
    //đặt tên theo rule: XRepositoryCustom
    List<BuildingEntity> findByCondition(Pageable page,BuildingSearchBuilder builder);
    int countTotalItem();
}

