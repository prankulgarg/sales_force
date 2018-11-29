package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Servey;

public class ServeyRowMapper implements RowMapper<Servey> {

	@Override
	public Servey mapRow(ResultSet rs, int arg1) throws SQLException {
		Servey servey = new Servey();
		
		servey.setServeyId(rs.getInt("servey_id"));
		servey.setTitle(rs.getString("title"));
		servey.setDescription(rs.getString("description"));
		servey.setStartDate(rs.getString("start_date"));
		servey.setEndDate(rs.getString("end_date"));
		servey.setDistributerId(rs.getInt("distributer_id"));
		
		return servey;
	}

}
