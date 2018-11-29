package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.ServeyReport;

public class ServeyReportRowMapper implements RowMapper<ServeyReport> {

	@Override
	public ServeyReport mapRow(ResultSet rs, int arg1) throws SQLException {
		ServeyReport ServeyReport = new ServeyReport();
		ServeyReport.setId(rs.getInt("id"));	
		ServeyReport.setUserAnswerId(rs.getInt("user_answer_id"));
		ServeyReport.setQuestionId(rs.getInt("question_id"));
		ServeyReport.setMemberId(rs.getInt("member_id"));
		ServeyReport.setRetailerId(rs.getInt("retailer_id"));
		ServeyReport.setUserAnswer(rs.getString("answerGiven"));
		ServeyReport.setQuestions(rs.getString("questionAsk"));
		ServeyReport.setMemberName(rs.getString("srName"));
		ServeyReport.setRoleId(rs.getInt("roleName"));
		ServeyReport.setRetailerName(rs.getString("retailerName"));
	
		
		return ServeyReport;
	}

}
