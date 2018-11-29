package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class ExciseOrder {
	private int id;
	private int exciseOrderId;
	private int  retailerId;
	private String retailerName;
	private String issueDate;
	private String dispatchDate;
	private String receiveDate;
	private String OrderCreateDate;
	private int srId;
	private int srName;
	private int tsmId;
	private int asmId;
	private int managerId;
	private int distributerId;
	private int createdByid;
	private List <Product> exciseProductList = new ArrayList();

	
	
	
	public List<Product> getExciseProductList() {
		return exciseProductList;
	}
	public void setExciseProductList(List<Product> exciseProductList) {
		this.exciseProductList = exciseProductList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getExciseOrderId() {
		return exciseOrderId;
	}
	public void setExciseOrderId(int exciseOrderId) {
		this.exciseOrderId = exciseOrderId;
	}
	public int getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}
	
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getOrderCreateDate() {
		return OrderCreateDate;
	}
	public void setOrderCreateDate(String orderCreateDate) {
		OrderCreateDate = orderCreateDate;
	}
	public int getSrId() {
		return srId;
	}
	public void setSrId(int srId) {
		this.srId = srId;
	}
	public int getTsmId() {
		return tsmId;
	}
	public void setTsmId(int tsmId) {
		this.tsmId = tsmId;
	}
	public int getAsmId() {
		return asmId;
	}
	public void setAsmId(int asmId) {
		this.asmId = asmId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getDistributerId() {
		return distributerId;
	}
	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}
	public int getCreatedByid() {
		return createdByid;
	}
	public void setCreatedByid(int createdByid) {
		this.createdByid = createdByid;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public int getSrName() {
		return srName;
	}
	public void setSrName(int srName) {
		this.srName = srName;
	}
	
	
	
	
	

}
