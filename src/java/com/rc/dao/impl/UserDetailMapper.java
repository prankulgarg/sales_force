/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rc.model.UserDetail;

/**
 *
 * @author Admin
 */
public class UserDetailMapper implements ResultSetExtractor<UserDetail> {

    @Override
    public UserDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
       UserDetail ud = new UserDetail();
        
     if (rs.next()){
        while (rs.next())
        {
         
             System.out.println("inside Data Filling");
         ud.setCity(rs.getString("city_name"));
         ud.setDesignationId(rs.getInt("designation_id"));
         ud.setEmailId(rs.getString("email_id"));
         ud.setEmployeeId(rs.getInt("employee_id"));
         ud.setEmployeeName(rs.getString("employee_name"));
         if(rs.getDate("joining_date")!=null){
         ud.setJoininDate(rs.getDate("joining_date").toString());}
         ud.setLocality(rs.getString("locality"));
         ud.setMobileNumber(rs.getString("mobile"));
         ud.setRoleName(rs.getString("role_name"));
         ud.setStateName(rs.getString("state_name"));
         ud.setStreet(rs.getString("street"));
         ud.setResponseMsg("true");
        }
      
       }
      else 
       {
           System.out.println("result not fouud");
        ud.setResponseMsg("false");
       
       }
       
         return ud;
    }

    
}
