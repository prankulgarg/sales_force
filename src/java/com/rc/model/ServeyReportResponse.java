package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class ServeyReportResponse extends GenericResponse{
private List<ServeyReport> lstServeyReport = new ArrayList<>();

public List<ServeyReport> getLstServeyReport() {
	return lstServeyReport;
}

public void setLstServeyReport(List<ServeyReport> lstServeyReport) {
	this.lstServeyReport = lstServeyReport;
}



}
