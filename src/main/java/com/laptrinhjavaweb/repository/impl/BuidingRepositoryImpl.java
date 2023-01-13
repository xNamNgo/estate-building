package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.constant.BuildingConstant;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.entity.BuildingEntity;
import com.laptrinhjavaweb.utils.GetConnectionUtils;
import com.laptrinhjavaweb.utils.MapUtils;
import com.laptrinhjavaweb.utils.NumberUtils;
import com.laptrinhjavaweb.utils.StringUtils;

@Repository
public class BuidingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> params,
			List<String> renttypes) {
		List<BuildingEntity> results = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = GetConnectionUtils.getConnection();
			stmt = conn.createStatement();
			StringBuilder sql = new StringBuilder(BuildingConstant.SELECT_FROM_BULIDING);

			if (!params.isEmpty()) {
				StringBuilder joinQuery = new StringBuilder();
				StringBuilder whereQuery = new StringBuilder();

				buildingQueryWithoutJoin(params, whereQuery);
				buildingQueryWithJoin(params, renttypes, joinQuery, whereQuery);

				sql.append(joinQuery).append(BuildingConstant.WHERE_ONE_EQUAL_ONE)
						.append(whereQuery).append(BuildingConstant.GROUP_BY_BUILDING_ID);
			}

			String sqlDebug = sql.toString();
			rs = stmt.executeQuery(sql.toString());
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setId(rs.getLong("id"));
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setDistrictId(rs.getLong("districtid"));
				buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
				buildingEntity.setFloorArea(rs.getInt("floorarea"));
				buildingEntity.setDirection(rs.getString("direction"));
				buildingEntity.setLevel(rs.getString("level"));
				buildingEntity.setRentPrice(rs.getLong("rentprice"));
				results.add(buildingEntity);
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void buildingQueryWithoutJoin(Map<String, Object> params,
			StringBuilder whereQuery) {
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if (!entry.getKey().equals("rentprice") && !entry.getKey().equals("district")
					&& !entry.getKey().equals("staffId")
					&& !entry.getKey().equals("phone")
					&& !entry.getKey().equals("fromrentarea")
					&& !entry.getKey().equals("torentarea")) {
				String value = entry.getValue().toString();
				if (NumberUtils.isInteger(value)) {
					whereQuery.append(
							" and b." + entry.getKey() + " = " + Integer.parseInt(value));
				}
				if (!StringUtils.isNullOrEmpty(value)) {
					whereQuery.append(
							" and b." + entry.getKey() + " like '%" + value + "%'");
				}
			}
		}

//		Trường hợp đặc biệt mình tự custom.
		Long fromRentPrice = MapUtils.getObject(params, "from-rent-price", Long.class);
		Long toRentPrice = MapUtils.getObject(params, "to-rent-price", Long.class);
		if (fromRentPrice != null) {
			whereQuery.append(" and rentprice >= " + fromRentPrice);
		}
		if (toRentPrice != null) {
			whereQuery.append(" and rentprice <= " + toRentPrice);
		}
	}

	private void buildingQueryWithJoin(Map<String, Object> params, List<String> renttypes,
			StringBuilder joinQuery, StringBuilder whereQuery) {
		String district = MapUtils.getObject(params, "district", String.class);
		Long staffId = MapUtils.getObject(params, "staffid", Long.class);
		String phone = MapUtils.getObject(params, "phone", String.class);
		Long fromRentArea = MapUtils.getObject(params, "fromrentarea", Long.class);
		Long toRentArea = MapUtils.getObject(params, "torentarea", Long.class);

		if (district != null) {
			joinQuery.append(" join district on district.id = b.districtid ");
			whereQuery.append(" and district.code = '" + district + "'");
		}

		if (fromRentArea != null || toRentArea != null) {
			joinQuery.append(" join rentarea on rentarea.buildingid = b.id");
			if (fromRentArea != null) {
//				Long fromRentArea = Long.parseLong(fromRentAreaClient);
				whereQuery.append(" and rentarea.value >= " + fromRentArea);
			}
			if (toRentArea != null) {
//				Long toRentArea = Long.parseLong(toRentAreaClient);
				whereQuery.append(" and rentarea.value <= " + toRentArea);
			}
		}

		if (renttypes != null) {
			joinQuery.append(
					" join buildingrenttype bRenttype on bRenttype.buildingid = b.id")
					.append(" join renttype on bRenttype.renttypeid = renttype.id");
//			whereQuery.append(" and (renttype.code = '" + renttypes.get(0) + "'");
//			for (int i = 1; i < renttypes.size(); i++) {
//				whereQuery.append(" or renttype.code = '" + renttypes.get(i) + "'");
//			}
//			whereQuery.append(")");

			List<String> split = new ArrayList<>();
			for (String item : renttypes) {
				split.add("'" + item + "'");
			}
			whereQuery.append(" and renttype.code in (" + String.join(",", split) + ")");

		}

		if (staffId != null || phone != null) {
//			Long staffId = Long.parseLong(staffIdClient);
			joinQuery.append(
					" join assignmentbuilding aBuilding on aBuilding.buildingid = b.id")
					.append(" join user on user.id = aBuilding.staffid");
			whereQuery.append(" and user.id = " + staffId);
		}
		if (phone != null) {
			whereQuery.append(" and user.phone = " + phone);
		}
	}

	// lấy tên loại tòa nhà
	public String getBuildingRenttype(Long buildingId) {
		List<String> result = new ArrayList<>();
		try {
			Connection conn = GetConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "select * from buildingrenttype as bRenttype "
					+ "join renttype on bRenttype.renttypeid = renttype.id "
					+ "where buildingid = " + buildingId;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				result.add(rs.getString("renttype.name"));
			}
			return String.join(",", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
