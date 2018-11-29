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

/**
 *
 * @author Admin
 */
public class CheckDuplicateResultExtracter implements ResultSetExtractor<String>{

    @Override
    public String extractData(ResultSet rs) throws SQLException, DataAccessException {
if(rs.next()){
    return"exist";
}
else 
{
    return"not exist";
}
    }
    
}
