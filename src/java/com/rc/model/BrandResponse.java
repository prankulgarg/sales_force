package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class BrandResponse extends GenericResponse {
	
	private List<Brand> brandList = new ArrayList();

	public List<Brand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<Brand> brandList) {
		this.brandList = brandList;
	}
	
	

}
