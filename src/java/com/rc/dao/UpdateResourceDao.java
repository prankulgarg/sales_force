package com.rc.dao;

import com.rc.model.AddRetailerRequest;
import com.rc.model.AssetByRetailerData;
import com.rc.model.AuditAssetReatiler;
import com.rc.model.Brand;
import com.rc.model.CommonDetail;
import com.rc.model.GenericResponse;
import com.rc.model.Member;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;
import com.rc.model.RetailerBlockedORDefaulterReq;
import com.rc.model.RetailerDetailedData;

public interface UpdateResourceDao {

	

	GenericResponse verifyRetailer(AddRetailerRequest addRetailerRequest);

	GenericResponse updateMember(Member addMember);

	GenericResponse makeRetailerDefaulter(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq);

	GenericResponse makeRetailerBlocked(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq);

	GenericResponse makeRetailerUnDefaulter(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq);

	GenericResponse makeRetailerUnBlocked(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq);

	GenericResponse updateRole(CommonDetail commonDetail);

	GenericResponse updateDesignation(CommonDetail commonDetail);

	GenericResponse updateRetailer(AddRetailerRequest updateRetailerRequest);

	GenericResponse updateRetailerType(RetailerDetailedData retailerCategoryRequest);

	GenericResponse updateRetailerSubType(RetailerDetailedData retailerCategoryRequest);

	GenericResponse updateRetailerCategory(RetailerDetailedData retailerCategoryRequest);

	GenericResponse updateZone(RetailerDetailedData retailerCategoryRequest);

	GenericResponse updateAsset(AssetByRetailerData assetDetailedData);

	GenericResponse makeRetailerActive(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq);

	GenericResponse makeRetailerInactive(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq);

	GenericResponse updateProduct(Product product);

	GenericResponse updateBrand(Brand brand);

	GenericResponse updatePackagetype(ProductRawdata packageType);

	GenericResponse updateProductCategory(ProductRawdata packageType);

	GenericResponse updateProductSegment(ProductRawdata packageType);

	GenericResponse updateProductSubType(ProductRawdata packageType);

	GenericResponse updateProductType(ProductRawdata packageType);

	GenericResponse auditAsset(AuditAssetReatiler auditAssetReatiler);


}
