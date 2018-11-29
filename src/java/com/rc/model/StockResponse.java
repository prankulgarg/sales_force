
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
public class StockResponse extends GenericResponse{

private List<StockDetailedData> lstStockResponse = new ArrayList();

 public List<StockDetailedData> getlstStockResponse() {
return lstStockResponse;
}

public void setlstStockResponse(List<StockDetailedData> lstStockResponse) {
this.lstStockResponse = lstStockResponse;
}



}