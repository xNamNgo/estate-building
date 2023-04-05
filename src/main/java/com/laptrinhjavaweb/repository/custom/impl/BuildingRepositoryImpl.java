package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.BuildingConstant;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findByCondition(Pageable page, BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder(buildQueryFilter(builder))
                .append(" LIMIT ").append(page.getPageSize())
                .append(" OFFSET ").append(page.getOffset());
        System.out.println("SQL query : " + sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(BuildingSearchBuilder builder) {
        String sql = buildQueryFilter(builder);
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter(BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder(BuildingConstant.SELECT_FROM_BULIDING);
        sql = buildSqlJoining(builder, sql);
        sql.append(" " + BuildingConstant.WHERE_ONE_EQUAL_ONE + " ");
        sql = buildSqlCommon(builder, sql);
        sql = buildSqlSpecial(builder, sql);
        sql.append(BuildingConstant.GROUP_BY_BUILDING_ID);
        return sql.toString();
    }

    private StringBuilder buildSqlJoining(BuildingSearchBuilder builder, StringBuilder sql) {
        Long rentAreaFrom = builder.getRentAreaFrom();
        Long rentAreaTo = builder.getRentAreaTo();
        Integer staffId = builder.getStaffId();
        if (rentAreaFrom != null || rentAreaTo != null) {
            sql.append(" join rent_area as ra on ra.building_id = b.id");
        }
        if (staffId != null) {
            sql.append(" join assignment_building as ab on ab.building_id = b.id")
               .append(" join user on user.id = ab.staff_id");
        }
        return sql;
    }

    private StringBuilder buildSqlCommon(BuildingSearchBuilder builder, StringBuilder sql) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

                // các case có dùng các toán tử, câu lệnh đặt biệt thì xử lí riêng.
                if (!fieldName.equals("types") && !fieldName.startsWith("costRent")
                        && !fieldName.startsWith("rentArea") && !fieldName.equals("staffId")) {
                    Object value = field.get(builder);
                    if (value != null && !value.equals("")) {
                        if (field.getType().getName().equals("java.lang.String")) {
                            sql.append(" and b." + fieldName + " like '%" + value + "%'");
                        } else if (field.getType().getName().equals("java.lang.Integer")) {
                            sql.append(" and b." + fieldName + " = " + value);
                        } else if (field.getType().getName().equals("java.lang.Long")) {
                            sql.append(" and b." + fieldName + " = " + value);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return sql;
    }

    private StringBuilder buildSqlSpecial(BuildingSearchBuilder builder, StringBuilder sql) {
        Integer staffId = builder.getStaffId();
        Long rentAreaFrom = builder.getRentAreaFrom();
        Long rentAreaTo = builder.getRentAreaTo();
        Long costRentFrom = builder.getCostRentFrom();
        Long costRentTo = builder.getCostRentTo();
        List<String> types = builder.getTypes();
        if (staffId != null) {
            sql.append(" and user.id = " + staffId);
        }
        if (rentAreaFrom != null) {
            sql.append(" and ra.value >= " + rentAreaFrom);
        }
        if (rentAreaTo != null) {
            sql.append(" and ra.value <= " + rentAreaTo);
        }

        if (costRentFrom != null) {
            sql.append(" and b.rent_price >= " + costRentFrom);
        }
        if (costRentTo != null) {
            sql.append(" and b.rent_price <= " + costRentTo);
        }
        if (types != null) {
            sql.append(" and (");
            String sqlJoin = types.stream().map(item -> "b.type like '%" + item + "%'").collect(Collectors.joining(" or "));
            sql.append(sqlJoin);
            sql.append(" )");
        }
        return sql;
    }


}

