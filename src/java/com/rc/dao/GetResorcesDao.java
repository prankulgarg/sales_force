/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao;

import java.util.List;

import com.rc.dao.impl.ExciseDutyResponse;
import com.rc.model.ActivityRequest;
import com.rc.model.ActivityResponse;
import com.rc.model.AdvanceSearch;
import com.rc.model.AssetByRetailerResponse;
import com.rc.model.AttendenceResponse;
import com.rc.model.BrandResponse;
import com.rc.model.CommonDetailResponse;
import com.rc.model.DistributerResponse;
import com.rc.model.FeedbackResponse;
import com.rc.model.GenericRequest;
import com.rc.model.GenericResponse;
import com.rc.model.LoginDetail;
import com.rc.model.LoginResponse;
import com.rc.model.MemberResponse;
import com.rc.model.ProductRawdataResponse;
import com.rc.model.ProductResponse;
import com.rc.model.PromiseOrderResponse;
import com.rc.model.QtyPcsResponse;
import com.rc.model.RetailerDetailResponse;
import com.rc.model.RetailerRawDataResponse;
import com.rc.model.Routeresponse;
import com.rc.model.ScheduleResponse;
import com.rc.model.SearchProductResponse;
import com.rc.model.StockResponse;
import com.rc.model.TeamActivityHistoryResponse;

import exception.AccessDeniedException;

/**
 *
 * @author Admin
 */
public interface GetResorcesDao {

    public  CommonDetailResponse getAllRole();

    public CommonDetailResponse getAllDesignation();

    public RetailerDetailResponse getAllretailerCat();

 

    public RetailerRawDataResponse getAllretailer(int id);

    public RetailerDetailResponse getAllretailertype();

    public RetailerDetailResponse getAllretailerSubtype();
    public RetailerDetailResponse getZone();
    

	public MemberResponse getTeamByRole(GenericRequest genericRequest);

	public MemberResponse getAllMember(int id);

    public AssetByRetailerResponse getAssetByRetailerId(GenericRequest gr);

	public RetailerRawDataResponse getAllGroupr(int distrId);

	public MemberResponse getTeamById(int id);



	public RetailerRawDataResponse getRetailerByRetailerId(int id);

	public CommonDetailResponse getAllState();

	public MemberResponse getAsmListForProfile( GenericRequest genericRequest);

	public RetailerRawDataResponse getretailerByDist(GenericRequest genericRequest);

	public LoginResponse validateCredentials(LoginDetail loginDetail) throws AccessDeniedException;

	public RetailerRawDataResponse getRetailerByGroupId(int id);

	public RetailerRawDataResponse getBlockedretailer(int distributerid);

	public Routeresponse getAllRoute(int i);

	public DistributerResponse getAllDistributer();

	public ProductRawdataResponse getPackagetype();

	public ProductRawdataResponse getProductCategory();

	public ProductRawdataResponse getProductSegment();

	public ProductRawdataResponse getProductSubType();

	public ProductRawdataResponse getProductType();

	public QtyPcsResponse getQtyPcs();

	public BrandResponse getBrand(GenericRequest genericRequest);

	public ProductResponse getProduct(GenericRequest genericRequest);

	public ProductRawdataResponse getFeedbackType();

	public ProductRawdataResponse getFeedbackRating();

	public RetailerRawDataResponse getRetailerByRouteId(int id);

	public Routeresponse getRouteBySrId(int i);

	public MemberResponse getTeamByAdvanceSearch(AdvanceSearch advanceSearch);
	
	 public AssetByRetailerResponse getAsset(GenericRequest genericRequest);

	public AssetByRetailerResponse getAssetOfAllRetailer(int id);

	public FeedbackResponse getFeedback(GenericRequest genericRequest);

	public MemberResponse getNonAssignedRouteSR(AdvanceSearch advancesearch);

	//public TeamActivityHistoryResponse getNonAssignedRouteSR(int retailerId, String fromDate, String toDate);

	public MemberResponse getTeamActivity(GenericRequest genericRequest);

	public RetailerRawDataResponse getTeamActivityRetailerWise(GenericRequest genericRequest);

	public PromiseOrderResponse getPromiseOrder(GenericRequest genericRequest);

	public ExciseDutyResponse getExciseOrderByDistributerId(GenericRequest genericRequest);

	 public StockResponse getStock(GenericRequest genericRequest);

	public AttendenceResponse getAttendence(GenericRequest genericRequest);

	public ActivityResponse getActivity(GenericRequest genericRequest);
	
	  public ScheduleResponse getSchedule(int memberId);

	public GenericResponse getTarget(GenericRequest genericRequest);

	public GenericResponse getScheme(GenericRequest genericRequest);

	public GenericResponse getServey(GenericRequest genericRequest);

	public GenericResponse getQestionAndAnswerByServeyId(GenericRequest genericRequest);

	public GenericResponse getServeyReport(GenericRequest genericRequest);

	public ProductResponse getProductForDownload(List<Integer> productList);

	public GenericResponse getShedule(GenericRequest genericRequest);
	public TeamActivityHistoryResponse getTeamActivityHistoryBy(ActivityRequest ar);

	public SearchProductResponse searchProduct(List<Integer> productId,List<Integer> brandId,List<Integer> categoryId,List<Integer> typeId,List<Integer> subTypeId,List<Integer> licenseId);
 

    public RetailerRawDataResponse getRetailerSearchById(int memberId,String name);
}
