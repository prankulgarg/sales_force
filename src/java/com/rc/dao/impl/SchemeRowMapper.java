package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Scheme;

public class SchemeRowMapper implements RowMapper<Scheme> {

	@Override
	public Scheme mapRow(ResultSet rs, int arg1) throws SQLException {
		Scheme Scheme = new Scheme();
		Scheme.setSchemeAssignId(rs.getInt("id"));
		Scheme.setId(rs.getInt("scheme_id"));
		Scheme.setRetailerId(rs.getInt("retailer_id"));
		Scheme.setAssignDate(rs.getString("assign_date"));
		
		
		
		return Scheme;
	}

}
