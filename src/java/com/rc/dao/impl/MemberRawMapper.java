package com.rc.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Member;

public class MemberRawMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int arg1) throws SQLException {
		Member member = new Member();
		member.setEmployeeId(rs.getInt("employee_id"));
		member.setEmployeeName(rs.getString("employee_name"));
		member.setEmailId(rs.getString("email_id"));
		member.setMobile(rs.getString("mobile"));
		member.setAlternatemobileNumber(rs.getString("alternative_mobile"));
		member.setAdddress(rs.getString("address"));
		member.setJoiningDate(rs.getString("joining_date"));
		member.setRoleId(rs.getInt("role_id"));
		member.setDesignationId(rs.getInt("designation_id"));
		member.setDistributerId(rs.getInt("distributor_id"));
		member.setTsmId(rs.getInt("tsm_id"));
		member.setAsmId(rs.getInt("asm_id"));
		member.setManagerId(rs.getInt("manager_id"));
		member.setCretedById(rs.getInt("created_by"));
		member.setEmpId(rs.getString("emp_id"));
		member.setState(rs.getString("state"));
		member.setReportTo(rs.getInt("report_to"));
		member.setStatus(rs.getString("status"));
		member.setProfilePic(rs.getString("profile_pic"));
		
		return member;
	}
	
	
	

}
