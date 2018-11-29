package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.ProductRawdata;

public class FeedbackDataRowMapper implements RowMapper<ProductRawdata> {

	@Override
	public ProductRawdata mapRow(ResultSet rs, int arg1) throws SQLException {
		ProductRawdata productRawdata = new ProductRawdata();
		productRawdata.setId(rs.getInt("id"));
		productRawdata.setName(rs.getString("title"));
		productRawdata.setDescription(rs.getString("description"));
		
		return productRawdata;
		
		
	}

}
