package com.rc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.rc.dao.impl.MemberRawMapper;
import com.rc.dao.impl.RetailerRawdataMapper;
import com.rc.model.Member;
import com.rc.model.RetailerRawData;

public class CacheManagerService {
	private final JdbcTemplate jdbcTemplate;
	public static Map<Integer, RetailerRawData> retailerdata = new HashMap<Integer, RetailerRawData>();

	public CacheManagerService(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);// To change body of
													// generated methods, choose
													// Tools | Templates.
	}

	public CacheManagerService(DataSource dataSource, PlatformTransactionManager transactionManager) {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	public void getRetailerData() {

		List<RetailerRawData> lstRetailerRawData = new ArrayList();
		String getRSql = "Select * from tbl_retailer order by retailer_id desc";
		lstRetailerRawData = jdbcTemplate.query(getRSql, new RetailerRawdataMapper());
		for (RetailerRawData retailerRawData : lstRetailerRawData) {
			retailerdata.put(retailerRawData.getRetailerId(), retailerRawData);
		}
		// Cachemanagerimpl.putCacheValue(CacheConstant.RETAILER_LIST,
		// retailerdata);
	}

	public void getMemberData() {

		List<Member> newMemberList = new ArrayList();
		Map<Integer, Member> memberData = new HashMap<Integer, Member>();
		String getMember = "Select * from tbl_member  order by employee_id desc";
		newMemberList = jdbcTemplate.query(getMember, new MemberRawMapper());
		for (Member member : newMemberList) {
			memberData.put(member.getEmployeeId(), member);
		}
		// Cachemanagerimpl.putCacheValue(CacheConstant.MEMBER_LIST,
		// memberData);

	}

}
