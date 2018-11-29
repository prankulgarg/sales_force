package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class TwoValueresultExtracter implements ResultSetExtractor<String[]> {

	@Override
	public String[] extractData(ResultSet rs) throws SQLException, DataAccessException {
			String[] name = new String[2];
			while(rs.next()){
			name[0] = rs.getString(1);
			name[1] = rs.getString(2);
			}
		return name;
	}

}
