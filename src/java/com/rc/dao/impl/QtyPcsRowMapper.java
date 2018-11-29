package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.QtyInPcs;

public class QtyPcsRowMapper implements RowMapper<QtyInPcs> {

	@Override
	public QtyInPcs mapRow(ResultSet rs, int arg1) throws SQLException {
		QtyInPcs qtyInPcs = new QtyInPcs();
		qtyInPcs.setId(rs.getInt("id"));
		qtyInPcs.setPcs(rs.getInt("pieces"));
		qtyInPcs.setQtyMl(rs.getString("quantity_in_ml"));
		qtyInPcs.setDescription(rs.getString("description"));
		qtyInPcs.setStatus(rs.getInt("status"));
		
		return qtyInPcs;
		
		
	}

}
