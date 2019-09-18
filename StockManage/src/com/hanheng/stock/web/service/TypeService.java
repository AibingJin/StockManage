package com.hanheng.stock.web.service;

import com.hanheng.stock.web.model.TypeModel;

public class TypeService {
	public static boolean exist(String type_name){
		return TypeModel.dao.find("select * from type where type_name = '"+type_name+"'").size()>0;
	}
}
