package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.PromiseOrderProductDetail;

public class PromiseOrderDetailRowmapper implements RowMapper<PromiseOrderProductDetail> {

	@Override
	public PromiseOrderProductDetail mapRow(ResultSet rs, int arg1) throws SQLException {
		PromiseOrderProductDetail promiseOrderProductDetail = new PromiseOrderProductDetail();
		promiseOrderProductDetail.setId(rs.getInt("id"));
		promiseOrderProductDetail.setOrderId(rs.getInt("order_id"));
		promiseOrderProductDetail.setProductId(rs.getInt("product_id"));
		promiseOrderProductDetail.setBrandId(rs.getInt("brand_id"));
		promiseOrderProductDetail.setQty(rs.getInt("qty"));
		
		
		return promiseOrderProductDetail;
	}

}
