package com.rc.dao;

import com.rc.model.Brand;
import com.rc.model.GenericResponse;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;

public interface DeleteResourceDao {

	
	GenericResponse deleteTeamMember(int id);

	GenericResponse deleteRole(int id);

	GenericResponse deleteDesignation(int id);

	GenericResponse deleteRetailer(int id);

	GenericResponse deleteRetailerType(int id);

	GenericResponse deleteRetailerSubType(int id);

	GenericResponse deleteRetailerCategory(int id);

	GenericResponse deleteZone(int id);

	GenericResponse deleteProduct(Product product);

	GenericResponse deleteBrand(Brand brand);

	GenericResponse deletePackagetype(ProductRawdata packageType);

	GenericResponse deleteProductCategory(int id);

	GenericResponse deleteProductSubType(int id);

	GenericResponse deleteProductSegment(int id);

	GenericResponse deleteProductType(int id);

}
