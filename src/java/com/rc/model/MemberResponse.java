package com.rc.model;

import java.util.ArrayList;
import java.util.List;

import com.rc.model.GenericResponse;
import com.rc.model.Member;

public class MemberResponse extends GenericResponse {
	 private List<Member> lstMemberResponse = new ArrayList();
	 
	  public List<Member> getLstMemberResponse() {
			return lstMemberResponse;
		}

		public void setLstMemberResponse(List<Member> lstMemberResponse) {
			this.lstMemberResponse = lstMemberResponse;
		}

	 

}
