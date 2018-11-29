package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.VisitHistorys;

public class ActivityRowMapper implements RowMapper<VisitHistorys> {

	@Override
	public VisitHistorys mapRow(ResultSet rs, int arg1) throws SQLException {
		VisitHistorys visitHistorys = new VisitHistorys();
		visitHistorys.setId(rs.getInt("id"));
		visitHistorys.setVisitId(rs.getInt("visit_id"));
		visitHistorys.setVisitorid(rs.getInt("visitor_id"));
		visitHistorys.setRetailerId(rs.getInt("retailer_id"));
		visitHistorys.setActivityType(rs.getString("activity_type"));
		visitHistorys.setActivityId(rs.getInt("activity_id"));
		visitHistorys.setVisitDate(rs.getString("date"));
		return visitHistorys;
	}

}
