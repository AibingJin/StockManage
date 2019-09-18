package com.hanheng.stock.web.controller;

import java.util.List;
import java.util.Set;

import com.hanheng.stock.web.model.DetailsModel;
import com.jfinal.core.Controller;

public class DetailsController extends Controller {
	public void add() {
		String details_name = getPara("details_name");
		int type_id = getParaToInt("type_id");
		int product_id = getParaToInt("product_id");
		Set<String> keySet = this.getParaMap().keySet();
		String model_list = "";
		for (String key : keySet) {
			if (key.contains("model_arg")) {
				if ("".equals(model_list)) {
					model_list += getPara(key);
				} else {
					model_list += "," + getPara(key);
				}
			}
		}
		new DetailsModel().set("details_name", details_name)
				.set("type_id", type_id).set("product_id", product_id)
				.set("model_list", model_list).save();
		this.setAttr("success", true);
		this.renderJson();
	}

	public void delete() {
		int id = getParaToInt("id");
		DetailsModel.dao.deleteById(id);
		this.setAttr("success", true);
		this.renderJson();
	}

	public void edit() {
		int id = getParaToInt("id");
		DetailsModel dm = DetailsModel.dao.findById(id);
		String details_name = getPara("details_name")!=null?getPara("details_name"):dm.getStr("details_name");
		int type_id = getPara("type_id")!=null?getParaToInt("type_id"):dm.getInt("type_id");
		int product_id = getPara("product_id")!=null?getParaToInt("product_id"):dm.getInt("product_id");
		String model_list = getPara("model_list")!=null?getPara("model_list"):dm.getStr("model_list");
		DetailsModel.dao.findById(id).set("details_name", details_name)
				.set("type_id", type_id).set("product_id", product_id)
				.set("model_list", model_list).update();
		this.setAttr("success", true);
		this.renderJson();
	}

	public void query() {
		String sql = "select * from details";
		List<DetailsModel> details = DetailsModel.dao.find(sql);
		this.setAttr("details", details);
		this.setAttr("success", true);
		this.renderJson();
	}
}
