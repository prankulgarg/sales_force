package com.rc.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PrankulDemo {
	public static void main(String args[]) {
		int id = 0;
		String name = null;
		String address = null;

		String sql = "select * from tbl_member";

		Map<String, String> map = new HashMap<>();
		map.put("manager_id", "62");
		map.put("asm_id", "64");
		map.put("tsm_id", "68");
		map.put("employee_id", "74");
		String whereClause = getWhereClause(map);
		sql = sql + whereClause + "and isDelete='N'";
		System.out.print(sql);

	}

	public static String getWhereClause(Map<String, String> map) {
		String whereClause = "";
		Set<String> keySet = map.keySet();

		int counter = 0;
		for (String key : keySet) {
			counter++;
			whereClause += key + "=" + map.get(key) + " ";
			if (map.size() != counter) {
				whereClause += "and ";
			}
		}

		if (whereClause == null)
			whereClause = "";
		else
			whereClause = " where " + whereClause;
		return whereClause;
	}

}
