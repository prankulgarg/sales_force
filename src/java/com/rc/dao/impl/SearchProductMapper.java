
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import com.rc.model.SearchProductData;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;


/**
 *
 * @author Rajnish singh
 */
public class SearchProductMapper implements ResultSetExtractor<SearchProductData> {

	@Override
	public SearchProductData extractData(ResultSet rs) throws SQLException, DataAccessException {
		SearchProductData lstName =  new SearchProductData();
		while (rs.next()){
                        System.out.println("executed");	
			lstName.setBrandName(rs.getString(1));
			lstName.setBrandCode(rs.getString(2));
			lstName.setInternalBrandCode(rs.getString(3));
			lstName.setPackagetype(rs.getString(4));
			lstName.setProductcategoryName(rs.getString(5));
			lstName.setProductSegmentName(rs.getString(6));
			lstName.setProductSubTypeName(rs.getString(7));
			lstName.setProductTypeName(rs.getString(8));
			lstName.setQtyInmlName(rs.getString(9));
			lstName.setQtyInpcsValue(rs.getInt(10));
                     
                        
                      		
			
		}
		
		
		
		return lstName;
	}

}
