package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Target;

public class TargetRowMapper implements RowMapper<Target> {

	@Override
	public Target mapRow(ResultSet rs, int arg1) throws SQLException {
		Target target = new Target();
		target.setAssignId(rs.getInt("target_id"));
		target.setTargetMonth(rs.getString("target_month"));
		target.setTargetYear(rs.getString("targer_year"));
		target.setBrandId(rs.getInt("brand_id"));
		target.setQty(rs.getInt("target_qty"));
		target.setCreateDate(rs.getString("create_date"));
		target.setCreatedById(rs.getInt("creted_by"));
		target.setAssignId(rs.getInt("assigned_id"));
		target.setRoleId(rs.getInt("role_id"));
		return target;
	}

}
