/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.model;

/**
 *
 * @author Rajnish singh
 */
public class SearchProductData {
    
    
        private int productId;
        
	private String internalBrandCode;
	private String productName;     
	private String brandlicense;
	private int brandId;
	private String brandName;
	private String brandCode;											
	private int productCategoryId;
	private String productcategoryName;
	private int productSubtypeid;
	private String productSubTypeName;
	private String qtyInmlName;
	private int qtyInpcsValue;
	private int qtyMl;
	private int boxSize;
	private int productStatus;
	private String createDate;
	private String updateDate;
	private int createById;
	private int updateById;
	private int distributerId;
	private int packagetypeId;
	private String packagetype;
	private String productCode;
	private Double cif;
	private Double wsp;
	private Double mrp;
	private int productSegmentCode;
	private String productSegmentName;
	private int productTypeId;
	private String productTypeName;
	private int liscenseId;
	private String liscenseName;
        private double exciseDuty;
        private int qtyId;
        
        
	
	public int getLiscenseId() {
		return liscenseId;
	}
	public void setLiscenseId(int liscenseId) {
		this.liscenseId = liscenseId;
	}
	public int getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}
	
	
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public int getProductSegmentCode() {
		return productSegmentCode;
	}
	public void setProductSegmentCode(int productSegmentCode) {
		this.productSegmentCode = productSegmentCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Double getCif() {
		return cif;
	}
	public void setCif(Double cif) {
		this.cif = cif;
	}
	public Double getWsp() {
		return wsp;
	}
	public void setWsp(Double wsp) {
		this.wsp = wsp;
	}
	public Double getMrp() {
		return mrp;
	}
	public void setMrp(Double mrp) {
		this.mrp = mrp;
	}
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrandlicense() {
		return brandlicense;
	}
	public void setBrandlicense(String brandlicense) {
		this.brandlicense = brandlicense;
	}
	
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int branId) {
		this.brandId = branId;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public int getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public String getProductcategoryName() {
		return productcategoryName;
	}
	public void setProductcategoryName(String productcategoryName) {
		this.productcategoryName = productcategoryName;
	}
	
	public int getQtyMl() {
		return qtyMl;
	}
	public void setQtyMl(int qtyMl) {
		this.qtyMl = qtyMl;
	}

	public int getBoxSize() {
		return boxSize;
	}
	public void setBoxSize(int boxSize) {
		this.boxSize = boxSize;
	}
	
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public int getCreateById() {
		return createById;
	}
	public void setCreateById(int createById) {
		this.createById = createById;
	}
	public int getUpdateById() {
		return updateById;
	}
	public void setUpdateById(int updateById) {
		this.updateById = updateById;
	}
	public int getDistributerId() {
		return distributerId;
	}
	public void setDistributerId(int distributerId) {
		this.distributerId = distributerId;
	}
	public int getPackagetypeId() {
		return packagetypeId;
	}
	public void setPackagetypeId(int packagetypeId) {
		this.packagetypeId = packagetypeId;
	}
	public String getPackagetype() {
		return packagetype;
	}
	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}
	public String getProductSegmentName() {
		return productSegmentName;
	}
	public void setProductSegmentName(String productSegmentName) {
		this.productSegmentName = productSegmentName;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String string) {
		this.productTypeName = string;
	}
	public String getLiscenseName() {
		return liscenseName;
	}
	public void setLiscenseName(String liscenseName) {
		this.liscenseName = liscenseName;
	}
	
	
	
	
	public int getProductSubtypeid() {
		return productSubtypeid;
	}
	public void setProductSubtypeid(int productSubtypeid) {
		this.productSubtypeid = productSubtypeid;
	}
	public String getProductSubTypeName() {
		return productSubTypeName;
	}
	public void setProductSubTypeName(String productSubTypeName) {
		this.productSubTypeName = productSubTypeName;
	}
	public String getQtyInmlName() {
		return qtyInmlName;
	}
	public void setQtyInmlName(String qtyInmlName) {
		this.qtyInmlName = qtyInmlName;
	}
	public int getQtyInpcsValue() {
		return qtyInpcsValue;
	}
	public void setQtyInpcsValue(int qtyInpcsValue) {
		this.qtyInpcsValue = qtyInpcsValue;
	}
	public String getInternalBrandCode() {
		return internalBrandCode;
	}
	public void setInternalBrandCode(String internalBrandCode) {
		this.internalBrandCode = internalBrandCode;
	}

    public double getExciseDuty() {
        return exciseDuty;
    }

    public void setExciseDuty(double exciseDuty) {
        this.exciseDuty = exciseDuty;
    }

    public int getQtyId() {
        return qtyId;
    }

    public void setQtyId(int qtyId) {
        this.qtyId = qtyId;
    }

   
	
	
	
	
	
	

   

}