package com.hanheng.stock.web.controller;

import java.util.List;

import com.hanheng.stock.web.model.TypeModel;
import com.hanheng.stock.web.service.TypeService;
import com.jfinal.core.Controller;

public class TypeController extends Controller{
	public void add(){
		String type_name = getPara("type_name");
		if(TypeService.exist(type_name)){
			this.setAttr("result", false);
			this.setAttr("reason", type_name+"已存在！");
		}else{
			new TypeModel().set("type_name", type_name).save();
			this.setAttr("result", true);
			this.setAttr("reason", "ok");
		}
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void delete(){
		int id = getParaToInt("id");
		TypeModel.dao.deleteById(id);
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void edit(){
		int id = getParaToInt("id");
		String type_name = getPara("type_name");
		if(TypeService.exist(type_name)){
			this.setAttr("result", false);
			this.setAttr("reason", type_name+"已存在！");
		}else{
			TypeModel.dao.findById(id).set("type_name", type_name).update();
			this.setAttr("result", true);
			this.setAttr("reason", "ok");
		}
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void query(){
		String sql = "select * from type";
		List<TypeModel> types = TypeModel.dao.find(sql);
		this.setAttr("types", types);
		this.setAttr("success", true);
		this.renderJson();
	}
}
