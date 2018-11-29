package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class IntegerValueExtracter implements ResultSetExtractor<Integer> {

	@Override
	public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
		Integer i = null;
while (rs.next()){
	 i = rs.getInt(1);
	
	
	
}
		
return i;
		
		
	}

}
