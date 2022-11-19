package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.DistrictRepository;
import com.laptrinhjavaweb.utils.GetConnectionUtils;

@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
	@Override
	public String getDistrictNameById(Long id) {
		try {
			Connection conn = GetConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from district where id = " + id);
			while (rs.next()) {
				return rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
