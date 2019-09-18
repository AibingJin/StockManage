package com.hanheng.stock.web.controller;

import com.hanheng.stock.web.service.InStockService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;

public class InStockController extends Controller {
	public void getDetailsByTypeId() {
		int type_id = getPara("type_id")!=null?getParaToInt("type_id"):0;
		this.setAttr("details", InStockService.getDetailsByTypeId(type_id));
		this.setAttr("success", true);
		this.renderJson();
	}

	public void getModelByDetailsId() {
		int details_id = getParaToInt("details_id");
		this.setAttr("details", InStockService.getModelByDetailsId(details_id));
		this.setAttr("success", true);
		this.renderJson();
	}
	
	public void getTypeDetailsInfo() {
		this.setAttr("types", InStockService.getTypeDetailsInfo());
		this.setAttr("success", true);
		this.renderJson();
	}

	@Before(Tx.class)
	public void add() {	
		InStockService.inStock(this.getParaMap());
		this.setAttr("success", true);
		this.renderJson();
	}

	@Before(Tx.class)
	public void back() {
		int id = getParaToInt("id");
		InStockService.inStockBack(id);
		this.setAttr("success", true);
		this.renderJson();
	}

	public void query() {
		int typeId = getPara("type_id")!=null?getParaToInt("type_id"):0;
		int detailsId = getPara("details_id")!=null?getParaToInt("details_id"):0;
		String model = getPara("model")!=null?getPara("model"):"";
		int person_id = getPara("person_id")!=null?getParaToInt("person_id"):0;
		String startTime = getPara("startTime")!=null?getPara("startTime"):"";
		String stopTime = getPara("stopTime")!=null?getPara("stopTime"):"";
		this.setAttr("results", InStockService.query(typeId,detailsId,model,person_id,startTime,stopTime));
		this.setAttr("success", true);
		this.renderJson();
	}
}
