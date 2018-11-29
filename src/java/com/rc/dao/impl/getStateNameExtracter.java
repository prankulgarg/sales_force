package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.rc.model.CommonDetail;

public class getStateNameExtracter implements RowMapper<CommonDetail> {

	
	@Override
	public CommonDetail mapRow(ResultSet rs, int arg1) throws SQLException {
		CommonDetail commonDetail = new CommonDetail();
		commonDetail.setId(rs.getInt(1));
		commonDetail.setName(rs.getString(2));
		return commonDetail;
	}

}
