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
public class CommonDetailResponse extends GenericResponse {
    private List <CommonDetail> lstCommnodetail = new ArrayList<CommonDetail>(); 

    public List<CommonDetail> getLstCommnodetail() {
        return lstCommnodetail;
    }

    public void setLstCommnodetail(List<CommonDetail> lstCommnodetail) {
        this.lstCommnodetail = lstCommnodetail;
    }

}
