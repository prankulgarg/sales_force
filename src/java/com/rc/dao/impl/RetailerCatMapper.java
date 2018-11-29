/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.RetailerDetailedData;

/**
 *
 * @author Admin
 */
public class RetailerCatMapper implements RowMapper<RetailerDetailedData>{

    @Override
    public RetailerDetailedData mapRow(ResultSet rs, int i) throws SQLException {
RetailerDetailedData retailerDetailedData = new RetailerDetailedData();
retailerDetailedData.setName(rs.getString(2));
retailerDetailedData.setId(rs.getInt(1));
retailerDetailedData.setDescription(rs.getString("description"));
retailerDetailedData.setCreatedDate(rs.getString("created_date"));
retailerDetailedData.setUpdatedDate(rs.getString("updated_date"));


        return retailerDetailedData;


    }
       
   
    
}
