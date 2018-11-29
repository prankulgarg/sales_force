package com.rc.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.rc.dao.DeleteResourceDao;
import com.rc.model.Brand;
import com.rc.model.GenericResponse;
import com.rc.model.Product;
import com.rc.model.ProductRawdata;

public class DeleteResourceDaoimpl implements DeleteResourceDao {
	private final JdbcTemplate jdbcTemplate;

	public DeleteResourceDaoimpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);// To change body of
		// generated methods, choose
		// Tools | Templates.
	}


	@Override
	public GenericResponse deleteTeamMember(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
		   String checkMemberSql = "select a.employee_name,b.retailer_shop_name from  tbl_member as a , tbl_retailer as b   where a.manager_id="+id+" or a.asm_id ="+id+" or a.tsm_id="+id+" or b.manager_id="+id+" or b.asm_id ="+id+" or b.tsm_id="+id+"  or b.sr_id="+id+"  ";
		   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());
		   if(!check.equalsIgnoreCase("exist")){
		   
			String updateTableSQL = "UPDATE tbl_member SET isDelete='Y' where employee_id ="+id+"";
			
			int update = jdbcTemplate.update(updateTableSQL);
		 
			  if (update>0)
			   { 
			        genericResponse.setMessage("Delete successfully");
			        genericResponse.setStatus("success");
			        genericResponse.setStatusCode(0);
			   }
			    else
			   {
			        genericResponse.setMessage("Couldn't Delete ");
			           genericResponse.setStatus("fail");
			            genericResponse.setStatusCode(1);
			 
			   }
		   
		   }
	
		   else {
			   genericResponse.setMessage("Member is Assigned");
			   genericResponse.setStatusCode(-2);
			   genericResponse.setStatus("failure");
			   
		   }
	   }
		 
	  
	       
	

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteRole(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
		   String checkMemberSql = "select employee_name from  tbl_member where role_id="+id+"";
		   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

		   if(!check.equalsIgnoreCase("exist")){
		   String updateTableSQL = "UPDATE tbl_role SET isDelete='Y' where id ="+id+"";
	     	int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
		   }
		   
		   else {
			   genericResponse.setMessage("Role is Assigned");
			   genericResponse.setStatusCode(-2);
			   genericResponse.setStatus("failure");
			   
		   }
	       
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteDesignation(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
		   String checkMemberSql = "select employee_name from  tbl_member where designation_id="+id+"";
		   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

		   if(!check.equalsIgnoreCase("exist")){
			String updateTableSQL = "UPDATE tbl_designation SET isDelete='Y' where designation_id ="+id+"";
	    
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
 }
		   
		   else {
			   genericResponse.setMessage("Designation is Assigned");
			   genericResponse.setStatusCode(-2);
			   genericResponse.setStatus("failure");
			   
		   }
	       
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteRetailer(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_retailer SET isDeleted='Y' where retailer_id ="+id+"";
	    
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
	       
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteRetailerType(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_retailer_type SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select retailer_shop_name from  tbl_retailer where retailer_type_id="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteRetailerSubType(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_retailer_sub_type SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select retailer_shop_name from  tbl_retailer where retailer_sub_type_id="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteRetailerCategory(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_retailer_catagory SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select retailer_shop_name from  tbl_retailer where retailer_catagory_id="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteZone(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_zone SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select retailer_shop_name from  tbl_retailer where zoneid="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteProduct(Product product) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_product SET isDelete='Y' where id ="+product.getProductId()+"";
			

			  
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   
				
			   
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteBrand(Brand brand) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_product SET isDelete='Y' where brand_id ="+brand.getBrandId()+"";
		   int update = jdbcTemplate.update(updateTableSQL);
		   jdbcTemplate.update("UPDATE tbl_brand SET isDelete ='Y' where brand_id="+brand.getBrandId()+"");
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   
				
			   
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deletePackagetype(ProductRawdata packageType) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_pacakage_type SET isDelete='Y' where id ="+packageType.getId()+"";
			 String checkMemberSql = "select product_name from  tbl_product where pacakage_type_id="+packageType.getId()+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteProductCategory(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_product_catagory SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select product_name from  tbl_product where PRODUCT_catagory_id="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteProductSubType(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_product_sub_type SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select product_name from  tbl_product where product_sub_tyoe_id="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteProductSegment(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_product_segment SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select product_name from  tbl_product where PRODUCT_segment_code="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}


	@Override
	public GenericResponse deleteProductType(int id) {
	     
			GenericResponse genericResponse = new GenericResponse();
	   try{
			String updateTableSQL = "UPDATE tbl_product_type SET isDelete='Y' where id ="+id+"";
			 String checkMemberSql = "select product_name from  tbl_product where product_type_id="+id+"";
			   String check = jdbcTemplate.query(checkMemberSql,new CheckDuplicateResultExtracter());

			   if(!check.equalsIgnoreCase("exist")){
	   int update = jdbcTemplate.update(updateTableSQL);
	   if (update>0)
	   { 
	        genericResponse.setMessage("Delete successfully");
	        genericResponse.setStatus("success");
	        genericResponse.setStatusCode(0);
	   }
	   
	    else
	   {
	        genericResponse.setMessage("Couldn't Delete ");
	           genericResponse.setStatus("fail");
	            genericResponse.setStatusCode(1);
	 
	   }
			   }
				
			   else {
				   genericResponse.setMessage("Assigned");
				   genericResponse.setStatusCode(-2);
				   genericResponse.setStatus("failure");
				   
			   }    
	}

	catch(Exception e)
	{
	  genericResponse.setMessage("Couldn't Delete application server error");
	     genericResponse.setStatus("fail");
	      genericResponse.setStatusCode(-1);
	   e.printStackTrace();


	}    
	return  genericResponse;
}

}
