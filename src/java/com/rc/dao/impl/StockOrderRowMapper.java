
    package com.rc.dao.impl;

   import com.rc.model.StockOrderProductDetailedData;
   import java.sql.ResultSet;
   import java.sql.SQLException;
   import org.springframework.jdbc.core.RowMapper;



   public class StockOrderRowMapper implements RowMapper<StockOrderProductDetailedData>{

       @Override 
       public StockOrderProductDetailedData mapRow(ResultSet rs,int arg) throws SQLException{
           StockOrderProductDetailedData stockOrderProductDetailedData=new StockOrderProductDetailedData();
           stockOrderProductDetailedData.setId(rs.getInt("id"));
           stockOrderProductDetailedData.setProductId(rs.getInt("product_id"));
           stockOrderProductDetailedData.setBrandId(rs.getInt("brand_id"));
           stockOrderProductDetailedData.setQty(rs.getInt("qty"));

           return stockOrderProductDetailedData;
       }
   }