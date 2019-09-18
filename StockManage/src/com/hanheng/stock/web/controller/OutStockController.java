package com.hanheng.stock.web.controller;

import com.hanheng.stock.web.service.OutStockService;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.tx.Tx;

public class OutStockController extends Controller{
	public void queryByProject(){
		int projectId = getParaToInt("project_id");
		this.setAttr("result", OutStockService.queryByProject(projectId));
		this.setAttr("success", true);
		this.renderJson();
	}
	
	@Before(Tx.class)
	public void back(){
		int id = getParaToInt("id");
		OutStockService.backToStock(id);
		this.setAttr("success", true);
		this.renderJson();
	}
}
