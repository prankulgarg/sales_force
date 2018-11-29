package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class ServeyResponse extends GenericResponse {
	private int responseId;
	private List<Servey> serveyList = new ArrayList<>();
	

	public List<Servey> getServeyList() {
		return serveyList;
	}

	public void setServeyList(List<Servey> serveyList) {
		this.serveyList = serveyList;
	}

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}
	

}
