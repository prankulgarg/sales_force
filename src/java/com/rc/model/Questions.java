package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class Questions {
	
	private int serveyId;
	private int questionId;
	private String question;
	private String editable;
	private String objective;
	private String image;
	private List<String> answerList = new ArrayList<>();
	private List<AnswerResponse> answer = new ArrayList<>();
	
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public List<AnswerResponse> getAnswer() {
		return answer;
	}
	public void setAnswer(List<AnswerResponse> answer) {
		this.answer = answer;
	}
	public int getServeyId() {
		return serveyId;
	}
	public void setServeyId(int serveyId) {
		this.serveyId = serveyId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = editable;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<String> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}
	
	
	
	

}
