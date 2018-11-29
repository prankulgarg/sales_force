package com.rc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.rc.model.ExciseOrder;
import com.rc.model.GenericResponse;

public class ExciseDutyResponse extends GenericResponse{
List<ExciseOrder> lstExciseOrder = new ArrayList<>();

public List<ExciseOrder> getLstExciseOrder() {
	return lstExciseOrder;
}

public void setLstExciseOrder(List<ExciseOrder> lstExciseOrder) {
	this.lstExciseOrder = lstExciseOrder;
}






}
