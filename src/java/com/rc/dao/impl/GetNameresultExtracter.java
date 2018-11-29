/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.rc.model.GetNameRawWrapper;

/**
 *
 * @author Admin
 */
public class GetNameresultExtracter implements ResultSetExtractor<GetNameRawWrapper>{

    @Override
    public GetNameRawWrapper extractData(ResultSet rs) throws SQLException, DataAccessException {
GetNameRawWrapper getNameRawWrapper= new GetNameRawWrapper();
while(rs.next()){
getNameRawWrapper.setCatname(rs.getString(1));
System.out.println("category Id in result set  ==>>> "+rs.getString(1));
getNameRawWrapper.setTypeName(rs.getString(2));
System.out.println("type Name Id in result set  ==>>> "+rs.getString(2));

getNameRawWrapper.setSubTypename(rs.getString(3));
System.out.println("sub  type name  in result set  ==>>> "+rs.getString(3));


/*getNameRawWrapper.setGroupName(rs.getString(4));
System.out.println("group name  in result set  ==>>> "+rs.getString(4));
*/
getNameRawWrapper.setDistributerName(rs.getString(4));
System.out.println("setDistributerName  in result set  ==>>> "+rs.getString(4));

/*getNameRawWrapper.setSrName(rs.getString(5));
System.out.println("setSrName  in result set  ==>>> "+rs.getString(5));*/


}

return getNameRawWrapper;
    
}

   
    }
    

