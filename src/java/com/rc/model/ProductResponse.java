package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse extends GenericResponse {
	
	private List<Product> listProduct = new ArrayList();

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}
	

}
