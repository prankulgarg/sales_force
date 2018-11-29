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
public class AssetMapper implements RowMapper<AssetByRetailerData>{
    	@Override
	public AssetByRetailerData mapRow(ResultSet rs, int arg1) throws SQLException {
		AssetByRetailerData asset = new AssetByRetailerData();
		asset.setProductId(rs.getInt("product_id"));
		asset.setBrandId(rs.getInt("brand_id"));
		asset.setAssetName(rs.getString("asset_name"));
		asset.setAssetType(rs.getString("asset_type"));
                asset.setDescription(rs.getString("description"));
		
		
		
		return asset;
	}
	
}
