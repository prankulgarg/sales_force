package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.PromiseOrder;

public class PromiseOrderRowMapper implements RowMapper<PromiseOrder> {

	@Override
	public PromiseOrder mapRow(ResultSet rs, int arg1) throws SQLException {
		PromiseOrder promiseOrder = new PromiseOrder();
		promiseOrder.setId(rs.getInt("order_id"));
		promiseOrder.setRetailerId(rs.getInt("retailer_id"));
		promiseOrder.setVisitorId(rs.getInt("visitor_id"));
		promiseOrder.setRemarks(rs.getString("order_remarks"));
		promiseOrder.setPromiseDate(rs.getString("promise_date"));
		promiseOrder.setTakenDate(rs.getString("taken_date"));
		promiseOrder.setLatitude(rs.getString("lattitude"));
		promiseOrder.setLongitude(rs.getString("longitude"));
		promiseOrder.setVisitId(rs.getInt("visit_id"));
		
		
		
		
		return promiseOrder;
	}

}
