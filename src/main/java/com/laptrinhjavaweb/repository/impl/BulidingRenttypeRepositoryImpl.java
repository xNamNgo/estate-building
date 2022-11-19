package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.repository.BuildingRenttypeRepository;
import com.laptrinhjavaweb.utils.GetConnectionUtils;

@Repository
public class BulidingRenttypeRepositoryImpl implements BuildingRenttypeRepository {

	@Override
	public List<Long> getRenttypeId(Long buildingId) {
		List<Long> result = new ArrayList<>();
		try {
			Connection conn = GetConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from buildingrenttype where buildingid = " + buildingId);
			while(rs.next()) {
				result.add(rs.getLong("renttypeid"));
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
