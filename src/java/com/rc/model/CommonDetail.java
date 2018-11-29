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
public class CommonDetail extends GenericResponse {
    private int id;
    private String name;
    private String description;
	private String detailStatus;
	private int distributerId;
 

    public int getDistributerId() {
		return distributerId;
	}

	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}

	public String getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommonDetail{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
    
}
