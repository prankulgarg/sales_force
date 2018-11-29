package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class TeamActivityHistoryResponse extends GenericResponse {
private List<VisitHistorys> lstVisit = new ArrayList();

public List<VisitHistorys> getLstVisit() {
	return lstVisit;
}

public void setLstVisit(List<VisitHistorys> lstVisit) {
	this.lstVisit = lstVisit;
}


} 
