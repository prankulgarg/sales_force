package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class RouteRawDataResponse {
	private int routeId;
	private String routeName;
	private int distributerid;
	private int srId;
	private List<RetailerRawData> lstretailer = new ArrayList();
	private List<Member> employeeDetail = new ArrayList();
	private String contactNumber;
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public int getDistributerid() {
		return distributerid;
	}
	public void setDistributerid(int distributerid) {
		this.distributerid = distributerid;
	}
	public List<RetailerRawData> getLstretailer() {
		return lstretailer;
	}
	public void setLstretailer(List<RetailerRawData> lstretailer) {
		this.lstretailer = lstretailer;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public int getSrId() {
		return srId;
	}
	public void setSrId(int srId) {
		this.srId = srId;
	}
	public List<Member> getEmployeeDetail() {
		return employeeDetail;
	}
	public void setEmployeeDetail(List<Member> employeeDetail) {
		this.employeeDetail = employeeDetail;
	}
	
	
	
}
