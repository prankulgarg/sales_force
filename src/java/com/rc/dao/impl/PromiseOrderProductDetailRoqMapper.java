package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rc.model.PromiseOrderProductDetail;

public class PromiseOrderProductDetailRoqMapper implements ResultSetExtractor<PromiseOrderProductDetail>  {

	@Override
	public PromiseOrderProductDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
		PromiseOrderProductDetail promiseOrderProductDetail = new PromiseOrderProductDetail();
		while (rs.next()){
			promiseOrderProductDetail.setProductName(rs.getString(1));
			promiseOrderProductDetail.setBrandName(rs.getString(2));
			promiseOrderProductDetail.setQtyInMlId(rs.getInt(3));
			promiseOrderProductDetail.setProductCategoryId(rs.getInt(4));
			promiseOrderProductDetail.setProductTypeid(rs.getInt(5));
			promiseOrderProductDetail.setProductSubTypeId(rs.getInt(6));
			promiseOrderProductDetail.setPackageTypeId(rs.getInt(7));
			
			
		}
		
		
		
		// TODO Auto-generated method stub
		return promiseOrderProductDetail;
	}

}
