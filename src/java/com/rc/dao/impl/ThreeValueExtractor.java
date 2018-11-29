
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
* @author Rajnish singh
*/
public class ThreeValueExtractor implements ResultSetExtractor<String[]> {
    
   @Override
   public String[] extractData(ResultSet rs) throws SQLException,DataAccessException{
       String[] name=new String[3];
       while(rs.next()){
           name[0]=rs.getString(1);
           name[1]=rs.getString(2);
           name[2]=rs.getString(3);
       }
       return name;
   }
}