package com.rc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rc.dao.DeleteResourceDao;
import com.rc.model.Brand;
import com.rc.model.GenericRequest;
import com.rc.model.GenericResponse;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;


@RestController
@EnableWebMvc
public class DeleteResource {

	 @Autowired
	    public DeleteResourceDao deleteResourceDao;
	
	  @RequestMapping(value = "/deleteTeamMember", method = RequestMethod.POST, headers = "Accept=application/json")
			public GenericResponse deleteTeamMember(@RequestBody GenericRequest genericRequest ) {

				GenericResponse genericResponse = new GenericResponse();

				try {
					System.out.println("Initiate Request");
					genericResponse = deleteResourceDao.deleteTeamMember(genericRequest.getId());
				} catch (Exception e) {
				}

				return genericResponse;
			}
	  
	  
	  @RequestMapping(value = "/deleteRole", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteRole(@RequestBody GenericRequest genericRequest ) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteRole(genericRequest.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/deleteDesignation", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteDesignation(@RequestBody GenericRequest genericRequest ) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteDesignation(genericRequest.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/deleteRetailer", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteRetailer(@RequestBody GenericRequest genericRequest ) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				
				genericResponse = deleteResourceDao.deleteRetailer(genericRequest.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/deleteRetailerType", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteRetailerType(@RequestBody GenericRequest genericRequest ) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				
				genericResponse = deleteResourceDao.deleteRetailerType(genericRequest.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/deleteRetailerSubType", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteRetailerSubType(@RequestBody GenericRequest genericRequest ) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				
				genericResponse = deleteResourceDao.deleteRetailerSubType(genericRequest.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
	  @RequestMapping(value = "/deleteRetailerCategory", method = RequestMethod.POST, headers = "Accept=application/json")
			public GenericResponse deleteRetailerCategory(@RequestBody GenericRequest genericRequest ) {

				GenericResponse genericResponse = new GenericResponse();

				try {
					System.out.println("Initiate Request");
					
					genericResponse = deleteResourceDao.deleteRetailerCategory(genericRequest.getId());
				} catch (Exception e) {
				}

				return genericResponse;
			}
		  
	  
	  @RequestMapping(value = "/deleteZone", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteZone(@RequestBody GenericRequest genericRequest ) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				
				genericResponse = deleteResourceDao.deleteZone(genericRequest.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteProduct(@RequestBody Product product) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteProduct(product);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  
	  @RequestMapping(value = "/deleteBrand", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteBrand(@RequestBody Brand brand) {

			GenericResponse genericResponse = new GenericResponse();
			
			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteBrand(brand);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return genericResponse;
		}
	  
	  @RequestMapping(value = "/deletePackagetype", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deletePackagetype(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deletePackagetype(packageType);
			} catch (Exception e) {
			}

			return genericResponse;
		}

		@RequestMapping(value = "/deleteProductCategory", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteProductCategory(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteProductCategory(packageType.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
		
		
		@RequestMapping(value = "/deleteProductSegment", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteProductSegment(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteProductSegment(packageType.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
		
		
		
		@RequestMapping(value = "/deleteProductSubType", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteProductSubType(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteProductSubType(packageType.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}
		
		
		
		@RequestMapping(value = "/deleteProductType", method = RequestMethod.POST, headers = "Accept=application/json")
		public GenericResponse deleteProductType(@RequestBody ProductRawdata packageType) {

			GenericResponse genericResponse = new GenericResponse();

			try {
				System.out.println("Initiate Request");
				genericResponse = deleteResourceDao.deleteProductType(packageType.getId());
			} catch (Exception e) {
			}

			return genericResponse;
		}

		  

}
