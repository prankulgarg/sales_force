package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.ExciseOrder;

public class ExciseOrderResultExtracter implements RowMapper<ExciseOrder> {

	@Override
	public ExciseOrder mapRow(ResultSet rs, int arg1) throws SQLException {
		ExciseOrder exciseOrder = new ExciseOrder();
		exciseOrder.setId(rs.getInt("id"));
		exciseOrder.setExciseOrderId(rs.getInt("excise_order_no"));
		exciseOrder.setRetailerId(rs.getInt("retailer_id"));
		//exciseOrder.setProductId(rs.getInt("product_id"));
		//exciseOrder.setLicenseId(rs.getInt("liscense_id"));
		//exciseOrder.setTpNo(rs.getString("TP_no"));
		//exciseOrder.setPoNo(rs.getString("PO_no"));
		//exciseOrder.setCasesPcsInt(rs.getInt("cases_pcs"));
		//exciseOrder.setExciseDuty(rs.getString("excise_duty"));
		//exciseOrder.setAmount(rs.getDouble("amount"));
		//exciseOrder.setNetPaybleAmount(rs.getDouble("net_payable_amount"));
		exciseOrder.setIssueDate(rs.getString("issue_date"));
		exciseOrder.setDispatchDate(rs.getString("dispatch_date"));
		exciseOrder.setReceiveDate(rs.getString("recieved_date"));
		exciseOrder.setOrderCreateDate(rs.getString("order_created_date"));
		exciseOrder.setSrId(rs.getInt("sr_id"));
		exciseOrder.setTsmId(rs.getInt("tsm_id"));
		exciseOrder.setAsmId(rs.getInt("asm_id"));
		exciseOrder.setManagerId(rs.getInt("manager_id"));
		exciseOrder.setDistributerId(rs.getInt("distributer_id"));
		exciseOrder.setCreatedByid(rs.getInt("created_by_id"));
	//	exciseOrder.setBrandId(rs.getInt("brand_id"));
		return exciseOrder;
	}
	

}
