package com.hanheng.stock.web.controller;

import com.hanheng.stock.web.model.CartModel;
import com.hanheng.stock.web.model.StockModel;
import com.hanheng.stock.web.service.StockService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;

public class StockController extends Controller{
	public void queryById(){
		int typeId = getPara("type_id")!=null?getParaToInt("type_id"):0;
		int detailsId = getPara("details_id")!=null?getParaToInt("details_id"):0;
		String model = getPara("model")!=null?getPara("model"):"";
		this.setAttr("results", StockService.queryById(typeId,detailsId,model));
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void queryByName(){
		String details_name = getPara("details_name")!=null?getPara("details_name"):"";
		String model = getPara("model")!=null?getPara("model"):"";
		this.setAttr("results", StockService.queryByName(details_name,model));
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void addToCart(){
		int id = getParaToInt("id");
		int count = getParaToInt("count");
		StockModel sm = StockModel.dao.findById(id);
		if(sm.getInt("stock")>=count){
			new CartModel().set("stock_id", id).set("count", count).save();
			this.setAttr("result", true);
			this.setAttr("reason", "OK");
		}else{
			this.setAttr("result", false);
			this.setAttr("reason", "库存不足！");
		}
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void clearCart(){
		Db.delete("truncate table cart");
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void showCart(){
		this.setAttr("results", StockService.showCart());
		this.setAttr("success", true);
		this.renderJson();
	}
	
	@Before(Tx.class)
	public void out(){
		int personId = getParaToInt("person_id");
		int projectId = getParaToInt("project_id");
		StockService.outStock(personId, projectId);
		this.setAttr("success", true);
		this.renderJson();
	}
}
