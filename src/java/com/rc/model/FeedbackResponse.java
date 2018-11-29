package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class FeedbackResponse extends GenericResponse {
	
	private List<Feedback> feedbackList = new  ArrayList();

	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	
	
	

}
