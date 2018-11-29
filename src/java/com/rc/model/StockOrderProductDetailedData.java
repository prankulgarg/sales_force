
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
public class StockOrderProductDetailedData {
   private int id;
   private int orderId;
   private int productId;
   private String productName;
   private int brandId;
   private String brandName;
   private int qty;



   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public int getOrderId() {
       return orderId;
   }

   public void setOrderId(int orderId) {
       this.orderId = orderId;
   }

   public String getProductName() {
       return productName;
   }

   public int getProductId() {
       return productId;
   }

   public void setProductId(int productId) {
       this.productId = productId;
   }
   
   

   public void setProductName(String productName) {
       this.productName = productName;
   }

   public int getBrandId() {
       return brandId;
   }

   public void setBrandId(int brandId) {
       this.brandId = brandId;
   }

   public String getBrandName() {
       return brandName;
   }

   public void setBrandName(String brandName) {
       this.brandName = brandName;
   }

   public int getQty() {
       return qty;
   }

   public void setQty(int qty) {
       this.qty = qty;
   }
   
   
}