package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class getNameByAssetRawid implements ResultSetExtractor<String[]>{

	@Override
	public String[] extractData(ResultSet rs) throws SQLException, DataAccessException {
	 String name[] = new String[6]; 
         while(rs.next()){
	 name[1] = rs.getString(1);
	 name[2] = rs.getString(2);
	 name[3] = rs.getString(3);
	 name [4] = rs.getString(4);
	 name [5] = rs.getString(6);
         }
		return name;
	}

}
