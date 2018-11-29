package com.rc.model;

import java.util.ArrayList;
import java.util.List;

public class QtyPcsResponse extends GenericResponse {
	List<QtyInPcs> listQtyInPcs = new ArrayList();

	public List<QtyInPcs> getListQtyInPcs() {
		return listQtyInPcs;
	}

	public void setListQtyInPcs(List<QtyInPcs> listQtyInPcs) {
		this.listQtyInPcs = listQtyInPcs;
	}
	

}
