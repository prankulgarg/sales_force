package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rc.model.GetNameRawWrapper;
import com.rc.model.Member;

public class GetMemberRawdataMapper  implements ResultSetExtractor<Member> {

	@Override
	public Member extractData(ResultSet rs) throws SQLException, DataAccessException {
		Member member = new Member();
		while(rs.next()){
		member.setRoleName(rs.getString(1));
		member.setDistributerName(rs.getString(2));
		member.setDesignationname(rs.getString(3));
		member.setState(rs.getString(4));
		}
		return member;
	}

}
