package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class GetEmpNameResultExtracter  implements ResultSetExtractor<String>{

	@Override
	public String extractData(ResultSet rs) throws SQLException, DataAccessException {
		if(rs.next()){
			return rs.getString(1);
		}
		else
		{
			return "null";
		}
		
	}

}
