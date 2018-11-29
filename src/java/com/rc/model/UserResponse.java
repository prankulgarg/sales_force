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
public class UserResponse {
    private String token;
    private int employee_id;
    private String employee_name;
    private String response;

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "UserResponse{" + "token=" + token + ", employee_id=" + employee_id + ", employee_name=" + employee_name + ", response=" + response + '}';
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

   


  
    
    
    

}
