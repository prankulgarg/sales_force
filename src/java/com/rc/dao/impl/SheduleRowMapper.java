package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.CreateScheduleData;

public class SheduleRowMapper implements RowMapper<CreateScheduleData> {

	@Override
	public CreateScheduleData mapRow(ResultSet rs, int arg1) throws SQLException {
		CreateScheduleData createScheduleData = new CreateScheduleData();
		createScheduleData.setId(rs.getInt("id"));
		createScheduleData.setTitle(rs.getString("title"));
		createScheduleData.setRetailerId(rs.getInt("retailer_id"));
		createScheduleData.setAssignerId(rs.getInt("assigner_id"));
		createScheduleData.setNote(rs.getString("note"));
		createScheduleData.setVisitDate(rs.getString("visit_date"));
		createScheduleData.setStartTime(rs.getString("start_time"));
		createScheduleData.setEndTime(rs.getString("end_time"));
		createScheduleData.setAssignedId(rs.getInt("assigned_id"));
		createScheduleData.setRetailerName(rs.getString("retailerName"));
		createScheduleData.setAssignerName(rs.getString("assignerName"));
		createScheduleData.setAssignedName(rs.getString("assignName"));
		createScheduleData.setRoleId(rs.getInt("role_id"));
		return createScheduleData;
	}

}
