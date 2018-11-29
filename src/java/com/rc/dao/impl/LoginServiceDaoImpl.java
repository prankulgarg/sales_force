/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
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

import com.rc.dao.LoginServiceDao;
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
import com.rc.model.Feedback;
import com.rc.model.GenericResponse;
import com.rc.model.LoginDetail;
import com.rc.model.LoginResponse;
import com.rc.model.Member;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;
import com.rc.model.PromiseOrder;
import com.rc.model.PromiseOrderProductDetail;
import com.rc.model.QtyInPcs;
import com.rc.model.Questions;
import com.rc.model.RetailerDetailedData;
import com.rc.model.Route;
import com.rc.model.ScheduleResponse;
import com.rc.model.Scheme;
import com.rc.model.Servey;
import com.rc.model.ServeyResponse;
import com.rc.model.SubmitServey;
import com.rc.model.Target;
import com.rc.model.UserDetail;

import exception.AccessDeniedException;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Prankul Garg
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class LoginServiceDaoImpl implements LoginServiceDao {

	private final JdbcTemplate jdbcTemplate;

	private PlatformTransactionManager transactionManager;
	Integer[] herchId = new Integer[3];
	public static final String checkVisit = " select activity_perform_count from tbl_last_visit where visitor_id=?";
	public static final String checkVisitRetailer = " select activity_perform_count from tbl_last_visit where  retailer_id =? and visitor_id=? ";
	public static final String sqlVisitmaster = "INSERT INTO tbl_visit_master(visitor_id,retailer_id,visit_DateTime,Activity_performed_count) "
			+ " VALUES(?,?,?,?)";
	public static final String sqlLastVisit = "INSERT INTO tbl_last_visit(visit_id,visitor_id,retailer_id,visit_date_time,activity_perform_count) "
			+ " VALUES(?,?,?,?,?)";
	public static final String sql = "INSERT INTO tbl_feedback(visitor_id,retailer_id,feedback_type_id,feedback_rating_id,visit_id,visit_date,feedback_tittle,feedback_description,feedback_image,lattitude,longitude,distance,distributor_id) "
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_AUDIT_ASSEST = "INSERT INTO `tbl_asset_audit`(`asset_id`,`retailer_id`, `qty`,`amount`,`assign_date`, `isAvailable`, `feedback_rating_id`, `description`, `image1`, `image2`, `image3`, `audit_by_id`,`audit_by_role`,`audit_date`,latitude,longitude) "
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String INSERTORDER = "INSERT INTO tbl_promise_order(visitor_id,retailer_id,order_remarks,promise_date,taken_date,visit_id,lattitude,longitude,distance,distributer_id) "
			+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERTSTOCK = "INSERT INTO tbl_stock(visitor_id,retailer_id,remarks,taken_date,visit_id,lattitude,longitude,distance,distributer_id) "
			+ " VALUES(?,?,?,?,?,?,?,?,?)";

	public static final String visitinfo = "INSERT INTO tbl_visit_info(visit_id,visitor_id,retailer_id,activity_performed,activity_type,activity_id,date) "
			+ " VALUES(?,?,?,?,?,?,?)";
	public static final String INSERT_ORDER_PRODUCT_WISE = "INSERT INTO tbl_promise_order_detail(order_id,product_id,brand_id,qty) "
			+ " VALUES(?,?,?,?)";
	public static final String INSERT_STOCK_PRODUCT_WISE = "INSERT INTO tbl_stock_detail(stock_id,product_id,brand_id,qty) "
			+ " VALUES(?,?,?,?)";

	public LoginServiceDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);// To change body of
													// generated methods, choose
													// Tools | Templates.
	}

	public LoginServiceDaoImpl(DataSource dataSource, PlatformTransactionManager transactionManager) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		this.transactionManager = transactionManager;
	}

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
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
				// String findUserDataQuery = "Select * from tbl_member where
				// employee_id=?";

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
	public UserDetail getUserDetailById(int employeeId) {
		UserDetail userDetail = new UserDetail();
		try {
			String findUserDataQuery = "Select * from tbl_member where employee_id=?";

			System.out.println("employee_id" + employeeId);
			userDetail = jdbcTemplate.query(findUserDataQuery, new Object[] { employeeId }, new UserDetailMapper());
			return userDetail;
		} catch (Exception e) {
			e.printStackTrace();
			userDetail.setResponseMsg("false");
			return userDetail;
		}

	}

	@Override
	public GenericResponse insertRoleDetail(CommonDetail roledetail) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_role WHERE role_name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { roledetail.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String role_sql = "INSERT INTO tbl_role(role_name, description) " + " VALUES(?,?)";
				int update = jdbcTemplate.update(role_sql,
						new Object[] { roledetail.getName(), roledetail.getDescription() });
				if (update > 0) {
					genericResponse.setMessage("Role added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add role.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't add role name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add role application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse insertDesignationDetail(CommonDetail degdetail) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			String checkDuplicateSql = "SELECT * FROM tbl_designation WHERE designation_name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { degdetail.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String designation_sql = "INSERT INTO tbl_designation(designation_name, description,distributer_id) " + " VALUES(?,?,?)";
				int update = jdbcTemplate.update(designation_sql,
						new Object[] { degdetail.getName(), degdetail.getDescription(),degdetail.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Designation added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Designation.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't add Designation. name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Designation application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override

	public GenericResponse insertMemberDetail(Member memberDetail) {
		GenericResponse genericResponse = new GenericResponse();
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		try {
			String checkDuplicateSql = "SELECT * FROM tbl_member WHERE email_id=?";
			String checkDuplicateEmpSql = "SELECT * FROM tbl_member WHERE emp_id=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { memberDetail.getEmailId() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String checkEmp = jdbcTemplate.query(checkDuplicateEmpSql, new Object[] { memberDetail.getEmpId() },
						new CheckDuplicateResultExtracter());
				if (!checkEmp.equalsIgnoreCase("exist")) {
					String get_hechy = "select * from tbl_member where employee_id=?";

					String member_sql = "INSERT INTO tbl_member(employee_name,email_id,mobile,alternative_mobile,address,joining_date,role_id,designation_id,distributor_id,tsm_id,asm_id,manager_id,created_by,created_date,state,report_to,emp_id) "
							+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE email_id =VALUES(email_id)";
					// int update = jdbcTemplate.update(member_sql,new
					// Object[]{memberDetail.getEmployeeId(),memberDetail.getEmployeeName(),memberDetail.getEmailId(),memberDetail.getMobile(),memberDetail.getAlternatemobileNumber(),memberDetail.getAdddress(),memberDetail.getJoiningDate(),memberDetail.getRoleId(),memberDetail.getDesignationId(),memberDetail.getDistributerId(),memberDetail.getTsmId(),memberDetail.getAsmId(),memberDetail.getManagerId(),memberDetail.getCretedById(),new
					// Timestamp(System.currentTimeMillis())} );
					if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 37) {

						herchId[2] = memberDetail.getReportTo();

					}

					if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 40) {
						herchId = jdbcTemplate.query(get_hechy,
								new Object[] { memberDetail.getReportTo() != 0 ? memberDetail.getReportTo() : 0 },
								new getHrchyResultExtrcter());
						herchId[1] = memberDetail.getReportTo();
					}
					if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 39) {
						herchId = jdbcTemplate.query(get_hechy,
								new Object[] { memberDetail.getReportTo() != 0 ? memberDetail.getReportTo() : 0 },
								new getHrchyResultExtrcter());
						herchId[0] = memberDetail.getReportTo();
					}
					if (memberDetail.getReportTo() != -5 && memberDetail.getRoleId() == 38) {
						herchId = jdbcTemplate.query(get_hechy,
								new Object[] { memberDetail.getReportTo() != 0 ? memberDetail.getReportTo() : 0 },
								new getHrchyResultExtrcter());
						herchId[2] = memberDetail.getReportTo();
					}

					KeyHolder keyHolder = new GeneratedKeyHolder();
					int update = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(member_sql, new String[] { "employee_id" });
							// pst.setInt(1,memberDetail.getEmployeeId());
							pst.setString(1, memberDetail.getEmployeeName());
							pst.setString(2, memberDetail.getEmailId());
							pst.setString(3, memberDetail.getMobile());
							pst.setString(4, memberDetail.getAlternatemobileNumber());
							pst.setString(5, memberDetail.getAdddress());
							pst.setString(6, memberDetail.getJoiningDate());
							pst.setInt(7, memberDetail.getRoleId());
							pst.setInt(8, memberDetail.getDesignationId());
							pst.setInt(9, memberDetail.getDistributerId());
							pst.setInt(10, herchId[1] != null ? herchId[1] : 0);
							pst.setInt(11, herchId[0] != null ? herchId[0] : 0);
							pst.setInt(12, herchId[2] != null ? herchId[2] : 0);
							pst.setInt(13, memberDetail.getCretedById());
							pst.setString(14, new Timestamp(System.currentTimeMillis()).toString());
							pst.setString(15, memberDetail.getState());
							pst.setInt(16, memberDetail.getReportTo());
							pst.setString(17, memberDetail.getEmpId());
							return pst;
						}
					},

							keyHolder);
					BigInteger key = (BigInteger) keyHolder.getKey();

					System.out.println("Key ====>" + key);

					if (update > 0) {

						String member_login_sql = "INSERT INTO tbl_user_login(employee_id, email_id,password,role_id,token) "
								+ " VALUES(?,?,?,?,?)";
						int childUpdate = jdbcTemplate.update(member_login_sql, new Object[] { key,
								memberDetail.getEmailId(), memberDetail.getPassword(), memberDetail.getRoleId(), 5 });

						if (childUpdate > 0) {
							genericResponse.setMessage("Member added successfully");
							genericResponse.setStatus("success");
							genericResponse.setStatusCode(0);
							transactionManager.commit(status);

						}
					} else {
						genericResponse.setMessage("Couldn't add member.");
						genericResponse.setStatus("fail");
						genericResponse.setStatusCode(1);

					}
					;
				} else {
					genericResponse.setMessage("Couldn't add Member employee Id already exist");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(3);
				}
			}

			else {
				genericResponse.setMessage("Couldn't add Member Email Id already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Member application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();
			transactionManager.rollback(status);

		}

		return genericResponse;

	}

	@Override
	public GenericResponse saveRetailerCat(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_retailer_catagory WHERE retailer_catagory=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { retailerCategoryRequest.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String ret_cat_sql = "INSERT INTO tbl_retailer_catagory(retailer_catagory , description , created_date,distributer_id) "
						+ " VALUES(?,?,?,?)";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(), retailerCategoryRequest.getCreatedDate() ,retailerCategoryRequest.getDistributerId()  });
				if (update > 0) {
					genericResponse.setMessage("Category  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Category.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			}

			else {
				genericResponse.setMessage("Couldn't add Category already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Category application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse saveRetailerType(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_retailer_type WHERE retailer_type=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { retailerCategoryRequest.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {

				String ret_cat_sql = "INSERT INTO tbl_retailer_type(retailer_type , description , created_date,distributer_id) "
						+ " VALUES(?,?,?,?)";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(), retailerCategoryRequest.getCreatedDate() , retailerCategoryRequest.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Retailer type  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Retailer type.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't add Retailer type already Exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Retailer type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse saveRetailerSubType(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			String checkDuplicateSql = "SELECT * FROM tbl_retailer_sub_type WHERE retailer_sub_type=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { retailerCategoryRequest.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {

				String ret_cat_sql = "INSERT INTO tbl_retailer_sub_type(retailer_sub_type,description, created_date,distributer_id) "
						+ " VALUES(?,?,?,?)";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(), retailerCategoryRequest.getCreatedDate(),retailerCategoryRequest.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Retailer Sub type  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Retailer Sub type.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't add Retailer Sub type already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Retailer Sub type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse saveZone(RetailerDetailedData retailerCategoryRequest) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			String checkDuplicateSql = "SELECT * FROM tbl_zone WHERE tittle=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { retailerCategoryRequest.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {

				String ret_cat_sql = "INSERT INTO tbl_zone(tittle ,description, created_date,distributer_id) " + " VALUES(?,?,?,?)";
				int update = jdbcTemplate.update(ret_cat_sql, new Object[] { retailerCategoryRequest.getName(),
						retailerCategoryRequest.getDescription(), retailerCategoryRequest.getCreatedDate(),retailerCategoryRequest.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage(" Zone  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Zone.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't add Zone already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Zone application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse saveRetailer(AddRetailerRequest addRetailerRequest) {

		GenericResponse genericResponse = new GenericResponse();
		try {

			System.out.println("insise saveRetailer impl ");
			String ret_sql = "INSERT INTO tbl_retailer(retailer_shop_name, contact_person_name, contact_person_name1, contact_person_name2, credit_days, email, mobile, alt_mobile1, alt_mobile2, landline, sr_id, tsm_id, asm_id, retailer_type_id, retailer_sub_type_id, retailer_catagory_id, group_id, isgroup, retailer_shop_image, latitude, longitude,  locality, street, city, state, pincode, zoneid, created_by_id, created_date,distributor_id,retailer_excise_code,manager_id) "
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			int update = jdbcTemplate.update(ret_sql, new Object[] { addRetailerRequest.getRetailerShopname(),
					addRetailerRequest.getContactPersaonName1(), addRetailerRequest.getContactPersaonName2(),
					addRetailerRequest.getContactPersaonName3(), addRetailerRequest.getCreditDays(),
					addRetailerRequest.getRetailerEmailId(), addRetailerRequest.getMobileNumber(),
					addRetailerRequest.getAlternateMobileNumber1(), addRetailerRequest.getAlternateMobileNumber2(),
					addRetailerRequest.getLandline(),
					addRetailerRequest.getSrId() != 0 ? addRetailerRequest.getSrId() : null,
					addRetailerRequest.getTsmId() != 0 ? addRetailerRequest.getTsmId() : null,
					addRetailerRequest.getAsmId() != 0 ? addRetailerRequest.getAsmId() : null,
					addRetailerRequest.getTypeId() != 0 ? addRetailerRequest.getTypeId() : null,
					addRetailerRequest.getSubTypeId() != 0 ? addRetailerRequest.getSubTypeId() : null,
					addRetailerRequest.getCategoryid() != 0 ? addRetailerRequest.getCategoryid() : null,
					addRetailerRequest.getGroupId() != 0 ? addRetailerRequest.getGroupId() : null,
					addRetailerRequest.getIsGroup() != 0 ? 2 : 1, addRetailerRequest.getShopImage(),
					addRetailerRequest.getLatitude(), addRetailerRequest.getLongitude(),
					addRetailerRequest.getLocality(), addRetailerRequest.getStreet(), addRetailerRequest.getCity(),
					addRetailerRequest.getStateId() != 0 ? addRetailerRequest.getStateId() : null,
					addRetailerRequest.getPincode(),
					addRetailerRequest.getZoneId() != 0 ? addRetailerRequest.getZoneId() : null,
					addRetailerRequest.getCretedByid(), new Timestamp(System.currentTimeMillis()),
					addRetailerRequest.getDistributerId() != 0 ? addRetailerRequest.getDistributerId() : null,
					addRetailerRequest.getRetailerExciseCode(),
					addRetailerRequest.getManagerId() != 0 ? addRetailerRequest.getManagerId() : 0 });

			System.out.println("update===>" + update);

			if (update > 0) {
				genericResponse.setMessage("Retailer   added successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't add Retailer.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Retailer application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addPackageType(ProductRawdata packageType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_pacakage_type WHERE name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { packageType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_pacakage_type(name,description,distributer_id) " + " VALUES(?,?,?)";
				int update = jdbcTemplate.update(sql,
						new Object[] { packageType.getName(), packageType.getDescription() });
				if (update > 0) {
					genericResponse.setMessage("Package Type  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Package Type.");
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
			genericResponse.setMessage("Couldn't add Package Type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addProductCategory(ProductRawdata packageType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_catagory WHERE name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { packageType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_product_catagory(name,description,distributer_id) " + " VALUES(?,?,?)";
				int update = jdbcTemplate.update(sql,
						new Object[] { packageType.getName(), packageType.getDescription(),packageType.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Package Type  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Package Type.");
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
			genericResponse.setMessage("Couldn't add Package Type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addProduct(Product product) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			System.out.println("product===>" + product);

			String checkDuplicateSql = "SELECT * FROM tbl_product WHERE product_name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { product.getProductName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String role_sql = "INSERT INTO tbl_product(product_name, brand_id, Product_code, PRODUCT_catagory_id, PRODUCT_segment_code, qtyML, box_size,status, created_date, created_by_id, distributor_id, pacakage_type_id, product_type_id, cif, wsp, mrp,liscense_id,product_sub_tyoe_id,excise_duty) "
						+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				int update = jdbcTemplate.update(role_sql,
						new Object[] { product.getProductName(), product.getBranId(), product.getProductCode(),
								product.getProductCategoryId(), product.getProductSegmentCode(), product.getQtyMl(),
								product.getBoxSize(), product.getProductStatus(),
								(new Timestamp(System.currentTimeMillis())), product.getCreateById(),
								product.getDistributerId(), product.getPackagetypeId(), product.getProductTypeId(),
								product.getCif(), product.getWsp(), product.getMrp(), product.getLicenseId(),
								product.getProductSubtypeid(), product.getExciseDuty() });
				if (update > 0) {
					genericResponse.setMessage("Product  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Product.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't add Product is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Product application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addProductSegment(ProductRawdata packageType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_segment WHERE name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { packageType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_product_segment(name,description,distributer_id) " + " VALUES(?,?,?)";
				int update = jdbcTemplate.update(sql,
						new Object[] { packageType.getName(), packageType.getDescription(),packageType.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Product Segment  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Product Segment.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't Add Product Segment name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Package Type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addProductSubType(ProductRawdata packageType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_sub_type WHERE name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { packageType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_product_sub_type(name,description,distributer_id) " + " VALUES(?,?,?)";
				int update = jdbcTemplate.update(sql,
						new Object[] { packageType.getName(), packageType.getDescription(),packageType.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Product sub Type  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Product sub Type.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't Add Product sub Type name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add sub Type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addProductType(ProductRawdata packageType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_product_type WHERE name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { packageType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_product_type(name,description,distributer_id) " + " VALUES(?,?,?)";
				int update = jdbcTemplate.update(sql,
						new Object[] { packageType.getName(), packageType.getDescription(),packageType.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Product  Type  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Product  Type.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't Add Product  Type name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add  Type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addBrand(Brand brand) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_brand WHERE brand_name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { brand.getBrandName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_brand(brand_name, brand_code,internal_brand_code,principle,brand_owner,liscense_id,description,state_id,distributer_id) "
						+ " VALUES(?,?,?,?,?,?,?,?,?)";
				int update = jdbcTemplate.update(sql,
						new Object[] { brand.getBrandName(), brand.getBrandCode(), brand.getInternalBrandCode(),
								brand.getPrincipal(), brand.getBrandowner(), brand.getLicenseId(),
								brand.getDescription(), brand.getStateId(), brand.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("brand  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add brand.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't Add brand name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add brand application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addDistributerLicense(DistributerLicense distributerLicense) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String sql = "INSERT INTO tbl_dstributor_liscense(liscense_no, distributor_id,state_id) "
					+ " VALUES(?,?,?)";
			int update = jdbcTemplate.update(sql, new Object[] { distributerLicense.getLicenseNumber(),
					distributerLicense.getDistributerId(), distributerLicense.getStateid() });
			if (update > 0) {
				genericResponse.setMessage("license  added successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't add license.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("license add brand application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addQtyPcs(QtyInPcs qtyInPcs) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String sql = "INSERT INTO tbl_qty_pcs(quantity_in_ml, pieces,description,distributer_id) " + " VALUES(?,?,?,?)";
			int update = jdbcTemplate.update(sql,
					new Object[] { qtyInPcs.getQtyMl(), qtyInPcs.getPcs(), qtyInPcs.getDescription(),qtyInPcs.getDistributerId() });
			if (update > 0) {
				genericResponse.setMessage("  added successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't add ");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		}

		catch (Exception e) {
			genericResponse.setMessage("license add  application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addFeedbackType(ProductRawdata FeedbackType) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_feedback_type WHERE title=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { FeedbackType.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_feedback_type(title,description,create_date,distributer_id) " + " VALUES(?,?,?,?)";
				int update = jdbcTemplate.update(sql, new Object[] { FeedbackType.getName(),
						FeedbackType.getDescription(), new Timestamp(System.currentTimeMillis()),FeedbackType.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Feedback Type  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Feedback Type.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't Feedback Type name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Feedback Type application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addFeedbackRating(ProductRawdata FeedbackRating) {
		GenericResponse genericResponse = new GenericResponse();
		try {

			String checkDuplicateSql = "SELECT * FROM tbl_feedback_rating WHERE title=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { FeedbackRating.getName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_feedback_rating(title,description,create_date,distributer_id) " + " VALUES(?,?,?,?)";
				int update = jdbcTemplate.update(sql, new Object[] { FeedbackRating.getName(),
						FeedbackRating.getDescription(), new Timestamp(System.currentTimeMillis()),FeedbackRating.getDistributerId() });
				if (update > 0) {
					genericResponse.setMessage("Feedback Rating  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Feedback Rating.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't Feedback Rating name is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Feedback Rating application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addFeedback(Feedback feedback) {
		if(feedback.getImage()!=null){
			
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss:SSS");  
			  LocalDateTime now = LocalDateTime.now(); 
			   
			
			feedback.setImage(imageDecode(feedback.getImage(), dtf.format(now),"feedbackImage"));
		}
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		GenericResponse genericResponse = new GenericResponse();
		try {
			int updatelastVisit = 0;
			int updateFeedback = 0;
			int update_visit_info = 0;

			String check = jdbcTemplate.query(checkVisit, new Object[] { feedback.getVisitorId() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				System.out.println("fist time hit no sr id found");
				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
						// pst.setInt(1,memberDetail.getEmployeeId());
						pst.setInt(1, feedback.getVisitorId());
						pst.setInt(2, feedback.getRetailerId());
						pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
						pst.setInt(4, 1);
						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				if (update > 0) {
					updatelastVisit = jdbcTemplate.update(sqlLastVisit, new Object[] { key, feedback.getVisitorId(),
							feedback.getRetailerId(), new Timestamp(System.currentTimeMillis()), 1 });
				}

				if (updatelastVisit > 0) {
					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(sql, new String[] { "feedback_id" });
							pst.setInt(1, feedback.getVisitorId());
							pst.setInt(2, feedback.getRetailerId());
							pst.setInt(3, feedback.getFeedackTypeId());
							pst.setInt(4, feedback.getFeedbackratingid());
							pst.setInt(5, key.intValue());
							pst.setString(6, new Timestamp(System.currentTimeMillis()).toString());
							pst.setString(7, feedback.getFeedbackTitle());
							pst.setString(8, feedback.getDescription());
							pst.setString(9, feedback.getImage());
							pst.setString(10, feedback.getLattitude());
							pst.setString(11, feedback.getLongitude());
							pst.setString(12, null);
							pst.setInt(13, feedback.getDistributerId());

							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();

					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { key, feedback.getVisitorId(), feedback.getRetailerId(), null, 1,
										key1.intValue(), new Timestamp(System.currentTimeMillis()) });
				setAttendence(new Timestamp(System.currentTimeMillis()), feedback.getVisitorId(), key.intValue(), feedback.getRetailerId());
					}
				}

			} else {
				System.out.println(" sr id found checking for retailer id ");
				Integer checkVisitRetailerResult = jdbcTemplate.query(checkVisitRetailer,
						new Object[] { feedback.getRetailerId(), feedback.getVisitorId() },
						new IntegerValueExtracter());
				Integer visitId = jdbcTemplate.query("Select visit_id from tbl_visit_master where  visitor_id=? ",
						new Object[] { feedback.getVisitorId() }, new IntegerValueExtracter());
				if (checkVisitRetailerResult != null) {
					System.out.println(" sr id found and  retailer id found now updating the value ");
					Integer activityperform = checkVisitRetailerResult + 1;
					System.out.println("activityperform" + activityperform);
					String updateVisitMaster = "UPDATE tbl_visit_master SET Activity_performed_count = "
							+ activityperform + " WHERE visit_id = " + visitId + "";
					jdbcTemplate.update(updateVisitMaster);
					jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = " + activityperform
							+ "  WHERE   visit_id = " + visitId + "");
					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {

	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(sql, new String[] { "feedback_id" });
							pst.setInt(1, feedback.getVisitorId());
							pst.setInt(2, feedback.getRetailerId());
							pst.setInt(3, feedback.getFeedackTypeId());
							pst.setInt(4, feedback.getFeedbackratingid());
							pst.setInt(5, visitId);
							pst.setString(6, new Timestamp(System.currentTimeMillis()).toString());
							pst.setString(7, feedback.getFeedbackTitle());
							pst.setString(8, feedback.getDescription());
							pst.setString(9, feedback.getImage());
							pst.setString(10, feedback.getLattitude());
							pst.setString(11, feedback.getLongitude());
							pst.setString(12, null);
							pst.setInt(13, feedback.getDistributerId());

							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();

					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { visitId, feedback.getVisitorId(), feedback.getRetailerId(), null, 1,
										key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						updateAttendence(new Timestamp(System.currentTimeMillis()), feedback.getVisitorId(), visitId, feedback.getRetailerId());
					}
				}

				else {
					System.out.println(" sr id  but retailer id not found no updating the retailer id  ");

					KeyHolder keyHolder = new GeneratedKeyHolder();
					int update = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
							// pst.setInt(1,memberDetail.getEmployeeId());
							pst.setInt(1, feedback.getVisitorId());
							pst.setInt(2, feedback.getRetailerId());
							pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(4, 1);
							return pst;
						}
					},

							keyHolder);
					BigInteger key = (BigInteger) keyHolder.getKey();
					jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = 1,visit_id=" + key
							+ " ,  retailer_id = " + feedback.getRetailerId() + " WHERE visit_id = " + visitId + "");

					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(sql, new String[] { "feedback_id" });
							pst.setInt(1, feedback.getVisitorId());
							pst.setInt(2, feedback.getRetailerId());
							pst.setInt(3, feedback.getFeedackTypeId());
							pst.setInt(4, feedback.getFeedbackratingid());
							pst.setInt(5, key.intValue());
							pst.setString(6, new Timestamp(System.currentTimeMillis()).toString());
							pst.setString(7, feedback.getFeedbackTitle());
							pst.setString(8, feedback.getDescription());
							pst.setString(9, feedback.getImage());
							pst.setString(10, feedback.getLattitude());
							pst.setString(11, feedback.getLongitude());
							pst.setString(12, null);
							pst.setInt(13, feedback.getDistributerId());
							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();

					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { key.intValue(), feedback.getVisitorId(), feedback.getRetailerId(), null,
										1, key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						updateAttendence(new Timestamp(System.currentTimeMillis()), feedback.getVisitorId(),  visitId, feedback.getRetailerId());
					}

				}

	}

	if(update_visit_info>0){genericResponse.setMessage("feedback  added successfully");genericResponse.setStatus("success");genericResponse.setStatusCode(0);transactionManager.commit(status);}else{genericResponse.setMessage("Couldn't add Feedback ");genericResponse.setStatus("fail");genericResponse.setStatusCode(1);

	}

	}

	catch(
	Exception e){genericResponse.setMessage("Couldn't add Feedback Rating application server error");genericResponse.setStatus("fail");genericResponse.setStatusCode(-1);transactionManager.rollback(status);e.printStackTrace();

	}return genericResponse;}

	@Override
	public GenericResponse addRoute(Route route) {
		GenericResponse genericResponse = new GenericResponse();
		/*
		 * TransactionDefinition def1 = new DefaultTransactionDefinition();
		 * TransactionStatus status2 = transactionManager.getTransaction(def1);
		 */
		try {
			String checkDuplicateSql = "SELECT * FROM tbl_route_managment WHERE route_name=?";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { route.getRouteName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String sql = "INSERT INTO tbl_route_managment(route_name,sr_id,created_by_id,distributor_id,created_date) "
						+ " VALUES(?,?,?,?,?)";

				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sql, new String[] { "route_id" });
						// pst.setInt(1,memberDetail.getEmployeeId());
						pst.setString(1, route.getRouteName());
						pst.setInt(2, route.getSrId());
						pst.setInt(3, route.getCreatedbyid());
						pst.setInt(4, route.getDistributerid());
						pst.setString(5, new Timestamp(System.currentTimeMillis()).toString());

						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				System.out.println("Key ====>" + key);

				if (update > 0) {
					if (route.getManagerId() != 0 && route.getAsmId() != 0 && route.getTsmId() != 0
							&& route.getSrId() != 0) {
						StringBuilder strbul = new StringBuilder();

						for (String str : route.getRetailerId()) {
							System.out.println("value of str==> " + str);
							strbul.append(str);
							// for adding comma between elements
							if (route.getRetailerId().size() > 1) {
								strbul.append(",");
							}
						}
						if (route.getRetailerId().size() > 1) {
							strbul.deleteCharAt(strbul.length() - 1);
						}
						System.out.println("stringBuilder===>" + strbul);
						if (route.getRetailerId() != null) {
							String updateTableSQL = "UPDATE tbl_retailer SET route_id =?,manager_id=?,asm_id=?,tsm_id=?,sr_id=? WHERE retailer_id In("
									+ strbul + ")";

							int childUpdate = jdbcTemplate.update(updateTableSQL, new Object[] { key,
									route.getManagerId(), route.getAsmId(), route.getTsmId(), route.getSrId() });

							if (childUpdate > 0) {
								genericResponse.setMessage("route added successfully");
								genericResponse.setStatus("success");
								genericResponse.setStatusCode(0);
								// transactionManager.commit(status2);

							}

						}

					}
				}
			}

			else {
				genericResponse.setMessage("Couldn't route already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add route application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();
			// transactionManager.rollback(status2);

		}

		return genericResponse;

	}

	@Override
	public GenericResponse addAsset(AssetByRetailerData asset) {
		GenericResponse genericResponse = new GenericResponse();
		try {
			String checkDuplicateSql = "SELECT * from tbl_assets where asset_name=? ";

			String check = jdbcTemplate.query(checkDuplicateSql, new Object[] { asset.getAssetName() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				String assetSql = "INSERT INTO tbl_assets(asset_name,asset_type,last_update,description,distributor_id,brand_id,product_id,create_date)"
						+ "VALUES(?,?,?,?,?,?,?,?)";

				int update = jdbcTemplate.update(assetSql, new Object[] { asset.getAssetName(), asset.getAssetType(),
						(new Timestamp(System.currentTimeMillis())), asset.getDescription(), asset.getDistributorId(),
						asset.getBrandId(), asset.getProductId(), (new Timestamp(System.currentTimeMillis())) });
				if (update > 0) {
					genericResponse.setMessage("Asset  added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Couldn't add Product.");
					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);

				}
			} else {
				genericResponse.setMessage("Couldn't add Asset is already exist");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(3);
			}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add Asset application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse assignAssets(AssignAssets assignAssets) {
		GenericResponse genericResponse = new GenericResponse();
		int update = 0;
		try {

			String assetSql = "INSERT INTO tbl_asset_assign(asset_id,retailer_id,qty,amount,assign_date)"
					+ "VALUES(?,?,?,?,?)";
			for (Integer i : assignAssets.getRetailerList()) {

				update = jdbcTemplate.update(assetSql, new Object[] { assignAssets.getAssetId(), i,
						assignAssets.getQty(), assignAssets.getAmount(), (new Timestamp(System.currentTimeMillis())) });

			}
			if (update > 0) {
				genericResponse.setMessage("Asset Assign  added successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Couldn't Assign Asset.");
				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);

			}

		} catch (Exception e) {
			genericResponse.setMessage("Couldn't Assign Asset application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();

		}
		return genericResponse;
	}

	@Override
	public GenericResponse addPromiseOrder(PromiseOrder promiseOrder) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		GenericResponse genericResponse = new GenericResponse();
		try {
			int updatelastVisit = 0;
			int updateFeedback = 0;
			int update_visit_info = 0;

			String check = jdbcTemplate.query(checkVisit, new Object[] { promiseOrder.getVisitorId() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				System.out.println("fist time hit no sr id found");
				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
						// pst.setInt(1,memberDetail.getEmployeeId());
						pst.setInt(1, promiseOrder.getVisitorId());
						pst.setInt(2, promiseOrder.getRetailerId());
						pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
						pst.setInt(4, 1);
						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				if (update > 0) {
					updatelastVisit = jdbcTemplate.update(sqlLastVisit, new Object[] { key, promiseOrder.getVisitorId(),
							promiseOrder.getRetailerId(), new Timestamp(System.currentTimeMillis()), 1 });
				}

				if (updatelastVisit > 0) {
					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(INSERTORDER, new String[] { "order_id" });
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, promiseOrder.getRemarks());
							pst.setString(4, promiseOrder.getPromiseDate());
							pst.setString(5, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(6, key.intValue());
							pst.setString(7, promiseOrder.getLatitude());
							pst.setString(8, promiseOrder.getLongitude());
							pst.setString(9, null);
							pst.setInt(10,promiseOrder.getDistributerId());


							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();
					for (PromiseOrderProductDetail promiseOrderProductDetail:promiseOrder.getProductList())
					{
				 jdbcTemplate.update(INSERT_ORDER_PRODUCT_WISE,
							new Object[] { key1, promiseOrderProductDetail.getProductId(), promiseOrderProductDetail.getBrandId(),promiseOrderProductDetail.getQty() });
					}
					

					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { key, promiseOrder.getVisitorId(), promiseOrder.getRetailerId(), null, 2,
										key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						setAttendence(new Timestamp(System.currentTimeMillis()), promiseOrder.getVisitorId(), key.intValue(), promiseOrder.getRetailerId());
					}
				}

			} else {
				System.out.println(" sr id found checking for retailer id ");
				Integer checkVisitRetailerResult = jdbcTemplate.query(checkVisitRetailer,
						new Object[] { promiseOrder.getRetailerId(), promiseOrder.getVisitorId() },
						new IntegerValueExtracter());
				Integer visitId = jdbcTemplate.query("Select visit_id from tbl_visit_master where  visitor_id=? ",
						new Object[] { promiseOrder.getVisitorId() }, new IntegerValueExtracter());
				if (checkVisitRetailerResult != null) {
					System.out.println(" sr id found and  retailer id found now updating the value ");
					Integer activityperform = checkVisitRetailerResult + 1;
					System.out.println("activityperform" + activityperform);
					String updateVisitMaster = "UPDATE tbl_visit_master SET Activity_performed_count = "
							+ activityperform + " WHERE visit_id = " + visitId + "";
					jdbcTemplate.update(updateVisitMaster);
					jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = " + activityperform
							+ "  WHERE   visit_id = " + visitId + "");
					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {

	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(INSERTORDER, new String[] { "order_id" });
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, promiseOrder.getRemarks());
							pst.setString(4, promiseOrder.getPromiseDate());
							pst.setString(5, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(6, visitId);
							pst.setString(7, promiseOrder.getLatitude());
							pst.setString(8, promiseOrder.getLongitude());
							pst.setString(9, null);
							pst.setInt(10,promiseOrder.getDistributerId());
							
							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();
					
					for (PromiseOrderProductDetail promiseOrderProductDetail:promiseOrder.getProductList())
					{

						jdbcTemplate.update(INSERT_ORDER_PRODUCT_WISE,
							new Object[] { key1, promiseOrderProductDetail.getProductId(), promiseOrderProductDetail.getBrandId(), promiseOrderProductDetail.getQty() });
					}

					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { visitId, promiseOrder.getVisitorId(), promiseOrder.getRetailerId(), null, 2,
										key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						updateAttendence(new Timestamp(System.currentTimeMillis()), promiseOrder.getVisitorId(), visitId, promiseOrder.getRetailerId());

					}
				}

				else {
					System.out.println(" sr id  but retailer id not found no updating the retailer id  ");

					KeyHolder keyHolder = new GeneratedKeyHolder();
					int update = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
							
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(4, 1);
							return pst;
						}
					},

							keyHolder);
					BigInteger key = (BigInteger) keyHolder.getKey();
					jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = 1,visit_id=" + key
							+ " ,  retailer_id = " + promiseOrder.getRetailerId() + " WHERE visit_id = " + visitId + "");

					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(INSERTORDER, new String[] { "order_id" });
							
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, promiseOrder.getRemarks());
							pst.setString(4, promiseOrder.getPromiseDate());
							pst.setString(5, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(6, key.intValue());
							pst.setString(7, promiseOrder.getLatitude());
							pst.setString(8, promiseOrder.getLongitude());
							pst.setString(9, null);
							pst.setInt(10,promiseOrder.getDistributerId());
							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();

					for (PromiseOrderProductDetail promiseOrderProductDetail:promiseOrder.getProductList())
					{
				 jdbcTemplate.update(INSERT_ORDER_PRODUCT_WISE,
							new Object[] { key1, promiseOrderProductDetail.getProductId(), promiseOrderProductDetail.getBrandId(), promiseOrderProductDetail.getQty() });
					}
					
					
					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { key.intValue(), promiseOrder.getVisitorId(), promiseOrder.getRetailerId(), null,
										2, key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						updateAttendence(new Timestamp(System.currentTimeMillis()), promiseOrder.getVisitorId(), visitId, promiseOrder.getRetailerId());

					}

				}

	}

	if(update_visit_info>0){genericResponse.setMessage("feedback  added successfully");genericResponse.setStatus("success");genericResponse.setStatusCode(0);transactionManager.commit(status);}else{genericResponse.setMessage("Couldn't add Feedback ");genericResponse.setStatus("fail");genericResponse.setStatusCode(1);

	}

	}

	catch(
	Exception e){genericResponse.setMessage("Couldn't add Feedback Rating application server error");genericResponse.setStatus("fail");genericResponse.setStatusCode(-1);transactionManager.rollback(status);e.printStackTrace();

	}return genericResponse;}

	@Override
	public GenericResponse addRetailerStock(PromiseOrder promiseOrder) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);

		GenericResponse genericResponse = new GenericResponse();
		try {
			int updatelastVisit = 0;
			int updateFeedback = 0;
			int update_visit_info = 0;

			String check = jdbcTemplate.query(checkVisit, new Object[] { promiseOrder.getVisitorId() },
					new CheckDuplicateResultExtracter());

			if (!check.equalsIgnoreCase("exist")) {
				System.out.println("fist time hit no sr id found");
				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
						// pst.setInt(1,memberDetail.getEmployeeId());
						pst.setInt(1, promiseOrder.getVisitorId());
						pst.setInt(2, promiseOrder.getRetailerId());
						pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
						pst.setInt(4, 1);
						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				if (update > 0) {
					updatelastVisit = jdbcTemplate.update(sqlLastVisit, new Object[] { key, promiseOrder.getVisitorId(),
							promiseOrder.getRetailerId(), new Timestamp(System.currentTimeMillis()), 1 });
				}

				if (updatelastVisit > 0) {
					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(INSERTSTOCK, new String[] { "stock_id" });
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, promiseOrder.getRemarks());
							//pst.setString(4, promiseOrder.getPromiseDate());
							pst.setString(4, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(5, key.intValue());
							pst.setString(6, promiseOrder.getLatitude());
							pst.setString(7, promiseOrder.getLongitude());
							pst.setString(8, null);
							pst.setInt(9, promiseOrder.getDistributerId());

							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();
					for (PromiseOrderProductDetail promiseOrderProductDetail:promiseOrder.getProductList())
					{
						System.out.println("qty ==? "+ promiseOrderProductDetail.getQty());
						System.out.println("product id  ==? "+ promiseOrderProductDetail.getProductId());

						jdbcTemplate.update(INSERT_STOCK_PRODUCT_WISE,
							new Object[] { key1, promiseOrderProductDetail.getProductId(), promiseOrderProductDetail.getBrandId(),promiseOrderProductDetail.getQty() });
					}
					

					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { key, promiseOrder.getVisitorId(), promiseOrder.getRetailerId(), null, 3,
										key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						setAttendence(new Timestamp(System.currentTimeMillis()), promiseOrder.getVisitorId(), key.intValue(), promiseOrder.getRetailerId());

					}
				}

			} else {
				System.out.println(" sr id found checking for retailer id ");
				Integer checkVisitRetailerResult = jdbcTemplate.query(checkVisitRetailer,
						new Object[] { promiseOrder.getRetailerId(), promiseOrder.getVisitorId() },
						new IntegerValueExtracter());
				Integer visitId = jdbcTemplate.query("Select visit_id from tbl_visit_master where  visitor_id=? ",
						new Object[] { promiseOrder.getVisitorId() }, new IntegerValueExtracter());
				if (checkVisitRetailerResult != null) {
					System.out.println(" sr id found and  retailer id found now updating the value ");
					Integer activityperform = checkVisitRetailerResult + 1;
					System.out.println("activityperform" + activityperform);
					String updateVisitMaster = "UPDATE tbl_visit_master SET Activity_performed_count = "
							+ activityperform + " WHERE visit_id = " + visitId + "";
					jdbcTemplate.update(updateVisitMaster);
					jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = " + activityperform
							+ "  WHERE   visit_id = " + visitId + "");
					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {

	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(INSERTSTOCK, new String[] { "stock_id" });
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, promiseOrder.getRemarks());
							//pst.setString(4, promiseOrder.getPromiseDate());
							pst.setString(4, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(5, visitId);
							pst.setString(6, promiseOrder.getLatitude());
							pst.setString(7, promiseOrder.getLongitude());
							pst.setString(8, null);
							pst.setInt(9, promiseOrder.getDistributerId());

							
							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();
					
					for (PromiseOrderProductDetail promiseOrderProductDetail:promiseOrder.getProductList())
					{
						System.out.println("qty ==? "+ promiseOrderProductDetail.getQty());
						System.out.println("product id  ==? "+ promiseOrderProductDetail.getProductId());

						jdbcTemplate.update(INSERT_STOCK_PRODUCT_WISE,
							new Object[] { key1, promiseOrderProductDetail.getProductId(), promiseOrderProductDetail.getBrandId(), promiseOrderProductDetail.getQty() });
					}

					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { visitId, promiseOrder.getVisitorId(), promiseOrder.getRetailerId(), null, 3,
										key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						updateAttendence(new Timestamp(System.currentTimeMillis()), promiseOrder.getVisitorId(), visitId, promiseOrder.getRetailerId());

					}
				}

				else {
					System.out.println(" sr id  but retailer id not found no updating the retailer id  ");

					KeyHolder keyHolder = new GeneratedKeyHolder();
					int update = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
							
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(4, 1);
							return pst;
						}
					},

							keyHolder);
					BigInteger key = (BigInteger) keyHolder.getKey();
					jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = 1,visit_id=" + key
							+ " ,  retailer_id = " + promiseOrder.getRetailerId() + " WHERE visit_id = " + visitId + "");

					KeyHolder keyHolder1 = new GeneratedKeyHolder();
					updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
						public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
							PreparedStatement pst = con.prepareStatement(INSERTSTOCK, new String[] { "stock_id" });
							
							pst.setInt(1, promiseOrder.getVisitorId());
							pst.setInt(2, promiseOrder.getRetailerId());
							pst.setString(3, promiseOrder.getRemarks());
							//pst.setString(4, promiseOrder.getPromiseDate());
							pst.setString(4, new Timestamp(System.currentTimeMillis()).toString());
							pst.setInt(5, key.intValue());
							pst.setString(6, promiseOrder.getLatitude());
							pst.setString(7, promiseOrder.getLongitude());
							pst.setString(8, null);
							pst.setInt(9, promiseOrder.getDistributerId());
							return pst;
						}
					},

							keyHolder1);
					BigInteger key1 = (BigInteger) keyHolder1.getKey();

					for (PromiseOrderProductDetail promiseOrderProductDetail:promiseOrder.getProductList())
					{
						System.out.println("qty ==? "+ promiseOrderProductDetail.getQty());
						System.out.println("product id  ==? "+ promiseOrderProductDetail.getProductId());
				 jdbcTemplate.update(INSERT_STOCK_PRODUCT_WISE,
							new Object[] { key1, promiseOrderProductDetail.getProductId(), promiseOrderProductDetail.getBrandId(), promiseOrderProductDetail.getQty() });
					}
					
					
					if (updateFeedback > 0) {

						update_visit_info = jdbcTemplate.update(visitinfo,
								new Object[] { key.intValue(), promiseOrder.getVisitorId(), promiseOrder.getRetailerId(), null,
										3, key1.intValue(), new Timestamp(System.currentTimeMillis()) });
						updateAttendence(new Timestamp(System.currentTimeMillis()), promiseOrder.getVisitorId(), visitId, promiseOrder.getRetailerId());

					}

				}

	}

	if(update_visit_info>0){genericResponse.setMessage("feedback  added successfully");genericResponse.setStatus("success");genericResponse.setStatusCode(0);transactionManager.commit(status);}else{genericResponse.setMessage("Couldn't add Feedback ");genericResponse.setStatus("fail");genericResponse.setStatusCode(1);

	}

	}

	catch(
	Exception e){genericResponse.setMessage("Couldn't add Feedback Rating application server error");genericResponse.setStatus("fail");genericResponse.setStatusCode(-1);transactionManager.rollback(status);e.printStackTrace();

	}return genericResponse;}

	public String imageDecode(String s, String feed,String type) {

		String upload = null;

		try {

			FTPClient client = new FTPClient();
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodeByte = decoder.decodeBuffer(s);

			client.enterLocalPassiveMode();

		String sb = "public_html/bailiwicksolution.com/sales-force-admin/vendor/image/"+type+"/";
			//String sb = "img/";

			client.connect("www.bailiwicksolution.com");
			//client.connect("61.246.34.205");

			client.login("managbyc", "B@$#%*90!i");
			//client.login("bailiwick", "Thinkpad@450");

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

	public void setAttendence(Timestamp timestamp, int visitorId,int visitId,int retailerId){
	
		try {
System.out.println("inside set addendence ");
				String role_sql = "INSERT INTO tbl_attendence(in_time,visitor_id,visit_id,in_retailer_id) " + " VALUES(?,?,?,?)";
				 jdbcTemplate.update(role_sql,new Object[] {timestamp,visitorId,visitId,retailerId});
					
		}

		catch (Exception e) {
			
			e.printStackTrace();

		}
	
	}

	public void updateAttendence( Timestamp timestamp, int visitorId , int visitId, int retailerId){
		
		try {
			System.out.println("inside update addendence ");


				String role_sql = "UPDATE tbl_attendence SET out_time =?,visit_id=?,out_retailer_id=? where CAST(in_time AS DATE) = CAST('"+timestamp+"' AS DATE) and visitor_id =? ";
				 jdbcTemplate.update(role_sql,new Object[] { timestamp,visitId,retailerId,visitorId});
					
		}

		catch (Exception e) {
			
			e.printStackTrace();

		}
	
	}

@Override
public GenericResponse addAuditAsset(AuditAssetReatiler audit) {
	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss:SSS");  
	  LocalDateTime now = LocalDateTime.now(); 
	   
	
	
	
	if(audit.getImage1()!=null){
		audit.setImage1(imageDecode(audit.getImage1(), dtf.format(now),"audiAssetImage"));
	}
	if(audit.getImage2()!=null){
		audit.setImage2(imageDecode(audit.getImage2(), dtf.format(now),"audiAssetImage"));
	}
	if(audit.getImage3()!=null){
		audit.setImage3(imageDecode(audit.getImage3(), dtf.format(now),"audiAssetImage"));
	}
	
	
	TransactionDefinition def = new DefaultTransactionDefinition();
	TransactionStatus status = transactionManager.getTransaction(def);

	GenericResponse genericResponse = new GenericResponse();
	try {
		int updatelastVisit = 0;
		int updateFeedback = 0;
		int update_visit_info = 0;

		String check = jdbcTemplate.query(checkVisit, new Object[] { audit.getVisitorId() },
				new CheckDuplicateResultExtracter());

		if (!check.equalsIgnoreCase("exist")) {
			System.out.println("fist time hit no sr id found");
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int update = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
					// pst.setInt(1,memberDetail.getEmployeeId());
					pst.setInt(1, audit.getVisitorId());
					pst.setInt(2, audit.getRetailerId());
					pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
					pst.setInt(4, 1);
					return pst;
				}
			},

					keyHolder);
			BigInteger key = (BigInteger) keyHolder.getKey();

			if (update > 0) {
				updatelastVisit = jdbcTemplate.update(sqlLastVisit, new Object[] { key, audit.getVisitorId(),
						audit.getRetailerId(), new Timestamp(System.currentTimeMillis()), 1 });
			}

			if (updatelastVisit > 0) {
				KeyHolder keyHolder1 = new GeneratedKeyHolder();
				updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(INSERT_AUDIT_ASSEST, new String[] { "id" });
						pst.setInt(1, audit.getAssetId());
						pst.setInt(2, audit.getRetailerId());
						pst.setString(3, audit.getQty());
						pst.setString(4, audit.getAmount());
						pst.setString(5, audit.getAssignDate());
						pst.setString(6, audit.getIsAvailable());
						pst.setInt(7, audit.getRatingId());
						pst.setString(8, audit.getDescription());
						pst.setString(9, audit.getImage1());
						pst.setString(10, audit.getImage2());
						pst.setString(11, audit.getImage3());
						pst.setInt(12, audit.getVisitorId());
						pst.setInt(13, audit.getRoleID());
						pst.setString(14, new Timestamp(System.currentTimeMillis()).toString());
						pst.setString(15, audit.getLat());
						pst.setString(16, audit.getLng());
						
						return pst;
						
					}
				},

						keyHolder1);
				BigInteger key1 = (BigInteger) keyHolder1.getKey();

				if (updateFeedback > 0) {
					
					String sql = "UPDATE tbl_asset_assign SET isAvailable=?,feedback_rating_id=?,description=?,image1=?,image2=?,image3=? WHERE id=? " ;
					 jdbcTemplate.update(sql,
							new Object[] { audit.getIsAvailable(), audit.getRatingId() , audit.getDescription(),audit.getImage1(),audit.getImage2(),audit.getImage3(),audit.getAssignId() });

					update_visit_info = jdbcTemplate.update(visitinfo,
							new Object[] { key, audit.getVisitorId(), audit.getRetailerId(), null, 4,
									key1.intValue(), new Timestamp(System.currentTimeMillis()) });
			setAttendence(new Timestamp(System.currentTimeMillis()), audit.getVisitorId(), key.intValue(), audit.getRetailerId());
				}
			}

		} else {
			System.out.println(" sr id found checking for retailer id ");
			Integer checkVisitRetailerResult = jdbcTemplate.query(checkVisitRetailer,
					new Object[] { audit.getRetailerId(), audit.getVisitorId() },
					new IntegerValueExtracter());
			Integer visitId = jdbcTemplate.query("Select visit_id from tbl_visit_master where  visitor_id=? ",
					new Object[] { audit.getVisitorId() }, new IntegerValueExtracter());
			if (checkVisitRetailerResult != null) {
				System.out.println(" sr id found and  retailer id found now updating the value ");
				Integer activityperform = checkVisitRetailerResult + 1;
				System.out.println("activityperform" + activityperform);
				String updateVisitMaster = "UPDATE tbl_visit_master SET Activity_performed_count = "
						+ activityperform + " WHERE visit_id = " + visitId + "";
				jdbcTemplate.update(updateVisitMaster);
				jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = " + activityperform
						+ "  WHERE   visit_id = " + visitId + "");
				KeyHolder keyHolder1 = new GeneratedKeyHolder();
				updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {

	public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(INSERT_AUDIT_ASSEST, new String[] { "id" });
						pst.setInt(1, audit.getAssetId());
						pst.setInt(2, audit.getRetailerId());
						pst.setString(3, audit.getQty());
						pst.setString(4, audit.getAmount());
						pst.setString(5, audit.getAssignDate());
						pst.setString(6, audit.getIsAvailable());
						pst.setInt(7, audit.getRatingId());
						pst.setString(8, audit.getDescription());
						pst.setString(9, audit.getImage1());
						pst.setString(10, audit.getImage2());
						pst.setString(11, audit.getImage3());
						pst.setInt(12, audit.getVisitorId());
						pst.setInt(13, audit.getRoleID());
						pst.setString(14, new Timestamp(System.currentTimeMillis()).toString());
						pst.setString(15, audit.getLat());
						pst.setString(16, audit.getLng());
						
						return pst;
					}
				},

						keyHolder1);
				BigInteger key1 = (BigInteger) keyHolder1.getKey();

				if (updateFeedback > 0) {
					String sql = "UPDATE tbl_asset_assign SET isAvailable=?,feedback_rating_id=?,description=?,image1=?,image2=?,image3=? WHERE id=? " ;
					 jdbcTemplate.update(sql,
							new Object[] { audit.getIsAvailable(), audit.getRatingId() , audit.getDescription(),audit.getImage1(),audit.getImage2(),audit.getImage3(),audit.getAssignId() });

					update_visit_info = jdbcTemplate.update(visitinfo,
							new Object[] { visitId, audit.getVisitorId(), audit.getRetailerId(), null, 4,
									key1.intValue(), new Timestamp(System.currentTimeMillis()) });
					updateAttendence(new Timestamp(System.currentTimeMillis()), audit.getVisitorId(), visitId, audit.getRetailerId());
				}
			}

			else {
				System.out.println(" sr id  but retailer id not found no updating the retailer id  ");

				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sqlVisitmaster, new String[] { "visit_id" });
						// pst.setInt(1,memberDetail.getEmployeeId());
						pst.setInt(1, audit.getVisitorId());
						pst.setInt(2, audit.getRetailerId());
						pst.setString(3, new Timestamp(System.currentTimeMillis()).toString());
						pst.setInt(4, 1);
						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();
				jdbcTemplate.update("UPDATE tbl_last_visit SET activity_perform_count = 1,visit_id=" + key
						+ " ,  retailer_id = " + audit.getRetailerId() + " WHERE visit_id = " + visitId + "");

				KeyHolder keyHolder1 = new GeneratedKeyHolder();
				updateFeedback = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(INSERT_AUDIT_ASSEST, new String[] { "id" });
						pst.setInt(1, audit.getAssetId());
						pst.setInt(2, audit.getRetailerId());
						pst.setString(3, audit.getQty());
						pst.setString(4, audit.getAmount());
						pst.setString(5, audit.getAssignDate());
						pst.setString(6, audit.getIsAvailable());
						pst.setInt(7, audit.getRatingId());
						pst.setString(8, audit.getDescription());
						pst.setString(9, audit.getImage1());
						pst.setString(10, audit.getImage2());
						pst.setString(11, audit.getImage3());
						pst.setInt(12, audit.getVisitorId());
						pst.setInt(13, audit.getRoleID());
						pst.setString(14, new Timestamp(System.currentTimeMillis()).toString());
						pst.setString(15, audit.getLat());
						pst.setString(16, audit.getLng());
						
						return pst;
					}
				},

						keyHolder1);
				BigInteger key1 = (BigInteger) keyHolder1.getKey();

				if (updateFeedback > 0) {
					String sql = "UPDATE tbl_asset_assign SET isAvailable=?,feedback_rating_id=?,description=?,image1=?,image2=?,image3=? WHERE id=? " ;
					 jdbcTemplate.update(sql,
							new Object[] { audit.getIsAvailable(), audit.getRatingId() , audit.getDescription(),audit.getImage1(),audit.getImage2(),audit.getImage3(),audit.getAssignId() });

					update_visit_info = jdbcTemplate.update(visitinfo,
							new Object[] { key.intValue(), audit.getVisitorId(), audit.getRetailerId(), null,
									4, key1.intValue(), new Timestamp(System.currentTimeMillis()) });
					updateAttendence(new Timestamp(System.currentTimeMillis()), audit.getVisitorId(),  visitId, audit.getRetailerId());
				}

			}

	}if(update_visit_info>0){genericResponse.setMessage("audit  added successfully");genericResponse.setStatus("success");genericResponse.setStatusCode(0);transactionManager.commit(status);}else{genericResponse.setMessage("Couldn't add audit ");genericResponse.setStatus("fail");genericResponse.setStatusCode(1);

	}

	}

	catch(
	Exception e){genericResponse.setMessage("Couldn't add audit application server error");genericResponse.setStatus("fail");genericResponse.setStatusCode(-1);transactionManager.rollback(status);e.printStackTrace();

	}return genericResponse;
}


	

	@Override
	public GenericResponse addNote(AddNoteData addNoteData) {
		GenericResponse genericResponse = new GenericResponse();
		int update = 0;
		

		try {
	update = jdbcTemplate.update("update tbl_retailer set note='" + addNoteData.getNote()
						+ "' where retailer_id=" + addNoteData.getRetailerId());
			 if (update > 0) {
				genericResponse.setMessage("note added successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
			} else {
				genericResponse.setMessage("Coudn't  add note");

				genericResponse.setStatus("fail");
				genericResponse.setStatusCode(1);
			}
		} catch (Exception e) {
			genericResponse.setMessage("Counldn't add note application server error ");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);

			e.printStackTrace();
		}
		return genericResponse;
	}

	@Override
	public GenericResponse addTarget(Target target) {
		GenericResponse genericResponse = new GenericResponse();
		/*
		 * TransactionDefinition def1 = new DefaultTransactionDefinition();
		 * TransactionStatus status2 = transactionManager.getTransaction(def1);
		 */
		try {
			
			if (target.getOperationType()!=null && target.getOperationType().equalsIgnoreCase("update") )
			{
				
				 jdbcTemplate.update("update tbl_target set target_qty='" + target.getUpdatedTargetQty()
				+ "' where target_id=" + target.getTargetId());
				
				
			}
				String sql = "INSERT INTO tbl_target(target_month,targer_year,brand_id,target_qty,create_date,creted_by,assigned_id,role_id) "
						+ " VALUES(?,?,?,?,?,?,?,?)";

				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sql, new String[] { "route_id" });
						
						pst.setString(1, target.getTargetMonth());
						pst.setString(2, target.getTargetYear());
						pst.setInt(3, target.getBrandId());
						pst.setInt(4, target.getQty());
						pst.setString(5, new Timestamp(System.currentTimeMillis()).toString());
						pst.setInt(6, target.getCreatedById());
						pst.setInt(7, target.getAssignId());
						pst.setInt(8, target.getRoleId());
						

						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				System.out.println("Key ====>" + key);

				if (update > 0) {
					genericResponse.setMessage(" added successfully");
					genericResponse.setStatus("success");
					genericResponse.setStatusCode(0);
				} else {
					genericResponse.setMessage("Coudn't  add ");

					genericResponse.setStatus("fail");
					genericResponse.setStatusCode(1);
				}
				

					
			
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add target application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();
			// transactionManager.rollback(status2);

		}

		return genericResponse;

	}

	@Override
	public GenericResponse addScheme(Scheme scheme) {
		GenericResponse genericResponse = new GenericResponse();
		
		  TransactionDefinition def = new DefaultTransactionDefinition();
		  TransactionStatus status = transactionManager.getTransaction(def);
		 
		try {
			
				String sql = "INSERT INTO tbl_scheme(title,description,from_date,to_date,brand_id,product_id,create_date) "
						+ " VALUES(?,?,?,?,?,?,?)";

				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sql, new String[] { "id" });
						pst.setString(1, scheme.getTitle());
						pst.setString(2, scheme.getDescription());
						pst.setString(3, scheme.getFromDate());
						pst.setString(4, scheme.getToDate());
						pst.setInt(5, scheme.getBrandId());
						pst.setInt(6, scheme.getProductId());
						pst.setString(7, new Timestamp(System.currentTimeMillis()).toString());

						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				System.out.println("Key ====>" + key);

				if (update > 0) {
					String assetSql = "INSERT INTO tbl_scheme_assign(scheme_id,retailer_id,assign_date)"
							+ "VALUES(?,?,?)";
					int childUpdate = 0;
					for (Integer i : scheme.getRetailerList()) {

						 childUpdate = jdbcTemplate.update(assetSql, new Object[] { key.intValue(), i,(new Timestamp(System.currentTimeMillis())) });

					}

							if (childUpdate > 0) {
								genericResponse.setMessage(" added successfully");
								genericResponse.setStatus("success");
								genericResponse.setStatusCode(0);
								 transactionManager.commit(status);

							}

						

					
				}
		}

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add  application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();

		}

		return genericResponse;

	}

	@Override
	public GenericResponse addSurvey(Servey servey) {
		ServeyResponse serveyResponse = new ServeyResponse();
		
		 
		try {
			
				String sql = "INSERT INTO tbl_servey(title,description,start_date,end_date,create_date,distributer_id) "
						+ " VALUES(?,?,?,?,?,?)";

				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sql, new String[] { "servey_id" });
						pst.setString(1, servey.getTitle());
						pst.setString(2, servey.getDescription());
						pst.setString(3, servey.getStartDate());
						pst.setString(4, servey.getEndDate());
						pst.setString(5, new Timestamp(System.currentTimeMillis()).toString());
						pst.setInt(6, servey.getDistributerId());
						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				System.out.println("Key ====>" + key);

				serveyResponse.setMessage(" added successfully");
				serveyResponse.setStatus("success");
				serveyResponse.setStatusCode(0);
				serveyResponse.setResponseId(key.intValue());				 
							}
		

		catch (Exception e) {
			serveyResponse.setMessage("Couldn't add  application server error");
			serveyResponse.setStatus("fail");
			serveyResponse.setStatusCode(-1);
			e.printStackTrace();
			 

		}

		return serveyResponse;

	}

	@Override
	public GenericResponse addQuestion(Questions questions) {
		ServeyResponse serveyResponse = new ServeyResponse();
		int childUpdate = 0;
		 
		try {
			
				String sql = "INSERT INTO tbl_question(servey_id,questioon,editable,objective,image) "
						+ " VALUES(?,?,?,?,?)";

				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sql, new String[] { "question_id" });
						pst.setInt(1, questions.getServeyId());
						pst.setString(2, questions.getQuestion());
						pst.setString(3, questions.getEditable());
						pst.setString(4, questions.getObjective());
						pst.setString(5,questions.getImage());

						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				System.out.println("Key ====>" + key);
				if (key!=null){
					String sqlQuestion = "INSERT INTO tbl_answer(question_id,answer) "
							+ " VALUES(?,?)";
					
					for (String s :questions.getAnswerList() ){
			childUpdate = jdbcTemplate.update(sqlQuestion, new Object[] { key.intValue(),s});
					}
				}
				
				

				serveyResponse.setMessage(" added successfully");
				serveyResponse.setStatus("success");
				serveyResponse.setStatusCode(0);
				serveyResponse.setResponseId(key.intValue());				 
							}
		

		catch (Exception e) {
			serveyResponse.setMessage("Couldn't add  application server error");
			serveyResponse.setStatus("fail");
			serveyResponse.setStatusCode(-1);
			e.printStackTrace();
			 

		}

		return serveyResponse;

	}

	@Override
	public GenericResponse submitSurvey(SubmitServey submitServey) {
		GenericResponse genericResponse = new GenericResponse();
		int childUpdate = 0;
		 
		try {
			
				String sql = "INSERT INTO tbl_users_answer(user_answer_id,question_id,member_id,retailer_id,servey_id) "
						+ " VALUES(?,?,?,?,?)";

				childUpdate = jdbcTemplate.update(sql, new Object[] { submitServey.getUserAnwerId(),submitServey.getQuestionId(),submitServey.getMemberId(),submitServey.getRetailerId(),submitServey.getServeyid()});

				genericResponse.setMessage(" added successfully");
				genericResponse.setStatus("success");
				genericResponse.setStatusCode(0);
							 
		}
		

		catch (Exception e) {
			genericResponse.setMessage("Couldn't add  application server error");
			genericResponse.setStatus("fail");
			genericResponse.setStatusCode(-1);
			e.printStackTrace();
			 

		}

		return genericResponse;

	}

	@Override
    public ScheduleResponse createSchedule(CreateScheduleData createSchedule) {
        ScheduleResponse scheduleResponse = new ScheduleResponse();
    
        
       try {
        int update = 0;
      
       
              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
              Date date2 = new Date();  
              String str=formatter.format(date2);
              Date date=formatter.parse(str);
              String string=createSchedule.getVisitDate();
	       Date date1 = formatter.parse(string);
              
              System.out.println(date);
              System.out.println(date1);
           boolean b= date1.equals(date);
         System.out.println(b);
             if(date1.after(date) || date1.equals(date)){
                 
                 String create_sql = "INSERT INTO tbl_schedule(title,retailer_id,assigner_id,note,visit_date,start_time,end_time,assigned_id)"
                    + "VALUES(?,?,?,?,?,?,?,?)";
               update = jdbcTemplate.update(create_sql, new Object[]{createSchedule.getTitle(), createSchedule.getRetailerId(), createSchedule.getAssignerId(), createSchedule.getNote(), createSchedule.getVisitDate(), createSchedule.getStartTime(), createSchedule.getEndTime(),createSchedule.getAssignerId()});

             }else{
                  scheduleResponse.setMessage("Date has been past ");
                    
             }
                 

        
            
        
                  if (update > 0) {
                scheduleResponse.setMessage("Schedule has been created");
                scheduleResponse.setStatus("success");
                scheduleResponse.setStatusCode(0);
            } else {
                scheduleResponse.setMessage("Couldn't create schedule");
                scheduleResponse.setStatus("fail");
                scheduleResponse.setStatusCode(1);
            }
        } catch (Exception e) {
            scheduleResponse.setMessage("Couldn't create schedule application server error");
            scheduleResponse.setStatus("fail");
            scheduleResponse.setStatusCode(-1);

            e.printStackTrace();

        }
        return scheduleResponse;
    }

	@Override
	public GenericResponse addDistributer(AddDistributer addDistributer) {
	
		ServeyResponse serveyResponse = new ServeyResponse();
		
		 
		try {
			
				String sql = "INSERT INTO tbl_distributor(distributor_name,mobile_no,email_id,stateId,address,city_name) "
						+ " VALUES(?,?,?,?,?,?)";

				KeyHolder keyHolder = new GeneratedKeyHolder();
				int update = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement pst = con.prepareStatement(sql, new String[] { "distributor_id" });
						pst.setString(1, addDistributer.getDistrinuterName());
						pst.setString(2, addDistributer.getMobileNumber());
						pst.setString(3, addDistributer.getEmailId());
						pst.setInt(4, addDistributer.getStateId());
						pst.setString(5, addDistributer.getAddress());
						pst.setString(6, addDistributer.getCityName());
						return pst;
					}
				},

						keyHolder);
				BigInteger key = (BigInteger) keyHolder.getKey();

				System.out.println("Key ====>" + key);

				serveyResponse.setMessage(" added successfully");
				serveyResponse.setStatus("success");
				serveyResponse.setStatusCode(0);
				serveyResponse.setResponseId(key.intValue());				 
							}
		

		catch (Exception e) {
			serveyResponse.setMessage("Couldn't add  application server error");
			serveyResponse.setStatus("fail");
			serveyResponse.setStatusCode(-1);
			e.printStackTrace();
			 

		}

		return serveyResponse;

	}


}
