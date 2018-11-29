package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class TargetResponse extends GenericResponse {
List<Target> targetList = new ArrayList<>();

public List<Target> getTargetList() {
	return targetList;
}

public void setTargetList(List<Target> targetList) {
	this.targetList = targetList;
}



}
