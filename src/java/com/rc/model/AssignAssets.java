package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class AssignAssets {
	private int assetId;
	private int qty;
	private String amount;
	private List<Integer>retailerList = new ArrayList();
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<Integer> getRetailerList() {
		return retailerList;
	}
	public void setRetailerList(List<Integer> retailerList) {
		this.retailerList = retailerList;
	}
	
	
	
	

}
