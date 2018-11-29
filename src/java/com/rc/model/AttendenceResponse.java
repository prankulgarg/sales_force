package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class AttendenceResponse extends GenericResponse {
	private List<Attendence> lstAttendence = new ArrayList();

	public List<Attendence> getLstAttendence() {
		return lstAttendence;
	}

	public void setLstAttendence(List<Attendence> lstAttendence) {
		this.lstAttendence = lstAttendence;
	}
	
	

}
