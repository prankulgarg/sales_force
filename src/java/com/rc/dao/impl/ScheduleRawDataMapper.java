
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.rc.dao.impl;

import com.rc.model.CreateScheduleData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
*
* @author Rajnish singh
*/
public class ScheduleRawDataMapper implements ResultSetExtractor<CreateScheduleData>{
       @Override 
       public CreateScheduleData extractData(ResultSet rs) throws SQLException,DataAccessException{
           CreateScheduleData createScheduleData=new CreateScheduleData();
           while(rs.next()){
               createScheduleData.setMemberName(rs.getString("employee_name"));
               createScheduleData.setRetailerName(rs.getString("retailer_shop_name"));
           }
           return createScheduleData;
       }
       
       
}