package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class ActivityResponse extends GenericResponse {
	
	private  List<VisitHistorys> activiList = new ArrayList();

	public List<VisitHistorys> getActiviList() {
		return activiList;
	}

	public void setActiviList(List<VisitHistorys> activiList) {
		this.activiList = activiList;
	}
	
	

}
