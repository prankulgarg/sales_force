package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class GetAssetRowMapper implements RowMapper<GetAsset> {

	@Override
	public GetAsset mapRow(ResultSet rs, int arg1) throws SQLException {
		GetAsset getAsset = new GetAsset();
		String str;
		getAsset.setAssignId(rs.getInt("id"));
		getAsset.setAssetId(rs.getInt("asset_id"));
		getAsset.setQty(rs.getString("qty"));
		getAsset.setAssignDate(rs.getString("assign_date"));
		getAsset.setAmount(rs.getString("amount"));
		getAsset.setIsAvailable(rs.getString("isAvailable"));
		getAsset.setFeedbackRatingId(rs.getInt("feedback_rating_id"));
		getAsset.setDescription(rs.getString("description"));
		getAsset.setAuditById(rs.getInt("audit_by_id"));
		getAsset.setAuditByRole(rs.getInt("audit_by_role"));
		getAsset.setRetailerId(rs.getInt("retailer_id"));
		getAsset.setImage1(rs.getString("image1"));
		getAsset.setImage2(rs.getString("image2"));
		getAsset.setImage3(rs.getString("image3"));
		try {
		    str = rs.getString("audit_date");
		} catch (java.sql.SQLException e) {
		    str = null;
		}
		getAsset.setAuditDate(str);
		
		return getAsset;
	}

}
