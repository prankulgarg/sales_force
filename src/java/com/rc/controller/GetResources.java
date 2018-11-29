/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.rc.dao.GetResorcesDao;
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
import com.rc.model.GenericSearchRequest;
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







/**
 *
 * @author Thakur Ravi Chauhan
 */
 @RestController
@EnableWebMvc
public class GetResources {
   


    @Autowired
    public GetResorcesDao getResorcesDao;
    
    
    @RequestMapping(value = "/loginRequest", method = RequestMethod.POST, headers = "Accept=application/json")
	public LoginResponse validateCredentials(@RequestBody LoginDetail loginDetail) {
		//logger.info("loginRequest");
		
		LoginResponse loginResponse = new LoginResponse();
		try {
			
			System.out.println("Initiate Request");
			loginResponse = getResorcesDao.validateCredentials(loginDetail);
		} catch (Exception e) {
			loginResponse.setMessage("error while login");
			
			
		}

		return loginResponse;
	}

    
    
    @RequestMapping(value = "/getRole", method = RequestMethod.POST, headers = "Accept=application/json")
    public CommonDetailResponse getRole( ) {
         
         
          CommonDetailResponse lstcommonDetail = new CommonDetailResponse();
  
        try {
            System.out.println("Initiate Request");
             lstcommonDetail = getResorcesDao.getAllRole();
         } catch (Exception e) {
         }
      
        
        return lstcommonDetail;
    }
    
    
    @RequestMapping(value = "/getDesignation", method = RequestMethod.POST, headers = "Accept=application/json")
    public CommonDetailResponse getDesignation( ) {
         
         
          CommonDetailResponse lstcommonDetail = new CommonDetailResponse();
  
        try {
            System.out.println("Initiate Request");
             lstcommonDetail = getResorcesDao.getAllDesignation();
         } catch (Exception e) {
         }
      
        
        return lstcommonDetail;
    }
    
    
    @RequestMapping(value = "/getRetailerCat", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerDetailResponse getRetailerCat( ) {
         
         
          RetailerDetailResponse retailerDetailResponse = new RetailerDetailResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerDetailResponse = getResorcesDao.getAllretailerCat();
         } catch (Exception e) {
         }
      
        
        return retailerDetailResponse;
    }
    
    
    @RequestMapping(value = "/getRetailerType", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerDetailResponse getRetailerType( ) {
         
         
          RetailerDetailResponse retailerDetailResponse = new RetailerDetailResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerDetailResponse = getResorcesDao.getAllretailertype();
         } catch (Exception e) {
         }
      
        
        return retailerDetailResponse;
    }
    
    
     @RequestMapping(value = "/getRetailerSubType", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerDetailResponse getRetailerSubType( ) {
         
         
          RetailerDetailResponse retailerDetailResponse = new RetailerDetailResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerDetailResponse = getResorcesDao.getAllretailerSubtype();
         } catch (Exception e) {
         }
      
        
        return retailerDetailResponse;
    }
    
    
    
    
    
    
    
    
     @RequestMapping(value = "/getRetailer", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerRawDataResponse getRetailer(@RequestBody GenericRequest genericRequest) {
         
         
          RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerRawDataResponse = getResorcesDao.getretailerByDist(genericRequest);
         } catch (Exception e) {
         }
      
        
        return retailerRawDataResponse;
    }
     @RequestMapping(value = "/getRetailerByRetailerId", method = RequestMethod.POST, headers = "Accept=application/json")
     public RetailerRawDataResponse getRetailerByRetailerId(@RequestBody GenericRequest genericRequest ) {
          
          
           RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
   
         try {
             System.out.println("Initiate Request");
              retailerRawDataResponse = getResorcesDao.getRetailerByRetailerId(genericRequest.getId());
          } catch (Exception e) {
          }
       
         
         return retailerRawDataResponse;
     }
    
    
    
    
    @RequestMapping(value = "/getRetailerById", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerRawDataResponse getRetailerById( @RequestBody GenericRequest genericRequest ) {
         
         
          RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerRawDataResponse = getResorcesDao.getAllretailer(genericRequest.getId());
         } catch (Exception e) {
         }
      
        
        return retailerRawDataResponse;
    }
    
