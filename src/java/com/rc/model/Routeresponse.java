package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class Routeresponse extends GenericResponse {
 private List<RouteRawDataResponse> routelist = new ArrayList<RouteRawDataResponse>();

public List<RouteRawDataResponse> getRoutelist() {
	return routelist;
}

public void setRoutelist(List<RouteRawDataResponse> routelist) {
	this.routelist = routelist;
}



}
