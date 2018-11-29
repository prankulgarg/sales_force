package com.rc.model;

public class QtyInPcs {
	private int id;
	private String qtyMl;
	private int pcs;
	private String description;
	private int status;
	private int distributerId;
	
	
	
	public int getDistributerId() {
		return distributerId;
	}
	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQtyMl() {
		return qtyMl;
	}
	public void setQtyMl(String qtyMl) {
		this.qtyMl = qtyMl;
	}
	public int getPcs() {
		return pcs;
	}
	public void setPcs(int pcs) {
		this.pcs = pcs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
