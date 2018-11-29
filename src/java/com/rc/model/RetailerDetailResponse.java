/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class RetailerDetailResponse extends GenericResponse {
    private List<RetailerDetailedData> lstRetailerDetailedData = new ArrayList<RetailerDetailedData>();

    public List<RetailerDetailedData> getLstRetailerDetailedData() {
        return lstRetailerDetailedData;
    }

    public void setLstRetailerDetailedData(List<RetailerDetailedData> lstRetailerDetailedData) {
        this.lstRetailerDetailedData = lstRetailerDetailedData;
    }
    
}
