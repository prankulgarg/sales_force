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

import com.rc.model.LoginResponse;

/**
 *
 * @author root
 */
public class LoginMapper implements ResultSetExtractor<LoginResponse> {
        
	@Override
	public LoginResponse extractData(ResultSet rs) throws SQLException, DataAccessException {
	 LoginResponse loginResponse = new LoginResponse();	
            if(rs.next()) 
                 {   
                    
                     loginResponse.setEmployeeId(rs.getInt("employee_id"));
                     loginResponse.setMessage("login Succesfully");
                     loginResponse.setRoleId(rs.getString("role_id"));
                     loginResponse.setStatus("success");
                     loginResponse.setStatusCode(0);
                     
	           
                 }
            else{
                loginResponse.setStatus("fail");
                loginResponse.setMessage("login failed Authentication  Error");
                loginResponse.setStatusCode(2);
                
            }
	          return loginResponse; 
	}

}