    @RequestMapping(value = "/getRetailerByGroupId", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerRawDataResponse getRetailerByGroupId( @RequestBody GenericRequest genericRequest ) {
         
         
          RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerRawDataResponse = getResorcesDao.getRetailerByGroupId(genericRequest.getId());
         } catch (Exception e) {
         }
      
        
        return retailerRawDataResponse;
    }
    
    
    
    @RequestMapping(value = "/getRetailerByRouteId", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerRawDataResponse getRetailerByRouteId( @RequestBody GenericRequest genericRequest ) {
         
         
          RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerRawDataResponse = getResorcesDao.getRetailerByRouteId(genericRequest.getId());
         } catch (Exception e) {
         }
      
        
        return retailerRawDataResponse;
    }
    
    @RequestMapping(value = "/getBlockedretailer", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerRawDataResponse getBlockedretailer(@RequestBody GenericRequest genericRequest) {
         
         
          RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerRawDataResponse = getResorcesDao.getBlockedretailer(genericRequest.getDistributerId());
         } catch (Exception e) {
         }
      
        
        return retailerRawDataResponse;
    }
    
   
    
    
    
    
    @RequestMapping(value = "/getAllGroup", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerRawDataResponse getAllGroup(@RequestBody GenericRequest genericRequest) {
         
         
          RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerRawDataResponse = getResorcesDao.getAllGroupr(genericRequest.getDistributerId());
         } catch (Exception e) {
         }
      
        
        return retailerRawDataResponse;
    }
    
    
  
    @RequestMapping(value = "/getZone", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerDetailResponse getZone( ) {
         
         
          RetailerDetailResponse retailerDetailResponse = new RetailerDetailResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerDetailResponse = getResorcesDao.getZone();
         } catch (Exception e) {
         }
      
        
        return retailerDetailResponse;
    }
    
    

    @RequestMapping(value = "/getTeamByRole", method = RequestMethod.POST, headers = "Accept=application/json")
    public MemberResponse getTeamByRole( @RequestBody GenericRequest genericRequest ) {
         
         
    	MemberResponse memberResponse = new MemberResponse();
  
        try {
            System.out.println("Initiate Request");
            memberResponse = getResorcesDao.getTeamByRole(genericRequest);
         } catch (Exception e) {
         }
      
        
        return memberResponse;
    }
   
    
    @RequestMapping(value = "/getAllMemberBYDistributerId", method = RequestMethod.POST, headers = "Accept=application/json")
    public MemberResponse getAllMember(@RequestBody GenericRequest genericRequest) {
         
         
    	MemberResponse memberResponse = new MemberResponse();
  
        try {
            System.out.println("Initiate Request");
            memberResponse = getResorcesDao.getAllMember(genericRequest.getId());
         } catch (Exception e) {
         }
             
        return memberResponse;
    }
   
    

    @RequestMapping(value = "/getTeamById", method = RequestMethod.POST, headers = "Accept=application/json")
    public MemberResponse getTeamById( @RequestBody GenericRequest genericRequest ) {
         
         
    	MemberResponse memberResponse = new MemberResponse();
  
        try {
            System.out.println("Initiate Request");
            memberResponse = getResorcesDao.getTeamById(genericRequest.getId());
         } catch (Exception e) {
         }
      
        
        return memberResponse;
    }
    
    
    @RequestMapping(value = "/getTeamByAdvanceSearch", method = RequestMethod.POST, headers = "Accept=application/json")
    public MemberResponse getTeamByAdvanceSearch( @RequestBody AdvanceSearch advanceSearch ) {
         
         
    	MemberResponse memberResponse = new MemberResponse();
  
        try {
            System.out.println("Initiate Request");
            memberResponse = getResorcesDao.getTeamByAdvanceSearch(advanceSearch);
         } catch (Exception e) {
         }
      
        
        return memberResponse;
    }
    
    
    
    
    @RequestMapping(value = "/getListForProfile", method = RequestMethod.POST, headers = "Accept=application/json")
    public MemberResponse getAsmListForProfile( @RequestBody GenericRequest genericRequest ) {
         
         
    	MemberResponse memberResponse = new MemberResponse();
  
        try {
            System.out.println("Initiate Request");
            memberResponse = getResorcesDao.getAsmListForProfile(genericRequest);
         } catch (Exception e) {
         }
      
        
        return memberResponse;
    }
    
    
   
    
    
    
     @RequestMapping(value="/getAssetByRetailerId",method=RequestMethod.POST,headers="Accept=application/json")
    public AssetByRetailerResponse getAssetByRetailerId(@RequestBody GenericRequest genericRequest){
        AssetByRetailerResponse assetByRetailerResponse=new AssetByRetailerResponse();
        try{
            System.out.println("Initiate Request");
            assetByRetailerResponse=getResorcesDao.getAssetByRetailerId(genericRequest);
        }catch (Exception e){
            
        }
        return assetByRetailerResponse;
    }
     
