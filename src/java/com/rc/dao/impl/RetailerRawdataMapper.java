/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import static org.apache.commons.io.FilenameUtils.concat;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.rc.model.RetailerRawData;

/**
 *
 * @author Admin
 */
public class RetailerRawdataMapper implements RowMapper<RetailerRawData>{

    @Override
    public RetailerRawData mapRow(ResultSet rs, int i) throws SQLException {
       RetailerRawData retailerRawData = new RetailerRawData();
     /*  retailerRawData.setRetailerId(rs.getInt("retailer_id"));
       retailerRawData.setRetailerName(rs.getString("retailer_shop_name"));
       retailerRawData.setContactPersonName(rs.getString("contact_person_name"));
       retailerRawData.setContactPersonName1(rs.getString("contact_person_name1"));
       retailerRawData.setContactPersonName2(rs.getString("contact_person_name2"));
       retailerRawData.setCreditDays(rs.getInt("credit_days"));
       retailerRawData.setEmail(rs.getString("email"));
       retailerRawData.setMobile(rs.getString("mobile"));
       retailerRawData.setAlternatemobile1(rs.getString("alt_mobile1"));
       retailerRawData.setAlternatemobile2(rs.getString("alt_mobile1"));
       retailerRawData.setLandline(rs.getString("landline"));
       retailerRawData.setDistributerId(rs.getInt("distributor_id"));
       retailerRawData.setSrId(rs.getInt("sr_id"));
       retailerRawData.setTsmId(rs.getInt("tsm_id"));
       retailerRawData.setAsmId(rs.getInt("asm_id"));
       retailerRawData.setTypeId(rs.getInt("retailer_type_id"));
       retailerRawData.setSubTypeId(rs.getInt("retailer_sub_type_id"));
       retailerRawData.setCatogoryId(rs.getInt("retailer_catagory_id"));
       retailerRawData.setGroupId(rs.getInt("group_id"));
       retailerRawData.setIsGroup(rs.getInt("isgroup"));
       retailerRawData.setImage(rs.getString("retailer_shop_image"));
       retailerRawData.setLattitude(rs.getString("latitude"));
       retailerRawData.setLongitude(rs.getString("longitude"));
       retailerRawData.setVerified(rs.getInt("verfied"));
       retailerRawData.setIsDefaulter(rs.getInt("defaulter"));
       retailerRawData.setState(rs.getString("status"));
       retailerRawData.setBlock(rs.getInt("blocked"));
       retailerRawData.setLocality(rs.getString("locality"));
       retailerRawData.setStreet(rs.getString("street"));
       retailerRawData.setCity(rs.getString("city"));
       retailerRawData.setState(rs.getString("state"));
       retailerRawData.setPincode(rs.getInt("pincode"));
       retailerRawData.setZoneId(rs.getInt("zoneid"));
       retailerRawData.setCreateDate(rs.getString("created_by_id"));
       retailerRawData.setExciseCode(rs.getString("retailer_excise_code"));
       retailerRawData.setManagerId(rs.getInt("manager_id"));
       retailerRawData.setRouteId(rs.getInt("route_id"));
       retailerRawData.setSrName(rs.getString("sr_name"));
       retailerRawData.setTsmName(rs.getString("tsm_name"));
       retailerRawData.setAsmName(rs.getString("asm_name"));
       retailerRawData.setTypeName(rs.getString("retailerTypeName"));
       retailerRawData.setSubTypeName(rs.getString("retailerSubTypeName"));
       retailerRawData.setState(rs.getString("stateName"));
       retailerRawData.setZoneName(rs.getString("managerName"));
       retailerRawData.setGroupName(rs.getString("groupName"));*/
       
       
       
     retailerRawData.setStreet(rs.getString("street"));
     retailerRawData.setLocality(rs.getString("locality"));
     retailerRawData.setAddress(concat(retailerRawData.getStreet(),retailerRawData.getLocality()));
     retailerRawData.setCatogoryId(rs.getInt("retailer_catagory_id"));
     retailerRawData.setImage(rs.getString("retailer_shop_image"));
     retailerRawData.setIsDefaulter(rs.getInt("defaulter"));
     retailerRawData.setIsGroup(rs.getInt("isgroup"));
     retailerRawData.setGroupId(rs.getInt("group_id"));
     //retailerRawData.setIsScheme(rs.getInt(""));
     retailerRawData.setLattitude(rs.getString("latitude"));
     retailerRawData.setLongitude(rs.getString("longitude"));
     retailerRawData.setRetailerId(rs.getInt("retailer_id"));
     retailerRawData.setRetailerName(rs.getString("retailer_shop_name"));
     retailerRawData.setSubTypeId(rs.getInt("retailer_sub_type_id"));
     retailerRawData.setTypeId(rs.getInt("retailer_type_id"));
     retailerRawData.setMobile(rs.getString("mobile"));
     retailerRawData.setAlternatemobile1(rs.getString("alt_mobile1"));
     retailerRawData.setAlternatemobile2(rs.getString("alt_mobile2"));
     retailerRawData.setContactPersonName(rs.getString("contact_person_name"));
     retailerRawData.setContactPersonName1(rs.getString("contact_person_name1"));
     retailerRawData.setContactPersonName2(rs.getString("contact_person_name2"));
     retailerRawData.setSrId(rs.getInt("sr_id"));
     retailerRawData.setStreet(rs.getString("street"));
     retailerRawData.setLocality(rs.getString("locality"));
     retailerRawData.setDistributerId(rs.getInt("distributor_id"));
     retailerRawData.setCreditDays(rs.getInt("credit_days")); 
     retailerRawData.setLandline(rs.getString("landline"));
     retailerRawData.setManagerId(rs.getInt("manager_id"));
     retailerRawData.setAsmId(rs.getInt("asm_id"));
     retailerRawData.setTsmId(rs.getInt("tsm_id"));
     retailerRawData.setVerified(rs.getInt("verfied"));
     retailerRawData.setStatus(rs.getInt("status"));
     retailerRawData.setZoneId(rs.getInt("zoneid"));
     retailerRawData.setExciseCode(rs.getString("retailer_excise_code"));
     retailerRawData.setState(rs.getString("state"));
     retailerRawData.setEmail(rs.getString("email"));
     retailerRawData.setPincode(rs.getInt("pincode"));
     retailerRawData.setCreateDate(rs.getString("created_date"));
     retailerRawData.setRouteId(rs.getInt("route_id"));
     retailerRawData.setBlock(rs.getInt("blocked"));
     retailerRawData.setCity(rs.getString("city"));
     retailerRawData.setNote(rs.getString("note"));
     
             
     return retailerRawData;
     
     
             
       


    }
    
}
