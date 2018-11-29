package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.AnswerResponse;

public class AnswerRowMapper implements RowMapper<AnswerResponse> {

	@Override
	public AnswerResponse mapRow(ResultSet rs, int arg1) throws SQLException {
		AnswerResponse answerResponse = new AnswerResponse();
		answerResponse.setAnswerId(rs.getInt("answer_id"));
		answerResponse.setAnswer(rs.getString("answer"));
		return answerResponse;
	}

}
