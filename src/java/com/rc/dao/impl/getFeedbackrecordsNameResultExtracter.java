package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rc.model.Feedback;

public class getFeedbackrecordsNameResultExtracter implements ResultSetExtractor<Feedback> {

	@Override
	public Feedback extractData(ResultSet rs) throws SQLException, DataAccessException {
	Feedback feedback = new Feedback();
	while(rs.next())
	{	
		feedback.setFeedbackratingName(rs.getString(1));
		feedback.setFeedbackTypeName(rs.getString(2));
		feedback.setVisitorName(rs.getString(3));
		feedback.setRoleId(rs.getInt(4));
		feedback.setRetailerName(rs.getString(5));
		feedback.setRetailerLattitude(rs.getString(6));
		feedback.setRetailerLongitude(rs.getString(7));
		feedback.setSrId(rs.getInt(8));
	}
	
	
		return feedback;
	}

}
