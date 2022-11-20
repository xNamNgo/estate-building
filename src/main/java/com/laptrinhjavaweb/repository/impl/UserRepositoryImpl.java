package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.repository.entity.UserEntity;
import com.laptrinhjavaweb.utils.GetConnectionUtils;

@Repository
public class UserRepositoryImpl implements UserRepository{
	@Override
	public List<UserEntity> getStaffInfoByBuildingId(Long buildingId) {

		List<UserEntity> listStaff = new ArrayList<>();

		Connection conn;
		try {
			conn = GetConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "select * from `assignmentbuilding` " + "join user on user.id = assignmentbuilding.staffid "
					+ "where buildingid = " + buildingId;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setFullName(rs.getString("user.fullname"));
				userEntity.setPhone(rs.getString("user.phone"));
				listStaff.add(userEntity);
			}
			return listStaff;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
