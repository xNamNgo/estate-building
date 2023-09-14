package com.laptrinhjavaweb.repository.custom.impl;

import com.laptrinhjavaweb.builder.CustomerBuilder;
import com.laptrinhjavaweb.constant.CustomerConstant;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<CustomerEntity> findByCondition(Pageable page, CustomerBuilder builder) {
        StringBuilder sql = new StringBuilder(buildQueryFilter(builder))
                .append(" LIMIT ")
                .append(page.getPageSize())
                .append(" OFFSET ")
                .append(page.getOffset());
        System.out.println("SQL query : " + sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }

    @Override
    public int countTotalItem(CustomerBuilder builder) {
        String sql = buildQueryFilter(builder);
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList().size();
    }

    private String buildQueryFilter(CustomerBuilder builder) {
        StringBuilder sql = new StringBuilder(CustomerConstant.SELECT_FROM_CUSTOMER);
        sql = buildSqlJoining(builder, sql);
        sql.append(" " + CustomerConstant.WHERE_ONE_EQUAL_ONE + " ");
        sql = buildSqlCommon(builder, sql);
        sql = buildSqlSpecial(builder, sql);
        return sql.toString();
    }

    private StringBuilder buildSqlJoining(CustomerBuilder builder, StringBuilder sql) {
        Long staffId = builder.getStaff_id();

        if (staffId != null) {
            sql.append(" join assignment_customer as acustomer on acustomer.customer_id = c.id")
                    .append(" join user as u on u.id = acustomer.staff_id");
        }
        return sql;
    }

    private StringBuilder buildSqlCommon(CustomerBuilder builder, StringBuilder sql) {
        try {
            Field[] fields = CustomerBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();

                // các case có dùng các toán tử, câu lệnh đặt biệt thì xử lí riêng.
                if (!fieldName.equals("staff_id")) {
                    Object value = field.get(builder);
                    if (value != null && !value.equals("")) {
                        if (field.getType().getName().equals("java.lang.String")) {
                            sql.append(" and c." + fieldName + " like '%" + value + "%'");
                        } else if (field.getType().getName().equals("java.lang.Integer")) {
                            sql.append(" and c." + fieldName + " = " + value);
                        } else if (field.getType().getName().equals("java.lang.Long")) {
                            sql.append(" and c." + fieldName + " = " + value);
                        }
                    }
                }

            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return sql;
    }

    private StringBuilder buildSqlSpecial(CustomerBuilder builder, StringBuilder sql) {
        Long staffId = builder.getStaff_id();
        if (staffId != null) {
            sql.append(" and u.id = " + staffId);
        }
        return sql;
    }

}
