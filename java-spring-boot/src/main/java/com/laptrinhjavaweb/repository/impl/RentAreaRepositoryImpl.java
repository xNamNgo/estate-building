package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.RentAreaRepository;
import com.laptrinhjavaweb.utils.GetConnectionUtils;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
	@Override
	public List<Integer> getRentAreaById(Long bulidingId) {
		List<Integer> results = new ArrayList<>();
		try {
			Connection conn = GetConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from rentarea where buildingid = " + bulidingId);
			while (rs.next()) {
				results.add(rs.getInt("value"));
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
