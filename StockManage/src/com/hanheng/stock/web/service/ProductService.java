package com.hanheng.stock.web.service;

import com.hanheng.stock.web.model.ProductModel;

public class ProductService {
	public static boolean exist(String product_name){
		return ProductModel.dao.find("select * from product where product_name = '"+product_name+"'").size()>0;
	}
}
