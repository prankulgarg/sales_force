package com.rc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.rc.model.AssetByRetailerData;

public class GetAsset {
	
	private int assignId;
	private int assetId;
	private int retailerId;
	private String retailerName;
	private String  qty;
	private String amount;
	private String assignDate;
	private String  isAvailable;
	private int feedbackRatingId;
	private String feedbackRatingName;
	private String description;
	private int auditById;
	private String auditByName;
	private int auditByRole;
	private String srName;
	private String auditDate;
	private String image1;
	private String image2;
	private String image3;
	
	
	
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getFeedbackRatingName() {
		return feedbackRatingName;
	}
	public void setFeedbackRatingName(String feedbackRatingName) {
		this.feedbackRatingName = feedbackRatingName;
	}
	
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}

	private List<AssetByRetailerData> lstAssetDetail = new ArrayList<>();

	
	public String getAuditByName() {
		return auditByName;
	}
	public void setAuditByName(String auditByName) {
		this.auditByName = auditByName;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public int getAuditById() {
		return auditById;
	}
	public void setAuditById(int auditById) {
		this.auditById = auditById;
	}
	public int getAuditByRole() {
		return auditByRole;
	}
	public void setAuditByRole(int auditByRole) {
		this.auditByRole = auditByRole;
	}
	public int getAssignId() {
		return assignId;
	}
	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public int getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}

	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	
	public String getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getFeedbackRatingId() {
		return feedbackRatingId;
	}
	public void setFeedbackRatingId(int feedbackRatingId) {
		this.feedbackRatingId = feedbackRatingId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<AssetByRetailerData> getLstAssetDetail() {
		return lstAssetDetail;
	}
	public void setLstAssetDetail(List<AssetByRetailerData> lstAssetDetail) {
		this.lstAssetDetail = lstAssetDetail;
	}
	
	
	
	

}
