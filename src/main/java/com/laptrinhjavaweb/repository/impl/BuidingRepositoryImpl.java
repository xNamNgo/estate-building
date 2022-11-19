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

@Repository
public class BuidingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findBuilding(Map<String, String> params) {
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

				buildingQueryWithJoin(params, joinQuery, whereQuery);
				buildingQueryWithoutJoin(params, whereQuery);

				sql.append(joinQuery).append(BuildingConstant.WHERE_ONE_EQUAL_ONE).append(whereQuery)
						.append(BuildingConstant.GROUP_BY_BUILDING_ID);
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

	private void buildingQueryWithoutJoin(Map<String, String> params, StringBuilder whereQuery) {
		String name = (String) params.get("name");
//		Long floorArea = Long.parseLong(params.get("floorarea"));
		String floorAreaClient = params.get("floorarea");
		String districtIdClient = params.get("districtid");
		String ward = params.get("ward");
		String street = params.get("street");
		String numberOfBasementClient = params.get("numberofbasement");
		String direction = params.get("direction");
		String level = params.get("level");
		String fromRentPriceClient = params.get("fromrentprice");
		String toRentPriceClient = params.get("torentprice");

		if (name != null) {
			whereQuery.append(" and b.name like '%" + name + "%'");
		}
		if (floorAreaClient != null) {
			Long floorArea = Long.parseLong(params.get("floorarea"));
			whereQuery.append(" and floorarea = " + floorArea);
		}
		if (districtIdClient != null) {
			Long districtId = Long.parseLong(params.get("districtid"));
			whereQuery.append(" and districtid = " + districtId);
		}
		if (ward != null) {
			whereQuery.append(" and ward like '%" + ward + "%'");
		}
		if (street != null) {
			whereQuery.append(" and street like '%" + street + "%'");
		}
		if (numberOfBasementClient != null) {
			Long numberOfBasement = Long.parseLong(params.get("numberofbasement"));
			whereQuery.append(" and numberofbasement = " + numberOfBasement);
		}
		if (direction != null) {
			whereQuery.append(" and direction like '%" + direction + "%'");
		}
		if (level != null) {
			whereQuery.append(" and level like '%" + level + "%'");
		}
		if (fromRentPriceClient != null) {
			Long fromRentPrice = Long.parseLong(params.get("fromrentprice"));
			whereQuery.append(" and rentprice >= " + fromRentPrice);
		}
		if (toRentPriceClient != null) {
			Long toRentPrice = Long.parseLong(params.get("torentprice"));
			whereQuery.append(" and rentprice <= " + toRentPrice);
		}
	}

	private void buildingQueryWithJoin(Map<String, String> params, StringBuilder joinQuery, StringBuilder whereQuery) {
		String strRentType = params.get("renttype");
		String[] rentTypes = null;
		String staffName = params.get("staffname");
		String phone = params.get("phone");
		String fromRentAreaClient = params.get("fromrentarea");
		String toRentAreaClient = params.get("torentarea");

		if (strRentType != null) {
			rentTypes = strRentType.split(",");
		}

		if (fromRentAreaClient != null || toRentAreaClient != null) {
			joinQuery.append(" join rentarea on rentarea.buildingid = b.id");
			if (fromRentAreaClient != null) {
				Long fromRentArea = Long.parseLong(params.get("fromrentarea"));
				whereQuery.append(" and rentarea.value >= " + fromRentArea);
			}
			if (toRentAreaClient != null) {
				Long toRentArea = Long.parseLong(params.get("torentarea"));
				whereQuery.append(" and rentarea.value <= " + toRentArea);
			}
		}

		if (rentTypes != null) {
			joinQuery.append(" join buildingrenttype bRenttype on bRenttype.buildingid = b.id")
					.append(" join renttype on bRenttype.renttypeid = renttype.id");
			whereQuery.append(" and (renttype.code = '" + rentTypes[0] + "'");
			for (int i = 1; i < rentTypes.length; i++) {
				whereQuery.append(" or renttype.code = '" + rentTypes[i] + "'");
			}
			whereQuery.append(")");
		}

		if (staffName != null || phone != null) {
			joinQuery.append(" join assignmentbuilding aBuilding on aBuilding.buildingid = b.id")
					.append(" join user on user.id = aBuilding.staffid")
					.append(" join user_role on user_role.userid = user.id")
					.append(" join role on role.id = user_role.roleid");
			if (staffName != null) {
				whereQuery.append(" and user.fullname like '%" + staffName + "%'").append(" and role.code = 'staff'");
			}
			if (phone != null) {
				whereQuery.append(" and user.phone = " + phone);
			}
		}

	}

	// lấy tên loại tòa nhà
	public String getBuildingRenttype(Long buildingId) {
		List<String> result = new ArrayList<>();
		try {
			Connection conn = GetConnectionUtils.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "select * from buildingrenttype as bRenttype "
					+ "join renttype on bRenttype.renttypeid = renttype.id " + "where buildingid = " + buildingId;
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
