package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class PromiseOrderResponse extends GenericResponse  {
	
	private List<PromiseOrder> lstPromiseOrder = new  ArrayList();

	public List<PromiseOrder> getLstPromiseOrder() {
		return lstPromiseOrder;
	}

	public void setLstPromiseOrder(List<PromiseOrder> lstPromiseOrder) {
		this.lstPromiseOrder = lstPromiseOrder;
	}
			
	

}
