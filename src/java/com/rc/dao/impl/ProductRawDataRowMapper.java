package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rc.model.Product;

public class ProductRawDataRowMapper implements ResultSetExtractor<Product> {

	@Override
	public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
		Product lstName =  new Product();
		while (rs.next()){
			lstName.setBrandName(rs.getString(1));
			lstName.setBrandCode(rs.getString(2));
			lstName.setInternalBrandCode(rs.getString(3));
			lstName.setPackagetype(rs.getString(4));
			lstName.setProductcategoryName(rs.getString(5));
			lstName.setProductSegmentName(rs.getString(6));
			lstName.setProductSubTypeName(rs.getString(7));
			lstName.setProductTypeName(rs.getString(8));
			lstName.setQtyInmlName(rs.getString(9));
			lstName.setQtyInpcsValue(rs.getInt(10));
			
			
		}
		
		
		
		return lstName;
	}

}
