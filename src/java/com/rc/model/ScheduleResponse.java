
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
public class ScheduleResponse extends GenericResponse{
   List<CreateScheduleData> listCreateScheduleData=new ArrayList<>();

   public List<CreateScheduleData> getListCreateScheduleData() {
       return listCreateScheduleData;
   }

   public void setListCreateScheduleData(List<CreateScheduleData> listCreateScheduleData) {
       this.listCreateScheduleData = listCreateScheduleData;
   }
   
   
   
}