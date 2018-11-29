/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import com.rc.dao.GetResorcesDao;
import com.rc.model.ActivityRequest;
import com.rc.model.ActivityResponse;
import com.rc.model.AdvanceSearch;
import com.rc.model.AnswerResponse;
import com.rc.model.AssetByRetailerData;
import com.rc.model.AssetByRetailerResponse;
import com.rc.model.Attendence;
import com.rc.model.AttendenceResponse;
import com.rc.model.Brand;
import com.rc.model.BrandResponse;
import com.rc.model.CommonDetail;
import com.rc.model.CommonDetailResponse;
import com.rc.model.CreateScheduleData;
import com.rc.model.Distributer;
import com.rc.model.DistributerResponse;
import com.rc.model.ExciseOrder;
import com.rc.model.Feedback;
import com.rc.model.FeedbackResponse;
import com.rc.model.GenericRequest;
import com.rc.model.GenericResponse;
import com.rc.model.GetNameRawWrapper;
import com.rc.model.LoginDetail;
import com.rc.model.LoginResponse;
import com.rc.model.Member;
import com.rc.model.MemberResponse;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;
import com.rc.model.ProductRawdataResponse;
import com.rc.model.ProductResponse;
import com.rc.model.ProductRowDataRowMapper;
import com.rc.model.PromiseOrder;
import com.rc.model.PromiseOrderProductDetail;
import com.rc.model.PromiseOrderResponse;
import com.rc.model.QtyInPcs;
import com.rc.model.QtyPcsResponse;
import com.rc.model.QuestionResponse;
import com.rc.model.Questions;
import com.rc.model.RetailerDetailResponse;
import com.rc.model.RetailerDetailedData;
import com.rc.model.RetailerRawData;
import com.rc.model.RetailerRawDataResponse;
import com.rc.model.RouteRawDataResponse;
import com.rc.model.RouteResultExtracter;
import com.rc.model.Routeresponse;
import com.rc.model.ScheduleResponse;
import com.rc.model.Scheme;
import com.rc.model.SearchProductData;
import com.rc.model.SearchProductResponse;
import com.rc.model.Servey;
import com.rc.model.ServeyReport;
import com.rc.model.ServeyReportResponse;
import com.rc.model.ServeyResponse;
import com.rc.model.StockDetailedData;
import com.rc.model.StockResponse;
import com.rc.model.Target;
import com.rc.model.TargetResponse;
import com.rc.model.TeamActivityHistoryResponse;
import com.rc.model.VisitHistorys;
import com.rc.model.licenseNameRowMapper;

import exception.AccessDeniedException;
import static java.time.Clock.system;

/**
 *
 * @author Admin
 */
public class GetResourceDaoimpl implements GetResorcesDao {
	/*currentPage = 0;
	searchPaging.setActivePage(currentPage);
	
	currentPage = searchPaging.getActivePage();
	currentPage = (searchPaging.getPageSize() * currentPage);*/
	private final JdbcTemplate jdbcTemplate;

	public static final String GETMEMBERBYROLE = "Select * from tbl_member where role_id=? and distributor_id=? and isDelete='N'  order by employee_id desc";
	public static final	String GETLICENCEBYNAME = "select liscense_no from tbl_dstributor_liscense where liscense_id=?";

