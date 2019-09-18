package com.hanheng.stock.web.service;

import com.hanheng.stock.web.model.PersonModel;

public class PersonService {
	public static boolean exist(String name){
		return PersonModel.dao.find("select * from person where name = '"+name+"'").size()>0;
	}
}
