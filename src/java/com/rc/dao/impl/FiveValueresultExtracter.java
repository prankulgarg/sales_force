package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class FiveValueresultExtracter implements  ResultSetExtractor<String[]> {

	@Override
	public String[] extractData(ResultSet rs) throws SQLException, DataAccessException {
		String[] nameById  = new String[5];
		while (rs.next()){
		nameById[0] = rs.getString(1);
		nameById[1]= rs.getString(2);
		nameById[2]= rs.getString(3);
		nameById[3]= rs.getString(4);
		nameById[4]= rs.getString(5);
		}
		
		return nameById;
	}

}
