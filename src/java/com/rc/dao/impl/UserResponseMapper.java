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

import com.rc.model.UserResponse;

/**
 *
 * @author Admin
 */
public class UserResponseMapper implements ResultSetExtractor<UserResponse>{

    @Override
    public UserResponse extractData(ResultSet rs) throws SQLException, DataAccessException {
       
        
        UserResponse userResponse = new UserResponse();
        while(rs.next()){
        System.out.println("result set"+rs.getString("employee_name"));
        userResponse.setEmployee_id(rs.getInt("employee_id"));
       userResponse.setEmployee_name(rs.getString("employee_name"));
       // userResponse.setToken(rs.getString("isComparable"));
        userResponse.setResponse("true"); 
        }
     return userResponse;
    }
    
}
