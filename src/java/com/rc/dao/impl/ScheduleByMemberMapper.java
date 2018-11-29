package com.rc.dao.impl;

import com.rc.model.CreateScheduleData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
*
* @author Rajnish singh
*/
public class ScheduleByMemberMapper implements RowMapper<CreateScheduleData>{
       @Override
       public CreateScheduleData mapRow(ResultSet rs,int i) throws SQLException{
           CreateScheduleData createScheduleData=new CreateScheduleData();
           createScheduleData.setId(rs.getInt("id"));
           createScheduleData.setMemberId(rs.getInt("member_id"));
           createScheduleData.setNote(rs.getString("note"));
           createScheduleData.setRetailerId(rs.getInt("retailer_id"));
           createScheduleData.setStartTime(rs.getString("start_time"));
           createScheduleData.setEndTime(rs.getString("end_time"));
           createScheduleData.setTitle(rs.getString("title"));
           createScheduleData.setVisitDate(rs.getString("visit_date"));
           
           return createScheduleData;
       }
}