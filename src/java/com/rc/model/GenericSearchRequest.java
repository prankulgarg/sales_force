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
public class GenericSearchRequest {
  List<Integer> brandId=new ArrayList<>();
  List<Integer> categoryId=new ArrayList<>();
  List<Integer> typeId=new ArrayList<>();
  List<Integer> subTypeId=new ArrayList<>();
  List<Integer> liscenseId=new ArrayList<>();
  List<Integer> productId=new ArrayList<>();
  
   
  
    public List<Integer> getProductId() {
	return productId;
}

public void setProductId(List<Integer> productId) {
	this.productId = productId;
}

	public List<Integer> getBrandId() {
        return brandId;
    }

    public void setBrandId(List<Integer> brandId) {
        this.brandId = brandId;
    }

    public List<Integer> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Integer> categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getTypeId() {
        return typeId;
    }

    public void setTypeId(List<Integer> typeId) {
        this.typeId = typeId;
    }

    public List<Integer> getSubTypeId() {
        return subTypeId;
    }

    public void setSubTypeId(List<Integer> subTypeId) {
        this.subTypeId = subTypeId;
    }

    public List<Integer> getLiscenseId() {
        return liscenseId;
    }

    public void setLiscenseId(List<Integer> liscenseId) {
        this.liscenseId = liscenseId;
    }

    
}