     @RequestMapping(value="/getScheme",method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse getScheme(@RequestBody GenericRequest genericRequest){
    	 GenericResponse genericResponse=new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=getResorcesDao.getScheme(genericRequest);
         }catch (Exception e){
             
         }
         return genericResponse;
     } 
     
     
     @RequestMapping(value="/getAssetOfAllRetailer",method=RequestMethod.POST,headers="Accept=application/json")
     public AssetByRetailerResponse getAssetOfAllRetailer(@RequestBody GenericRequest genericRequest){
         AssetByRetailerResponse assetByRetailerResponse=new AssetByRetailerResponse();
         try{
             System.out.println("Initiate Request");
             assetByRetailerResponse=getResorcesDao.getAssetOfAllRetailer(genericRequest.getDistributerId());
         }catch (Exception e){
             
         }
         return assetByRetailerResponse;
     }
     
     
     
     
  
     @RequestMapping(value = "/getAllState", method = RequestMethod.POST, headers = "Accept=application/json")
     public CommonDetailResponse getAllState( ) {
          
          
           CommonDetailResponse lstcommonDetail = new CommonDetailResponse();
   
         try {
             System.out.println("Initiate Request");
              lstcommonDetail = getResorcesDao.getAllState();
          } catch (Exception e) {
          }
       
         
         return lstcommonDetail;
     }
     @RequestMapping(value = "/getAllDistributer", method = RequestMethod.POST, headers = "Accept=application/json")
     public DistributerResponse getAllDistributer( ) {
          
          
          DistributerResponse distributerResponse = new DistributerResponse();
   
         try {
             System.out.println("Initiate Request");
             distributerResponse = getResorcesDao.getAllDistributer();
          } catch (Exception e) {
          }
       
         
         return distributerResponse;
     }
     
   
     @RequestMapping(value = "/getAllRoute", method = RequestMethod.POST, headers = "Accept=application/json")
     public Routeresponse getAllRoute(@RequestBody GenericRequest genericRequest) {
          
          
           Routeresponse routeresponse = new Routeresponse();
   
         try {
             System.out.println("Initiate Request");
             routeresponse = getResorcesDao.getAllRoute(genericRequest.getDistributerId());
          } catch (Exception e) {
          }
       
         
         return routeresponse;
     }
     
     @RequestMapping(value = "/getRouteBySrId", method = RequestMethod.POST, headers = "Accept=application/json")
     public Routeresponse getRouteBySrId(@RequestBody GenericRequest genericRequest) {
          
          
           Routeresponse routeresponse = new Routeresponse();
   
         try {
             System.out.println("Initiate Request");
             routeresponse = getResorcesDao.getRouteBySrId(genericRequest.getId());
          } catch (Exception e) {
          }
       
         
         return routeresponse;
     }
     
     
     
     @RequestMapping(value = "/getPackagetype", method = RequestMethod.POST, headers = "Accept=application/json")
 	public GenericResponse addPackageType() {

 	ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();
 	

 		try {
 			System.out.println("Initiate Request");
 			productRawdataResponse = getResorcesDao.getPackagetype();
 		} catch (Exception e) {
 		}

 		return productRawdataResponse;
 	}

     
   
     @RequestMapping(value = "/getProductCategory", method = RequestMethod.POST, headers = "Accept=application/json")
  	public GenericResponse getProductCategory() {

  	ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();
  	  		try {
  			System.out.println("Initiate Request");
  			productRawdataResponse = getResorcesDao.getProductCategory();
  		} catch (Exception e) {
  		}

  		return productRawdataResponse;
  	}
     
     
     @RequestMapping(value = "/getProductSegment", method = RequestMethod.POST, headers = "Accept=application/json")
   	public GenericResponse getProductSegment() {

   	ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();
   	

   		try {
   			System.out.println("Initiate Request");
   			productRawdataResponse = getResorcesDao.getProductSegment();
   		} catch (Exception e) {
   		}

   		return productRawdataResponse;
   	}
     
