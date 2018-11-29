package com.rc.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.ByteArrayInputStream;

import java.io.InputStream;



import javax.imageio.ImageIO;


import sun.misc.BASE64Decoder;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.rc.dao.GetResorcesDao;
import com.rc.dao.UpdateResourceDao;
import com.rc.model.AddRetailerRequest;
import com.rc.model.AssetByRetailerData;
import com.rc.model.AuditAssetReatiler;
import com.rc.model.Brand;
import com.rc.model.CommonDetail;
import com.rc.model.GenericResponse;
import com.rc.model.Member;
import com.rc.model.MemberResponse;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;
import com.rc.model.RetailerBlockedORDefaulterReq;
import com.rc.model.RetailerDetailedData;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UpdateResourceDaoimpl implements UpdateResourceDao {
	private final JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager transactionManager;

	Integer[] herchId = new Integer[3];

	public UpdateResourceDaoimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);// To change body of
		// generated methods, choose
		// Tools | Templates.
	}

	public UpdateResourceDaoimpl(DataSource dataSource, PlatformTransactionManager transactionManager2) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.transactionManager = transactionManager2;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	@Autowired
	public GetResorcesDao getResorcesDao;

	@Override
	public GenericResponse verifyRetailer(AddRetailerRequest addRetailerRequest) {
		if(addRetailerRequest.getShopImage()!=null){
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss:SSS");  
			  LocalDateTime now = LocalDateTime.now(); 
			   
			addRetailerRequest.setShopImage(imageDecode(addRetailerRequest.getShopImage(),dtf.format(now),"retailerImage"));
		}
		GenericResponse genericResponse = new GenericResponse();
		try {
			System.out.println("shop image path "+addRetailerRequest.getShopImage());
			System.out.println("add Retailer Request==>" + addRetailerRequest);
			String updateTableSQL = "UPDATE tbl_retailer SET verfied =?,latitude=?,longitude=?,retailer_shop_image=? WHERE retailer_id = ?";

			int update = jdbcTemplate.update(updateTableSQL,
					new Object[] { addRetailerRequest.getIsVerified(),
							addRetailerRequest.getLatitude() != null ? addRetailerRequest.getLatitude() : null,
							addRetailerRequest.getLongitude() != null ? addRetailerRequest.getLongitude() : null,
							addRetailerRequest.getShopImage() != null ? addRetailerRequest.getShopImage() : null,
							addRetailerRequest.getRetailerId() });
			if (update > 0) {
				genericResponse.setMessage("Retailer   updated successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't update Retailer.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Retailer application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateMember(Member memberDetail) {

		GenericResponse genericResponse = new GenericResponse();
		MemberResponse memberResponse = new MemberResponse();
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		int oldAsmid = 0;
		int oldTsmId = 0;
		int oldManagerId = 0;

		try {

			String get_hechy = "select * from tbl_member where employee_id=?";

			String member_sql = "UPDATE tbl_member SET employee_name =?,mobile=?,alternative_mobile=?,address=?,designation_id=?,tsm_id=?,asm_id=?,manager_id=?,state=?,report_to=?,joining_date=?,update_date=?,update_by_id=? WHERE employee_id=?";
			memberResponse = getResorcesDao.getTeamById(memberDetail.getEmployeeId());
			for (Member m : memberResponse.getLstMemberResponse()) {
				oldAsmid = m.getAsmId();
				oldTsmId = m.getTsmId();
				oldManagerId = m.getManagerId();
			}
			
			if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 37) {
				
				herchId[2] = memberDetail.getReportTo();
				
			}
			
			if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 40) {
				herchId = jdbcTemplate.query(get_hechy,
						new Object[] { memberDetail.getReportTo() != 0 ? memberDetail.getReportTo() : 0 },
						new getHrchyResultExtrcter());
				herchId[1] = memberDetail.getReportTo();
				String retId = "select retailer_id from tbl_retailer where sr_id =" + memberDetail.getEmployeeId() + "";
				List<Integer> rs = jdbcTemplate.query(retId, new GetidListRowMapper());
				String retsql = "UPDATE tbl_retailer SET manager_id = " + herchId[2] + ",asm_id=" + herchId[0]
						+ ",tsm_id =" + herchId[1] + "  WHERE retailer_id =?";
				for (Integer in : rs) {
					jdbcTemplate.update(retsql, new Object[] { in });
				}

			}
			if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 39) {
				herchId = jdbcTemplate.query(get_hechy,
						new Object[] { memberDetail.getReportTo() != 0 ? memberDetail.getReportTo() : 0 },
						new getHrchyResultExtrcter());
				herchId[0] = memberDetail.getReportTo();
				System.out.println("report To ==>" + memberDetail.getReportTo());
				System.out.println("herchid" + herchId[0]);
				System.out.println("herchid" + herchId[1]);
				System.out.println("herchid" + herchId[2]);
				String empId = "select employee_id from tbl_member where tsm_id = " + memberDetail.getEmployeeId()
						+ " and asm_id = " + oldAsmid + " and manager_id = " + oldManagerId + "";
				String updateId = "UPDATE tbl_member set manager_id = " + herchId[2] + ", asm_id =" + herchId[0]
						+ " where employee_id =? ";
				List<Integer> ls = jdbcTemplate.query(empId, new GetidListRowMapper());
				for (Integer in : ls) {
					jdbcTemplate.update(updateId, new Object[] { in });
				}
				String retId = "select retailer_id from tbl_retailer where tsm_id =" + memberDetail.getEmployeeId()
						+ "";
				String retsql = "UPDATE tbl_retailer SET manager_id = " + herchId[2] + ",asm_id=" + herchId[0]
						+ " WHERE retailer_id =?";

				List<Integer> rs = jdbcTemplate.query(retId, new GetidListRowMapper());
				for (Integer in : rs) {
					jdbcTemplate.update(retsql, new Object[] { in });
				}
				String sql = "UPDATE tbl_member SET manager_id = " + herchId[2] + " WHERE employee_id =?";
				jdbcTemplate.update(sql, new Object[] { memberDetail.getEmployeeId() });

			}
			if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 38) {
				herchId = jdbcTemplate.query(get_hechy,
						new Object[] { memberDetail.getReportTo() != 0 ? memberDetail.getReportTo() : 0 },
						new getHrchyResultExtrcter());
				herchId[2] = memberDetail.getReportTo();

				String emplid = "select employee_id from tbl_member where asm_id =" + memberDetail.getEmployeeId()
						+ " and manager_id = " + oldManagerId + "";
				String retId = "select retailer_id from tbl_retailer where asm_id =" + memberDetail.getEmployeeId()
						+ " and manager_id = " + oldManagerId + "";
				List<Integer> ls = jdbcTemplate.query(emplid, new GetidListRowMapper());
				List<Integer> rs = jdbcTemplate.query(retId, new GetidListRowMapper());
				String sql = "UPDATE tbl_member SET manager_id = " + memberDetail.getReportTo()
						+ " WHERE employee_id =?";
				String retsql = "UPDATE tbl_retailer SET manager_id = " + memberDetail.getReportTo()
						+ " WHERE retailer_id =?";
				for (Integer in : ls) {
					System.out.println("employee id for asm update = " + in);
					jdbcTemplate.update(sql, new Object[] { in });
				}
				for (Integer in : rs) {
					jdbcTemplate.update(retsql, new Object[] { in });
				}
				System.out.println("role Id 38 manager id " + herchId[2]);
			}

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int update = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pst = con.prepareStatement(member_sql, new String[] { "employee_id" });
					pst.setString(1, memberDetail.getEmployeeName());
					pst.setString(2, memberDetail.getMobile());
					pst.setString(3, memberDetail.getAlternatemobileNumber());
					pst.setString(4, memberDetail.getAdddress());
					pst.setInt(5, memberDetail.getDesignationId());
					System.out.println("herchid" + herchId[1]);
					System.out.println("herchid" + herchId[0]);
					System.out.println("herchid" + herchId[2]);

					pst.setInt(6, herchId[1] != null ? herchId[1] : 0);
					pst.setInt(7, herchId[0] != null ? herchId[0] : 0);
					pst.setInt(8, herchId[2] != null ? herchId[2] : 0);
					pst.setString(9, memberDetail.getState());
					pst.setInt(10, memberDetail.getReportTo());
					pst.setString(11, memberDetail.getJoiningDate() != null ? memberDetail.getJoiningDate() : null);
					pst.setString(12, new Timestamp(System.currentTimeMillis()).toString());
					pst.setInt(13, memberDetail.getUpdateById());
					pst.setInt(14, memberDetail.getEmployeeId());
					return pst;
				}
			},

					keyHolder);
			BigInteger key = (BigInteger) keyHolder.getKey();

			System.out.println("Key ====>" + key);

			if (update > 0) {

				genericResponse.setMessage("Member updated successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
				transactionManager.commit(status);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Member application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();
			transactionManager.rollback(status);

		}

		return genericResponse;

	}

	@Override
	public GenericResponse makeRetailerDefaulter(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_retailer SET defaulter =?,defaulter_date=?,defaulter_by_id=?,defalter_remarks=? WHERE retailer_id = ?";

			int update = jdbcTemplate.update(updateTableSQL, new Object[] { 2,
					new Timestamp(System.currentTimeMillis()), retailerBlockedORDefaulterReq.getOpertaionById(),
					retailerBlockedORDefaulterReq.getRemarks(), retailerBlockedORDefaulterReq.getRetailerId() });
			if (update > 0) {
				genericResponse.setMessage("Defalter Set  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't set defaluter.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't set defaluter application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse makeRetailerBlocked(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_retailer SET blocked =?,blocked_date=?,blocked_by_id=?,blocked_remarks=? WHERE retailer_id = ?";

			int update = jdbcTemplate.update(updateTableSQL, new Object[] { 2,
					new Timestamp(System.currentTimeMillis()), retailerBlockedORDefaulterReq.getOpertaionById(),
					retailerBlockedORDefaulterReq.getRemarks(), retailerBlockedORDefaulterReq.getRetailerId() });
			if (update > 0) {
				genericResponse.setMessage("Defalter Set  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't set defaluter.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't set defaluter application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse makeRetailerUnDefaulter(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_retailer SET defaulter =? WHERE retailer_id = ?";

			int update = jdbcTemplate.update(updateTableSQL,
					new Object[] { 1, retailerBlockedORDefaulterReq.getRetailerId() });
			if (update > 0) {
				genericResponse.setMessage("Defalter Set  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't set defaluter.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't set defaluter application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse makeRetailerUnBlocked(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_retailer SET blocked =? WHERE retailer_id = ?";

			int update = jdbcTemplate.update(updateTableSQL,
					new Object[] { 1, retailerBlockedORDefaulterReq.getRetailerId() });
			if (update > 0) {
				genericResponse.setMessage("Defalter Set  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't set defaluter.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't set defaluter application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateRole(CommonDetail commonDetail) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_role SET role_name =?,description=?,status=? WHERE id = ?";

			int update = jdbcTemplate.update(updateTableSQL, new Object[] { commonDetail.getName(),
					commonDetail.getDescription(), commonDetail.getDetailStatus(), commonDetail.getId() });
			if (update > 0) {
				genericResponse.setMessage("Role Update  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't update Role.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't Update Role application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateDesignation(CommonDetail commonDetail) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_designation SET designation_name =?,description=?,update_date=?,status=? WHERE designation_id = ?";

			int update = jdbcTemplate.update(updateTableSQL,
					new Object[] { commonDetail.getName(), commonDetail.getDescription(),
							new Timestamp(System.currentTimeMillis()), commonDetail.getDetailStatus(),
							commonDetail.getId() });
			if (update > 0) {
				genericResponse.setMessage("Designation Updated  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't update Designation.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't Update Designation application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateRetailer(AddRetailerRequest updateRetailerRequest) {

		GenericResponse genericResponse = new GenericResponse();
		try {

			System.out.println("insise saveRetailer impl ");
			String ret_sql = "UPDATE tbl_retailer SET retailer_shop_name =?, contact_person_name =? , contact_person_name1 =?, contact_person_name2 =? , credit_days =?, email =?, mobile =?, alt_mobile1 =?, alt_mobile2 =?, landline =?, sr_id =?, tsm_id =?, asm_id =?, retailer_type_id =?, retailer_sub_type_id =?, retailer_catagory_id =?, group_id =?, isgroup =?,   locality =?, street =?, city =?, pincode =?, zoneid =?, updated_by =?, updated_date =?,distributor_id =?,retailer_excise_code=?,manager_id=? WHERE retailer_id=?";

			int update = jdbcTemplate.update(ret_sql,
					new Object[] { updateRetailerRequest.getRetailerShopname(),
							updateRetailerRequest.getContactPersaonName1(),
							updateRetailerRequest.getContactPersaonName2(),
							updateRetailerRequest.getContactPersaonName3(), updateRetailerRequest.getCreditDays(),
							updateRetailerRequest.getRetailerEmailId(), updateRetailerRequest.getMobileNumber(),
							updateRetailerRequest.getAlternateMobileNumber1(),
							updateRetailerRequest.getAlternateMobileNumber2(), updateRetailerRequest.getLandline(),
							updateRetailerRequest.getSrId() != 0 ? updateRetailerRequest.getSrId() : null,
							updateRetailerRequest.getTsmId() != 0 ? updateRetailerRequest.getTsmId() : null,
							updateRetailerRequest.getAsmId() != 0 ? updateRetailerRequest.getAsmId() : null,
							updateRetailerRequest.getTypeId() != 0 ? updateRetailerRequest.getTypeId() : null,
							updateRetailerRequest.getSubTypeId() != 0 ? updateRetailerRequest.getSubTypeId() : null,
							updateRetailerRequest.getCategoryid() != 0 ? updateRetailerRequest.getCategoryid() : null,
							updateRetailerRequest.getGroupId() != 0 ? updateRetailerRequest.getGroupId() : null,
							updateRetailerRequest.getIsGroup() != 0 ? 2 : 1, updateRetailerRequest.getLocality(),
							updateRetailerRequest.getStreet(), updateRetailerRequest.getCity(),
							updateRetailerRequest.getPincode(),
							updateRetailerRequest.getZoneId() != 0 ? updateRetailerRequest.getZoneId() : null,
							updateRetailerRequest.getUpdatedById(), new Timestamp(System.currentTimeMillis()),
							updateRetailerRequest.getDistributerId() != 0 ? updateRetailerRequest.getDistributerId()
									: null,
							updateRetailerRequest.getRetailerExciseCode(),
							updateRetailerRequest.getManagerId() != 0 ? updateRetailerRequest.getManagerId() :null, updateRetailerRequest.getRetailerId()});

			System.out.println("update===>" + update);

			if (update > 0) {
				genericResponse.setMessage("Retailer   updated  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't Update Retailer.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Retailer application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateRetailerType(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			

				String ret_cat_sql = "UPDATE tbl_retailer_type SET retailer_type =? , description =? , updated_date=? WHERE id =?) ";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(),new Timestamp(System.currentTimeMillis()).toString(),retailerCategoryRequest.getId() });
				if (update > 0) {
					genericResponse.setMessage("Retailer type  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update Retailer type.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Retailer type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateRetailerSubType(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			

				String ret_cat_sql = "UPDATE tbl_retailer_sub_type SET retailer_sub_type =? , description =? , updated_date=? WHERE id =?) ";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(),new Timestamp(System.currentTimeMillis()).toString(),retailerCategoryRequest.getId()});
				if (update > 0) {
					genericResponse.setMessage("Retailer subtype  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update Retailer subtype.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Retailer subtype application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateRetailerCategory(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			

				String ret_cat_sql = "UPDATE tbl_retailer_catagory SET retailer_catagory =? , description =? , updated_date=? WHERE id =?) ";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(),new Timestamp(System.currentTimeMillis()).toString(),retailerCategoryRequest.getId()});
				if (update > 0) {
					genericResponse.setMessage("Retailer category  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update Retailer category.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Retailer category application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateZone(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			

				String ret_cat_sql = "UPDATE tbl_zone SET tittle =? , description =? , updated_date=? WHERE id =?) ";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(),new Timestamp(System.currentTimeMillis()).toString(),retailerCategoryRequest.getId()});
				if (update > 0) {
					genericResponse.setMessage("zone  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't zone ");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update zone application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateAsset(AssetByRetailerData asset) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			
				String assetSql = "UPDATE tbl_assets SET asset_name=?,asset_type=?,last_update =?,description =?,brand_id =?,product_id =? where id=?";

				int update = jdbcTemplate.update(assetSql, new Object[] { asset.getAssetName(), asset.getAssetType(),
						(new Timestamp(System.currentTimeMillis())), asset.getDescription(), 
						asset.getBrandId(), asset.getProductId(),asset.getId() });
				if (update > 0) {
					genericResponse.setMessage("Asset  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update Asset.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Asset application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse makeRetailerActive(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_retailer SET status =? WHERE retailer_id = ?";

			int update = jdbcTemplate.update(updateTableSQL, new Object[] { 1,retailerBlockedORDefaulterReq.getRetailerId() });
			if (update > 0) {
				genericResponse.setMessage("Active Set  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't set Active.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't set Active application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse makeRetailerInactive(RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

		GenericResponse genericResponse = new GenericResponse();
		try {
			String updateTableSQL = "UPDATE tbl_retailer SET status =? WHERE retailer_id = ?";

			int update = jdbcTemplate.update(updateTableSQL, new Object[] { 2,retailerBlockedORDefaulterReq.getRetailerId() });
			if (update > 0) {
				genericResponse.setMessage("Inactive Set  successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't set inactive.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't set Inactive application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateProduct(Product product) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			System.out.println("product===>" + product);

			
				String role_sql = "UPDATE tbl_product SET brand_id=?, Product_code=?, PRODUCT_catagory_id =?, PRODUCT_segment_code =?, qtyML =?, box_size =?,status =?,  updated_by_id =?,  pacakage_type_id =?, product_type_id =?, cif =?, wsp =?, mrp =?,liscense_id =?,product_sub_tyoe_id =?,excise_duty=? WHERE product_id=? ";
				int update = jdbcTemplate.update(role_sql,
						new Object[] { product.getBranId(), product.getProductCode(),
								product.getProductCategoryId(), product.getProductSegmentCode(), product.getQtyMl(),
								product.getBoxSize(), product.getProductStatus(),
								 product.getUpdateById(),
								 product.getPackagetypeId(), product.getProductTypeId(),
								product.getCif(), product.getWsp(), product.getMrp(), product.getLicenseId(),
								product.getProductSubtypeid(),product.getExciseDuty(),product.getProductId()});
				
				if (update > 0) {
					genericResponse.setMessage("Product  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update Product.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Product application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateBrand(Brand brand) {
		GenericResponse genericResponse = new GenericResponse();
		try {

		
			
				String sql = "UPDATE  tbl_brand SET brand_name, brand_code,internal_brand_code,principle,brand_owner,liscense_id,description,state_id where tbl_brand =? ";
				int update = jdbcTemplate.update(sql,
						new Object[] { brand.getBrandName(), brand.getBrandCode(), brand.getInternalBrandCode(),
								brand.getPrincipal(), brand.getBrandowner(), brand.getLicenseId(),
								brand.getDescription(), brand.getStateId(), brand.getBrandId() });
				if (update > 0) {
					genericResponse.setMessage("brand  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update brand.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update brand application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updatePackagetype(ProductRawdata packageType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_pacakage_type WHERE name=? AND id!="+packageType.getId()+"";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { packageType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "UPDATE tbl_pacakage_type SET name=?,description=? WHERE id=? " ;
				int update = jdbcTemplate.update(sql,
						new Object[] { packageType.getName(), packageType.getDescription() , packageType.getId() });
				if (update > 0) {
					genericResponse.setMessage("Package Type  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update Package Type.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't Package Type name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update Package Type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateProductCategory(ProductRawdata productCategory) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_catagory WHERE name=? AND id!="+productCategory.getId()+"";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { productCategory.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "UPDATE tbl_product_catagory SET name=?,description=? WHERE id=? " ;
				int update = jdbcTemplate.update(sql,
						new Object[] { productCategory.getName(), productCategory.getDescription() , productCategory.getId() });
				if (update > 0) {
					genericResponse.setMessage("  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update ");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't  name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateProductSegment(ProductRawdata productSegment) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_segment WHERE name=? AND id!="+productSegment.getId()+"";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { productSegment.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "UPDATE tbl_product_segment SET name=?,description=? WHERE id=? " ;
				int update = jdbcTemplate.update(sql,
						new Object[] { productSegment.getName(), productSegment.getDescription() , productSegment.getId() });
				if (update > 0) {
					genericResponse.setMessage("  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update ");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't  name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateProductSubType(ProductRawdata productSubType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_sub_type WHERE name=? AND id!="+productSubType.getId()+"";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { productSubType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "UPDATE tbl_product_sub_type SET name=?,description=? WHERE id=? " ;
				int update = jdbcTemplate.update(sql,
						new Object[] { productSubType.getName(), productSubType.getDescription() , productSubType.getId() });
				if (update > 0) {
					genericResponse.setMessage("  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update ");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't  name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse updateProductType(ProductRawdata productType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_type WHERE name=? AND id!="+productType.getId()+"";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { productType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "UPDATE tbl_product_type SET name=?,description=? WHERE id=? " ;
				int update = jdbcTemplate.update(sql,
						new Object[] { productType.getName(), productType.getDescription() , productType.getId() });
				if (update > 0) {
					genericResponse.setMessage("  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update ");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't  name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}
	
	
	public String imageDecode(String s, String feed,String type) {

		String upload = null;

		try {

			FTPClient client = new FTPClient();
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodeByte = decoder.decodeBuffer(s);

			client.enterLocalPassiveMode();

			String sb = "public_html/bailiwicksolution.com/sales-force-admin/vendor/image/"+type+"/";

			client.connect("www.bailiwicksolution.com");
			client.login("managbyc", "B@$#%*90!i");
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.enterLocalPassiveMode();

			upload = sb.concat("image"+feed+".png");
			InputStream inputStream = new ByteArrayInputStream(decodeByte);

			try {
				client.storeFile( upload,inputStream);
				client.logout();
				client.disconnect();
			} catch (Exception e) {
                   
			}

			System.out.println("done");
			/* ======================================================================= */

		} catch (Exception e) {

		}

		String urlForImage=upload.replace("public_html/","http://");
		return urlForImage;
	}

	@Override
		
	public GenericResponse auditAsset(AuditAssetReatiler auditAssetReatiler) {
		GenericResponse genericResponse = new GenericResponse();
		try {
				String sql = "UPDATE tbl_asset_assign SET isAvailable=?,feedback_rating_id=?,description=?,image1=?,image2=?,image3=? WHERE id=? " ;
				int update = jdbcTemplate.update(sql,
						new Object[] { auditAssetReatiler.getIsAvailable(), auditAssetReatiler.getRatingId() , auditAssetReatiler.getDescription(),auditAssetReatiler.getImage1(),auditAssetReatiler.getImage2(),auditAssetReatiler.getImage3(),auditAssetReatiler.getAssignId() });
				if (update > 0) {
					genericResponse.setMessage("  updated successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't update ");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			 
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't update application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}
	
	

}
