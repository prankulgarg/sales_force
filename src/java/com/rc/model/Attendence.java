package com.rc.model;

import java.util.Date;

public class Attendence {
	private int id ;
	private String inTime;
	private String outTime;
	private String inDate;
	private String outDate;
	private String attendenceInTime;
	private String attendenceOutTime;

	private int visitorId;
	private String visitorName;
	private int visitId;
	private int inRetailerId;
	private String inRetailerName;
	private int outRetailerId;
	private String outRetailerName;
	private String diffrence;

	
	
	
	public String getAttendenceInTime() {
		return attendenceInTime;
	}
	public void setAttendenceInTime(String attendenceInTime) {
		this.attendenceInTime = attendenceInTime;
	}
	public String getAttendenceOutTime() {
		return attendenceOutTime;
	}
	public void setAttendenceOutTime(String attendenceOutTime) {
		this.attendenceOutTime = attendenceOutTime;
	}
	
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getDiffrence() {
		return diffrence;
	}
	public void setDiffrence(String diffrence) {
		this.diffrence = diffrence;
	}
	private int activityType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public int getVisitId() {
		return visitId;
	}
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	public int getInRetailerId() {
		return inRetailerId;
	}
	public void setInRetailerId(int inRetailerId) {
		this.inRetailerId = inRetailerId;
	}
	public String getInRetailerName() {
		return inRetailerName;
	}
	public void setInRetailerName(String inRetailerName) {
		this.inRetailerName = inRetailerName;
	}
	public int getOutRetailerId() {
		return outRetailerId;
	}
	public void setOutRetailerId(int outRetailerId) {
		this.outRetailerId = outRetailerId;
	}
	public String getOutRetailerName() {
		return outRetailerName;
	}
	public void setOutRetailerName(String outRetailerName) {
		this.outRetailerName = outRetailerName;
	}
	

}