     @RequestMapping(value = "/getProductSubType", method = RequestMethod.POST, headers = "Accept=application/json")
    	public GenericResponse getProductSubType() {

    	ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();
    	

    		try {
    			System.out.println("Initiate Request");
    			productRawdataResponse = getResorcesDao.getProductSubType();
    		} catch (Exception e) {
    		}

    		return productRawdataResponse;
    	}
     
     
     @RequestMapping(value = "/getProductType", method = RequestMethod.POST, headers = "Accept=application/json")
 	public GenericResponse getProductType() {

 	ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();
 	

 		try {
 			System.out.println("Initiate Request");
 			productRawdataResponse = getResorcesDao.getProductType();
 		} catch (Exception e) {
 		}

 		return productRawdataResponse;
 	}
     
     
     @RequestMapping(value = "/getQtyPcs", method = RequestMethod.POST, headers = "Accept=application/json")
  	public QtyPcsResponse getQtyPcs() {

  	QtyPcsResponse qtyPcsResponse = new QtyPcsResponse();
  	

  		try {
  			System.out.println("Initiate Request");
  			qtyPcsResponse = getResorcesDao.getQtyPcs();
  		} catch (Exception e) {
  		}

  		return qtyPcsResponse;
  	}
      
     
     @RequestMapping(value = "/getBrand", method = RequestMethod.POST, headers = "Accept=application/json")
 	public BrandResponse getBrand(@RequestBody GenericRequest genericRequest) {

    	 BrandResponse brandResponse = new BrandResponse();

 		try {
 			System.out.println("Initiate Request");
 			brandResponse = getResorcesDao.getBrand(genericRequest);
 		} catch (Exception e) {
 		}

 		return brandResponse;
 	}
 	
     
     @RequestMapping(value = "/getProduct", method = RequestMethod.POST, headers = "Accept=application/json")
  	public ProductResponse getProduct(@RequestBody GenericRequest genericRequest) {

    	 ProductResponse productResponse = new ProductResponse();

  		try {
  			System.out.println("Initiate Request");
  			productResponse = getResorcesDao.getProduct(genericRequest);
  		} catch (Exception e) {
  		}

  		return productResponse;
  	}
  	
     @RequestMapping(value = "/getProductForDownload", method = RequestMethod.POST, headers = "Accept=application/json")
   	public ProductResponse getProductForDownload(@RequestBody List<Integer> productList) {

     	 ProductResponse productResponse = new ProductResponse();

   		try {
   			System.out.println("Initiate Request");
   			productResponse = getResorcesDao.getProductForDownload(productList);
   		} catch (Exception e) {
   		}

   		return productResponse;
   	}
   	
     
     
     
     @RequestMapping(value = "/getFeedbackType", method = RequestMethod.POST, headers = "Accept=application/json")
 	public ProductRawdataResponse getFeedbackType() {

 	ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();
 	

 		try {
 			System.out.println("Initiate Request");
 			productRawdataResponse = getResorcesDao.getFeedbackType();
 		} catch (Exception e) {
 		}

 		return productRawdataResponse;
 	}
  
     @RequestMapping(value = "/getFeedbackRating", method = RequestMethod.POST, headers = "Accept=application/json")
 	public ProductRawdataResponse getFeedbackRating() {

 	ProductRawdataResponse productRawdataResponse = new ProductRawdataResponse();
 	

 		try {
 			System.out.println("Initiate Request");
 			productRawdataResponse = getResorcesDao.getFeedbackRating();
 		} catch (Exception e) {
 		}

 		return productRawdataResponse;
 	}
  
     @RequestMapping(value="/getAsset",method=RequestMethod.POST,headers="Accept=application/json")   
     public AssetByRetailerResponse getAsset(@RequestBody GenericRequest  genericRequest){
         AssetByRetailerResponse assetByRetailerResponse=new AssetByRetailerResponse();
         try{
             System.out.println("Inititate request");
             assetByRetailerResponse=getResorcesDao.getAsset(genericRequest);
         }catch(Exception e){
             
         }
         return assetByRetailerResponse;
     }
     
     
     @RequestMapping(value="/getFeedback",method=RequestMethod.POST,headers="Accept=application/json")   
     public FeedbackResponse getFeedback(@RequestBody GenericRequest  genericRequest){
    	 FeedbackResponse feedbackResponse=new FeedbackResponse();
         try{
             System.out.println("Inititate request");
             feedbackResponse=getResorcesDao.getFeedback(genericRequest);
         }catch(Exception e){
             
         }
         return feedbackResponse;
     }
     
