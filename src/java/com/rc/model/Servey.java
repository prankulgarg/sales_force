package com.rc.model;

public class Servey {
	private int serveyId;
	private String title;
	private String description;
	private String startDate;
	private String endDate;
	private int distributerId;
	
	
	
	public int getDistributerId() {
		return distributerId;
	}
	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}
	public int getServeyId() {
		return serveyId;
	}
	public void setServeyId(int serveyId) {
		this.serveyId = serveyId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	

}