	public GetResourceDaoimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);// To change body of
		// generated methods, choose
													// Tools | Templates.
	}

	public GetResourceDaoimpl(DataSource dataSource, PlatformTransactionManager transactionManager) {
		jdbcTemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public CommonDetailResponse getAllRole() {
		List<CommonDetail> lstcommonDetail = new ArrayList<>();
		CommonDetailResponse commonDetailResponse = new CommonDetailResponse();
		try {
			String getRoleSql = "Select * from tbl_role where isDelete ='N' ORDER BY id DESC ";

			lstcommonDetail = jdbcTemplate.query(getRoleSql, new CommonRowMapper());
			if (!lstcommonDetail.isEmpty()) {
				commonDetailResponse.setLstCommnodetail(lstcommonDetail);
				commonDetailResponse.setMessage("Data Retrive successfully");
				commonDetailResponse.setStatus("success");
				commonDetailResponse.setStatusCode(0);
			} else {
				commonDetailResponse.setMessage(" No Data Retrive ");
				commonDetailResponse.setStatus("fail");
				commonDetailResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			commonDetailResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			commonDetailResponse.setStatus("fail");
			commonDetailResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return commonDetailResponse;

	}

	@Override
	public CommonDetailResponse getAllDesignation() {
		List<CommonDetail> lstcommonDetail = new ArrayList<>();
		CommonDetailResponse commonDetailResponse = new CommonDetailResponse();
		try {
			String getRoleSql = "Select * from tbl_designation where status=1  order by designation_id DESC ";

			lstcommonDetail = jdbcTemplate.query(getRoleSql, new DesignationRowMapper());
			if (!lstcommonDetail.isEmpty()) {
				commonDetailResponse.setLstCommnodetail(lstcommonDetail);
				commonDetailResponse.setMessage("Data Retrive successfully");
				commonDetailResponse.setStatus("success");
				commonDetailResponse.setStatusCode(0);
			} else {
				commonDetailResponse.setMessage(" No Data Retrive ");
				commonDetailResponse.setStatus("fail");
				commonDetailResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			commonDetailResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			commonDetailResponse.setStatus("fail");
			commonDetailResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return commonDetailResponse;

	}

	@Override
	public RetailerDetailResponse getAllretailerCat() {

		List<RetailerDetailedData> lstRetailerDetailedData = new ArrayList<>();
		RetailerDetailResponse retailerDetailedData = new RetailerDetailResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer_catagory where isDelete='N' order by id desc";

			lstRetailerDetailedData = jdbcTemplate.query(getRCatSql, new RetailerCatMapper());
			if (!lstRetailerDetailedData.isEmpty()) {
				retailerDetailedData.setLstRetailerDetailedData(lstRetailerDetailedData);
				retailerDetailedData.setMessage("Data Retrive successfully");
				retailerDetailedData.setStatus("success");
				retailerDetailedData.setStatusCode(0);
			} else {
				retailerDetailedData.setMessage(" No Data Retrive ");
				retailerDetailedData.setStatus("fail");
				retailerDetailedData.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerDetailedData.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerDetailedData.setStatus("fail");
			retailerDetailedData.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerDetailedData;

	}

	@Override
	public RetailerRawDataResponse getretailerByDist(GenericRequest genericRequest) {
		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {
			// String groupName = null;
			String getRCatSql = "Select * from tbl_retailer where distributor_id=? and blocked=1 and isgroup=1 and status=1 and isDeleted='N' order by retailer_id desc";
		
			//String RetailerSql = "SELECT tbl_retailer.`retailer_id`,tbl_retailer.`retailer_shop_name`,tbl_retailer.`contact_person_name`,tbl_retailer.`contact_person_name1`,tbl_retailer.`contact_person_name2`,tbl_retailer.`credit_days`,tbl_retailer.`email`,tbl_retailer.`mobile`,tbl_retailer.`alt_mobile1`,tbl_retailer.`alt_mobile2`,tbl_retailer.`landline`,tbl_retailer.`distributor_id`,tbl_retailer.`sr_id`,tbl_retailer.`tsm_id`,tbl_retailer.`asm_id`,tbl_retailer.`retailer_type_id`,tbl_retailer.`retailer_sub_type_id`,tbl_retailer.`retailer_catagory_id`,tbl_retailer.`group_id`,tbl_retailer.`isgroup`,tbl_retailer.`retailer_shop_image`,tbl_retailer.`latitude`,tbl_retailer.`longitude`,tbl_retailer.`verfied`,tbl_retailer.`defaulter`,tbl_retailer.`status`,tbl_retailer.`defaulter_date`,tbl_retailer.`blocked`,tbl_retailer.`defaulter_by_id`,tbl_retailer.`blocked_date`,tbl_retailer.`blocked_by_id`,tbl_retailer.`kyc_comleted`,tbl_retailer.`locality`,tbl_retailer.`street`,tbl_retailer.`city`,tbl_retailer.`state`,tbl_retailer.`pincode`,tbl_retailer.`zoneid`,tbl_retailer.`created_by_id`,tbl_retailer.`created_date`,tbl_retailer.`updated_by`,tbl_retailer.`updated_date`,tbl_retailer.`retailer_excise_code`,tbl_retailer.`manager_id`,tbl_retailer.`route_id`,tbl_retailer.`blocked_remarks`,tbl_retailer.`defalter_remarks`,tbl_retailer.`isDeleted` , a.`employee_name` AS sr_name,b.`employee_name` AS tsm_name,c.`employee_name` AS asm_name,d.`employee_name` AS createdByName, `tbl_retailer_type`.`retailer_type` AS retailerTypeName,tbl_retailer_sub_type.`retailer_sub_type` AS retailerSubTypeName,`tbl_state`.`state_name` AS stateName,`tbl_zone`.`tittle` AS zoneName,e.`employee_name` AS managerName,COALESCE(tbl_retailer.group_id,0) AS Groupid,nt.`retailer_shop_name` as groupName FROM`tbl_retailer` LEFT JOIN tbl_retailer AS nt ON COALESCE(tbl_retailer.group_id,0)=nt.retailer_id LEFT  JOIN tbl_member AS a ON tbl_retailer.`sr_id` = a.`employee_id` LEFT  JOIN tbl_member AS b ON tbl_retailer.`tsm_id` = b.`employee_id` LEFT  JOIN tbl_member AS c ON tbl_retailer.`asm_id` = c.employee_id LEFT  JOIN tbl_member AS d ON tbl_retailer.`created_by_id` = d.employee_id LEFT  JOIN tbl_member AS e  ON  tbl_retailer.`manager_id` = e.employee_id LEFT  JOIN `tbl_retailer_type` ON `tbl_retailer`.`retailer_type_id` = `tbl_retailer_type`.`id` LEFT  JOIN `tbl_retailer_sub_type` ON `tbl_retailer`.`retailer_sub_type_id` = `tbl_retailer_sub_type`.`id` LEFT  JOIN `tbl_state` ON `tbl_retailer`.`state` = `tbl_state`.`state_id` LEFT  JOIN `tbl_zone` ON `tbl_retailer`.`zoneid` = `tbl_zone`.`id`where tbl_retailer.distributor_id=? and tbl_retailer.blocked=1 and tbl_retailer.isgroup=1 and tbl_retailer.status=1 and tbl_retailer.isDeleted='N' order by tbl_retailer.retailer_id desc ";
		
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] {genericRequest.getDistributerId() }, new RetailerRawdataMapper());
			if (!lstRetailerRawData.isEmpty()) {
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);
			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	@Override
	public RetailerRawDataResponse getAllretailer(int srId) {
		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer where (sr_id=? or tsm_id=? or asm_id=? or manager_id=?)  and blocked=1 and status=1 and isDeleted='N' order by retailer_id desc";
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { srId, srId, srId, srId },
					new RetailerRawdataMapper());
			if (!lstRetailerRawData.isEmpty()) {
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);
			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	@Override
	public RetailerDetailResponse getAllretailertype() {
		List<RetailerDetailedData> lstRetailerDetailedData = new ArrayList<>();
		RetailerDetailResponse retailerDetailedData = new RetailerDetailResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer_type where isDelete='N' order by id desc ";

			lstRetailerDetailedData = jdbcTemplate.query(getRCatSql, new RetailerCatMapper());
			if (!lstRetailerDetailedData.isEmpty()) {
				retailerDetailedData.setLstRetailerDetailedData(lstRetailerDetailedData);
				retailerDetailedData.setMessage("Data Retrive successfully");
				retailerDetailedData.setStatus("success");
				retailerDetailedData.setStatusCode(0);
			} else {
				retailerDetailedData.setMessage(" No Data Retrive ");
				retailerDetailedData.setStatus("fail");
				retailerDetailedData.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerDetailedData.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerDetailedData.setStatus("fail");
			retailerDetailedData.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerDetailedData;

	}

	@Override
	public RetailerDetailResponse getAllretailerSubtype() {
		List<RetailerDetailedData> lstRetailerDetailedData = new ArrayList<>();
		RetailerDetailResponse retailerDetailedData = new RetailerDetailResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer_sub_type where isDelete='N' order by id desc ";

			lstRetailerDetailedData = jdbcTemplate.query(getRCatSql, new RetailerCatMapper());
			if (!lstRetailerDetailedData.isEmpty()) {
				retailerDetailedData.setLstRetailerDetailedData(lstRetailerDetailedData);
				retailerDetailedData.setMessage("Data Retrive successfully");
				retailerDetailedData.setStatus("success");
				retailerDetailedData.setStatusCode(0);
			} else {
				retailerDetailedData.setMessage(" No Data Retrive ");
				retailerDetailedData.setStatus("fail");
				retailerDetailedData.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerDetailedData.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerDetailedData.setStatus("fail");
			retailerDetailedData.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerDetailedData;

	}

	@Override
	public RetailerDetailResponse getZone() {

		List<RetailerDetailedData> lstRetailerDetailedData = new ArrayList<>();
		RetailerDetailResponse retailerDetailedData = new RetailerDetailResponse();
		try {
			String getRCatSql = "Select * from tbl_zone where isDelete='N' order by id desc";

			lstRetailerDetailedData = jdbcTemplate.query(getRCatSql, new RetailerCatMapper());
			if (!lstRetailerDetailedData.isEmpty()) {
				retailerDetailedData.setLstRetailerDetailedData(lstRetailerDetailedData);
				retailerDetailedData.setMessage("Data Retrive successfully");
				retailerDetailedData.setStatus("success");
				retailerDetailedData.setStatusCode(0);
			} else {
				retailerDetailedData.setMessage(" No Data Retrive ");
				retailerDetailedData.setStatus("fail");
				retailerDetailedData.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerDetailedData.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerDetailedData.setStatus("fail");
			retailerDetailedData.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerDetailedData;

	}

	@Override
	public MemberResponse getTeamByRole(GenericRequest genericRequestd) {

		List<Member> memberlst = new ArrayList<>();
		MemberResponse responseMember = new MemberResponse();
		try {

			// Map<Integer,Member> MemberMap=(Map<Integer,Member>)
			// Cachemanagerimpl.getCacheValueById(CacheConstant.MEMBER_LIST);
			// Member m = MemberMap.get(4);
			// System.out.println("m value ===>>"+m.getEmployeeName());

			List<Member> newMemberList = new ArrayList<>();
			memberlst = jdbcTemplate.query(GETMEMBERBYROLE,
					new Object[] { genericRequestd.getRoleId(), genericRequestd.getDistributerId() },
					new MemberRawMapper());
			if (!memberlst.isEmpty()) {
				newMemberList = setMemberData(memberlst);
				responseMember.setLstMemberResponse(newMemberList);
				responseMember.setMessage("Data Retrive successfully");
				responseMember.setStatus("success");
				responseMember.setStatusCode(0);

			} else {
				responseMember.setMessage(" No Data Retrive ");
				responseMember.setStatus("fail");
				responseMember.setStatusCode(1);
			}

		} catch (Exception e) {
			responseMember.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			responseMember.setStatus("fail");
			responseMember.setStatusCode(-1);
			e.printStackTrace();
		}

		return responseMember;

	}

	@Override
	public MemberResponse getAllMember(int distributerId) {

		List<Member> memberlst = new ArrayList<>();
		MemberResponse responseMember = new MemberResponse();
		try {
			System.out.println("distributerId" + distributerId);
			// Map<Integer,Member> MemberMap=(Map<Integer,Member>)
			// Cachemanagerimpl.getCacheValueById(CacheConstant.MEMBER_LIST);
			// Member m = MemberMap.get(4);
			// System.out.println("m value ===>>"+m.getEmployeeName());

			String getMember = "Select * from tbl_member where distributor_id=? and isDelete='N' order by employee_id desc";
			List<Member> newMemberList = new ArrayList<>();
			memberlst = jdbcTemplate.query(getMember, new Object[] { distributerId }, new MemberRawMapper());
			if (!memberlst.isEmpty()) {
				newMemberList = setMemberData(memberlst);
				responseMember.setLstMemberResponse(newMemberList);
				responseMember.setMessage("Data Retrive successfully");
				responseMember.setStatus("success");
				responseMember.setStatusCode(0);
			} else {
				responseMember.setMessage(" No Data Retrive ");
				responseMember.setStatus("fail");
				responseMember.setStatusCode(1);
			}

		} catch (Exception e) {
			responseMember.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			responseMember.setStatus("fail");
			responseMember.setStatusCode(-1);
			e.printStackTrace();
		}

		return responseMember;

	}

	@Override
	public AssetByRetailerResponse getAssetByRetailerId(GenericRequest genericRequest) {
		AssetByRetailerResponse assetByRetailerResponse = new AssetByRetailerResponse();

		List<GetAsset> lstAsset = new ArrayList<>();
		List<GetAsset> newAssetlst = new ArrayList<>();

		List<AssetByRetailerData> list = new ArrayList<>();

		try {

			StringBuilder type_sql = new StringBuilder();
			if (genericRequest.getOperationtype() == null) {
				type_sql.append("SELECT * FROM tbl_asset_assign");
				if (genericRequest.getId() != 0) {
					type_sql.append(" where retailer_id = " + genericRequest.getId() + "");

				}

				type_sql.append(" order by id desc");
			}

			if (genericRequest.getOperationtype() != null
					&& genericRequest.getOperationtype().equalsIgnoreCase("audit")) {
				type_sql.append("SELECT * FROM tbl_asset_audit");

				if (genericRequest.getId() != 0) {
					type_sql.append(" where retailer_id = " + genericRequest.getId() + "");

				}
				if (genericRequest.getActivityId() != 0) {
					type_sql.append(" where id = " + genericRequest.getActivityId() + "");

				}
				type_sql.append(" order by id desc");
			}

			lstAsset = jdbcTemplate.query(type_sql.toString(), new GetAssetRowMapper());

			if (!lstAsset.isEmpty()) {
				for (GetAsset asst : lstAsset) {
					List<AssetByRetailerData> lstAssetDetail = new ArrayList<>();
					list = jdbcTemplate.query("SELECT * FROM `tbl_assets` WHERE `id` =? ",
							new Object[] { asst.getAssetId() }, new AssetByRetailerMapper());
					String retailerName = jdbcTemplate.query(
							"SELECT `retailer_shop_name` FROM `tbl_retailer` WHERE `retailer_id` =?",
							new Object[] { asst.getRetailerId() }, new GetEmpNameResultExtracter());
					String name[] = jdbcTemplate.query(
							"SELECT  a.employee_name , c.title FROM tbl_member AS a  , tbl_feedback_rating AS c WHERE a.employee_id = ?  AND c.id = ? ",
							new Object[] { asst.getAuditById(), asst.getFeedbackRatingId() },
							new TwoValueresultExtracter());
					String assetSql = "SELECT a.brand_name,b.product_name From tbl_brand as a,tbl_product as b  WHERE a.brand_id=?  and b.product_id=?";
					/*
					 * String srName = jdbcTemplate.
					 * query("SELECT `employee_name` FROM `tbl_member` WHERE `employee_id` =?"
					 * , new Object[] { asst.getRetailerId()}, new
					 * GetEmpNameResultExtracter());
					 */
					asst.setAuditByName(name[0]);
					// asst.setSrName(name[2]);
					asst.setFeedbackRatingName(name[1]);
					asst.setRetailerName(retailerName);
					for (AssetByRetailerData assetByRetailerData : list) {
						AssetByRetailerData idByAsset = jdbcTemplate.query(assetSql,
								new Object[] { assetByRetailerData.getBrandId(), assetByRetailerData.getProductId() },
								new AssetRawDataMapper());
						assetByRetailerData.setBrandName(idByAsset.getBrandName());
						assetByRetailerData.setProductName(idByAsset.getProductName());
						lstAssetDetail.add(assetByRetailerData);
					}
					asst.setLstAssetDetail(lstAssetDetail);
					newAssetlst.add(asst);

				}

				/*
				 * if (!list.isEmpty()) { for (AssetByRetailerData
				 * assetByRetailerData : list) { AssetByRetailerData
				 * newAssetByRetailerData = new AssetByRetailerData(); String
				 * getnamebySQl =
				 * "SELECT a.brand_name,b.product_name , c.qty ,c.amount,c.id from tbl_brand as a , tbl_product as b  , tbl_asset_assign as c where a.brand_id=? and b.product_id=? and c.id =?"
				 * ; String[] name = jdbcTemplate.query(getnamebySQl, new
				 * Object[] { assetByRetailerData.getBrandId() != 0 ?
				 * assetByRetailerData.getBrandId() : null,
				 * assetByRetailerData.getProductId() != 0 ?
				 * assetByRetailerData.getProductId() : null,
				 * assetByRetailerData.getId() != 0 ?
				 * assetByRetailerData.getId() : null }, new
				 * getNameByAssetRawid());
				 * newAssetByRetailerData.setBrandName(name[1]);
				 * newAssetByRetailerData.setProductName(name[2]);
				 * 
				 * newAssetByRetailerData.setAssetName(assetByRetailerData.
				 * getAssetName());
				 * newAssetByRetailerData.setAssetType(assetByRetailerData.
				 * getAssetType());
				 * newAssetByRetailerData.setBrandId(assetByRetailerData.
				 * getBrandId());
				 * newAssetByRetailerData.setCreateDate(assetByRetailerData.
				 * getCreateDate());
				 * newAssetByRetailerData.setDescription(assetByRetailerData.
				 * getDescription()); if (name[3] != null) {
				 * newAssetByRetailerData.setQuantity(Integer.parseInt(name[3]))
				 * ; } if (name[5] != null) {
				 * newAssetByRetailerData.setAssignId(Integer.parseInt(name[5]))
				 * ; } newAssetByRetailerData.setLastUpdate(assetByRetailerData.
				 * getLastUpdate()); newAssetByRetailerData.setAmount(name[4]);
				 * 
				 * newListAssetByRetailerData.add(newAssetByRetailerData);
				 * 
				 * }
				 */ assetByRetailerResponse.setListAsset(newAssetlst);
				assetByRetailerResponse.setMessage("Data Retrive successfully");
				assetByRetailerResponse.setStatus("success");
				assetByRetailerResponse.setStatusCode(0);
			} else {
				assetByRetailerResponse.setMessage("Couldn't retrive data");
				assetByRetailerResponse.setStatus("fail");
				assetByRetailerResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			assetByRetailerResponse.setMessage("Data Couldn't retrive application server error");
			assetByRetailerResponse.setStatus("fail");
			assetByRetailerResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return assetByRetailerResponse;
	}

	@Override
	public RetailerRawDataResponse getAllGroupr(int ditriId) {
		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer where isgroup=2 and distributor_id=? and status =1 order by retailer_id desc";
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { ditriId }, new RetailerRawdataMapper());
			if (!lstRetailerRawData.isEmpty()) {
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);

			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	@Override
	public MemberResponse getTeamById(int employeeId) {

		List<Member> memberlst = new ArrayList<>();
		MemberResponse responseMember = new MemberResponse();
		try {

			String getMember = "Select * from tbl_member where employee_id=? and isDelete='N' order by employee_id desc";
			List<Member> newMemberList = new ArrayList<>();
			memberlst = jdbcTemplate.query(getMember, new Object[] { employeeId }, new MemberRawMapper());
			if (!memberlst.isEmpty()) {
				newMemberList = setMemberData(memberlst);
				responseMember.setLstMemberResponse(newMemberList);
				responseMember.setMessage("Data Retrive successfully");
				responseMember.setStatus("success");
				responseMember.setStatusCode(0);
			} else {
				responseMember.setMessage(" No Data Retrive ");
				responseMember.setStatus("fail");
				responseMember.setStatusCode(1);
			}

		} catch (Exception e) {
			responseMember.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			responseMember.setStatus("fail");
			responseMember.setStatusCode(-1);
			e.printStackTrace();
		}

		return responseMember;

	}

	@Override
	public RetailerRawDataResponse getRetailerByRetailerId(int retailerId) {
		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer where retailer_id =? and blocked=1 and isDeleted='N' and status =1  order by retailer_id desc";
			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { retailerId },
					new RetailerRawdataMapper());
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			if (!lstRetailerRawData.isEmpty()) {

				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);
			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	public List<RetailerRawData> setRetailerData(List<RetailerRawData> lstRetailerRawData) {

		List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();

		newlstRetailerRawData = new ArrayList<>();
		String getNamebyIdSql = "SELECT a.retailer_catagory  ,b.retailer_type ,c.retailer_sub_type,  e.distributor_name  from tbl_retailer_catagory as a , tbl_retailer_type as b , tbl_retailer_sub_type as c  , tbl_distributor as e  where a.id=? and b.id=? and c.id=?  and e.distributor_id=?";
		String getSrNAme = "SELECT a.employee_name from tbl_member as a , tbl_role as b where a.employee_id=? and b.role_name ='SR' ";
		String getGroupName = "SELECT retailer_shop_name FROM tbl_retailer WHERE retailer_id=?";
		String getManagerName = "SELECT a.employee_name from tbl_member as a , tbl_role as b where a.employee_id=? and b.role_name ='manager' ";
		String getAsmName = "SELECT a.employee_name from tbl_member as a , tbl_role as b where a.employee_id=? and b.role_name ='ASM' ";
		String getTSMName = "SELECT a.employee_name from tbl_member as a , tbl_role as b where a.employee_id=? and b.role_name ='TSM' ";
		String getzoneName = "SELECT tittle FROM tbl_zone WHERE id=?";
		String getstateName = "SELECT state_name FROM tbl_state  WHERE state_id=?";
		String getRetailerCount = "select count(retailer_id) from tbl_retailer where group_id =? and blocked=1 and isDeleted='N'";

		for (RetailerRawData retailerRawData : lstRetailerRawData) {
			String groupName = null;
			String zoneName = null;
			String stateName = null;
			System.out.println("Retailer Id  ====>" + retailerRawData.getRetailerId());
			GetNameRawWrapper getNameRawWrapper = jdbcTemplate.query(getNamebyIdSql, new Object[] {
					(retailerRawData.getCatogoryId() != 0 ? retailerRawData.getCatogoryId() : "'isnull'"),
					(retailerRawData.getTypeId() != 0 ? retailerRawData.getTypeId() : "'isnull'"),
					(retailerRawData.getSubTypeId() != 0 ? retailerRawData.getSubTypeId() : "'isnull'"),
					(retailerRawData.getDistributerId() != 0 ? retailerRawData.getDistributerId() : "'isnull'"), },
					new GetNameresultExtracter());
			String asmName = jdbcTemplate.query(getAsmName,
					new Object[] { retailerRawData.getAsmId() != 0 ? retailerRawData.getAsmId() : 0 },
					new GetEmpNameResultExtracter());
			String managerName = jdbcTemplate.query(getManagerName,
					new Object[] { retailerRawData.getManagerId() != 0 ? retailerRawData.getManagerId() : 0 },
					new GetEmpNameResultExtracter());
			String tsmName = jdbcTemplate.query(getTSMName,
					new Object[] { retailerRawData.getTsmId() != 0 ? retailerRawData.getTsmId() : 0 },
					new GetEmpNameResultExtracter());

			String srName = jdbcTemplate.query(getSrNAme,
					new Object[] { retailerRawData.getSrId() != 0 ? retailerRawData.getSrId() : 0 },
					new GetEmpNameResultExtracter());
			String StateName = jdbcTemplate.query("select state_name from tbl_state where 	state_id =? ",
					new Object[] { retailerRawData.getState()},
					new GetEmpNameResultExtracter());

			zoneName = jdbcTemplate.query(getzoneName,
					new Object[] { retailerRawData.getZoneId() != 0 ? retailerRawData.getZoneId() : 0 },
					new GetEmpNameResultExtracter());
			stateName = jdbcTemplate
					.query(getstateName,
							new Object[] { Integer.parseInt(retailerRawData.getState()) != 0
									? Integer.parseInt(retailerRawData.getState()) : 0 },
							new GetEmpNameResultExtracter());

			System.out.println("name by id ====>" + getNameRawWrapper);
			System.out.println(" catogory Id Id's===>" + retailerRawData.getCatogoryId());
			System.out.println(" type Id's===>" + retailerRawData.getTypeId());
			System.out.println(" group  Id's===>" + retailerRawData.getGroupId());
			System.out.println("sub type Id's===>" + retailerRawData.getSubTypeId());
			if (retailerRawData.getGroupId() != 0) {
				groupName = jdbcTemplate.query(getGroupName, new Object[] { retailerRawData.getGroupId() },
						new GetEmpNameResultExtracter());

			}

			RetailerRawData newRetailerRawData = new RetailerRawData();
			if (retailerRawData.getIsGroup() != 0 && retailerRawData.getIsGroup() == 2) {

				String retailerCount = jdbcTemplate.query(getRetailerCount,
						new Object[] { retailerRawData.getRetailerId() }, new GetEmpNameResultExtracter());
				if (retailerCount != null) {
					newRetailerRawData.setCount(Integer.parseInt(retailerCount));
				}
			}

			newRetailerRawData.setGroupId(retailerRawData.getGroupId());
			newRetailerRawData.setBlock(retailerRawData.getBlock());
			newRetailerRawData.setState(stateName);
			newRetailerRawData.setZoneName(zoneName);
			newRetailerRawData.setCity(retailerRawData.getCity());
			newRetailerRawData.setDistributerName(getNameRawWrapper.getDistributerName());
			newRetailerRawData.setSrName(srName);
			newRetailerRawData.setAsmName(asmName);
			newRetailerRawData.setManagerName(managerName);
			newRetailerRawData.setTsmName(tsmName);
			newRetailerRawData.setRetailerId(retailerRawData.getRetailerId());
			newRetailerRawData.setRetailerName(retailerRawData.getRetailerName());
			newRetailerRawData.setAddress(retailerRawData.getAddress());
			newRetailerRawData.setImage(retailerRawData.getImage());
			System.out.println("catgory Name ==>>>" + getNameRawWrapper.getCatname());
			System.out.println("group Name ==>>>" + getNameRawWrapper.getGroupName());
			newRetailerRawData.setCatogoryId(retailerRawData.getCatogoryId());
			newRetailerRawData.setTypeId(retailerRawData.getTypeId());
			newRetailerRawData.setSubTypeId(retailerRawData.getSubTypeId());
			newRetailerRawData.setVerified(retailerRawData.getVerified());
			newRetailerRawData.setIsScheme(retailerRawData.getIsScheme());
			newRetailerRawData.setZoneId(retailerRawData.getZoneId());
			newRetailerRawData.setExciseCode(retailerRawData.getExciseCode());
			newRetailerRawData.setState(StateName);
			newRetailerRawData.setOutStanding(retailerRawData.getOutStanding());
			newRetailerRawData.setDistributerId(retailerRawData.getDistributerId());
			newRetailerRawData.setCatogoryName(getNameRawWrapper.getCatname());
			newRetailerRawData.setGroupName(groupName);
			newRetailerRawData.setTypeName(getNameRawWrapper.getTypeName());
			newRetailerRawData.setSubTypeName(getNameRawWrapper.getSubTypename());
			newRetailerRawData.setIsGroup(retailerRawData.getIsGroup());
			newRetailerRawData.setMobile(retailerRawData.getMobile());
			newRetailerRawData.setLattitude(retailerRawData.getLattitude());
			newRetailerRawData.setLongitude(retailerRawData.getLongitude());
			newRetailerRawData.setIsDefaulter(retailerRawData.getIsDefaulter());
			newRetailerRawData.setSrId(retailerRawData.getSrId());
			newRetailerRawData.setLocality(retailerRawData.getLocality());
			newRetailerRawData.setStreet(retailerRawData.getStreet());
			newRetailerRawData.setContactPersonName(retailerRawData.getContactPersonName());
			newRetailerRawData.setContactPersonName1(retailerRawData.getContactPersonName1());
			newRetailerRawData.setContactPersonName2(retailerRawData.getContactPersonName2());
			newRetailerRawData.setAlternatemobile1(retailerRawData.getAlternatemobile1());
			newRetailerRawData.setAlternatemobile2(retailerRawData.getAlternatemobile2());
			newRetailerRawData.setCreditDays(retailerRawData.getCreditDays());
			newRetailerRawData.setManagerId(retailerRawData.getManagerId());
			newRetailerRawData.setAsmId(retailerRawData.getAsmId());
			newRetailerRawData.setTsmId(retailerRawData.getTsmId());
			newRetailerRawData.setStatus(retailerRawData.getStatus());
			newRetailerRawData.setLandline(retailerRawData.getLandline());
			newRetailerRawData.setEmail(retailerRawData.getEmail());
			newRetailerRawData.setPincode(retailerRawData.getPincode());
			newRetailerRawData.setCreateDate(retailerRawData.getCreateDate());
			newRetailerRawData.setRouteId(retailerRawData.getRouteId());
			newRetailerRawData.setNote(retailerRawData.getNote());
			newlstRetailerRawData.add(newRetailerRawData);
		}

		return newlstRetailerRawData;

	}

	@Override
	public CommonDetailResponse getAllState() {
		List<CommonDetail> lstcommonDetail = new ArrayList<>();
		CommonDetailResponse commonDetailResponse = new CommonDetailResponse();
		try {
			String getStateSql = "Select * from tbl_state ORDER BY state_id DESC ";

			lstcommonDetail = jdbcTemplate.query(getStateSql, new getStateNameExtracter());
			if (lstcommonDetail != null) {
				commonDetailResponse.setLstCommnodetail(lstcommonDetail);
				commonDetailResponse.setMessage("Data Retrive successfully");
				commonDetailResponse.setStatus("success");
				commonDetailResponse.setStatusCode(0);
			} else {
				commonDetailResponse.setMessage(" No Data Retrive ");
				commonDetailResponse.setStatus("fail");
				commonDetailResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			commonDetailResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			commonDetailResponse.setStatus("fail");
			commonDetailResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return commonDetailResponse;

	}

	@Override
	public MemberResponse getAsmListForProfile(GenericRequest genericRequest) {

		List<Member> memberlst = new ArrayList<>();
		MemberResponse responseMember = new MemberResponse();
		try {

			// Map<Integer,Member> MemberMap=(Map<Integer,Member>)
			// Cachemanagerimpl.getCacheValueById(CacheConstant.MEMBER_LIST);
			// Member m = MemberMap.get(4);
			// System.out.println("m value ===>>"+m.getEmployeeName());

			StringBuilder getMember = new StringBuilder("Select * from tbl_member where ");
			if (genericRequest.getRoleId() == 37) {
				getMember.append("manager_id=? and isDelete='N'");
			}
			if (genericRequest.getRoleId() == 38) {
				getMember.append("asm_id=? and isDelete='N'");
			}
			if (genericRequest.getRoleId() == 39) {
				getMember.append("tsm_id=? and isDelete='N'");
			}
			getMember.append(" order by employee_id desc");

			String query = new String(getMember);
			System.out.println("query===>" + query);

			List<Member> newMemberList = new ArrayList<>();
			memberlst = jdbcTemplate.query(query, new Object[] { genericRequest.getId() }, new MemberRawMapper());
			if (!memberlst.isEmpty()) {
				newMemberList = setMemberData(memberlst);
				responseMember.setLstMemberResponse(newMemberList);
				responseMember.setMessage("Data Retrive successfully");
				responseMember.setStatus("success");
				responseMember.setStatusCode(0);

			} else {
				responseMember.setMessage(" No Data Retrive ");
				responseMember.setStatus("fail");
				responseMember.setStatusCode(1);
			}

		} catch (Exception e) {
			responseMember.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			responseMember.setStatus("fail");
			responseMember.setStatusCode(-1);
			e.printStackTrace();
		}

		return responseMember;

	}

	public List<Member> setMemberData(List<Member> memberlst) {

		List<Member> newMemberList = new ArrayList<>();

		String getNamebyIdSql = "SELECT a.role_name ,b.distributor_name,c.designation_name,d.state_name  from tbl_role as a , tbl_distributor as b ,tbl_designation as c,tbl_state as d  where a.id=? and b.distributor_id=? and c.designation_id=? and d.state_id=?";
		String getManagerName = "SELECT a.employee_name from tbl_member as a , tbl_role as b where a.employee_id=? and b.role_name ='manager' ";
		String getAsmName = "SELECT a.employee_name from tbl_member as a , tbl_role as b where a.employee_id=? and b.role_name ='ASM' ";
		String getTSMName = "SELECT a.employee_name from tbl_member as a , tbl_role as b where a.employee_id=? and b.role_name ='TSM' ";
		String getRetailerCount = "select count(retailer_id) from tbl_retailer where (tsm_id=? or asm_id=? or manager_id=? and distributor_id=? or sr_id=?) and blocked=1";

		String getreportToName = "SELECT employee_name from tbl_member  where employee_id=? ";

		for (Member member : memberlst) {
			Member nameMember = jdbcTemplate.query(getNamebyIdSql,
					new Object[] { member.getRoleId() != 0 ? member.getRoleId() : 0,
							member.getDistributerId() != 0 ? member.getDistributerId() : 0,
							member.getDesignationId() != 0 ? member.getDesignationId() : null,
							member.getState() != null ? member.getState() : null },
					new GetMemberRawdataMapper());
			String asmName = jdbcTemplate.query(getAsmName,
					new Object[] { member.getAsmId() != 0 ? member.getAsmId() : 0 }, new GetEmpNameResultExtracter());
			String managerName = jdbcTemplate.query(getManagerName,
					new Object[] { member.getManagerId() != 0 ? member.getManagerId() : 0 },
					new GetEmpNameResultExtracter());
			String tsmName = jdbcTemplate.query(getTSMName,
					new Object[] { member.getTsmId() != 0 ? member.getTsmId() : 0 }, new GetEmpNameResultExtracter());
			String ReportToName = jdbcTemplate.query(getreportToName,
					new Object[] { member.getReportTo() != 0 ? member.getReportTo() : 0 },
					new GetEmpNameResultExtracter());

			String retailerCount = jdbcTemplate.query(getRetailerCount,
					new Object[] { member.getEmployeeId() != 0 ? member.getEmployeeId() : null,
							member.getEmployeeId() != 0 ? member.getEmployeeId() : null,
							member.getEmployeeId() != 0 ? member.getEmployeeId() : null,
							member.getDistributerId() != 0 ? member.getDistributerId() : null,
							member.getEmployeeId() != 0 ? member.getEmployeeId() : null },
					new GetEmpNameResultExtracter());
			System.out.println("retailerCount====>" + retailerCount);
			System.out.println("name by id ====>" + nameMember);
			Member newMember = new Member();
			newMember.setStatus(member.getStatus());
			newMember.setRetailerCount(Integer.parseInt(retailerCount));
			newMember.setAdddress(member.getAdddress());
			newMember.setAlternatemobileNumber(member.getAlternatemobileNumber());
			newMember.setAsmId(member.getAsmId());
			newMember.setCretedById(member.getCretedById());
			newMember.setDesignationId(member.getDesignationId());
			newMember.setDistributerId(member.getDistributerId());
			newMember.setDistributerName(nameMember.getDistributerName());
			newMember.setEmailId(member.getEmailId());
			newMember.setEmpId(member.getEmpId());
			if (member.getState() != null) {
				newMember.setStateId(Integer.parseInt(member.getState()));
			}
			newMember.setEmployeeId(member.getEmployeeId());
			newMember.setEmployeeName(member.getEmployeeName());
			newMember.setJoiningDate(member.getJoiningDate());
			newMember.setManagerId(member.getManagerId());
			newMember.setMobile(member.getMobile());
			newMember.setReportTo(member.getReportTo());
			newMember.setRoleId(member.getRoleId());
			newMember.setRoleName(nameMember.getRoleName());
			newMember.setState(nameMember.getState());
			newMember.setTsmId(member.getTsmId());
			newMember.setAsmName(asmName);
			newMember.setTsmName(tsmName);
			newMember.setManagerName(managerName);
			newMember.setDesignationname(nameMember.getDesignationname());
			newMember.setReportToName(member.getReportTo() == -5 ? member.getEmployeeName() : ReportToName);
			newMember.setProfilePic(member.getProfilePic());

			newMemberList.add(newMember);
		}

		return newMemberList;

	}

	@Override
	public LoginResponse validateCredentials(LoginDetail loginDetail) throws AccessDeniedException {
		LoginResponse loginResponse = new LoginResponse();
		try {

			String validateQuery = "Select * from tbl_user_login where email_id=? and password=?";

			loginResponse = jdbcTemplate.query(validateQuery,
					new Object[] { loginDetail.getEmailId(), loginDetail.getPassword() }, new LoginMapper());
			System.out.println("result====>" + loginResponse.getStatus());
			System.out.println("employeeid====>" + loginResponse.getEmployeeId());

			if (loginResponse.getStatus().equalsIgnoreCase("success")) {
				String findUserDataQuery = "Select distributor_id from tbl_member where employee_id=?";
				String distributerId = jdbcTemplate.query(findUserDataQuery,
						new Object[] { loginResponse.getEmployeeId() }, new GetEmpNameResultExtracter());
				loginResponse.setDistributerId(Integer.parseInt(distributerId));
				String updateTableSQL = "UPDATE tbl_user_login SET last_login =? WHERE employee_id = "
						+ loginResponse.getEmployeeId() + " ";

				// userResponse = jdbcTemplate.query(findUserDataQuery, new
				// Object[]{id}, new UserResponseMapper());
				jdbcTemplate.update(updateTableSQL, new Object[] { new Timestamp(System.currentTimeMillis()) });

			}

		} catch (Exception e) {
			e.printStackTrace();
			loginResponse.setMessage("Couldn't Login application server error");
			loginResponse.setStatus("fail");
			loginResponse.setStatusCode(-1);

		}
		return loginResponse;
	}

	@Override
	public RetailerRawDataResponse getRetailerByGroupId(int id) {

		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {

			String getRCatSql = "Select * from tbl_retailer where group_id=? and blocked=1 and status=1 and isDeleted='N' order by retailer_id desc";
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { id }, new RetailerRawdataMapper());
			if (!lstRetailerRawData.isEmpty()) {
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);

			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	@Override
	public RetailerRawDataResponse getBlockedretailer(int distributerId) {

		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer where blocked=2 and distributor_id=?  and status=1 and isDeleted='N'  order by retailer_id desc";
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { distributerId },
					new RetailerRawdataMapper());
			if (!lstRetailerRawData.isEmpty()) {
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);
			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	@Override
	public Routeresponse getAllRoute(int distributerId) {
		System.out.println("inside get All route");
		List<RouteRawDataResponse> lstroute = new ArrayList<>();
		List<RouteRawDataResponse> newlstroute = new ArrayList<>();
		Routeresponse routeresponse = new Routeresponse();
		try {
			String getStateSql = "Select * from tbl_route_managment where distributor_id=? ORDER BY route_id DESC ";

			lstroute = jdbcTemplate.query(getStateSql, new Object[] { distributerId }, new RouteResultExtracter());
			System.out.println("size of route list==>" + lstroute.size());

			if (lstroute != null) {
				newlstroute = setRouteData(lstroute);

				/*
				 * for (RouteRawDataResponse route : lstroute) {
				 * 
				 * List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
				 * try { String getRCatSql =
				 * "Select * from tbl_retailer where route_id=? and blocked=1 order by retailer_id desc"
				 * ; lstRetailerRawData = jdbcTemplate.query(getRCatSql, new
				 * Object[] { route.getRouteId() }, new
				 * RetailerRawdataMapper()); newlstRetailerRawData =
				 * setRetailerData(lstRetailerRawData);
				 * route.setLstretailer(newlstRetailerRawData); String getMember
				 * =
				 * "Select * from tbl_member where employee_id=? order by employee_id desc"
				 * ; List<Member> memberlst = new ArrayList<>(); List<Member>
				 * newMemberList = new ArrayList<>(); memberlst =
				 * jdbcTemplate.query(getMember, new Object[] { route.getSrId()
				 * }, new MemberRawMapper()); if (!memberlst.isEmpty()) {
				 * newMemberList = setMemberData(memberlst);
				 * route.setEmployeeDetail(newMemberList); }
				 * 
				 * newlstroute.add(route); } catch (Exception e) {
				 * 
				 * e.printStackTrace(); }
				 */
				routeresponse.setRoutelist(newlstroute);
				routeresponse.setMessage("Data Retrive successfully");
				routeresponse.setStatus("success");
				routeresponse.setStatusCode(0);

			} else {
				routeresponse.setMessage(" No Data Retrive ");
				routeresponse.setStatus("fail");
				routeresponse.setStatusCode(1);
			}

		} catch (Exception e) {
			routeresponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			routeresponse.setStatus("fail");
			routeresponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return routeresponse;

	}

	public List<RouteRawDataResponse> setRouteData(List<RouteRawDataResponse> lstroute) {
		List<RouteRawDataResponse> newlstroute = new ArrayList<>();
		String getRCatSql = "Select * from tbl_retailer where route_id=? and blocked=1 and isDeleted='N' order by retailer_id desc";
		String getMember = "Select * from tbl_member where employee_id=? and isDelete='N' order by employee_id desc";

		for (RouteRawDataResponse route : lstroute) {
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
			try {
				lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { route.getRouteId() },
						new RetailerRawdataMapper());
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				route.setLstretailer(newlstRetailerRawData);
				List<Member> memberlst = new ArrayList<>();
				List<Member> newMemberList = new ArrayList<>();
				memberlst = jdbcTemplate.query(getMember, new Object[] { route.getSrId() }, new MemberRawMapper());
				if (!memberlst.isEmpty()) {
					newMemberList = setMemberData(memberlst);
					route.setEmployeeDetail(newMemberList);
				}

				newlstroute.add(route);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newlstroute;
	}

	@Override
	public DistributerResponse getAllDistributer() {
		List<Distributer> lstDistributerRowMapper = new ArrayList<>();
		List<Distributer> newlstDistributerRowMapper = new ArrayList<>();

		DistributerResponse distributerResponse = new DistributerResponse();
		List<GenericRequest> lstlicenseName = new ArrayList<>();

		try {
			String sql = "Select * from tbl_distributor ORDER BY distributor_id DESC ";
			String stateSql = "SELECT state_name FROM tbl_state WHERE state_id=? ";
			String getlicense = "select * from tbl_dstributor_liscense where distributor_id=? and state_id=?";
			lstDistributerRowMapper = jdbcTemplate.query(sql, new DistributerRowMapper());
			if (lstDistributerRowMapper != null) {
				for (Distributer distributer : lstDistributerRowMapper) {
					String stateName = null;
					lstlicenseName = new ArrayList<>();
					if (distributer.getStateId() != 0) {
						stateName = jdbcTemplate.query(stateSql, new Object[] { distributer.getStateId() },
								new GetEmpNameResultExtracter());
						distributer.setStateName(stateName);
					}
					if (distributer.getStateId() != 0 && distributer.getDistributerId() != 0) {
						lstlicenseName = jdbcTemplate.query(getlicense,
								new Object[] { distributer.getDistributerId(), distributer.getStateId() },
								new licenseNameRowMapper());
						distributer.setLicense(lstlicenseName);
					}

					newlstDistributerRowMapper.add(distributer);
				}

				distributerResponse.setLstDistributer(newlstDistributerRowMapper);
				distributerResponse.setMessage("Data Retrive successfully");
				distributerResponse.setStatus("success");
				distributerResponse.setStatusCode(0);
			} else {
				distributerResponse.setMessage(" No Data Retrive ");
				distributerResponse.setStatus("fail");
				distributerResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			distributerResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			distributerResponse.setStatus("fail");
			distributerResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return distributerResponse;

	}

	@Override
	public ProductRawdataResponse getPackagetype() {
		List<ProductRawdata> lstProductRawdata = new ArrayList<>();
		ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();

		try {
			String sql = "Select * from tbl_pacakage_type ORDER BY id DESC ";

			lstProductRawdata = jdbcTemplate.query(sql, new ProductRowDataRowMapper());
			if (lstProductRawdata != null) {
				productRawdataResponse.setLstProductRawdata(lstProductRawdata);
				productRawdataResponse.setMessage("Data Retrive successfully");
				productRawdataResponse.setStatus("success");
				productRawdataResponse.setStatusCode(0);
			} else {
				productRawdataResponse.setMessage(" No Data Retrive ");
				productRawdataResponse.setStatus("fail");
				productRawdataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productRawdataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productRawdataResponse.setStatus("fail");
			productRawdataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productRawdataResponse;

	}

	@Override
	public ProductRawdataResponse getProductCategory() {
		List<ProductRawdata> lstProductRawdata = new ArrayList<>();
		ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();

		try {
			String sql = "Select * from tbl_product_catagory ORDER BY id DESC ";

			lstProductRawdata = jdbcTemplate.query(sql, new ProductRowDataRowMapper());
			if (lstProductRawdata != null) {
				productRawdataResponse.setLstProductRawdata(lstProductRawdata);
				productRawdataResponse.setMessage("Data Retrive successfully");
				productRawdataResponse.setStatus("success");
				productRawdataResponse.setStatusCode(0);
			} else {
				productRawdataResponse.setMessage(" No Data Retrive ");
				productRawdataResponse.setStatus("fail");
				productRawdataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productRawdataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productRawdataResponse.setStatus("fail");
			productRawdataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productRawdataResponse;

	}

	@Override
	public ProductRawdataResponse getProductSegment() {
		List<ProductRawdata> lstProductRawdata = new ArrayList<>();
		ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();

		try {
			String sql = "Select * from tbl_product_segment ORDER BY id DESC ";

			lstProductRawdata = jdbcTemplate.query(sql, new ProductRowDataRowMapper());
			if (lstProductRawdata != null) {
				productRawdataResponse.setLstProductRawdata(lstProductRawdata);
				productRawdataResponse.setMessage("Data Retrive successfully");
				productRawdataResponse.setStatus("success");
				productRawdataResponse.setStatusCode(0);
			} else {
				productRawdataResponse.setMessage(" No Data Retrive ");
				productRawdataResponse.setStatus("fail");
				productRawdataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productRawdataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productRawdataResponse.setStatus("fail");
			productRawdataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productRawdataResponse;

	}

	@Override
	public ProductRawdataResponse getProductSubType() {
		List<ProductRawdata> lstProductRawdata = new ArrayList<>();
		ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();

		try {
			String sql = "Select * from tbl_product_sub_type ORDER BY id DESC ";

			lstProductRawdata = jdbcTemplate.query(sql, new ProductRowDataRowMapper());
			if (lstProductRawdata != null) {
				productRawdataResponse.setLstProductRawdata(lstProductRawdata);
				productRawdataResponse.setMessage("Data Retrive successfully");
				productRawdataResponse.setStatus("success");
				productRawdataResponse.setStatusCode(0);
			} else {
				productRawdataResponse.setMessage(" No Data Retrive ");
				productRawdataResponse.setStatus("fail");
				productRawdataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productRawdataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productRawdataResponse.setStatus("fail");
			productRawdataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productRawdataResponse;

	}

	@Override
	public ProductRawdataResponse getProductType() {
		List<ProductRawdata> lstProductRawdata = new ArrayList<>();
		ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();

		try {
			String sql = "Select * from tbl_product_type ORDER BY id DESC ";

			lstProductRawdata = jdbcTemplate.query(sql, new ProductRowDataRowMapper());
			if (lstProductRawdata != null) {
				productRawdataResponse.setLstProductRawdata(lstProductRawdata);
				productRawdataResponse.setMessage("Data Retrive successfully");
				productRawdataResponse.setStatus("success");
				productRawdataResponse.setStatusCode(0);
			} else {
				productRawdataResponse.setMessage(" No Data Retrive ");
				productRawdataResponse.setStatus("fail");
				productRawdataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productRawdataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productRawdataResponse.setStatus("fail");
			productRawdataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productRawdataResponse;

	}

	@Override
	public QtyPcsResponse getQtyPcs() {
		List<QtyInPcs> lstQtyInPcs = new ArrayList<>();
		QtyPcsResponse qtyPcsResponse = new QtyPcsResponse();

		try {
			String sql = "select * from tbl_qty_pcs ORDER BY id DESC ";

			lstQtyInPcs = jdbcTemplate.query(sql, new QtyPcsRowMapper());
			if (lstQtyInPcs != null) {

				qtyPcsResponse.setListQtyInPcs(lstQtyInPcs);
				qtyPcsResponse.setMessage("Data Retrive successfully");
				qtyPcsResponse.setStatus("success");
				qtyPcsResponse.setStatusCode(0);
			} else {
				qtyPcsResponse.setMessage(" No Data Retrive ");
				qtyPcsResponse.setStatus("fail");
				qtyPcsResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			qtyPcsResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			qtyPcsResponse.setStatus("fail");
			qtyPcsResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return qtyPcsResponse;

	}

	@Override
	public BrandResponse getBrand(GenericRequest genericRequest) {
		List<Brand> lstBrand = new ArrayList<>();
		List<Brand> newlstBrand = new ArrayList<>();
		BrandResponse brandResponse = new BrandResponse();

		try {
			StringBuilder sql = new StringBuilder("select * from tbl_brand where distributer_id=? ");
			if (genericRequest.getLicenseId() != 0) {
				sql.append(" and liscense_id =" + genericRequest.getLicenseId() + "");

			}
			sql.append(" ORDER BY brand_id DESC");

		
			lstBrand = jdbcTemplate.query(sql.toString(), new Object[] { genericRequest.getDistributerId() },
					new BrandRowMapper());
			if (lstBrand != null) {
				for (Brand brand : lstBrand) {
					if (brand.getLicenseId() != 0) {
						String licenceName = null;
						licenceName = jdbcTemplate.query(GETLICENCEBYNAME, new Object[] { brand.getLicenseId() },
								new GetEmpNameResultExtracter());
						brand.setLicenseName(licenceName);
						newlstBrand.add(brand);
					}

				}

				brandResponse.setBrandList(newlstBrand);
				brandResponse.setMessage("Data Retrive successfully");
				brandResponse.setStatus("success");
				brandResponse.setStatusCode(0);
			} else {
				brandResponse.setMessage(" No Data Retrive ");
				brandResponse.setStatus("fail");
				brandResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			brandResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			brandResponse.setStatus("fail");
			brandResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return brandResponse;

	}

	@Override
	public ProductResponse getProduct(GenericRequest genericRequest) {
		List<Product> lstproduct = new ArrayList<>();
		List<Product> newlstproduct = new ArrayList<>();
		
		ProductResponse productResponse = new ProductResponse();

		try {
			// StringBuilder sql = new StringBuilder("select * from tbl_product
			// where distributor_id="+genericRequest.getDistributerId()+" ");

			String sql = "SELECT tbl_product.`product_id`,tbl_product.`product_name`,tbl_product.`brand_id`,tbl_product.`Product_code`,tbl_product.`PRODUCT_catagory_id`,tbl_product.`PRODUCT_segment_code`,tbl_product.`qtyML`,tbl_product.`box_size`,tbl_product.`liscense_id`,tbl_product.`excise_duty`,tbl_product.`status`,tbl_product.`created_date`,tbl_product.`updated_date`,tbl_product.`created_by_id`,tbl_product.`updated_by_id`,tbl_product.`distributor_id`,tbl_product.`pacakage_type_id`,tbl_product.`product_type_id`,tbl_product.`cif`,tbl_product.`wsp`,tbl_product.`mrp`,tbl_product.`product_sub_tyoe_id`,tbl_product.`isDelete`,`tbl_brand`.`brand_name` AS brandName,`tbl_product_catagory`.`name` AS categoryName,`tbl_product_segment`.`name` AS segmentName,`tbl_qty_pcs`.`quantity_in_ml` AS qtyML,`tbl_qty_pcs`.`pieces` AS qtyPcs,`tbl_dstributor_liscense`.`liscense_no` AS licenseName,`tbl_pacakage_type`.`name` AS packageName,`tbl_product_type`.`name` AS productTypeName,`tbl_product_sub_type`.`name` AS productSubTypeName FROM(`tbl_product`) left JOIN tbl_brand ON tbl_product.brand_id = tbl_brand.brand_id LEFT JOIN `tbl_product_catagory` ON `tbl_product`.`PRODUCT_catagory_id` = `tbl_product_catagory`.`id` LEFT JOIN `tbl_product_segment` ON `tbl_product`.`PRODUCT_segment_code` = `tbl_product_segment`.`id` LEFT JOIN `tbl_qty_pcs` ON `tbl_product`.`qtyML` = `tbl_qty_pcs`.`id` left JOIN `tbl_dstributor_liscense` ON `tbl_product`.`liscense_id` = tbl_dstributor_liscense.`liscense_id` LEFT JOIN `tbl_pacakage_type` ON `tbl_product`.`pacakage_type_id` = tbl_pacakage_type.`id` LEFT JOIN `tbl_product_type` ON `tbl_product`.`product_type_id` = tbl_product_type.`id` LEFT JOIN `tbl_product_sub_type` ON `tbl_product`.`product_sub_tyoe_id` = tbl_product_sub_type.`id`";

			Map<String, Integer> map = new HashMap<>();
			if (genericRequest.getDistributerId() != 0) {
				map.put("tbl_product.distributor_id", genericRequest.getDistributerId());
			}
			
			if (genericRequest.getId() != 0) {
				map.put("tbl_product.product_id", genericRequest.getId());
			}
			if (genericRequest.getBrandId() != 0) {
				map.put("tbl_product.brand_id", genericRequest.getBrandId());
			}

			String whereClause = getWhereClause(map);
			sql = sql + whereClause + " ORDER BY tbl_product.product_name";

			if (genericRequest.getOperationtype()!=null && genericRequest.getOperationtype().equalsIgnoreCase("jugaad")){
				sql = sql + "  LIMIT 500";	
				 
			}
                            System.out.println(sql.toString());
			lstproduct = jdbcTemplate.query(sql.toString(), new ProductRowMapper());
			
                        if (lstproduct != null) {
				if (genericRequest.getOperationtype() != null) {
				for (Product product : lstproduct) {
				/*	String licenseName = jdbcTemplate.query(
							"SELECT `liscense_no` FROM `tbl_dstributor_liscense`WHERE`liscense_id`=?",
							new Object[] { product.getLicenseId() }, new GetEmpNameResultExtracter());
					Product idByproduct = jdbcTemplate.query(nameByid,
							new Object[] { product.getBranId(), product.getPackagetypeId(),
									product.getProductCategoryId(), product.getProductSegmentCode(),
									product.getProductSubtypeid(), product.getProductTypeId(), product.getQtyMl() },
							new ProductRawDataRowMapper());
					product.setLicenseName(licenseName);
					product.setBrandName(idByproduct.getBrandName());
					product.setBrandCode(idByproduct.getBrandCode());
					product.setInternalBrandCode(idByproduct.getInternalBrandCode());
					product.setPackagetype(idByproduct.getPackagetype());
					product.setProductcategoryName(idByproduct.getProductcategoryName());
					product.setProductSegmentName(idByproduct.getProductSegmentName());
					product.setProductSubTypeName(idByproduct.getProductSubTypeName());
					product.setProductTypeName(idByproduct.getProductTypeName());
					product.setQtyInmlName(idByproduct.getQtyInmlName());
					product.setQtyInpcsValue(idByproduct.getQtyInpcsValue());*/
					if (genericRequest.getOperationtype() != null) {
						if (genericRequest.getOperationtype().equalsIgnoreCase("excise")) {
 System.out.println("inside product detail for excise");
							String[] excisevalue = jdbcTemplate
									.query(" SELECT `TP_no`,`PO_no`,`net_payable_amount` FROM `tbl_excise_order_detail` WHERE `product_id`= "
											+ product.getProductId() + " ", new ThreeValueResultEXtracter());

							product.setTpNo(excisevalue[1]);
							product.setPoNo(excisevalue[2]);
							product.setNetPaybleAount(excisevalue[0]);
						}
						if (genericRequest.getOperationtype().equalsIgnoreCase("stock")) {

							String qty = jdbcTemplate.query(
									" SELECT `qty` FROM `tbl_stock_detail` WHERE `product_id`= ? and stock_id =? ",
									new Object[] { product.getProductId(), genericRequest.getStockId() },
									new GetEmpNameResultExtracter());
							if (qty != null) {
								product.setQty(Integer.parseInt(qty));
							}
						}
					}
					newlstproduct.add(product);

				}
				productResponse.setListProduct(newlstproduct);
				productResponse.setMessage("Data Retrive successfully");
				productResponse.setStatus("success");
				productResponse.setStatusCode(0);
				}
				else {
					productResponse.setListProduct(lstproduct);
					productResponse.setMessage("Data Retrive successfully");
					productResponse.setStatus("success");
					productResponse.setStatusCode(0);
					
					
				}
				
			} else {
				productResponse.setMessage(" No Data Retrive ");
				productResponse.setStatus("fail");
				productResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productResponse.setStatus("fail");
			productResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productResponse;

	}

	@Override
	public ProductRawdataResponse getFeedbackType() {
		List<ProductRawdata> lstProductRawdata = new ArrayList<>();
		ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();

		try {
			String sql = "Select * from tbl_feedback_type ORDER BY id DESC ";

			lstProductRawdata = jdbcTemplate.query(sql, new FeedbackDataRowMapper());
			if (lstProductRawdata != null) {
				productRawdataResponse.setLstProductRawdata(lstProductRawdata);
				productRawdataResponse.setMessage("Data Retrive successfully");
				productRawdataResponse.setStatus("success");
				productRawdataResponse.setStatusCode(0);
			} else {
				productRawdataResponse.setMessage(" No Data Retrive ");
				productRawdataResponse.setStatus("fail");
				productRawdataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productRawdataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productRawdataResponse.setStatus("fail");
			productRawdataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productRawdataResponse;

	}

	@Override
	public ProductRawdataResponse getFeedbackRating() {
		List<ProductRawdata> lstProductRawdata = new ArrayList<>();
		ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();

		try {
			String sql = "Select * from tbl_feedback_rating ORDER BY id DESC ";

			lstProductRawdata = jdbcTemplate.query(sql, new FeedbackDataRowMapper());
			if (lstProductRawdata != null) {
				productRawdataResponse.setLstProductRawdata(lstProductRawdata);
				productRawdataResponse.setMessage("Data Retrive successfully");
				productRawdataResponse.setStatus("success");
				productRawdataResponse.setStatusCode(0);
			} else {
				productRawdataResponse.setMessage(" No Data Retrive ");
				productRawdataResponse.setStatus("fail");
				productRawdataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productRawdataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productRawdataResponse.setStatus("fail");
			productRawdataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productRawdataResponse;

	}

	@Override
	public RetailerRawDataResponse getRetailerByRouteId(int id) {

		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {

			String getRCatSql = "Select * from tbl_retailer where route_id=? and blocked=1 and status=1 and isDeleted='N' order by retailer_id desc";
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { id }, new RetailerRawdataMapper());
			if (!lstRetailerRawData.isEmpty()) {
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);
			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	@Override
	public Routeresponse getRouteBySrId(int srId) {
		System.out.println("inside get All route");
		List<RouteRawDataResponse> lstroute = new ArrayList<>();
		List<RouteRawDataResponse> newlstroute = new ArrayList<>();
		Routeresponse routeresponse = new Routeresponse();
		try {
			String getStateSql = "Select * from tbl_route_managment where sr_id=? ORDER BY route_id DESC ";

			lstroute = jdbcTemplate.query(getStateSql, new Object[] { srId }, new RouteResultExtracter());
			System.out.println("size of route list==>" + lstroute.size());

			if (lstroute != null) {
				newlstroute = setRouteData(lstroute);

				routeresponse.setRoutelist(newlstroute);
				routeresponse.setMessage("Data Retrive successfully");
				routeresponse.setStatus("success");
				routeresponse.setStatusCode(0);

			} else {
				routeresponse.setMessage(" No Data Retrive ");
				routeresponse.setStatus("fail");
				routeresponse.setStatusCode(1);
			}

		} catch (Exception e) {
			routeresponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			routeresponse.setStatus("fail");
			routeresponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return routeresponse;

	}

	@Override
	public MemberResponse getTeamByAdvanceSearch(AdvanceSearch advanceSearch) {
		List<Member> memberlst = new ArrayList<>();
		MemberResponse responseMember = new MemberResponse();
		try {
			String sql = "select * from tbl_member";

			Map<String, Integer> map = new HashMap<>();
			if (advanceSearch.getManagerid() != 0) {
				map.put("manager_id", advanceSearch.getManagerid());
				
			}
			if (advanceSearch.getAsmId() != 0) {
				map.put("asm_id", advanceSearch.getAsmId());
			}
			if (advanceSearch.getTsmid() != 0) {
				map.put("tsm_id", advanceSearch.getTsmid());
			}
			if (advanceSearch.getSrid() != 0) {
				map.put("employee_id", advanceSearch.getSrid());
			}

			String whereClause = getWhereClause(map);
			sql = sql + whereClause + "and isDelete ='N'";
			System.out.print(sql);
			List<Member> newMemberList = new ArrayList<>();
			memberlst = jdbcTemplate.query(sql, new MemberRawMapper());
			if (!memberlst.isEmpty()) {
				newMemberList = setMemberData(memberlst);
				responseMember.setLstMemberResponse(newMemberList);
				responseMember.setMessage("Data Retrive successfully");
				responseMember.setStatus("success");
				responseMember.setStatusCode(0);
			} else {
				responseMember.setMessage(" No Data Retrive ");
				responseMember.setStatus("fail");
				responseMember.setStatusCode(1);
			}

		} catch (Exception e) {
			responseMember.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			responseMember.setStatus("fail");
			responseMember.setStatusCode(-1);
			e.printStackTrace();
		}

		return responseMember;

	}

	public static String getWhereClause(Map<String, Integer> map) {
		String whereClause = "";
		Set<String> keySet = map.keySet();

		int counter = 0;
		for (String key : keySet) {
			counter++;
			whereClause += key + "=" + map.get(key) + " ";
			if (map.size() != counter) {
				whereClause += "and ";
			}
		}

		if (whereClause == null)
			whereClause = "";
		else
			whereClause = " where " + whereClause;
		return whereClause;
	}

	@Override
	public AssetByRetailerResponse getAsset(GenericRequest generic) {
		List<AssetByRetailerData> listAsset = new ArrayList<>();
		List<AssetByRetailerData> listAsset1 = new ArrayList<>();

		AssetByRetailerResponse assetResponse = new AssetByRetailerResponse();
		try {
			StringBuilder getMember = new StringBuilder("Select * from tbl_assets where distributor_id=?");

			if (generic.getId() != 0) {
				getMember.append(" and product_id = " + generic.getId() + "");
			}
			getMember.append(" order by id desc");

			System.out.println("query ==>" + getMember.toString());
			// String asset_sql = "SELECT * From tbl_assets WHERE
			// distributor_id=? ORDER BY id";
			listAsset = jdbcTemplate.query(getMember.toString(), new Object[] { generic.getDistributerId() },
					new AssetByRetailerMapper());
			String assetSql = "SELECT a.brand_name,b.product_name From tbl_brand as a,tbl_product as b  WHERE a.brand_id=?  and b.product_id=?";

			/* listAsset = jdbcTemplate.query(assetSql, new AssetMapper()); */
			if (listAsset != null) {
				for (AssetByRetailerData assetByRetailerData : listAsset) {
					AssetByRetailerData idByAsset = jdbcTemplate.query(assetSql,
							new Object[] { assetByRetailerData.getBrandId(), assetByRetailerData.getProductId() },
							new AssetRawDataMapper());
					assetByRetailerData.setBrandName(idByAsset.getBrandName());
					assetByRetailerData.setProductName(idByAsset.getProductName());
					listAsset1.add(assetByRetailerData);
				}

				assetResponse.setListAssetByretailerData(listAsset1);
				assetResponse.setMessage("Data Retrive successfully");
				assetResponse.setStatus("success");
				assetResponse.setStatusCode(0);
			} else {
				assetResponse.setMessage(" No Data Retrive ");
				assetResponse.setStatus("fail");
				assetResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			assetResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			assetResponse.setStatus("fail");
			assetResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return assetResponse;

	}

	@Override
	public AssetByRetailerResponse getAssetOfAllRetailer(int id) {
		AssetByRetailerResponse assetByRetailerResponse = new AssetByRetailerResponse();
		List<AssetByRetailerData> list = new ArrayList<>();
		List<AssetByRetailerData> newListAssetByRetailerData = new ArrayList<>();
		try {

			String type_sql = "SELECT * FROM tbl_assets where id in(select asset_id from tbl_asset_assign) and distributor_id = ?";
			list = jdbcTemplate.query(type_sql, new Object[] { id }, new AssetByRetailerMapper());

			if (!list.isEmpty()) {
				for (AssetByRetailerData assetByRetailerData : list) {
					AssetByRetailerData newAssetByRetailerData = new AssetByRetailerData();
					String getnamebySQl = "SELECT a.brand_name,b.product_name , c.qty ,c.amount  from tbl_brand as a , tbl_product as b  , tbl_asset_assign as c where a.brand_id=? and b.product_id=? and c.id =?";
					String[] name = jdbcTemplate.query(getnamebySQl,
							new Object[] {
									assetByRetailerData.getBrandId() != 0 ? assetByRetailerData.getBrandId() : null,
									assetByRetailerData.getProductId() != 0 ? assetByRetailerData.getProductId() : null,
									assetByRetailerData.getId() != 0 ? assetByRetailerData.getId() : null },
							new getNameByAssetRawid());
					newAssetByRetailerData.setBrandName(name[1]);
					newAssetByRetailerData.setProductName(name[2]);
					newAssetByRetailerData.setAssetName(assetByRetailerData.getAssetName());
					newAssetByRetailerData.setAssetType(assetByRetailerData.getAssetType());
					newAssetByRetailerData.setBrandId(assetByRetailerData.getBrandId());
					newAssetByRetailerData.setCreateDate(assetByRetailerData.getCreateDate());
					newAssetByRetailerData.setDescription(assetByRetailerData.getDescription());
					if (name[3] != null) {
						newAssetByRetailerData.setQuantity(Integer.parseInt(name[3]));
					}
					newAssetByRetailerData.setLastUpdate(assetByRetailerData.getLastUpdate());
					newAssetByRetailerData.setAmount(name[4]);

					newListAssetByRetailerData.add(newAssetByRetailerData);

				}
				assetByRetailerResponse.setListAssetByretailerData(newListAssetByRetailerData);
				assetByRetailerResponse.setMessage("Data Retrive successfully");
				assetByRetailerResponse.setStatus("success");
				assetByRetailerResponse.setStatusCode(0);
			} else {
				assetByRetailerResponse.setMessage("Couldn't retrive data");
				assetByRetailerResponse.setStatus("fail");
				assetByRetailerResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			assetByRetailerResponse.setMessage("Data Couldn't retrive application server error");
			assetByRetailerResponse.setStatus("fail");
			assetByRetailerResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return assetByRetailerResponse;
	}

	@Override
	public FeedbackResponse getFeedback(GenericRequest genericRequest) {
		List<Feedback> list = new ArrayList<>();
		List<Feedback> responseList = new ArrayList<>();
		FeedbackResponse response = new FeedbackResponse();

		try {

			StringBuilder type_sql = new StringBuilder("SELECT * FROM tbl_feedback where distributor_id =?");
			if (genericRequest.getId() != 0) {
				type_sql.append(" and retailer_id =" + genericRequest.getId() + "");
			}
			if (genericRequest.getActivityId() != 0) {
			  	type_sql.append(" and feedback_id =" + genericRequest.getActivityId() + "");
			}
			if (genericRequest.getStartDate()!=null && genericRequest.getEndDate()!=null ) {
				type_sql.append(" and Date(visit_date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(visit_date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d')");
			}
			type_sql.append(" order by feedback_id desc");
			list = jdbcTemplate.query(type_sql.toString(), new Object[] { genericRequest.getDistributerId() },
					new FeedbackRowMapper());

			if (!list.isEmpty()) {
				for (Feedback feedback : list) {

					String getnamebySQl = "SELECT a.`title`,b.`title`,c.`employee_name`,c.role_id,d.`retailer_shop_name`,d.`latitude`,d.`longitude`,d.sr_id FROM `tbl_feedback_rating` AS a ,`tbl_feedback_type` AS b ,`tbl_member` AS c , `tbl_retailer` AS d WHERE a.`id`=? AND b.`id`=? AND c.`employee_id`=? AND d.`retailer_id` =? ";
					Feedback listNamebyId = jdbcTemplate.query(getnamebySQl,
							new Object[] { feedback.getFeedbackratingid(), feedback.getFeedackTypeId(),
									feedback.getVisitorId(), feedback.getRetailerId() },
							new getFeedbackrecordsNameResultExtracter());
					String roleName = jdbcTemplate.query("select role_name from tbl_role where id=?",
							new Object[] { listNamebyId.getRoleId() }, new GetEmpNameResultExtracter());
					String srName = jdbcTemplate.query("select employee_name from tbl_member where employee_id=?",
							new Object[] { listNamebyId.getSrId() }, new GetEmpNameResultExtracter());
					feedback.setFeedbackratingName(listNamebyId.getFeedbackratingName());
					feedback.setFeedbackTypeName(listNamebyId.getFeedbackTypeName());
					feedback.setVisitorName(listNamebyId.getVisitorName());
					feedback.setRetailerName(listNamebyId.getRetailerName());
					feedback.setRetailerLattitude(listNamebyId.getRetailerLattitude());
					feedback.setRetailerLongitude(listNamebyId.getRetailerLongitude());
					feedback.setRoleId(listNamebyId.getRoleId());
					feedback.setSrId(listNamebyId.getSrId());
					feedback.setSrName(srName);
					feedback.setRoleName(roleName);
					 if (feedback.getLattitude() != null &&
					 feedback.getLongitude() != null
					 && feedback.getRetailerLattitude() != null &&
					 feedback.getRetailerLongitude() != null) {
					 double distance =distance(Double.parseDouble(feedback.getRetailerLattitude()),Double.parseDouble(feedback.getLattitude()), Double.parseDouble(feedback.getRetailerLongitude()), Double.parseDouble(feedback.getLongitude()));
					 feedback.setDistance(distance);
					 }
					responseList.add(feedback);
				}
				response.setFeedbackList(responseList);
				response.setMessage("Data Retrive successfully");
				response.setStatus("success");
				response.setStatusCode(0);
			} else {
				response.setMessage("Couldn't retrive data");
				response.setStatus("fail");
				response.setStatusCode(1);
			}
		} catch (Exception e) {
			response.setMessage("Data Couldn't retrive application server error");
			response.setStatus("fail");
			response.setStatusCode(-1);

			e.printStackTrace();

		}
		return response;
	}

	@Override
	public MemberResponse getNonAssignedRouteSR(AdvanceSearch advanceSearch) {
		List<Member> memberlst = new ArrayList<>();
		MemberResponse responseMember = new MemberResponse();
		try {
			String sql = "select * from tbl_member";

			Map<String, Integer> map = new HashMap<>();
			if (advanceSearch.getManagerid() != 0) {
				map.put("manager_id", advanceSearch.getManagerid());
			}
			if (advanceSearch.getAsmId() != 0) {
				map.put("asm_id", advanceSearch.getAsmId());
			}
			if (advanceSearch.getTsmid() != 0) {
				map.put("tsm_id", advanceSearch.getTsmid());
			}

			String whereClause = getWhereClause(map);
			sql = sql + whereClause
					+ "and isDelete ='N' AND role_id = 40 and employee_id not IN(SELECT sr_id FROM tbl_route_managment)";
			System.out.print(sql);
			List<Member> newMemberList = new ArrayList<>();
			memberlst = jdbcTemplate.query(sql, new MemberRawMapper());
			if (!memberlst.isEmpty()) {
				newMemberList = setMemberData(memberlst);
				responseMember.setLstMemberResponse(newMemberList);
				responseMember.setMessage("Data Retrive successfully");
				responseMember.setStatus("success");
				responseMember.setStatusCode(0);
			} else {
				responseMember.setMessage(" No Data Retrive ");
				responseMember.setStatus("fail");
				responseMember.setStatusCode(1);
			}

		} catch (Exception e) {
			responseMember.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			responseMember.setStatus("fail");
			responseMember.setStatusCode(-1);
			e.printStackTrace();
		}

		return responseMember;

	}

	public static double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371;

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000;

		if (distance != 0) {
			distance = Math.pow(distance, 2);
			return Math.sqrt(distance);
		} else {
			return 0;
		}

	}

	@Override
	public TeamActivityHistoryResponse getTeamActivityHistoryBy(ActivityRequest ar) {
		TeamActivityHistoryResponse teamActivityHistoryResponse = new TeamActivityHistoryResponse();

		try { 
			
			
			List<VisitHistorys> lstVisitHistory = new ArrayList<>();
			List<VisitHistorys> newlstVisitHistory = new ArrayList<>();
			String sql = "SELECT a.`employee_name`,b.`retailer_shop_name`,b.street FROM `tbl_member`AS a , `tbl_retailer` AS b WHERE a.`employee_id` =? AND b.`retailer_id` =?";
			
			if ( ar.getOperationtype()!=null  && ar.getOperationtype().equalsIgnoreCase("all"))
			{
				lstVisitHistory = jdbcTemplate.query("SELECT * FROM tbl_visit_info WHERE `visitor_id` in (select employee_id from tbl_member where manager_id= "+ar.getId()+") and  Date(date) >= Str_to_date('"+ar.getFromDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+ar.getToDate()+"', '%Y-%m-%d') order by id desc ",
						
						new VisitHistoryRowMapper());
				
				
				//lstVisitHistory = "SELECT * FROM `tbl_visit_info` WHERE `visitor_id` in (select employee_id from tbl_member where manager_id= "+genericRequest.getId()+") and  Date(date) >= Str_to_date('"+ar.getStartDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d') order by id desc ";
			}
			
		
			if (ar.getActivityType()!=null){
				if (ar.getId()!=0){
					System.out.println("inside get activity member wise ");
					lstVisitHistory = jdbcTemplate.query("SELECT * FROM tbl_visit_info WHERE  Date(date) >= Str_to_date('"+ar.getFromDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+ar.getToDate()+"', '%Y-%m-%d') AND `visitor_id` in (select employee_id from tbl_member where manager_id= "+ar.getId()+") and activity_type = "+ar.getActivityType()+" order by id desc", 
							
							new VisitHistoryRowMapper());
					
				}
				else{
				lstVisitHistory = jdbcTemplate.query("SELECT * FROM tbl_visit_info WHERE  Date(date) >= Str_to_date('"+ar.getFromDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+ar.getToDate()+"', '%Y-%m-%d') AND `retailer_id`=? and activity_type = "+ar.getActivityType()+" order by id desc", new Object[] { ar.getRetailerId() },
						
						new VisitHistoryRowMapper());
				}}
			if(ar.getOperationtype()==null && ar.getActivityType()==null )
			{
			
			lstVisitHistory = jdbcTemplate.query("SELECT * FROM tbl_visit_info WHERE  Date(date) >= Str_to_date('"+ar.getFromDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+ar.getToDate()+"', '%Y-%m-%d') AND `retailer_id`=?", new Object[] { ar.getRetailerId() },
					new VisitHistoryRowMapper());
			}
		//	"SELECT * FROM tbl_visit_info WHERE DATE BETWEEN '" + fromDate+ "' AND  '" + toDate + "' AND `retailer_id`=?"
			if (!lstVisitHistory.isEmpty()) {
				for (VisitHistorys visitHistory : lstVisitHistory) {
					String name[] = jdbcTemplate.query(sql,
							new Object[] { visitHistory.getVisitorid(), visitHistory.getRetailerId() },
							new ThreeValueResultEXtracter());
					visitHistory.setRetailerName(name[2]);
					visitHistory.setVisitorName(name[1]);
					visitHistory.setAddress(name[0]);
					if (visitHistory.getActivityType().equalsIgnoreCase("1")) {
						String description = jdbcTemplate.query(
								"SELECT feedback_description FROM tbl_feedback where feedback_id=?",
								new Object[] { visitHistory.getActivityId() }, new GetEmpNameResultExtracter());
						visitHistory.setActivityType("feedback");
						visitHistory.setDescription(description);
					}
					if (visitHistory.getActivityType().equalsIgnoreCase("2")) {
						String description = jdbcTemplate.query(
								"select order_remarks from tbl_promise_order where order_id=?",
								new Object[] { visitHistory.getActivityId() }, new GetEmpNameResultExtracter());
						visitHistory.setActivityType("Promise Order");
						visitHistory.setDescription(description);
					}
					if (visitHistory.getActivityType().equalsIgnoreCase("3")) {
						String description = jdbcTemplate.query("select remarks from tbl_stock where stock_id=?",
								new Object[] { visitHistory.getActivityId() }, new GetEmpNameResultExtracter());
						visitHistory.setActivityType("Stock");
						visitHistory.setDescription(description);
					}

					newlstVisitHistory.add(visitHistory);
				}
				teamActivityHistoryResponse.setLstVisit(newlstVisitHistory);
				teamActivityHistoryResponse.setMessage("  Data Retrive ");
				teamActivityHistoryResponse.setStatus("success");
				teamActivityHistoryResponse.setStatusCode(0);
			} else {
				teamActivityHistoryResponse.setMessage(" No Data Retrive ");
				teamActivityHistoryResponse.setStatus("fail");
				teamActivityHistoryResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			teamActivityHistoryResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			teamActivityHistoryResponse.setStatus("fail");
			teamActivityHistoryResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return teamActivityHistoryResponse;
	}

	@Override
	public MemberResponse getTeamActivity(GenericRequest genericRequest) {

		List<Member> memberlst = new ArrayList<>();
		MemberResponse responseMember = new MemberResponse();
		try {

			List<Member> newMemberList = new ArrayList<>();
			List<Member> resMemberList = new ArrayList<>();
			memberlst = jdbcTemplate.query(GETMEMBERBYROLE,
					new Object[] { genericRequest.getRoleId(), genericRequest.getDistributerId() },
					new MemberRawMapper());
			if (!memberlst.isEmpty()) {
				newMemberList = setMemberData(memberlst);
				for (Member member : newMemberList) {
					// SELECT `retailer_id` FROM `tbl_retailer` WHERE sr_id=3 OR
					// `asm_id`=3 OR `tsm_id`=3 OR `manager_id`=3
					String visitCount = jdbcTemplate.query(
							"SELECT COUNT(*) FROM `tbl_visit_master` WHERE retailer_id IN(SELECT `retailer_id` FROM `tbl_retailer` WHERE (sr_id=? OR `asm_id`=? OR `tsm_id`=? OR `manager_id`=?) and Date(visit_DateTime) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(visit_DateTime) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d'))",
							new Object[] { member.getEmployeeId() != 0 ? member.getEmployeeId() : null,
									member.getEmployeeId() != 0 ? member.getEmployeeId() : null,
									member.getEmployeeId() != 0 ? member.getEmployeeId() : null,
									member.getDistributerId() != 0 ? member.getEmployeeId() : null },
							new GetEmpNameResultExtracter());

					member.setVisitCount(visitCount);
					resMemberList.add(member);

				}

				responseMember.setLstMemberResponse(resMemberList);
				responseMember.setMessage("Data Retrive successfully");
				responseMember.setStatus("success");
				responseMember.setStatusCode(0);

			} else {
				responseMember.setMessage(" No Data Retrive ");
				responseMember.setStatus("fail");
				responseMember.setStatusCode(1);
			}

		} catch (Exception e) {
			responseMember.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			responseMember.setStatus("fail");
			responseMember.setStatusCode(-1);
			e.printStackTrace();
		}

		return responseMember;

	}

	@Override
	public RetailerRawDataResponse getTeamActivityRetailerWise(GenericRequest genericRequest) {
		List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
		RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
		try {
			String getRCatSql = "Select * from tbl_retailer where (sr_id=? or tsm_id=? or asm_id=? or manager_id=?) and isgroup=1 and blocked=1 and status=1 and isDeleted='N' order by retailer_id desc";
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();
			List<RetailerRawData> reslstRetailerRawData = new ArrayList<>();

			lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] { genericRequest.getId(), genericRequest.getId(), genericRequest.getId(), genericRequest.getId() },
					new RetailerRawdataMapper());
			if (!lstRetailerRawData.isEmpty()) {
				newlstRetailerRawData = setRetailerData(lstRetailerRawData);
				for (RetailerRawData retailerRawData : newlstRetailerRawData) {
					String visitCount = jdbcTemplate.query(
							"SELECT COUNT(*) FROM `tbl_visit_master` WHERE retailer_id = ? and Date(visit_DateTime) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(visit_DateTime) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d')",
							new Object[] { retailerRawData.getRetailerId() }, new GetEmpNameResultExtracter());
					retailerRawData.setVisitCount(visitCount);
					reslstRetailerRawData.add(retailerRawData);

				}
				retailerRawDataResponse.setLstRetailerRawData(reslstRetailerRawData);
				retailerRawDataResponse.setMessage("Data Retrive successfully");
				retailerRawDataResponse.setStatus("success");
				retailerRawDataResponse.setStatusCode(0);
			} else {
				retailerRawDataResponse.setMessage(" No Data Retrive ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			retailerRawDataResponse.setStatus("fail");
			retailerRawDataResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return retailerRawDataResponse;

	}

	@Override
	public PromiseOrderResponse getPromiseOrder(GenericRequest genericRequest) {
		List<PromiseOrder> lstpromiseOrder = new ArrayList<>();
		List<PromiseOrder> newlstpromiseOrder = new ArrayList<>();
		PromiseOrderResponse promiseOrderResponse = new PromiseOrderResponse();
		try {

			StringBuilder sql = new StringBuilder("SELECT * FROM tbl_promise_order where distributer_id ="
					+ genericRequest.getDistributerId() + "    ");

			if (genericRequest.getId() != 0) {
				sql.append(" and retailer_id = " + genericRequest.getId() + "");
			}

			if (genericRequest.getActivityId() != 0) {
				sql.append(" and order_id =" + genericRequest.getActivityId() + "");
			}
			
			if (genericRequest.getStartDate()!=null && genericRequest.getEndDate()!=null ) {
				sql.append(" and Date(taken_date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(taken_date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d')");
			}
			sql.append(" ORDER BY `order_id` desc ");
			lstpromiseOrder = jdbcTemplate.query(sql.toString(), new PromiseOrderRowMapper());
			if (!lstpromiseOrder.isEmpty()) {

				for (PromiseOrder promiseOrder : lstpromiseOrder) {
					List<PromiseOrderProductDetail> newPromiseOrderProductDetail = new ArrayList<>();
					List<PromiseOrderProductDetail> lstpromiseOrderProductDetail = new ArrayList<>();
					String name[] = jdbcTemplate
							.query("SELECT a.`retailer_shop_name`,b.`employee_name`,b.role_id FROM `tbl_retailer` AS a , `tbl_member` AS b WHERE a.`retailer_id` = "
									+ promiseOrder.getRetailerId() + " AND b.`employee_id`="
									+ promiseOrder.getVisitorId() + "", new ThreeValueResultEXtracter());
					String roleName = jdbcTemplate.query(
							"SELECT `role_name` FROM `tbl_role` WHERE `id`=" + name[0] + "",
							new GetEmpNameResultExtracter());
					promiseOrder.setRoleName(roleName);
					promiseOrder.setRetailerName(name[1] != null ? name[1] : null);
					promiseOrder.setVisitorName(name[2] != null ? name[2] : null);
					promiseOrder.setRoleId(name[0] != null ? Integer.parseInt(name[0]) : null);
					lstpromiseOrderProductDetail = jdbcTemplate.query(
							"SELECT * FROM tbl_promise_order_detail where order_id=" + promiseOrder.getId() + " ",
							new PromiseOrderDetailRowmapper());
					for (PromiseOrderProductDetail PromiseOrderProductDetail : lstpromiseOrderProductDetail) {
						PromiseOrderProductDetail nameDetail = jdbcTemplate.query(
								"SELECT a.`product_name`,b.`brand_name`,a.qtyML , a.`PRODUCT_catagory_id` , a.`product_type_id`,a.`product_sub_tyoe_id`,a.`pacakage_type_id`  FROM `tbl_product`AS a , tbl_brand AS b WHERE a.`product_id`="
										+ PromiseOrderProductDetail.getProductId() + " AND b.`brand_id`="
										+ PromiseOrderProductDetail.getBrandId() + "",
								new PromiseOrderProductDetailRoqMapper());
						String productDetail[] = jdbcTemplate.query(
								" SELECT a.`quantity_in_ml`,b.`name`,c.`name`,d.`name`,e.`name` FROM `tbl_qty_pcs` AS a ,`tbl_product_catagory` AS b , `tbl_product_type` AS c ,`tbl_product_sub_type` AS d,`tbl_pacakage_type` AS e where a.`id` =? and b.`id`=? and c.id=? and d.`id`=? and e.`id`=?",
								new Object[] { nameDetail.getQtyInMlId(), nameDetail.getProductCategoryId(),
										nameDetail.getProductTypeid(), nameDetail.getProductSubTypeId(),
										nameDetail.getPackageTypeId() },
								new FiveValueresultExtracter());
						PromiseOrderProductDetail.setProductName(nameDetail.getProductName());
						PromiseOrderProductDetail.setBrandName(nameDetail.getBrandName());
						PromiseOrderProductDetail.setQtyInMlId(nameDetail.getQtyInMlId());
						PromiseOrderProductDetail.setProductCategoryId(nameDetail.getProductCategoryId());
						PromiseOrderProductDetail.setProductTypeid(nameDetail.getProductId());
						PromiseOrderProductDetail.setProductSubTypeId(nameDetail.getProductSubTypeId());
						PromiseOrderProductDetail.setPackageTypeId(nameDetail.getPackageTypeId());
						PromiseOrderProductDetail.setQtyInMlValue(productDetail[0]);
						PromiseOrderProductDetail.setProductCategoryName(productDetail[1]);
						PromiseOrderProductDetail.setProductTypeName(productDetail[2]);
						PromiseOrderProductDetail.setProductSubTypeName(productDetail[3]);
						PromiseOrderProductDetail.setPackageTypeName(productDetail[4]);
						newPromiseOrderProductDetail.add(PromiseOrderProductDetail);

					}
					promiseOrder.setProductList(newPromiseOrderProductDetail);

					newlstpromiseOrder.add(promiseOrder);
				}

				promiseOrderResponse.setLstPromiseOrder(newlstpromiseOrder);
				promiseOrderResponse.setMessage("Data Retrive successfully");
				promiseOrderResponse.setStatus("success");
				promiseOrderResponse.setStatusCode(0);
			} else {
				promiseOrderResponse.setMessage(" No Data Retrive ");
				promiseOrderResponse.setStatus("fail");
				promiseOrderResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			promiseOrderResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			promiseOrderResponse.setStatus("fail");
			promiseOrderResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return promiseOrderResponse;

	}

	@Override
	public ExciseDutyResponse getExciseOrderByDistributerId(GenericRequest genericRequest) {
		ExciseDutyResponse ExciseDutyResponse = new ExciseDutyResponse();
		List<ExciseOrder> lstExciseOrder = new ArrayList<>();
		List<ExciseOrder> newlstExciseOrder = new ArrayList<>();
		try {
			System.out.println("inside get excise order ");
			StringBuilder sql = new StringBuilder(
					"SELECT * FROM tbl_excise_order where distributer_id =" + genericRequest.getDistributerId() + "");

			if (genericRequest.getId() != 0) {
				sql.append(" and retailer_id = " + genericRequest.getId() + "");
			}
			sql.append(" ORDER BY `id`");
			lstExciseOrder = jdbcTemplate.query(sql.toString(), new ExciseOrderResultExtracter());
			if (!lstExciseOrder.isEmpty()) {
				System.out.println("inside get excise order retailer data fetching ");

				for (ExciseOrder excOrdr : lstExciseOrder) {
					System.out.println("inside get excise order product data fetching ");

					List<Product> productlst = new ArrayList<>();
					String retailerName = jdbcTemplate.query(
							" SELECT `retailer_shop_name` FROM `tbl_retailer` WHERE `retailer_id`=?",
							new Object[] { excOrdr.getRetailerId() }, new GetEmpNameResultExtracter());
					excOrdr.setRetailerName(retailerName);
					List<Integer> productId = jdbcTemplate
							.query(" SELECT `product_id` FROM `tbl_excise_order_detail` WHERE `order_id` = "
									+ excOrdr.getExciseOrderId() + "", new GetRetailerIdResultExtracter());
					for (Integer i : productId) {
						ProductResponse productResponse = new ProductResponse();
						GenericRequest gnrcreq = new GenericRequest();
						gnrcreq.setId(i);
						System.out.println("productId for excise"+i);
						gnrcreq.setOperationtype("excise");
						productResponse = getProduct(gnrcreq);
						productlst.addAll(productResponse.getListProduct());

					}
					excOrdr.setExciseProductList(productlst);
					newlstExciseOrder.add(excOrdr);
				}

				ExciseDutyResponse.setLstExciseOrder(newlstExciseOrder);

				ExciseDutyResponse.setMessage("Data Retrive successfully");
				ExciseDutyResponse.setStatus("success");
				ExciseDutyResponse.setStatusCode(0);
			} else {
				ExciseDutyResponse.setMessage(" No Data Retrive ");
				ExciseDutyResponse.setStatus("fail");
				ExciseDutyResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			ExciseDutyResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			ExciseDutyResponse.setStatus("fail");
			ExciseDutyResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return ExciseDutyResponse;
	}

	
	@Override
	public StockResponse getStock(GenericRequest genericRequest) {
		StockResponse stockResponse = new StockResponse();
		List<StockDetailedData> lstStockDetailedData = new ArrayList<>();
		List<StockDetailedData> newStockDetailedData = new ArrayList<>();
		try {
			System.out.println("inside get stock ");
			StringBuilder sql = new StringBuilder(
					"SELECT * FROM tbl_stock where distributer_id = " + genericRequest.getDistributerId() + "");

			if (genericRequest.getId() != 0) {
				sql.append(" and retailer_id = " + genericRequest.getId() + "");
			}
			if (genericRequest.getActivityId() != 0) {
				sql.append(" and stock_id =" + genericRequest.getActivityId() + "");
			}
			if (genericRequest.getStartDate()!=null && genericRequest.getEndDate()!=null ) {
				sql.append(" and Date(taken_date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(taken_date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d')");
			}

			sql.append(" ORDER BY `stock_id` desc ");
			lstStockDetailedData = jdbcTemplate.query(sql.toString(), new StockRowMapper());
			if (!lstStockDetailedData.isEmpty()) {

				for (StockDetailedData stockOrdr : lstStockDetailedData) {
					System.out.println("inside first loop");

					List<Product> productlst = new ArrayList<>();
					String retailerName = jdbcTemplate.query(
							" SELECT `retailer_shop_name` FROM `tbl_retailer` WHERE `retailer_id`=?",
							new Object[] { stockOrdr.getRetailerId() }, new GetEmpNameResultExtracter());
					String visitorName = jdbcTemplate.query(
							" SELECT `employee_name` FROM `tbl_member` WHERE `employee_id`=?",
							new Object[] { stockOrdr.getVisitorId() }, new GetEmpNameResultExtracter());
					stockOrdr.setRetailerName(retailerName);
					stockOrdr.setVisitorName(visitorName);
					List<Integer> productId = jdbcTemplate
							.query(" SELECT `product_id` FROM `tbl_stock_detail` WHERE `stock_id` = "
									+ stockOrdr.getStockId() + "", new GetRetailerIdResultExtracter());
					for (Integer i : productId) {	
						stockOrdr.setVisitorName(visitorName);
						
					System.out.println("inside second loop");
						ProductResponse productResponse = new ProductResponse();
						GenericRequest gnrcreq = new GenericRequest();
						gnrcreq.setId(i);
						gnrcreq.setOperationtype("stock");
						gnrcreq.setStockId(stockOrdr.getStockId());
						productResponse = getProduct(gnrcreq);
						productlst.addAll(productResponse.getListProduct());

					}
					stockOrdr.setStockProductList(productlst);
					newStockDetailedData.add(stockOrdr);
				}

				stockResponse.setlstStockResponse(newStockDetailedData);

				stockResponse.setMessage("Data Retrive successfully");
				stockResponse.setStatus("success");
				stockResponse.setStatusCode(0);
			} else {
				stockResponse.setMessage(" No Data Retrive ");
				stockResponse.setStatus("fail");
				stockResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			stockResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			stockResponse.setStatus("fail");
			stockResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return stockResponse;
	}

	@Override
	public AttendenceResponse getAttendence(GenericRequest genericRequest) {
		System.out.println("inside getAttendence");
		AttendenceResponse attendenceResponse = new AttendenceResponse();
		List<Attendence> atndsLst = new ArrayList<>();
		List<Attendence> newAtndsLst = new ArrayList<>();

		/*
		 * lstVisitHistory = jdbcTemplate.
		 * query("SELECT * FROM tbl_visit_info WHERE DATE BETWEEN '" + fromDate
		 * + "' AND  '" + toDate + "' AND `retailer_id`=?", new Object[] {
		 * retailerId }, new VisitHistoryRowMapper());
		 */

		try {
			String getAttendence = "SELECT * FROM `tbl_attendence` WHERE CAST(in_time as Date) BETWEEN '"
					+ genericRequest.getStartDate() + "' and '" + genericRequest.getEndDate()
					+ "' and  `visitor_id`=? ORDER BY `id` DESC  ";
			
			
		String sqlAttendence = "SELECT A.visitor_id, A.inretailerid, A.intime, B.outretailerid, B.outtime  FROM   (SELECT visitor_id, Date(date),  retailer_id AS INRETAILERID,  Min(date)   AS INTIME,  Date(date)  AS actualdate  FROM   tbl_visit_info   WHERE  Date(date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d') AND visitor_id = "+genericRequest.getId()+"  GROUP  BY visitor_id,   Date(date) ORDER  BY Date(date), visitor_id) A  INNER JOIN (SELECT visitor_id,  Date(date), retailer_id AS OUTRETAILERID,  Max(date)   AS OUTTIME,   Date(date)  AS actualdate  FROM   tbl_visit_info  WHERE  Date(date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d')  AND visitor_id = "+genericRequest.getId()+"  GROUP  BY visitor_id,   Date(date)  ORDER  BY Date(date),  visitor_id)   ON A.visitor_id = B.visitor_id   AND A.actualdate = B.actualdate ";
			String sj = "SELECT A.visitor_id, "
					+ "A.inretailerid, "
					+ "A.intime,"
					+ "B.outretailerid, "
					+ "B.outtime "
					+ " FROM   (SELECT visitor_id, "
					+ " Date(date),"
					+ " retailer_id AS INRETAILERID,"
					+ " Min(date)   AS INTIME,"
					+ " Date(date)  AS actualdate "
					+ " FROM   tbl_visit_info"
					+ "  WHERE  Date(date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d')"
					+ "  AND visitor_id = "+genericRequest.getId()+" "
					+ " GROUP  BY visitor_id,"
					+ " Date(date) "
					+ " ORDER  BY Date(date), "
					+ " visitor_id) A"
					+ " INNER JOIN (SELECT visitor_id,"
					+ " Date(date),"
					+ " retailer_id AS OUTRETAILERID,"
					+ " Max(date)   AS OUTTIME, "
					+ "  Date(date)  AS actualdate"
					+ " FROM   tbl_visit_info"
					+ "  WHERE  Date(date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d')"
					+ "  AND visitor_id = "+genericRequest.getId()+""
					+ "   GROUP  BY visitor_id,"
					+ " Date(date)"
					+ "  ORDER  BY Date(date),"
					+ "  visitor_id) B"
					+ "   ON A.visitor_id = B.visitor_id "
					+ "AND A.actualdate = B.actualdate";
			
			
			
			String sp = "SELECT b.visitor_id, "
					+ " intime, "
					+ " outtime,"
					+ " tb.retailer_id AS INID"
					+ " tb1.retailer_id AS OutID"
					+ " FROM   (SELECT visitor_id,"
					+ " MIN(DATE)   AS INTIME,"
					+ " MAX(DATE) AS OutTime"
					+ " FROM   tbl_visit_info uy"
					+ " WHERE  DATE(DATE) >= STR_TO_DATE('"+genericRequest.getStartDate()+"', '%Y-%m-%d') AND DATE(DATE) <= STR_TO_DATE('"+genericRequest.getStartDate()+"', '%Y-%m-%d')"
					+ "  AND uy.visitor_id = "+genericRequest.getId()+""
					+ " GROUP  BY uy.visitor_id"
					+ "   )b LEFT JOIN tbl_visit_info tb ON tb.visitor_id=b.visitor_id AND tb.date=b.INTIME "
					+ "  LEFT JOIN tbl_visit_info tb1 ON tb1.visitor_id=b.visitor_id AND tb1.date=b.OutTime";
			
			String spj = "SELECT b.visitor_id, intime, outtime,tb.retailer_id AS INID, tb1.retailer_id AS OutID FROM   (SELECT visitor_id, MIN(DATE)   AS INTIME,MAX(DATE) AS OutTime FROM   tbl_visit_info uy WHERE  DATE(DATE) >= STR_TO_DATE('"+genericRequest.getStartDate()+"', '%Y-%m-%d') AND DATE(DATE) <= STR_TO_DATE('"+genericRequest.getEndDate()+"', '%Y-%m-%d') AND uy.visitor_id = "+genericRequest.getId()+" GROUP  BY uy.visitor_id )b LEFT JOIN tbl_visit_info tb ON tb.visitor_id=b.visitor_id AND tb.date=b.INTIME LEFT JOIN tbl_visit_info tb1 ON tb1.visitor_id=b.visitor_id AND tb1.date=b.OutTime";
			
			System.out.println("query attendence"+spj);
			atndsLst = jdbcTemplate.query(sj,
					new AttendenceRowMapper());
			if (atndsLst != null) {
				for (Attendence loopAttendence : atndsLst) {

					Integer i  = jdbcTemplate.query("SELECT retailer_id FROM tbl_visit_info WHERE visitor_id = ? AND DATE = '"+loopAttendence.getOutTime()+"'",new Object[] { loopAttendence.getVisitorId()},new IntegerValueExtracter());
					loopAttendence.setOutRetailerId(i);
					String name[] = jdbcTemplate.query(
							"SELECT a.`employee_name`, b.`retailer_shop_name`,c.`retailer_shop_name` FROM `tbl_member` AS a , `tbl_retailer` AS b ,`tbl_retailer` AS c WHERE a.`employee_id`= ? AND b.`retailer_id`=? AND c.`retailer_id` = ?",
							new Object[] { loopAttendence.getVisitorId(), loopAttendence.getInRetailerId(),
									loopAttendence.getOutRetailerId() },
							new ThreeValueResultEXtracter());
					loopAttendence.setVisitorName(name[1]);
					loopAttendence.setInRetailerName(name[2]);
					loopAttendence.setOutRetailerName(name[0]);
					
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					   Date convertedCurrentDate1 = sdf.parse(loopAttendence.getInTime());
					   Date convertedCurrentDate2 = sdf.parse(loopAttendence.getOutTime());

					 loopAttendence.setInDate(new SimpleDateFormat("dd-MM-yyyy").format(convertedCurrentDate1));
					 loopAttendence.setOutDate(new SimpleDateFormat("dd-MM-yyyy").format(convertedCurrentDate2));

					 loopAttendence.setAttendenceInTime(loopAttendence.getInTime().substring(11));
					loopAttendence.setAttendenceOutTime(loopAttendence.getOutTime().substring(11));

					SimpleDateFormat formatter6 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date d1 = (Date) formatter6.parse(loopAttendence.getInTime());
					Date d2 = (Date) formatter6.parse(loopAttendence.getOutTime());

					long diff = d2.getTime() - d1.getTime();
					long s = diff / 1000 % 60;
					long m = diff / (60 * 1000) % 60;
					long h = diff / (60 * 60 * 1000);

					String sec = String.format("%02d", s);
					String min = String.format("%02d", m);
					String hr = String.format("%02d", h);

					String time = hr + ":" + min + ":" + sec;
					loopAttendence.setDiffrence(time);
					newAtndsLst.add(loopAttendence);

				}

				attendenceResponse.setLstAttendence(newAtndsLst);
				attendenceResponse.setMessage("Data Retrive successfully");
				attendenceResponse.setStatus("success");
				attendenceResponse.setStatusCode(0);

			} else {
				attendenceResponse.setMessage(" No Data Retrive ");
				attendenceResponse.setStatus("fail");
				attendenceResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			attendenceResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			attendenceResponse.setStatus("fail");
			attendenceResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return attendenceResponse;

	}

	@Override
	public ActivityResponse getActivity(GenericRequest genericRequest) {
		System.out.println("inside getActivity");
		ActivityResponse activityResponse = new ActivityResponse();
		List<VisitHistorys> activityLst = new ArrayList<>();
		List<VisitHistorys> newActivityLst = new ArrayList<>();

		/*
		 * lstVisitHistory = jdbcTemplate.
		 * query("SELECT * FROM tbl_visit_info WHERE DATE BETWEEN '" + fromDate
		 * + "' AND  '" + toDate + "' AND `retailer_id`=?", new Object[] {
		 * retailerId }, new VisitHistoryRowMapper());
		 */

		try {
			Integer[] herchId = new Integer[3];
			String getActivity = null;
			if ( genericRequest.getOperationtype()!=null  && genericRequest.getOperationtype().equalsIgnoreCase("all"))
			{
				
				 getActivity = "SELECT * FROM `tbl_visit_info` WHERE `visitor_id` in (select employee_id from tbl_member where manager_id= "+genericRequest.getId()+") and  Date(date) >= Str_to_date('"+genericRequest.getStartDate()+"', '%Y-%m-%d') and Date(date) <= Str_to_date('"+genericRequest.getEndDate()+"', '%Y-%m-%d') order by id desc ";
			}
			else {
			 getActivity = "SELECT * FROM `tbl_visit_info` WHERE `visitor_id`=" + genericRequest.getId()
					+ " AND CAST(DATE AS DATE) = '" + genericRequest.getStartDate() + "' order by id desc ";
			}
			System.out.println(getActivity);
			activityLst = jdbcTemplate.query(getActivity, new ActivityRowMapper());
			if (activityLst != null) {

				for (VisitHistorys loopVisitHistorys : activityLst) {

					String name[] = jdbcTemplate.query(
							"SELECT a.retailer_shop_name ,b.`employee_name`, a.latitude , a.longitude  FROM `tbl_retailer` AS a , `tbl_member` AS b WHERE a.`retailer_id` =? AND b.`employee_id` = ?",
							new Object[] { loopVisitHistorys.getRetailerId(), loopVisitHistorys.getVisitorid() },
							new FourValueResultExtracter());
					loopVisitHistorys.setRetailerName(name[0]);
					loopVisitHistorys.setVisitorName(name[1]);
					loopVisitHistorys.setRetailerLattitude(name[2]);
					loopVisitHistorys.setRetailerLongitude(name[3]);
					if (loopVisitHistorys.getActivityType().equalsIgnoreCase("1")) {
						System.out.println("inside get feedback lat or long");
						String feedbackLatLong[] = jdbcTemplate.query(
								"SELECT `lattitude`,`longitude` FROM `tbl_feedback` WHERE `feedback_id`=?",
								new Object[] { loopVisitHistorys.getActivityId() }, new TwoValueresultExtracter());
						loopVisitHistorys.setLattitude(feedbackLatLong[0]);
						loopVisitHistorys.setLongitude(feedbackLatLong[1]);
						loopVisitHistorys.setActivityName("view feedback");

					}

					if (loopVisitHistorys.getActivityType().equalsIgnoreCase("2")) {
						String feedbackLatLong[] = jdbcTemplate.query(
								"SELECT `lattitude`,`longitude` FROM `tbl_promise_order` WHERE `order_id`=?",
								new Object[] { loopVisitHistorys.getActivityId() }, new TwoValueresultExtracter());
						loopVisitHistorys.setLattitude(feedbackLatLong[0]);
						loopVisitHistorys.setLongitude(feedbackLatLong[1]);
						loopVisitHistorys.setActivityName("view Promise Order");

					}

					if (loopVisitHistorys.getActivityType().equalsIgnoreCase("3")) {
						String feedbackLatLong[] = jdbcTemplate.query(
								"SELECT `lattitude`,`longitude` FROM `tbl_stock` WHERE `stock_id`=?",
								new Object[] { loopVisitHistorys.getActivityId() }, new TwoValueresultExtracter());
						loopVisitHistorys.setLattitude(feedbackLatLong[0]);
						loopVisitHistorys.setLongitude(feedbackLatLong[1]);
						loopVisitHistorys.setActivityName("view Stock");

					}
					
					if (loopVisitHistorys.getActivityType().equalsIgnoreCase("4")) {
						String feedbackLatLong[] = jdbcTemplate.query(
								"SELECT `latitude`,`longitude` FROM `tbl_asset_audit` WHERE `id`=?",
								new Object[] { loopVisitHistorys.getActivityId() }, new TwoValueresultExtracter());
						
						loopVisitHistorys.setLattitude(feedbackLatLong[0]);
						loopVisitHistorys.setLongitude(feedbackLatLong[1]);
						loopVisitHistorys.setActivityName("view Audit Asset");

					}

					

					newActivityLst.add(loopVisitHistorys);

				}

				activityResponse.setActiviList(newActivityLst);
				activityResponse.setMessage("Data Retrive successfully");
				activityResponse.setStatus("success");
				activityResponse.setStatusCode(0);

			} else {
				activityResponse.setMessage(" No Data Retrive ");
				activityResponse.setStatus("fail");
				activityResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			activityResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			activityResponse.setStatus("fail");
			activityResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return activityResponse;

	}

	@Override
	public ScheduleResponse getSchedule(int memberId) {
		List<CreateScheduleData> listCreateScheduleData = new ArrayList<>();
		List<CreateScheduleData> listCreateScheduleData1 = new ArrayList<>();

		ScheduleResponse scheduleResponse = new ScheduleResponse();
		try {
			String sche_sql = "SELECT * from tbl_schedule WHERE member_id=?";
			listCreateScheduleData = jdbcTemplate.query(sche_sql, new Object[] { memberId },
					new ScheduleByMemberMapper());

			String sc_sql = "SELECT a.retailer_shop_name,b.employee_name FROM tbl_retailer as a ,tbl_member as b where a.retailer_id=? and b.employee_id=?";

			if (listCreateScheduleData != null) {
				for (CreateScheduleData createScheduleData : listCreateScheduleData) {
					CreateScheduleData scheduleData = jdbcTemplate.query(sc_sql,
							new Object[] { createScheduleData.getRetailerId(), createScheduleData.getMemberId() },
							new ScheduleRawDataMapper());

					createScheduleData.setRetailerName(scheduleData.getRetailerName());
					createScheduleData.setMemberName(scheduleData.getMemberName());

					listCreateScheduleData1.add(createScheduleData);
				}
				scheduleResponse.setListCreateScheduleData(listCreateScheduleData1);
				scheduleResponse.setMessage("Data Retrive successfully");
				scheduleResponse.setStatus("success");
				scheduleResponse.setStatusCode(0);
			} else {
				scheduleResponse.setMessage(" No Data Retrive ");
				scheduleResponse.setStatus("fail");
				scheduleResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			scheduleResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			scheduleResponse.setStatus("fail");
			scheduleResponse.setStatusCode(-1);
			e.printStackTrace();
		}
		return scheduleResponse;
	}

	@Override
	public TargetResponse  getTarget(GenericRequest genericRequest) {
		Integer[] herchId = new Integer[3];
		List <Target> targetList = new ArrayList<>();
		List <Target> newTargetList = new ArrayList<>();
		TargetResponse targetResponse = new TargetResponse();
		try {
			String get_hechy = "select * from tbl_member where employee_id=? ";

			if (genericRequest.getRoleId()!=0){
					herchId = jdbcTemplate.query(get_hechy,
							new Object[] {genericRequest.getId() },
							new getHrchyResultExtrcter());
					String sche_sql = "SELECT * from tbl_target WHERE assigned_id IN ("+genericRequest.getId()+","+herchId[0]+","+herchId[1]+","+herchId[2]+") and target_month ='"+genericRequest.getMonth()+"' and targer_year ='"+genericRequest.getYear()+"' ";
					targetList = jdbcTemplate.query(sche_sql,
							new TargetRowMapper());
			}
			else{
			String sche_sql = "SELECT * from tbl_target WHERE assigned_id=? and target_month = '"+genericRequest.getMonth()+"' and targer_year = '"+genericRequest.getYear()+"' ";
			targetList = jdbcTemplate.query(sche_sql, new Object[] { genericRequest.getId() },
					new TargetRowMapper());
			}	
			if (targetList != null) {
				for (Target ter :targetList ){
				String name[] = jdbcTemplate.query("SELECT a.`employee_name` , b.`brand_name`,b.internal_brand_code,c.liscense_no FROM `tbl_member` AS a , `tbl_brand` AS b , tbl_dstributor_liscense as c  WHERE a.`employee_id`=? AND b.`brand_id`=? and c.liscense_id=(select liscense_id from tbl_brand where brand_id =?)", new Object[] { ter.getAssignId(),ter.getBrandId(),ter.getBrandId() },
						new FourValueResultExtracter());
				
						ter.setAssignName(name[0]);
						ter.setBrandName(name[1]);
						ter.setInternalBrandCode(name [2]);
						ter.setLicenceName(name[3]);
						newTargetList.add(ter);
				}
				targetResponse.setTargetList(newTargetList);
				targetResponse.setMessage("Data Retrive successfully");
				targetResponse.setStatus("success");
				targetResponse.setStatusCode(0);
			} else {
				targetResponse.setMessage(" No Data Retrive ");
				targetResponse.setStatus("fail");
				targetResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			targetResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			targetResponse.setStatus("fail");
			targetResponse.setStatusCode(-1);
			e.printStackTrace();
		}
		return targetResponse;
	}

	@Override
	public GenericResponse getScheme(GenericRequest genericRequest) {
		

		List<Scheme> lstScheme = new ArrayList<>();
		List<Scheme> newlstScheme = new ArrayList<>();
		SchemeResponse schemeResponse = new SchemeResponse();
	

		try {
				StringBuilder type_sql = new StringBuilder();
				type_sql.append("SELECT * FROM tbl_scheme_assign  ");
				if (genericRequest.getId() != 0) {
					type_sql.append(" where retailer_id = " + genericRequest.getId() + "");
				}
	
				type_sql.append(" order by id desc");
	
		

				lstScheme = jdbcTemplate.query(type_sql.toString(), new SchemeRowMapper());

			if (!lstScheme.isEmpty()) {
				for (Scheme scm : lstScheme) {
					Scheme scmDetail =jdbcTemplate.query("select * from tbl_scheme where id = ?", new Object[]{scm.getId()}, new SchemeRawDataRawMapper());
					scmDetail.setId(scm.getId());
					scmDetail.setRetailerId(scm.getRetailerId());
					scmDetail.setAssignDate(scm.getAssignDate());
				String [] name = jdbcTemplate.query("SELECT `brand_name`,`product_name` FROM `tbl_brand` AS a ,`tbl_product` AS b WHERE a.`brand_id` = ? AND b.`product_id` =?", new Object[]{scmDetail.getBrandId(),scmDetail.getProductId()}, new TwoValueresultExtracter());
					scmDetail.setProductName(name[1]);
					scmDetail.setBrandName(name[0]);
					newlstScheme.add(scmDetail);
				}

			 schemeResponse.setLstScheme(newlstScheme);
			 schemeResponse.setMessage("Data Retrive successfully");
			 schemeResponse.setStatus("success");
			 schemeResponse.setStatusCode(0);
			} else {
				schemeResponse.setMessage("Couldn't retrive data");
				schemeResponse.setStatus("fail");
				schemeResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			schemeResponse.setMessage("Data Couldn't retrive application server error");
			schemeResponse.setStatus("fail");
			schemeResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return schemeResponse;
	}

	@Override
	public GenericResponse getServey(GenericRequest genericRequest) {
		

		List<Servey> lstServey = new ArrayList<>();
		ServeyResponse serveyResponse = new ServeyResponse();
		
	

		try {
				StringBuilder type_sql = new StringBuilder();
				if (genericRequest.getOperationtype()!=null && genericRequest.getOperationtype().equalsIgnoreCase("ap")){
					
					type_sql.append("SELECT * FROM tbl_servey WHERE distributer_id = "+genericRequest.getDistributerId()+" AND servey_id NOT IN(SELECT `servey_id` FROM `tbl_users_answer` WHERE `retailer_id` = "+genericRequest.getId()+") ");

				}
				else {
					type_sql.append("SELECT * FROM tbl_servey where distributer_id ="+genericRequest.getDistributerId()+" order By servey_id desc ");

				}
			
	
		

				lstServey = jdbcTemplate.query(type_sql.toString(), new ServeyRowMapper());

			if (!lstServey.isEmpty()) {
				

				serveyResponse.setServeyList(lstServey);
				serveyResponse.setMessage("Data Retrive successfully");
				serveyResponse.setStatus("success");
				serveyResponse.setStatusCode(0);
			} else {
				serveyResponse.setMessage("Couldn't retrive data");
				serveyResponse.setStatus("fail");
				serveyResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			serveyResponse.setMessage("Data Couldn't retrive application server error");
			serveyResponse.setStatus("fail");
			serveyResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return serveyResponse;
	}

	
	
	
	@Override
	public GenericResponse getQestionAndAnswerByServeyId(GenericRequest genericRequest) {
		

		List<Questions> lstQuestions = new ArrayList<>();
	QuestionResponse qr = new QuestionResponse();
		List<AnswerResponse> answer = new ArrayList<>();
		List<Questions> newlstQuestions = new ArrayList<>();
		
	

		try {
				StringBuilder type_sql = new StringBuilder();
				type_sql.append("SELECT * FROM tbl_question where servey_id ="+genericRequest.getId()+"  ");
				
				lstQuestions = jdbcTemplate.query(type_sql.toString(), new QuestionRowMapper());
				if (!lstQuestions.isEmpty()){
				for (Questions qs :lstQuestions){
					answer = new ArrayList<>();
				answer = jdbcTemplate.query("select * from tbl_answer where question_id = "+qs.getQuestionId()+"", new AnswerRowMapper());
				qs.setAnswer(answer);
				newlstQuestions.add(qs);
				}
				
				
				
				

				qr.setQuestionList(newlstQuestions);
				qr.setMessage("Data Retrive successfully");
				qr.setStatus("success");
				qr.setStatusCode(0);
			} else {
				qr.setMessage("Couldn't retrive data");
				qr.setStatus("fail");
				qr.setStatusCode(1);
			}
		} catch (Exception e) {
			qr.setMessage("Data Couldn't retrive application server error");
			qr.setStatus("fail");
			qr.setStatusCode(-1);

			e.printStackTrace();

		}
		return qr;
	}

	@Override
	public GenericResponse getServeyReport(GenericRequest genericRequest) {
		
		ServeyReportResponse response = new ServeyReportResponse();
		List<ServeyReport> ServeyReportList = new ArrayList<>();
		
	

		try {
				StringBuilder type_sql = new StringBuilder();
				type_sql.append(" SELECT tbl_users_answer.`id`, tbl_users_answer.`user_answer_id`,tbl_users_answer.`question_id`,tbl_users_answer.`member_id`,tbl_users_answer.`retailer_id`,a.`answer` AS answerGiven,b.`questioon` AS questionAsk,c.`employee_name` AS srName,c.`role_id` AS roleName,d.`retailer_shop_name` AS retailerName FROM tbl_users_answer LEFT JOIN tbl_answer  AS a ON tbl_users_answer.`user_answer_id` = a.`answer_id` LEFT JOIN `tbl_question`AS b ON `tbl_users_answer`.`question_id` = `b`.`question_id` LEFT JOIN `tbl_member` AS c ON `tbl_users_answer`.`member_id` = `c`.`employee_id` LEFT JOIN `tbl_retailer` AS d ON `tbl_users_answer`.`retailer_id` = d.`retailer_id` WHERE tbl_users_answer.`servey_id`="+genericRequest.getId()+"");
				
				ServeyReportList = jdbcTemplate.query(type_sql.toString(), new ServeyReportRowMapper());
				if (!ServeyReportList.isEmpty()){
				System.out.println("serveyRList"+ServeyReportList.size());
			
					response.setLstServeyReport(ServeyReportList);
					response.setMessage("Data Retrive successfully");
					response.setStatus("success");
					response.setStatusCode(0);
			} else {
				response.setMessage("Couldn't retrive data");
				response.setStatus("fail");
				response.setStatusCode(1);
			}
		} catch (Exception e) {
			response.setMessage("Data Couldn't retrive application server error");
			response.setStatus("fail");
			response.setStatusCode(-1);

			e.printStackTrace();

		}
		return response;
	}

	@Override
	public ProductResponse getProductForDownload(List<Integer> productList) {/*
		List<Product> lstproduct = new ArrayList<>();
		List<Product> newlstproduct = new ArrayList<>();
		
		ProductResponse productResponse = new ProductResponse();

		try {
			// StringBuilder sql = new StringBuilder("select * from tbl_product
			// where distributor_id="+genericRequest.getDistributerId()+" ");

			String sql = "SELECT tbl_product.`product_id`,tbl_product.`product_name`,tbl_product.`brand_id`,tbl_product.`Product_code`,tbl_product.`PRODUCT_catagory_id`,tbl_product.`PRODUCT_segment_code`,tbl_product.`qtyML`,tbl_product.`box_size`,tbl_product.`liscense_id`,tbl_product.`excise_duty`,tbl_product.`status`,tbl_product.`created_date`,tbl_product.`updated_date`,tbl_product.`created_by_id`,tbl_product.`updated_by_id`,tbl_product.`distributor_id`,tbl_product.`pacakage_type_id`,tbl_product.`product_type_id`,tbl_product.`cif`,tbl_product.`wsp`,tbl_product.`mrp`,tbl_product.`product_sub_tyoe_id`,tbl_product.`isDelete`,`tbl_brand`.`brand_name` AS brandName,`tbl_product_catagory`.`name` AS categoryName,`tbl_product_segment`.`name` AS segmentName,`tbl_qty_pcs`.`quantity_in_ml` AS qtyML,`tbl_qty_pcs`.`pieces` AS qtyPcs,`tbl_dstributor_liscense`.`liscense_no` AS licenseName,`tbl_pacakage_type`.`name` AS packageName,`tbl_product_type`.`name` AS productTypeName,`tbl_product_sub_type`.`name` AS productSubTypeName FROM(`tbl_product`) INNER JOIN tbl_brand ON tbl_product.brand_id = tbl_brand.brand_id LEFT JOIN `tbl_product_catagory` ON `tbl_product`.`PRODUCT_catagory_id` = `tbl_product_catagory`.`id` LEFT JOIN `tbl_product_segment` ON `tbl_product`.`PRODUCT_segment_code` = `tbl_product_segment`.`id` LEFT JOIN `tbl_qty_pcs` ON `tbl_product`.`qtyML` = `tbl_qty_pcs`.`id` INNER JOIN `tbl_dstributor_liscense` ON `tbl_product`.`liscense_id` = tbl_dstributor_liscense.`liscense_id` LEFT JOIN `tbl_pacakage_type` ON `tbl_product`.`pacakage_type_id` = tbl_pacakage_type.`id` LEFT JOIN `tbl_product_type` ON `tbl_product`.`product_type_id` = tbl_product_type.`id` LEFT JOIN `tbl_product_sub_type` ON `tbl_product`.`product_sub_tyoe_id` = tbl_product_sub_type.`id`";

			Map<String, Integer> map = new HashMap<>();
			if (genericRequest.getDistributerId() != 0) {
				map.put("tbl_product.distributor_id", genericRequest.getDistributerId());
			}
			
			if (genericRequest.getId() != 0) {
				map.put("tbl_product.product_id", genericRequest.getId());
			}
			if (genericRequest.getBrandId() != 0) {
				map.put("tbl_product.brand_id", genericRequest.getBrandId());
			}

			String whereClause = getWhereClause(map);
			sql = sql + whereClause + " ORDER BY tbl_product.product_name";

			if (genericRequest.getOperationtype()!=null && genericRequest.getOperationtype().equalsIgnoreCase("jugaad")){
				sql = sql + "  LIMIT 500";	
				 
			}

			lstproduct = jdbcTemplate.query(sql.toString(), new ProductRowMapper());
			if (lstproduct != null) {
				if (genericRequest.getOperationtype() != null) {
				for (Product product : lstproduct) {
					String licenseName = jdbcTemplate.query(
							"SELECT `liscense_no` FROM `tbl_dstributor_liscense`WHERE`liscense_id`=?",
							new Object[] { product.getLicenseId() }, new GetEmpNameResultExtracter());
					Product idByproduct = jdbcTemplate.query(nameByid,
							new Object[] { product.getBranId(), product.getPackagetypeId(),
									product.getProductCategoryId(), product.getProductSegmentCode(),
									product.getProductSubtypeid(), product.getProductTypeId(), product.getQtyMl() },
							new ProductRawDataRowMapper());
					product.setLicenseName(licenseName);
					product.setBrandName(idByproduct.getBrandName());
					product.setBrandCode(idByproduct.getBrandCode());
					product.setInternalBrandCode(idByproduct.getInternalBrandCode());
					product.setPackagetype(idByproduct.getPackagetype());
					product.setProductcategoryName(idByproduct.getProductcategoryName());
					product.setProductSegmentName(idByproduct.getProductSegmentName());
					product.setProductSubTypeName(idByproduct.getProductSubTypeName());
					product.setProductTypeName(idByproduct.getProductTypeName());
					product.setQtyInmlName(idByproduct.getQtyInmlName());
					product.setQtyInpcsValue(idByproduct.getQtyInpcsValue());
					if (genericRequest.getOperationtype() != null) {
						if (genericRequest.getOperationtype().equalsIgnoreCgase("excise")) {

							String[] excisevalue = jdbcTemplate
									.query(" SELECT `TP_no`,`PO_no`,`net_payable_amount` FROM `tbl_excise_order_detail` WHERE `product_id`= "
											+ product.getProductId() + " ", new ThreeValueResultEXtracter());

							product.setTpNo(excisevalue[1]);
							product.setPoNo(excisevalue[2]);
							product.setNetPaybleAount(excisevalue[0]);
						}
						if (genericRequest.getOperationtype().equalsIgnoreCase("stock")) {

							String qty = jdbcTemplate.query(
									" SELECT `qty` FROM `tbl_stock_detail` WHERE `product_id`= ? and stock_id =? ",
									new Object[] { product.getProductId(), genericRequest.getStockId() },
									new GetEmpNameResultExtracter());
							if (qty != null) {
								product.setQty(Integer.parseInt(qty));
							}
						}
					}
					newlstproduct.add(product);

				}
				productResponse.setListProduct(newlstproduct);
				productResponse.setMessage("Data Retrive successfully");
				productResponse.setStatus("success");
				productResponse.setStatusCode(0);
				}
				else {
					productResponse.setListProduct(lstproduct);
					productResponse.setMessage("Data Retrive successfully");
					productResponse.setStatus("success");
					productResponse.setStatusCode(0);
					
					
				}
				
			} else {
				productResponse.setMessage(" No Data Retrive ");
				productResponse.setStatus("fail");
				productResponse.setStatusCode(1);
			}

		} catch (Exception e) {
			productResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
			productResponse.setStatus("fail");
			productResponse.setStatusCode(-1);
			e.printStackTrace();
		}

		return productResponse;

	*/
		return  null;}

	@Override
	public GenericResponse getShedule(GenericRequest genericRequest) {
		
		ScheduleResponse response = new ScheduleResponse();
		List<CreateScheduleData> createScheduleDataList = new ArrayList<>();
		
	

		try {
				StringBuilder type_sql = new StringBuilder();
				type_sql.append("SELECT tbl_schedule.`id`, tbl_schedule.`title`,tbl_schedule.`retailer_id`,tbl_schedule.`assigner_id`,tbl_schedule.`note`, tbl_schedule.`visit_date`,tbl_schedule.`start_time`,tbl_schedule.`end_time`, tbl_schedule.`assigned_id`,`tbl_retailer`.`retailer_shop_name` AS retailerName ,`a`.`employee_name` AS assignerName ,`a`.`role_id` AS role_id,`b`.`employee_name` AS assignName FROM `tbl_schedule` LEFT  JOIN tbl_retailer  ON tbl_schedule.`retailer_id` = tbl_retailer.`retailer_id` LEFT  JOIN tbl_member AS a ON tbl_schedule.`assigner_id` = a.`employee_id` LEFT  JOIN tbl_member AS b ON tbl_schedule.`assigned_id` = b.`employee_id` WHERE tbl_schedule.`assigned_id` = "+genericRequest.getId()+" AND tbl_schedule.visit_date  >= STR_TO_DATE('"+genericRequest.getStartDate()+"', '%Y-%m-%d') order by tbl_schedule.`visit_date` desc ");
				
				createScheduleDataList = jdbcTemplate.query(type_sql.toString(), new SheduleRowMapper());
				if (!createScheduleDataList.isEmpty()){
				
			
					response.setListCreateScheduleData(createScheduleDataList);;
					response.setMessage("Data Retrive successfully");
					response.setStatus("success");
					response.setStatusCode(0);
			} else {
				response.setMessage("Couldn't retrive data");
				response.setStatus("fail");
				response.setStatusCode(1);
			}
		} catch (Exception e) {
			response.setMessage("Data Couldn't retrive application server error");
			response.setStatus("fail");
			response.setStatusCode(-1);

			e.printStackTrace();

		}
		return response;
	}
	
    public String string(List list) {
        StringBuilder ss=new StringBuilder();
      int s=list.size();

                 for(Object i:list) {
                         if(s<=1) {
                                 ss.append(i);
                         }
                         else {
                                 ss.append(i+",");
                         }
                         s--;
                 }

            return ss.toString();

       }
	

	 @Override 
     public SearchProductResponse searchProduct(List brandId,List categoryId,List typeId,List subTypeId,List liscenseId,List productId){
        
         SearchProductResponse searchProductResponse=new SearchProductResponse();
       List<SearchProductData> listSearchProductData=new ArrayList<SearchProductData>();
      List<SearchProductData> listSearchProductData1=new ArrayList<SearchProductData>();
     
       
       try{
            StringBuilder sb=new StringBuilder();             
           
            
           if(!brandId.isEmpty()){
               String ss=string(brandId);
               sb.append("brand_id IN ("+ss+") ");
           }
           if(!categoryId.isEmpty()){
               String ss=string(categoryId);
                 if(brandId.isEmpty()){
                   sb.append("PRODUCT_catagory_id IN ("+ss+") "); 
                }else{
                    sb.append(" and PRODUCT_catagory_id IN ("+ss+") ");
                }
                
           }
           if(!typeId.isEmpty()){
               String ss=string(typeId);
               if(brandId.isEmpty() && categoryId.isEmpty()){
               sb.append(" product_type_id IN ("+ss+") ");
               }else{
                   sb.append("and product_type_id IN ("+ss+") ");
               }
           }
           if(!subTypeId.isEmpty()){
               String ss=string(subTypeId);
               if(brandId.isEmpty() && categoryId.isEmpty() && typeId.isEmpty()){
                  sb.append(" product_sub_tyoe_id IN ("+ss+")"); 
               }
               else{
               sb.append("and product_sub_tyoe_id IN ("+ss+")");
                       }
           }
           if(!liscenseId.isEmpty()){
               String ss=string(liscenseId);
               if(brandId.isEmpty() && categoryId.isEmpty() && typeId.isEmpty() && subTypeId.isEmpty()){
                   sb.append(" liscense_id In("+ss+") ");
               }else{
                   sb.append("and  liscense_id In("+ss+") ");
               }
           }
           if(!productId.isEmpty()){
        	   String ss=string(productId);
               if(brandId.isEmpty() && categoryId.isEmpty() && typeId.isEmpty() && subTypeId.isEmpty() && liscenseId.isEmpty()){
            	  sb.append(" product_id IN("+ss+")"); 
               }else{
            	   sb.append(" and product_id In("+ss+")");
               }

           }
           
           
         String sql="SELECT * from tbl_product WHERE "+sb+"";
           System.out.println(sql);
           listSearchProductData=jdbcTemplate.query(sql,new SearchMapper());
           String nameByid = "select a.brand_name,a.brand_code,a.internal_brand_code,b.name,c.name,d.name,e.name,f.name,g.quantity_in_ml,g.pieces from tbl_brand as a ,tbl_pacakage_type as b,tbl_product_catagory as c , tbl_product_segment as d  , tbl_product_sub_type as e ,tbl_product_type as f ,tbl_qty_pcs as g where a.brand_id=? and b.id =? and c.id =? and d.id =? and e.id =? and f.id =? and g.id=? ";
           
           
           if (!listSearchProductData.isEmpty()){
                     for(SearchProductData newSearchProductData:listSearchProductData){
                         
                         System.out.println(newSearchProductData.getProductName());
                         SearchProductData product = jdbcTemplate.query(nameByid,new Object[]{newSearchProductData.getBrandId(),newSearchProductData.getPackagetypeId(),newSearchProductData.getProductCategoryId(),newSearchProductData.getProductSegmentCode(),newSearchProductData.getProductSubtypeid(),newSearchProductData.getProductTypeId(),newSearchProductData.getQtyMl()},new SearchProductMapper());
						newSearchProductData.setBrandName(product.getBrandName());
						newSearchProductData.setBrandCode(product.getBrandCode());
						newSearchProductData.setInternalBrandCode(product.getInternalBrandCode());
						newSearchProductData.setPackagetype(product.getPackagetype());
						newSearchProductData.setProductcategoryName(product.getProductcategoryName());
						newSearchProductData.setProductSegmentName(product.getProductSegmentName());
						newSearchProductData.setProductSubTypeName(product.getProductSubTypeName());
						newSearchProductData.setProductTypeName(product.getProductTypeName());
						newSearchProductData.setQtyInmlName(product.getQtyInmlName());
						newSearchProductData.setQtyInpcsValue(product.getQtyInpcsValue());
                                             
						listSearchProductData1.add(newSearchProductData);
					}
                         
                         
                         
                             searchProductResponse.setListSearchProductData(listSearchProductData1);
         	                searchProductResponse.setMessage("Data Retrive successfully");
				searchProductResponse.setStatus("success");
				searchProductResponse.setStatusCode(0);
			} else {
				searchProductResponse.setMessage("Couldn't retrive data");
				searchProductResponse.setStatus("fail");
				searchProductResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			searchProductResponse.setMessage("Data Couldn't retrive application server error");
			searchProductResponse.setStatus("fail");
			searchProductResponse.setStatusCode(-1);

			e.printStackTrace();

		}
       return searchProductResponse;
     }
   
	 @Override
		public RetailerRawDataResponse getRetailerSearchById(int id,String name) {
			List<RetailerRawData> lstRetailerRawData = new ArrayList<>();
			List<RetailerRawData> newlstRetailerRawData = new ArrayList<>();

			RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
			try {
				
				String getRCatSql = "Select * from tbl_retailer where (sr_id=? or tsm_id=? or asm_id=? or manager_id=?) and retailer_shop_name LIKE '"+name+"%'  and blocked=1 ";
				
				lstRetailerRawData = jdbcTemplate.query(getRCatSql, new Object[] {id,id,id,id}, new RetailerRawdataMapper());
					if (!lstRetailerRawData.isEmpty()) {
	                                  
						newlstRetailerRawData=setRetailerData(lstRetailerRawData);

						
						retailerRawDataResponse.setLstRetailerRawData(newlstRetailerRawData);
						retailerRawDataResponse.setMessage("Data Retrive successfully");
						retailerRawDataResponse.setStatus("success");
						retailerRawDataResponse.setStatusCode(0);
						} else {
					retailerRawDataResponse.setMessage(" No Data Retrive ");
					retailerRawDataResponse.setStatus("fail");
					retailerRawDataResponse.setStatusCode(1);
				}

			} catch (Exception e) {
				retailerRawDataResponse.setMessage(" Data Retrive Activity Failed Due to Application Error ");
				retailerRawDataResponse.setStatus("fail");
				retailerRawDataResponse.setStatusCode(-1);
				e.printStackTrace();
			}

			return retailerRawDataResponse;

		}
	

}
