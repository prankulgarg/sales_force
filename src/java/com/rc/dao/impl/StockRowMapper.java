
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.rc.dao.impl;

import com.rc.model.StockDetailedData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
*
* @author Rajnish singh
*/
public class StockRowMapper implements RowMapper<StockDetailedData>{
   @Override
public StockDetailedData mapRow(ResultSet rs, int arg1) throws SQLException{
           StockDetailedData stockDetailedData=new StockDetailedData();
           stockDetailedData.setStockId(rs.getInt("stock_id"));
           stockDetailedData.setRetailerId(rs.getInt("retailer_id"));
           stockDetailedData.setVisitorId(rs.getInt("visitor_id"));
           stockDetailedData.setRemarks(rs.getString("remarks"));
           stockDetailedData.setTakenDate(rs.getString("taken_date"));
           stockDetailedData.setLattitude(rs.getDouble("lattitude"));
          stockDetailedData.setLongitude(rs.getDouble("longitude"));
           stockDetailedData.setDistance(rs.getInt("distance"));
           stockDetailedData.setVisitId(rs.getInt("visit_id"));
           
           return stockDetailedData;
       }
}