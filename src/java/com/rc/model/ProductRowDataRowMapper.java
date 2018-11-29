package com.rc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductRowDataRowMapper implements RowMapper<ProductRawdata> {

	@Override
	public ProductRawdata mapRow(ResultSet rs, int arg1) throws SQLException {
		ProductRawdata productRawdata = new ProductRawdata();
		
		productRawdata.setId(rs.getInt("id"));
		productRawdata.setName(rs.getString("name"));
		productRawdata.setDescription(rs.getString("description"));
		
		return productRawdata;
	}

}
