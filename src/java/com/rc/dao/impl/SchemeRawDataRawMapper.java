package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rc.model.Scheme;

public class SchemeRawDataRawMapper implements ResultSetExtractor<Scheme> {

	@Override
	public Scheme extractData(ResultSet rs) throws SQLException, DataAccessException {
		Scheme Scheme = new Scheme();
		while (rs.next()){
			
		Scheme.setTitle(rs.getString("title"));
		Scheme.setDescription(rs.getString("description"));
		Scheme.setFromDate(rs.getString("from_date"));
		Scheme.setToDate(rs.getString("to_date"));
		Scheme.setBrandId(rs.getInt("brand_id"));
		Scheme.setProductId(rs.getInt("product_id"));
		Scheme.setCreateDate(rs.getString("create_date"));
		Scheme.setStatus(rs.getInt("status"));
		}
		return Scheme;
	}

}
