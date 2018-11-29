package com.rc.model;

import java.util.ArrayList;
import java.util.List;



public class QuestionResponse extends GenericResponse {
	private List<Questions> questionList = new ArrayList<>();

	public List<Questions> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Questions> questionList) {
		this.questionList = questionList;
	}

	
}
