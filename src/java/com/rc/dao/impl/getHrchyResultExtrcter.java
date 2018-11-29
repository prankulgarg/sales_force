package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class getHrchyResultExtrcter implements ResultSetExtractor<Integer[]>{

	@Override
	public Integer[] extractData(ResultSet rs) throws SQLException, DataAccessException {
		 Integer id[] = new Integer[3]; 
	while(rs.next()){
		 id[0] = rs.getInt("asm_id");
		 id[1]=rs.getInt("tsm_id");
		 id[2]=rs.getInt("manager_id");
		 
	
	}
	
		return id;
	}

	

}
