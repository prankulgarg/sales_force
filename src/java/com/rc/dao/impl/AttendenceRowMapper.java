package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Attendence;

public class AttendenceRowMapper implements RowMapper<Attendence> {

	@Override
	public Attendence mapRow(ResultSet rs, int arg1) throws SQLException {
		Attendence attendence = new Attendence();
	
		
		//attendence.setId(rs.getInt("id"));
		attendence.setInTime(rs.getString("intime"));
		attendence.setOutTime(rs.getString("outtime"));
		attendence.setVisitorId(rs.getInt("visitor_id"));
		//attendence.setVisitId(rs.getInt("visit_id"));
		attendence.setInRetailerId(rs.getInt("inretailerid"));
		attendence.setOutRetailerId(rs.getInt("outretailerid"));
	
		return attendence;
	}

}
