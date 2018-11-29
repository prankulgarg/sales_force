package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Questions;

public class QuestionRowMapper implements RowMapper<Questions> {

	@Override
	public Questions mapRow(ResultSet rs, int arg1) throws SQLException {
		Questions questions = new Questions();
		questions.setQuestionId(rs.getInt("question_id"));
		questions.setServeyId(rs.getInt("servey_id"));
		questions.setQuestion(rs.getString("questioon"));
		questions.setEditable(rs.getString("editable"));
		questions.setObjective(rs.getString("objective"));
		questions.setImage(rs.getString("image"));
		return questions;
	}

}
