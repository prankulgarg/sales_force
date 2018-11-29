package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Brand;

public class BrandRowMapper implements RowMapper<Brand>{

	@Override
	public Brand mapRow(ResultSet rs, int arg1) throws SQLException {
		
		Brand Brand = new Brand();
		Brand.setBrandId(rs.getInt("brand_id"));
		Brand.setBrandName(rs.getString("brand_name"));
		Brand.setBrandCode(rs.getString("brand_code"));
		Brand.setInternalBrandCode(rs.getString("internal_brand_code"));
		Brand.setPrincipal(rs.getString("principle"));
		Brand.setBrandowner(rs.getString("brand_owner"));
		Brand.setDescription(rs.getString("description"));
		Brand.setStateId(rs.getInt("state_id"));
		Brand.setLicenseId(rs.getInt("liscense_id"));
		Brand.setDistributerId(rs.getInt("distributer_id"));
		
		
		return Brand;
	}

}
