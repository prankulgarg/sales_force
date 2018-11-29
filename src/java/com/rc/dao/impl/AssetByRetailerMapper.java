/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import com.rc.model.AssetByRetailerData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Rajnish singh
 */
public class AssetByRetailerMapper implements RowMapper<AssetByRetailerData>{
     @Override
    public AssetByRetailerData mapRow(ResultSet rs, int i) throws SQLException{
        AssetByRetailerData assetByRetailerData=new AssetByRetailerData();
     assetByRetailerData.setId(rs.getInt("id"));
     assetByRetailerData.setAssetType(rs.getString("asset_type"));
     assetByRetailerData.setAssetName(rs.getString("asset_name")); 
     assetByRetailerData.setLastUpdate(rs.getString("last_update"));    
     assetByRetailerData.setDescription(rs.getString("description"));
     assetByRetailerData.setBrandId(rs.getInt("brand_id"));
     assetByRetailerData.setProductId(rs.getInt("product_id"));
     assetByRetailerData.setCreateDate(rs.getString("create_date"));
  
             
     return assetByRetailerData;
    }
}
