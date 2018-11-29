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
public class GetNameRawWrapper {
    private String catname;
    private String typeName;
    private String subTypename;
    private String groupName;
    private String distributerName;
    public String getDistributerName() {
		return distributerName;
	}

	public void setDistributerName(String distributerName) {
		this.distributerName = distributerName;
	}

	public String getSrName() {
		return srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}

	private String srName;

    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
    public String toString() {
        return "GetNameRawWrapper{" + "catname=" + catname + ", typeName=" + typeName + ", subTypename=" + subTypename + '}';
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSubTypename() {
        return subTypename;
    }

    public void setSubTypename(String subTypename) {
        this.subTypename = subTypename;
    }
    
}
