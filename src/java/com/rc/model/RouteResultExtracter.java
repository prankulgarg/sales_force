package com.rc.model;

import java.sql.ResultSet;
import java.sql.SQLException;



import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class RouteResultExtracter implements RowMapper<RouteRawDataResponse> {

	
	@Override
	public RouteRawDataResponse mapRow(ResultSet rs, int arg1) throws SQLException {
		System.out.println("inside route result extracter");
		RouteRawDataResponse route = new RouteRawDataResponse();
		route.setRouteId(rs.getInt("route_id"));
		route.setRouteName(rs.getString("route_name"));
		route.setDistributerid(rs.getInt("distributor_id"));
		route.setSrId(rs.getInt("sr_id"));
		return route;
	}

}
