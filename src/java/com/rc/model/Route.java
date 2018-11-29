package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class Route {
	
	private int routeId;
	private String routeName;
	private int srId;
	private int managerId;
	private int asmId;
	private int tsmId;
	private String srName;
	private int createdbyid;
	private int updateById;
	private int distributerid;
	private String createDate;
	private String UpdateDate;
	private int count;
	private List<RetailerRawData> lstretailer = new ArrayList();
	public List<RetailerRawData> getLstretailer() {
		return lstretailer;
	}
	public void setLstretailer(List<RetailerRawData> lstretailer) {
		this.lstretailer = lstretailer;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private List<String> retailerId = new ArrayList(); 
	
	
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getAsmId() {
		return asmId;
	}
	public void setAsmId(int asmId) {
		this.asmId = asmId;
	}
	public int getTsmId() {
		return tsmId;
	}
	public void setTsmId(int tsmId) {
		this.tsmId = tsmId;
	}
	
	public List<String> getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(List<String> retailerId) {
		this.retailerId = retailerId;
	}
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
	public int getSrId() {
		return srId;
	}
	public void setSrId(int srId) {
		this.srId = srId;
	}
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}
	public int getCreatedbyid() {
		return createdbyid;
	}
	public void setCreatedbyid(int createdbyid) {
		this.createdbyid = createdbyid;
	}
	public int getUpdateById() {
		return updateById;
	}
	public void setUpdateById(int updateById) {
		this.updateById = updateById;
	}
	public int getDistributerid() {
		return distributerid;
	}
	public void setDistributerid(int distributerid) {
		this.distributerid = distributerid;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", routeName=" + routeName + ", srId=" + srId + ", managerId=" + managerId
				+ ", asmId=" + asmId + ", tsmId=" + tsmId + ", srName=" + srName + ", createdbyid=" + createdbyid
				+ ", updateById=" + updateById + ", distributerid=" + distributerid + ", createDate=" + createDate
				+ ", UpdateDate=" + UpdateDate + ", count=" + count + ", retailerId=" + retailerId + "]";
	}
	
	

}
