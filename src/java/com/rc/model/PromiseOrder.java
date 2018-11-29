package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class PromiseOrder {
private int id;
private int retailerId;
private String retailerName;
private int visitorId;
private String visitorName;
private String remarks;
private List<PromiseOrderProductDetail> productList = new ArrayList();
private String promiseDate;
private String takenDate;
private String latitude;
private String longitude;
private int visitId;
private int roleId;
private String roleName;
private int distributerId;



public int getDistributerId() {
	return distributerId;
}
public void setDistributerId(int distributerId) {
	this.distributerId = distributerId;
}
public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}
public int getVisitId() {
	return visitId;
}
public void setVisitId(int visitId) {
	this.visitId = visitId;
}
public String getRetailerName() {
	return retailerName;
}
public void setRetailerName(String retailerName) {
	this.retailerName = retailerName;
}
public String getVisitorName() {
	return visitorName;
}
public void setVisitorName(String visitorName) {
	this.visitorName = visitorName;
}
public String getLatitude() {
	return latitude;
}
public void setLatitude(String latitude) {
	this.latitude = latitude;
}
public String getLongitude() {
	return longitude;
}
public void setLongitude(String longitude) {
	this.longitude = longitude;
}
public int getId() {
	return id;
}
public void setId(int orderId) {
	this.id = orderId;
}
public int getRetailerId() {
	return retailerId;
}
public void setRetailerId(int retailerId) {
	this.retailerId = retailerId;
}
public int getVisitorId() {
	return visitorId;
}
public void setVisitorId(int visitorId) {
	this.visitorId = visitorId;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String orderRemarks) {
	this.remarks = orderRemarks;
}

public List<PromiseOrderProductDetail> getProductList() {
	return productList;
}
public void setProductList(List<PromiseOrderProductDetail> productList) {
	this.productList = productList;
}
public String getPromiseDate() {
	return promiseDate;
}
public void setPromiseDate(String promiseDate) {
	this.promiseDate = promiseDate;
}
public String getTakenDate() {
	return takenDate;
}
public void setTakenDate(String takenDate) {
	this.takenDate = takenDate;
}
@Override
public String toString() {
	return "PromiseOrder [id=" + id + ", retailerId=" + retailerId + ", retailerName=" + retailerName + ", visitorId="
			+ visitorId + ", visitorName=" + visitorName + ", remarks=" + remarks + ", productList=" + productList
			+ ", promiseDate=" + promiseDate + ", takenDate=" + takenDate + ", latitude=" + latitude + ", longitude="
			+ longitude + ", visitId=" + visitId + ", roleId=" + roleId + ", roleName=" + roleName + "]";
}



}