     @RequestMapping(value="/getNonAssignedRouteSR",method=RequestMethod.POST,headers="Accept=application/json")   
     public MemberResponse getNonAssignedRouteSR(@RequestBody AdvanceSearch advancesearch){
    	 MemberResponse MemberResponse=new MemberResponse();
         try{
             System.out.println("Inititate request");
             MemberResponse=getResorcesDao.getNonAssignedRouteSR(advancesearch);
         }catch(Exception e){
             
         }
         return MemberResponse;
     }
     
     
     @RequestMapping(value="/getTeamActivityHistoryBy",method=RequestMethod.POST,headers="Accept=application/json")   
     public TeamActivityHistoryResponse getNonAssignedRouteSR(@RequestBody ActivityRequest ar){
    	 TeamActivityHistoryResponse teamActivityHistoryResponse=new TeamActivityHistoryResponse();
         try{
             System.out.println("Inititate request");
             teamActivityHistoryResponse =getResorcesDao.getTeamActivityHistoryBy(ar);
         }catch(Exception e){
             
         }
         return teamActivityHistoryResponse;
     }
     
     @RequestMapping(value="/getTeamActivity",method=RequestMethod.POST,headers="Accept=application/json")   
     public MemberResponse getTeamActivity(@RequestBody GenericRequest genericRequest){
    	 MemberResponse teamActivityHistoryResponse=new MemberResponse();
         try{
             System.out.println("Inititate request");
             teamActivityHistoryResponse =getResorcesDao.getTeamActivity(genericRequest);
         }catch(Exception e){
             
         }
         return teamActivityHistoryResponse;
     }
     	
     
     @RequestMapping(value = "/getTeamActivityRetailerWise", method = RequestMethod.POST, headers = "Accept=application/json")
     public RetailerRawDataResponse getTeamActivityRetailerWise( @RequestBody GenericRequest genericRequest ) {
          
          
           RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
   
         try {
             System.out.println("Initiate Request");
              retailerRawDataResponse = getResorcesDao.getTeamActivityRetailerWise(genericRequest);
          } catch (Exception e) {
          }
       
         
         return retailerRawDataResponse;
     }
     
     
     @RequestMapping(value = "/getPromiseOrder", method = RequestMethod.POST, headers = "Accept=application/json")
     public PromiseOrderResponse getPromiseOrder( @RequestBody GenericRequest genericRequest ) {
          
          
    	 PromiseOrderResponse promiseOrderResponse = new PromiseOrderResponse();
   
         try {
             System.out.println("Initiate Request");
             promiseOrderResponse = getResorcesDao.getPromiseOrder(genericRequest);
          } catch (Exception e) {
          }
       
         
         return promiseOrderResponse;
     }
     
     
     @RequestMapping(value = "/getExciseOrderByDistributerId", method = RequestMethod.POST, headers = "Accept=application/json")
     public ExciseDutyResponse getExciseOrderByDistributerId( @RequestBody GenericRequest genericRequest ) {
          
          
    	 ExciseDutyResponse exciseDutyResponse = new ExciseDutyResponse();
   
         try {
             System.out.println("Initiate Request");
             exciseDutyResponse = getResorcesDao.getExciseOrderByDistributerId(genericRequest);
          } catch (Exception e) {
          }
       
         
         return exciseDutyResponse;
     }
     
    
        @RequestMapping(value="/getStock",method=RequestMethod.POST,headers="Accept=application/json")
          public StockResponse getStock(@RequestBody GenericRequest genericRequest){
              StockResponse stockResponse=new StockResponse();
              try{
                  System.out.println("Inititate Request");
                  stockResponse=getResorcesDao.getStock(genericRequest);
                  
              }catch(Exception e){
                  
              }
              return stockResponse;
          }
        @RequestMapping(value="/getAttendence",method=RequestMethod.POST,headers="Accept=application/json")
        public AttendenceResponse getAttendence(@RequestBody GenericRequest genericRequest){
           AttendenceResponse attendenceResponse = new AttendenceResponse();
            try{
                System.out.println("Inititate Request");
                attendenceResponse=getResorcesDao.getAttendence(genericRequest);
                
            }catch(Exception e){
                
            }
            return attendenceResponse;
        }
        
        
        @RequestMapping(value="/getActivity",method=RequestMethod.POST,headers="Accept=application/json")
        public ActivityResponse getActivity(@RequestBody GenericRequest genericRequest){
        	ActivityResponse activityResponse = new ActivityResponse();
            try{
                System.out.println("Inititate Request");
                activityResponse=getResorcesDao.getActivity(genericRequest);
                
            }catch(Exception e){
                
            }
            return activityResponse;
        }




