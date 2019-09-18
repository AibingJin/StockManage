package com.hanheng.stock.web.controller;

import java.util.List;

import com.hanheng.stock.web.model.ProductModel;
import com.hanheng.stock.web.service.PersonService;
import com.hanheng.stock.web.service.ProductService;
import com.jfinal.core.Controller;

public class ProductController extends Controller{
	public void add(){
		String product_name = getPara("product_name");
		if(ProductService.exist(product_name)){
			this.setAttr("result", false);
			this.setAttr("reason", product_name+"已存在！");
		}else{
			new ProductModel().set("product_name", product_name).save();
			this.setAttr("result", true);
			this.setAttr("reason", "ok");
		}
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void delete(){
		int id = getParaToInt("id");
		ProductModel.dao.deleteById(id);
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void edit(){
		int id = getParaToInt("id");
		String product_name = getPara("product_name");
		if(PersonService.exist(product_name)){
			this.setAttr("result", false);
			this.setAttr("reason", product_name+"已存在！");
		}else{
			ProductModel.dao.findById(id).set("product_name", product_name).update();
			this.setAttr("result", true);
			this.setAttr("reason", "ok");
		}
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void query(){
		String sql = "select * from product";
		List<ProductModel> persons = ProductModel.dao.find(sql);
		this.setAttr("products", persons);
		this.setAttr("success", true);
		this.renderJson();
	}
}
