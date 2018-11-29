/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import com.rc.model.AssetByRetailerData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 *
 * @author Rajnish singh
 */
public class AssetRawDataMapper implements ResultSetExtractor<AssetByRetailerData>{
        @Override 
        public AssetByRetailerData extractData(ResultSet rs) throws SQLException,DataAccessException{
            AssetByRetailerData lstName=new AssetByRetailerData();
            while(rs.next()){
                lstName.setBrandName(rs.getString("brand_name"));
                lstName.setProductName(rs.getString("product_name"));
            }
            return lstName;
        }
}


