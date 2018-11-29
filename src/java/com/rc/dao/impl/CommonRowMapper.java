/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.CommonDetail;

/**
 *
 * @author Admin
 */
public class CommonRowMapper implements RowMapper<CommonDetail> {

    

    @Override
    public CommonDetail mapRow(ResultSet rs, int i) throws SQLException {
    CommonDetail commonDetail = new CommonDetail(); 
    
    commonDetail.setId(rs.getInt("id"));
    commonDetail.setName(rs.getString("role_name"));
    commonDetail.setDescription(rs.getString("description"));
   return commonDetail;
    }
    
}
