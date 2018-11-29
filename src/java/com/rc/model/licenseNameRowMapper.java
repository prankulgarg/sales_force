package com.rc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class licenseNameRowMapper implements RowMapper<GenericRequest> {

	@Override
	public GenericRequest mapRow(ResultSet rs, int arg1) throws SQLException {
		GenericRequest GenericRequest = new GenericRequest();
		GenericRequest.setId(rs.getInt("liscense_id"));
		GenericRequest.setName(rs.getString("liscense_no"));

		return GenericRequest;
	}

}
