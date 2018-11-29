package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Feedback;

public class FeedbackRowMapper implements RowMapper<Feedback> {

	@Override
	public Feedback mapRow(ResultSet rs, int arg1) throws SQLException {
		Feedback feedback = new Feedback();
		feedback.setId(rs.getInt("feedback_id"));
		feedback.setVisitorId(rs.getInt("visitor_id"));
		feedback.setRetailerId(rs.getInt("retailer_id"));
		feedback.setFeedackTypeId(rs.getInt("feedback_type_id"));
		feedback.setFeedbackratingid(rs.getInt("feedback_rating_id"));
        feedback.setVisitId(rs.getInt("visit_id"));
        feedback.setVisitDate(rs.getString("visit_date"));
        feedback.setFeedbackTitle(rs.getString("feedback_tittle"));
        feedback.setDescription(rs.getString("feedback_description"));
        feedback.setImage(rs.getString("feedback_image"));
        feedback.setLattitude(rs.getString("lattitude"));
        feedback.setLongitude(rs.getString("longitude"));
        return feedback;
	}

}
