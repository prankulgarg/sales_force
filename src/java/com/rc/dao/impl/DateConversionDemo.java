package com.rc.dao.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConversionDemo {
	public static void main(String [] args ) throws ParseException{
		
		SimpleDateFormat formatter6=new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss"); 	
		 Date d1=(Date) formatter6.parse("2018-07-03 18:26:17");  
		 Date d2=(Date) formatter6.parse("2018-07-03 18:29:08");  
		 long diff = d2.getTime() - d1.getTime();
		 long diffHours = diff / (60 * 60 * 1000);
		System.out.println(diffHours);
	}

}
