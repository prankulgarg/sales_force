package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class ProductRawdataResponse extends GenericResponse {
	
	List<ProductRawdata> lstProductRawdata = new ArrayList();

	public List<ProductRawdata> getLstProductRawdata() {
		return lstProductRawdata;
	}

	public void setLstProductRawdata(List<ProductRawdata> lstProductRawdata) {
		this.lstProductRawdata = lstProductRawdata;
	}
	

}
