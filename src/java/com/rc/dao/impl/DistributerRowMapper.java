package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Distributer;

public class DistributerRowMapper implements RowMapper<Distributer> {

	@Override
	public Distributer mapRow(ResultSet rs, int arg1) throws SQLException {
		Distributer distributer = new Distributer();
		distributer.setDistributerName(rs.getString("distributor_name"));
		distributer.setDistributerId(rs.getInt("distributor_id"));
		distributer.setMobile(rs.getString("mobile_no"));
		distributer.setEmailId(rs.getString("email_id"));
		distributer.setStateId(rs.getInt("stateId"));
		distributer.setAddress(rs.getString("address"));
		distributer.setCityName(rs.getString("city_name"));
		distributer.setImagelogo(rs.getString("img_logo"));
		
		
		return distributer;
		
	}

}
