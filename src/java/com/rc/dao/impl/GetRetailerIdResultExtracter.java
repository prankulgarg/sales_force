package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GetRetailerIdResultExtracter implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet rs, int arg1) throws SQLException {
		
		int i = rs.getInt(1);
		
		
		return i;
	}

}
