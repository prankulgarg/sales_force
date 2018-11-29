/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.model;

import java.util.ArrayList;
import java.util.List;

import com.rc.dao.impl.GetAsset;

/**
 *
 * @author Admin
 */
public class AssetByRetailerResponse extends GenericResponse  {
    private List<AssetByRetailerData> listAssetByretailerData = new ArrayList();
	private List <GetAsset> listAsset = new ArrayList();
	public List<AssetByRetailerData> getListAssetByretailerData() {
		return listAssetByretailerData;
	}
	public void setListAssetByretailerData(List<AssetByRetailerData> listAssetByretailerData) {
		this.listAssetByretailerData = listAssetByretailerData;
	}
	public List<GetAsset> getListAsset() {
		return listAsset;
	}
	public void setListAsset(List<GetAsset> listAsset) {
		this.listAsset = listAsset;
	}
	
	
    
    
}
