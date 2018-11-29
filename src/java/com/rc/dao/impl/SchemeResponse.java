package com.rc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.rc.model.GenericResponse;
import com.rc.model.Scheme;

public class SchemeResponse extends GenericResponse {
	private List<Scheme> lstScheme = new ArrayList<>();

	public List<Scheme> getLstScheme() {
		return lstScheme;
	}

	public void setLstScheme(List<Scheme> lstScheme) {
		this.lstScheme = lstScheme;
	}
	
	

}
