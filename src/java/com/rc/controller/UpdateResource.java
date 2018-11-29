package com.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rc.dao.UpdateResourceDao;
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

@RestController
@EnableWebMvc
public class UpdateResource {
	  @Autowired
	    public UpdateResourceDao updateResourceDao;
	
	  
	  @RequestMapping(value = "/verifyRetailer", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse addretailer(@RequestBody AddRetailerRequest addRetailerRequest) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.verifyRetailer(addRetailerRequest);
			} catch (Exception e) {
			}

			return genericResponse;
		}

	  @RequestMapping(value = "/updateMember", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse addMember(@RequestBody Member addMember) {

			GenericResponse genericResponse = new GenericResponse();
			System.out.println("Request Received ===>" + addMember);

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateMember(addMember);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/makeRetailerDefaulter", method = RequestMethod.POST, headers = "Accept=application/json")
			public GenericResponse makeRetailerDefaulter(@RequestBody RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

				GenericResponse genericResponse = new GenericResponse();
				

				try {
					System.out.println("Initiate Request");
					genericResponse = updateResourceDao.makeRetailerDefaulter(retailerBlockedORDefaulterReq);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return genericResponse;
			}
		  
	  
	  @RequestMapping(value = "/makeRetailerUnDefaulter", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse makeRetailerUnDefaulter(@RequestBody RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

			GenericResponse genericResponse = new GenericResponse();
			

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.makeRetailerUnDefaulter(retailerBlockedORDefaulterReq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/makeRetailerBlocked", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse makeRetailerBlocked(@RequestBody RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.makeRetailerBlocked(retailerBlockedORDefaulterReq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/makeRetailerUnBlocked", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse makeRetailerUnBlocked(@RequestBody RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.makeRetailerUnBlocked(retailerBlockedORDefaulterReq);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/makeRetailerActive", method = RequestMethod.POST, headers = "Accept=application/json")
			public GenericResponse makeRetailerActive(@RequestBody RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

				GenericResponse genericResponse = new GenericResponse();
				
				try {
					System.out.println("Initiate Request");
					genericResponse = updateResourceDao.makeRetailerActive(retailerBlockedORDefaulterReq);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return genericResponse;
			}
		  
	  @RequestMapping(value = "/makeRetailerInactive", method = RequestMethod.POST, headers = "Accept=application/json")
			public GenericResponse makeRetailerInactive(@RequestBody RetailerBlockedORDefaulterReq retailerBlockedORDefaulterReq) {

				GenericResponse genericResponse = new GenericResponse();
				
				try {
					System.out.println("Initiate Request");
					genericResponse = updateResourceDao.makeRetailerInactive(retailerBlockedORDefaulterReq);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return genericResponse;
			}
		  
	  
	  
	  @RequestMapping(value = "/updateRole", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateRole(@RequestBody CommonDetail commonDetail) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateRole(commonDetail);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/updateDesignation", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateDesignation(@RequestBody CommonDetail commonDetail) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateDesignation(commonDetail);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/updateRetailer", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateRetailer(@RequestBody AddRetailerRequest updateRetailerRequest) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateRetailer(updateRetailerRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/updateRetailerType", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateRetailerType(@RequestBody RetailerDetailedData retailerCategoryRequest) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateRetailerType(retailerCategoryRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/updateRetailerSubType", method = RequestMethod.POST, headers = "Accept=application/json")
			public GenericResponse updateRetailerSubType(@RequestBody RetailerDetailedData retailerCategoryRequest) {

				GenericResponse genericResponse = new GenericResponse();
				
				try {
					System.out.println("Initiate Request");
					genericResponse = updateResourceDao.updateRetailerSubType(retailerCategoryRequest);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return genericResponse;
			}
	  @RequestMapping(value = "/updateRetailerCategory", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateRetailerCategory(@RequestBody RetailerDetailedData retailerCategoryRequest) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateRetailerCategory(retailerCategoryRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}

	  
	  @RequestMapping(value = "/updateZone", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateZone(@RequestBody RetailerDetailedData retailerCategoryRequest) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateZone(retailerCategoryRequest);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}

	  @RequestMapping(value = "/updateAsset", method = RequestMethod.POST, headers = "Accept=application/json")
			public GenericResponse updateAsset(@RequestBody AssetByRetailerData assetDetailedData) {

				GenericResponse genericResponse = new GenericResponse();
				
				try {
					System.out.println("Initiate Request");
					genericResponse = updateResourceDao.updateAsset(assetDetailedData);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return genericResponse;
			}
	  
	  
	  
	  
	  
	  @RequestMapping(value = "/updateProduct", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateProduct(@RequestBody Product product) {

			GenericResponse genericResponse = new GenericResponse();
			System.out.println("update Product "+product);
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateProduct(product);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/updateBrand", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateBrand(@RequestBody Brand brand) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateBrand(brand);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/updatePackagetype", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updatePackagetype(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updatePackagetype(packageType);
			} catch (Exception e) {
			}

			return genericResponse;
		}

		@RequestMapping(value = "/updateProductCategory", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateProductCategory(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateProductCategory(packageType);
			} catch (Exception e) {
			}

			return genericResponse;
		}
		
		
		@RequestMapping(value = "/updateProductSegment", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateProductSegment(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateProductSegment(packageType);
			} catch (Exception e) {
			}

			return genericResponse;
		}
		
		
		
		@RequestMapping(value = "/updateProductSubType", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateProductSubType(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateProductSubType(packageType);
			} catch (Exception e) {
			}

			return genericResponse;
		}
		
		
		
		@RequestMapping(value = "/updateProductType", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse updateProductType(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.updateProductType(packageType);
			} catch (Exception e) {
			}

			return genericResponse;
		}

		
		@RequestMapping(value = "/auditAsset", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse auditAsset(@RequestBody AuditAssetReatiler auditAssetReatiler) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = updateResourceDao.auditAsset(auditAssetReatiler);
			} catch (Exception e) {
			}

			return genericResponse;
		}

		  
	  

}
