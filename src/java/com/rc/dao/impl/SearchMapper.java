/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import com.rc.model.SearchProductData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Rajnish singh
 */
public class SearchMapper implements RowMapper<SearchProductData>{

	@Override
	public SearchProductData mapRow(ResultSet rs, int arg1) throws SQLException {
		SearchProductData product = new SearchProductData();
                
                
                
		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setBrandId(rs.getInt("brand_id"));
		product.setProductCode(rs.getString("Product_code"));
		product.setProductCategoryId(rs.getInt("PRODUCT_catagory_id"));
		product.setProductSegmentCode(rs.getInt("PRODUCT_segment_code"));
		product.setQtyMl(rs.getInt("qtyML"));
		product.setBoxSize(rs.getInt("box_size"));
		product.setLiscenseId(rs.getInt("liscense_id"));
		product.setProductStatus(rs.getInt("status"));
		product.setCreateDate(rs.getString("created_date"));
		product.setDistributerId(rs.getInt("distributor_id"));
		product.setPackagetypeId(rs.getInt("pacakage_type_id"));
		product.setProductTypeId(rs.getInt("product_type_id"));
		product.setCif(rs.getDouble("cif"));
		product.setWsp(rs.getDouble("wsp"));
		product.setMrp(rs.getDouble("mrp"));
		product.setProductSubtypeid(rs.getInt("product_sub_tyoe_id"));
		product.setExciseDuty(rs.getDouble("excise_duty"));
		product.setLiscenseId(rs.getInt("liscense_id"));
		
		
		return product;
	}
	

}
 