package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class Distributer {
	private int distributerId;
	private String distributerName;
	private String mobile;
	private String emailId;
	private int stateId;
	private String stateName;
	private String address;
	private String cityName;
	private String imagelogo;
	private List <GenericRequest> license = new ArrayList();
	
	
	public List<GenericRequest> getLicense() {
		return license;
	}
	public void setLicense(List<GenericRequest> license) {
		this.license = license;
	}
	public int getDistributerId() {
		return distributerId;
	}
	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}
	public String getDistributerName() {
		return distributerName;
	}
	public void setDistributerName(String distributerName) {
		this.distributerName = distributerName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getImagelogo() {
		return imagelogo;
	}
	public void setImagelogo(String imagelogo) {
		this.imagelogo = imagelogo;
	}
	

}
