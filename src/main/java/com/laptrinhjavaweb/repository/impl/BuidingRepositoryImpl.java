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

@Repository
public class BuidingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> params, List<String> renttypes) {
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

				buildingQueryWithJoin(params, renttypes, joinQuery, whereQuery);
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

	private void buildingQueryWithoutJoin(Map<String, Object> params, StringBuilder whereQuery) {
		String name = MapUtils.getObject(params, "name", String.class);
		Long floorArea = MapUtils.getObject(params, "floorarea", Long.class);
		String ward = MapUtils.getObject(params, "ward", String.class);
		String street = MapUtils.getObject(params, "street", String.class);
		Long numberOfBasement = MapUtils.getObject(params, "numberofbasement", Long.class);
		String direction = MapUtils.getObject(params, "direction", String.class);
		String level = MapUtils.getObject(params, "level", String.class);
		Long fromRentPrice = MapUtils.getObject(params, "fromrentprice", Long.class);
		Long toRentPrice = MapUtils.getObject(params, "torentprice", Long.class);

		if (name != null) {
			whereQuery.append(" and b.name like '%" + name + "%'");
		}
		if (floorArea != null) {
//			Long floorArea = Long.parseLong(params.get("floorarea"));
			whereQuery.append(" and floorarea = " + floorArea);
		}

		if (ward != null) {
			whereQuery.append(" and ward like '%" + ward + "%'");
		}
		if (street != null) {
			whereQuery.append(" and street like '%" + street + "%'");
		}
		if (numberOfBasement != null) {
//			Long numberOfBasement = Long.parseLong(params.get("numberofbasement"));
			whereQuery.append(" and numberofbasement = " + numberOfBasement);
		}
		if (direction != null) {
			whereQuery.append(" and direction like '%" + direction + "%'");
		}
		if (level != null) {
			whereQuery.append(" and level like '%" + level + "%'");
		}
		if (fromRentPrice != null) {
//			Long fromRentPrice = Long.parseLong(params.get("fromrentprice"));
			whereQuery.append(" and rentprice >= " + fromRentPrice);
		}
		if (toRentPrice != null) {
//			Long toRentPrice = Long.parseLong(params.get("torentprice"));
			whereQuery.append(" and rentprice <= " + toRentPrice);
		}
	}

	private void buildingQueryWithJoin(Map<String, Object> params, List<String> renttypes, StringBuilder joinQuery,
			StringBuilder whereQuery) {
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
			if (toRentArea!= null) {
//				Long toRentArea = Long.parseLong(toRentAreaClient);
				whereQuery.append(" and rentarea.value <= " + toRentArea);
			}
		}

		if (renttypes != null) {
			joinQuery.append(" join buildingrenttype bRenttype on bRenttype.buildingid = b.id")
					.append(" join renttype on bRenttype.renttypeid = renttype.id");
			whereQuery.append(" and (renttype.code = '" + renttypes.get(0) + "'");
			for (int i = 1; i < renttypes.size(); i++) {
				whereQuery.append(" or renttype.code = '" + renttypes.get(i) + "'");
			}
			whereQuery.append(")");
		}

		if (staffId != null || phone != null) {
//			Long staffId = Long.parseLong(staffIdClient);
			joinQuery.append(" join assignmentbuilding aBuilding on aBuilding.buildingid = b.id")
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
