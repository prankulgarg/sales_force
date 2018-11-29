/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.rc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rajnish singh
 */
public class StockDetailedData {

	private int StockId;
	private int retailerId;
	private String retailerName;
	private String visitorName;
	private String srName;
	private String asmName;
	private String tsmName;
	private int visitorId;
	private String remarks;
	private String promiseDate;
	private String takenDate;
	private String roleId;
	private double lattitude;
	private double longitude;
	private int distance;
	private int visitId;
	private List<Product> stockProductList = new ArrayList();
	
	
	
	

	public String getSrName() {
		return srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}

	public String getAsmName() {
		return asmName;
	}

	public void setAsmName(String asmName) {
		this.asmName = asmName;
	}

	public String getTsmName() {
		return tsmName;
	}

	public void setTsmName(String tsmName) {
		this.tsmName = tsmName;
	}

	public List<Product> getStockProductList() {
		return stockProductList;
	}

	public void setStockProductList(List<Product> stockProductList) {
		this.stockProductList = stockProductList;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public int getStockId() {
		return StockId;
	}

	public void setStockId(int StockId) {
		this.StockId = StockId;
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
}
