package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class DistributerResponse extends GenericResponse {
	List<Distributer> lstDistributer = new ArrayList();

	public List<Distributer> getLstDistributer() {
		return lstDistributer;
	}

	public void setLstDistributer(List<Distributer> lstDistributer) {
		this.lstDistributer = lstDistributer;
	}

}
