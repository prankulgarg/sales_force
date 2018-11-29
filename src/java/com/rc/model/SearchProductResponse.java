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
 * @author Rajnish singh
 */
public class SearchProductResponse extends GenericResponse {
       List<SearchProductData> listSearchProductData=new ArrayList<>();

    public List<SearchProductData> getListSearchProductData() {
        return listSearchProductData;
    }

    public void setListSearchProductData(List<SearchProductData> listSearchProductData) {
        this.listSearchProductData = listSearchProductData;
    }
}
      
       
       
