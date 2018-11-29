/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao;

import org.springframework.transaction.annotation.Transactional;

import com.rc.model.AddDistributer;
import com.rc.model.AddNoteData;
import com.rc.model.AddRetailerRequest;
import com.rc.model.AssetByRetailerData;
import com.rc.model.AssignAssets;
import com.rc.model.AuditAssetReatiler;
import com.rc.model.Brand;
import com.rc.model.CommonDetail;
import com.rc.model.CreateScheduleData;
import com.rc.model.DistributerLicense;
import com.rc.model.GenericResponse;
import com.rc.model.LoginDetail;
import com.rc.model.LoginResponse;
import com.rc.model.Member;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;
import com.rc.model.PromiseOrder;
import com.rc.model.QtyInPcs;
import com.rc.model.Questions;
import com.rc.model.RetailerDetailedData;
import com.rc.model.Route;
import com.rc.model.Scheme;
import com.rc.model.Servey;
import com.rc.model.SubmitServey;
import com.rc.model.Target;
import com.rc.model.UserDetail;
import com.rc.model.Feedback;

import exception.AccessDeniedException;


/**
 *
 * @author Prankul Garg 
 */
@Transactional 
public interface LoginServiceDao {
    public LoginResponse validateCredentials(LoginDetail loginDetail ) throws AccessDeniedException;
    public UserDetail getUserDetailById(int employeeId);
    public GenericResponse insertRoleDetail(CommonDetail roledetail);
    public GenericResponse insertDesignationDetail(CommonDetail roledetail);
    public GenericResponse insertMemberDetail(Member addMember);
    public GenericResponse saveRetailerCat(RetailerDetailedData retailerCategoryRequest);
    public GenericResponse saveRetailerType(RetailerDetailedData retailerCategoryRequest);
    public GenericResponse saveRetailerSubType(RetailerDetailedData retailerCategoryRequest);
    public GenericResponse saveZone(RetailerDetailedData retailerCategoryRequest);
	public GenericResponse saveRetailer(AddRetailerRequest addRetailerRequest);
	public GenericResponse addPackageType(ProductRawdata packageType);
	public GenericResponse addProductCategory(ProductRawdata packageType);
	public GenericResponse addProduct(Product product);
	public GenericResponse addProductSegment(ProductRawdata packageType);
	public GenericResponse addProductSubType(ProductRawdata packageType);
	public GenericResponse addProductType(ProductRawdata packageType);
	public GenericResponse addBrand(Brand brand);
	public GenericResponse addDistributerLicense(DistributerLicense distributerLicense);
	public GenericResponse addQtyPcs(QtyInPcs qtyInPcs);
	public GenericResponse addFeedbackType(ProductRawdata productRawdata);
	public GenericResponse addFeedbackRating(ProductRawdata productRawdata);
	public GenericResponse addFeedback(Feedback feedback);
	public GenericResponse addRoute(Route route);
	public GenericResponse addAsset(AssetByRetailerData assetDetailedData);
	public GenericResponse assignAssets(AssignAssets assignAssets);
	public GenericResponse addPromiseOrder(PromiseOrder promiseOrder);
	public GenericResponse addRetailerStock(PromiseOrder promiseOrder);
	public GenericResponse addAuditAsset(AuditAssetReatiler audit);
	public GenericResponse createSchedule(CreateScheduleData  createScheduleData);
    public GenericResponse addNote(AddNoteData addNoteData);
	public GenericResponse addTarget(Target target);
	public GenericResponse addScheme(Scheme scheme);
	public GenericResponse addSurvey(Servey servey);
	public GenericResponse addQuestion(Questions questions);
	public GenericResponse submitSurvey(SubmitServey submitServey);
	public GenericResponse addDistributer(AddDistributer addDistributer);
    
}
