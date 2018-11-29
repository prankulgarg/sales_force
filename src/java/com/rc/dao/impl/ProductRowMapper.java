package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int arg1) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setBranId(rs.getInt("brand_id"));
		product.setProductCode(rs.getString("Product_code"));
		product.setProductCategoryId(rs.getInt("PRODUCT_catagory_id"));
		product.setProductSegmentCode(rs.getInt("PRODUCT_segment_code"));
		product.setQtyMl(rs.getInt("qtyML"));
		product.setBoxSize(rs.getInt("box_size"));
		product.setLicenseId(rs.getInt("liscense_id"));
		product.setExciseDuty(rs.getDouble("excise_duty"));
		product.setProductStatus(rs.getInt("status"));
		product.setCreateDate(rs.getString("created_date"));
		product.setUpdateDate(rs.getString("updated_date"));
		product.setCreateById(rs.getInt("created_by_id"));
		product.setUpdateById(rs.getInt("updated_by_id"));
		product.setDistributerId(rs.getInt("distributor_id"));
		product.setPackagetypeId(rs.getInt("pacakage_type_id"));
		product.setProductTypeId(rs.getInt("product_type_id"));
		product.setCif(rs.getDouble("cif"));
		product.setWsp(rs.getDouble("wsp"));
		product.setMrp(rs.getDouble("mrp"));
		product.setProductSubtypeid(rs.getInt("product_sub_tyoe_id"));
		product.setIsDelete(rs.getString("isDelete"));
		product.setBrandName(rs.getString("brandName"));
		product.setProductcategoryName(rs.getString("categoryName"));
		product.setProductSegmentName(rs.getString("segmentName"));
		product.setQtyInmlName(rs.getString("qtyML"));
		product.setQtyInpcsValue(rs.getInt("qtyPcs"));
		product.setLicenseName(rs.getString("licenseName"));
		product.setPackagetype(rs.getString("packageName"));
		product.setProductTypeName(rs.getString("productTypeName"));
		product.setProductSubTypeName(rs.getString("productSubTypeName"));
		
		return product;
	}
	

}