     @RequestMapping(value="/getSchedule",method=RequestMethod.POST,headers="Accept=application/json")
    public ScheduleResponse getSchedule(@RequestBody GenericRequest genericRequest){
    	 ScheduleResponse scheduleResponse=new ScheduleResponse();
        try{
            System.out.println("Initiate Request");
            scheduleResponse=getResorcesDao.getSchedule(genericRequest.getId());
        }catch(Exception e){
            
        }
        return scheduleResponse;
    }
     
     @RequestMapping(value="/getTarget",method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse getTarget(@RequestBody GenericRequest genericRequest){
    	 GenericResponse genericResponse=new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=getResorcesDao.getTarget(genericRequest);
         }catch(Exception e){
             
         }
         return genericResponse;
     }
     
     @RequestMapping(value="/getServey",method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse getServey(@RequestBody GenericRequest genericRequest){
    	 GenericResponse genericResponse=new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=getResorcesDao.getServey(genericRequest);
         }catch(Exception e){
             
         }
         return genericResponse;
     }
     
     
     
     @RequestMapping(value="/getQestionAndAnswerByServeyId",method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse getQestionAndAnswerByServeyId(@RequestBody GenericRequest genericRequest){
    	 GenericResponse genericResponse=new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=getResorcesDao.getQestionAndAnswerByServeyId(genericRequest);
         }catch(Exception e){
             
         }
         return genericResponse;
     }
     
     
     @RequestMapping(value="/getServeyReport",method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse getServeyReport(@RequestBody GenericRequest genericRequest){
    	 GenericResponse genericResponse=new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=getResorcesDao.getServeyReport(genericRequest);
         }catch(Exception e){
             
         }
         return genericResponse;
     } 
     
     @RequestMapping(value="/getShedule",method=RequestMethod.POST,headers="Accept=application/json")
     public GenericResponse getShedule(@RequestBody GenericRequest genericRequest){
    	 GenericResponse genericResponse=new GenericResponse();
         try{
             System.out.println("Initiate Request");
             genericResponse=getResorcesDao.getShedule(genericRequest);
         }catch(Exception e){
             
         }
         return genericResponse;
     }
     
     @RequestMapping(value="/searchProduct",method=RequestMethod.POST,headers="Accept=application/json")
     public SearchProductResponse  searchProduct(@RequestBody GenericSearchRequest genericSearchRequest){
         SearchProductResponse searchProductResponse =new SearchProductResponse ();
         try{
             System.out.println("Initiate Request");
             searchProductResponse=getResorcesDao.searchProduct(genericSearchRequest.getBrandId(),
             genericSearchRequest.getCategoryId(),genericSearchRequest.getProductId(),genericSearchRequest.getTypeId(),genericSearchRequest.getSubTypeId(),genericSearchRequest.getLiscenseId());
         }catch(Exception e){
             
         }
         return searchProductResponse;
     }
     
     @RequestMapping(value = "/getRetailerSearchById", method = RequestMethod.POST, headers = "Accept=application/json")
    public RetailerRawDataResponse getRetailerSearchById( @RequestBody GenericRequest genericRequest ) {
         
         
          RetailerRawDataResponse retailerRawDataResponse = new RetailerRawDataResponse();
  
        try {
            System.out.println("Initiate Request");
             retailerRawDataResponse = getResorcesDao.getRetailerSearchById(genericRequest.getId(),genericRequest.getName());
         } catch (Exception e) {
         }
        return retailerRawDataResponse;
    }

}
