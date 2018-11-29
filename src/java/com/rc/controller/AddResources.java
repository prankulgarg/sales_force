/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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

/**
 *
 * @author Thakur Ravi Chauhan
 */
@RestController
@EnableWebMvc
public class AddResources {

	private static Logger logger = Logger.getLogger(AddResources.class);

	@Autowired
	public LoginServiceDao commonService;

	/*@RequestMapping(value = "/loginRequest", method = RequestMethod.POST, headers = "Accept=application/json")
	public LoginResponse validateCredentials(@RequestBody LoginDetail loginDetail) {
		logger.info("loginRequest");
		
		LoginResponse loginResponse = new LoginResponse();
		try {
			
			System.out.println("Initiate Request");
			loginResponse = commonService.validateCredentials(loginDetail);
		} catch (Exception e) {
			loginResponse.setMessage("error while login");
			
			
		}

		return loginResponse;
	}*/

	@RequestMapping(value = "/addRole", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse saveRoleDetail(@RequestBody CommonDetail commonDetail) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.insertRoleDetail(commonDetail);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addDesignation", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse saveDesignationDetail(@RequestBody CommonDetail commonDetail) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.insertDesignationDetail(commonDetail);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addMember(@RequestBody Member addMember) {

		GenericResponse genericResponse = new GenericResponse();
		System.out.println("Request Received ===>" + addMember);

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.insertMemberDetail(addMember);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addRetaileCat", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse saveRetailerCat(@RequestBody RetailerDetailedData retailerCategoryRequest) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.saveRetailerCat(retailerCategoryRequest);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addRetailerType", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse saveRetailertype(@RequestBody RetailerDetailedData retailerCategoryRequest) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.saveRetailerType(retailerCategoryRequest);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addRetailerSubType", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse saveRetailerSubtype(@RequestBody RetailerDetailedData retailerCategoryRequest) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.saveRetailerSubType(retailerCategoryRequest);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addZone", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addZone(@RequestBody RetailerDetailedData retailerCategoryRequest) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.saveZone(retailerCategoryRequest);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addRetaler", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addretailer(@RequestBody AddRetailerRequest addRetailerRequest) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("add reatailer Request===>>"+addRetailerRequest);
			System.out.println("Initiate Request");
			genericResponse = commonService.saveRetailer(addRetailerRequest);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addPackagetype", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addPackageType(@RequestBody ProductRawdata packageType) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addPackageType(packageType);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	@RequestMapping(value = "/addProductCategory", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addProductCategory(@RequestBody ProductRawdata packageType) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addProductCategory(packageType);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	@RequestMapping(value = "/addProductSegment", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addProductSegment(@RequestBody ProductRawdata packageType) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addProductSegment(packageType);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	
	@RequestMapping(value = "/addProductSubType", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addProductSubType(@RequestBody ProductRawdata packageType) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addProductSubType(packageType);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	
	@RequestMapping(value = "/addProductType", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addProductType(@RequestBody ProductRawdata packageType) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addProductType(packageType);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	@RequestMapping(value = "/addBrand", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addBrand(@RequestBody Brand brand) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addBrand(brand);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	
	@RequestMapping(value = "/addDistributerLicense", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addDistributerLicense(@RequestBody DistributerLicense distributerLicense ) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addDistributerLicense(distributerLicense);
		} catch (Exception e) {
		}
		
		return genericResponse;
	}
	
	
	@RequestMapping(value = "/addDistributer", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addDistributer(@RequestBody AddDistributer addDistributer ) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addDistributer(addDistributer);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/addQtyPcs", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addQtyPcs(@RequestBody QtyInPcs qtyInPcs ) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addQtyPcs(qtyInPcs);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addProduct(@RequestBody Product product) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addProduct(product);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	@RequestMapping(value = "/addFeedbackType", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addFeedbackType(@RequestBody ProductRawdata productRawdata) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addFeedbackType(productRawdata);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	@RequestMapping(value = "/addFeedbackRating", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addFeedbackRating(@RequestBody ProductRawdata productRawdata) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addFeedbackRating(productRawdata);
		} catch (Exception e) {
		}

		return genericResponse;
	}

	
	@RequestMapping(value = "/addFeedback", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addFeedback(@RequestBody Feedback feedback) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addFeedback(feedback);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	@RequestMapping(value = "/addAuditAsset", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addAuditAsset(@RequestBody AuditAssetReatiler audit) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addAuditAsset(audit);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	
	@RequestMapping(value = "/addPromiseOrder", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addPromiseOrder(@RequestBody PromiseOrder promiseOrder) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addPromiseOrder(promiseOrder);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	@RequestMapping(value = "/addRetailerStock", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addRetailerStock(@RequestBody PromiseOrder promiseOrder) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addRetailerStock(promiseOrder);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	

	
	@RequestMapping(value = "/addRoute", method = RequestMethod.POST, headers = "Accept=application/json")
	public GenericResponse addRoute(@RequestBody Route route) {

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Initiate Request");
			genericResponse = commonService.addRoute(route);
		} catch (Exception e) {
		}

		return genericResponse;
	}
	
	
	 @RequestMapping(value="/addAsset", method=RequestMethod.POST,headers="Accept=application/json")
	       public GenericResponse addAssets(@RequestBody AssetByRetailerData assetDetailedData){
	           GenericResponse genericResponse =new GenericResponse();
	           try{
	               System.out.println("Initiate Request");
	               genericResponse=commonService.addAsset(assetDetailedData);
	           }catch(Exception e){
	               
	           }
	           return genericResponse;
	       }

	 @RequestMapping(value="/addScheme", method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse addScheme(@RequestBody Scheme scheme){
         GenericResponse genericResponse =new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=commonService.addScheme(scheme);
         }catch(Exception e){
             
         }
         return genericResponse;
     }

	 
	 
	 
	 @RequestMapping(value="/assignAssets", method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse assignAssets(@RequestBody AssignAssets assignAssets){
         GenericResponse genericResponse =new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=commonService.assignAssets(assignAssets);
         }catch(Exception e){
             
         }
         return genericResponse;
     }

	 @RequestMapping(value="/createSchedule",method=RequestMethod.POST ,headers="Accept=application/json" )
     public GenericResponse createSchedule(@RequestBody CreateScheduleData createScheduleData){
         GenericResponse genericResponse =new GenericResponse();
         try{
             System.out.println("Inititate request");
             genericResponse=commonService.createSchedule(createScheduleData);
            }catch(Exception e){
                    
                    }
            return genericResponse;
         }	


	    @RequestMapping(value="/addNote",method=RequestMethod.POST,headers="Accept=application/json")
	        public GenericResponse addNote(@RequestBody AddNoteData addNoteData){
	            GenericResponse genericResponse=new GenericResponse();
	            try{
	                System.out.println("Inititate Request");
	                genericResponse=commonService.addNote(addNoteData);
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	            return genericResponse;
	        }
	    
	    
	    @RequestMapping(value="/addTarget",method=RequestMethod.POST,headers="Accept=application/json")
        public GenericResponse addTarget(@RequestBody Target target){
            GenericResponse genericResponse=new GenericResponse();
            try{
                System.out.println("Inititate Request add Trget");
                genericResponse=commonService.addTarget(target);
            }catch(Exception e){
                e.printStackTrace();
            }
            return genericResponse;
        }
	    @RequestMapping(value="/addSurvey",method=RequestMethod.POST,headers="Accept=application/json")
        public GenericResponse addSurvey(@RequestBody Servey servey){
            GenericResponse genericResponse=new GenericResponse();
            try{
                System.out.println("Inititate Request add Trget");
                genericResponse=commonService.addSurvey(servey);
            }catch(Exception e){
                e.printStackTrace();
            }
            return genericResponse;
        }
	    
	    
	    
	    @RequestMapping(value="/addQuestion",method=RequestMethod.POST,headers="Accept=application/json")
        public GenericResponse addQuestion(@RequestBody Questions questions){
            GenericResponse genericResponse=new GenericResponse();
            try{
                System.out.println("Inititate Request add Trget");
                genericResponse=commonService.addQuestion(questions);
            }catch(Exception e){
                e.printStackTrace();
            }
            return genericResponse;
        }
	    
	    @RequestMapping(value="/submitSurvey",method=RequestMethod.POST,headers="Accept=application/json")
        public GenericResponse submitSurvey(@RequestBody SubmitServey submitServey){
            GenericResponse genericResponse=new GenericResponse();
            try{
                System.out.println("Inititate Request add Trget");
                genericResponse=commonService.submitSurvey(submitServey);
            }catch(Exception e){
                e.printStackTrace();
            }
            return genericResponse;
        }
	    
	 
}
