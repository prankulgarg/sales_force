package com.rc.dao.impl;

import com.rc.model.VisitHistorys;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


public class VisitHistoryRowMapper implements RowMapper<VisitHistorys> {

	@Override
	public VisitHistorys mapRow(ResultSet rs, int arg1) throws SQLException {
		VisitHistorys visitHistory = new VisitHistorys();
		visitHistory.setVisitId(rs.getInt("visit_id"));
		visitHistory.setVisitorid(rs.getInt("visitor_id"));
		visitHistory.setRetailerId(rs.getInt("retailer_id"));
		visitHistory.setActivityType(rs.getString("activity_type"));
		visitHistory.setActivityId(rs.getInt("activity_id"));
		visitHistory.setVisitDate(rs.getString("date"));
		
		return visitHistory;
	}

}
