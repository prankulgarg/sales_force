/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.model;

/**
 *
 * @author Admin
 */
public class UserDetail {
    private int employeeId;
    private String employeeName;
    private String emailId;
    private String mobileNumber;
    private String joininDate;
    private String roleName;
    private int designationId;

    public int getDesignationId() {
        return designationId;
    }

    public void setDesignationId(int designationId) {
        this.designationId = designationId;
    }
    private String designation;
    private String locality;
    private String street;
    private String city;
    private String stateName;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getJoininDate() {
        return joininDate;
    }

    public void setJoininDate(String joininDate) {
        this.joininDate = joininDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getResponseMsg() {
        return ResponseMsg;
    }

    public void setResponseMsg(String ResponseMsg) {
        this.ResponseMsg = ResponseMsg;
    }
    private String ResponseMsg;

    @Override
    public String toString() {
        return "UserDetail{" + "employeeId=" + employeeId + ", employeeName=" + employeeName + ", emailId=" + emailId + ", mobileNumber=" + mobileNumber + ", joininDate=" + joininDate + ", roleName=" + roleName + ", designation=" + designation + ", locality=" + locality + ", street=" + street + ", city=" + city + ", stateName=" + stateName + ", ResponseMsg=" + ResponseMsg + '}';
    }
    
    
    
    
}
