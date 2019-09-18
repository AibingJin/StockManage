package com.hanheng.stock.web.service;

import java.util.List;
import java.util.Map;

import com.hanheng.stock.web.model.DetailsModel;
import com.hanheng.stock.web.model.InStockModel;
import com.hanheng.stock.web.model.StockModel;
import com.hanheng.util.ToolClass;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class InStockService {
	public static List<DetailsModel> getDetailsByTypeId(int type_id) {
		String sql;
		if(type_id!=0){
			sql = "select id,details_name from details where type_id = "
				+ type_id;
		}else{
			sql = "select id,details_name from details";
		}
		return DetailsModel.dao.find(sql);
	}

	public static String getModelByDetailsId(int details_id) {
		return DetailsModel.dao.findById(details_id).getStr("model_list");
	}
	
	public static List<Record> getTypeDetailsInfo(){
		List<Record> types = Db.find("select * from type");
		for(Record type : types){
			int type_id = type.getInt("id");
			String sql = "select id as details_id,details_name,model_list from details where type_id = "+type_id;
			type.set("children", DetailsModel.dao.find(sql));
		}
		return types;
	}

	public static void inStock(Map<String, String[]> params) {
		int type_id = Integer.parseInt(params.get("type_id")[0]);
		int details_id = Integer.parseInt(params.get("details_id")[0]);
		String model = params.get("model")[0];
		float price = Float.valueOf(params.get("price")[0]);
		int count = Integer.parseInt(params.get("count")[0]);
		String unit = params.get("unit")[0];
		String position = params.get("position")[0];
		int person_id = Integer.parseInt(params.get("person_id")[0]);
		String sql = "select * from stock_list where details_id = "
				+ details_id + " and model = '" + model + "' and price = "
				+ price;
		if (Db.find(sql).size() > 0) {
			StockModel sm = StockModel.dao.findFirst(sql);
			sm.set("stock", sm.getInt("stock") + count);
			sm.update();
		} else {
			new StockModel().set("details_id", details_id).set("model", model)
					.set("price", price).set("stock", count).set("unit", unit)
					.set("type_id", type_id).set("position", position).save();
		}
		new InStockModel().set("type_id", type_id)
				.set("details_id", details_id).set("model", model)
				.set("price", price).set("count", count).set("unit", unit)
				.set("position", position).set("person_id", person_id).save();
	}
	
	public static void inStockBack(int inStockId){
		InStockModel ism = InStockModel.dao.findById(inStockId);
		int details_id = ism.getInt("details_id");
		String model = ism.getStr("model");
		float price = ism.getFloat("price");
		int count = ism.getInt("count");
		String sql = "select * from stock_list where details_id = "
				+ details_id + " and model = '" + model + "' and price = "
				+ price;
		StockModel sm = StockModel.dao.findFirst(sql);
		sm.set("stock", sm.getInt("stock")-count).update();
		ism.delete();
	}

	public static List<Record> query(int type_id, int details_id, String model, int person_id, String startTime, String stopTime) {
		StringBuffer sb = new StringBuffer();
		sb.append("select is.*,d.details_name,t.type_name,p.name from in_stock as is ");
		sb.append("left join details as d on d.id = is.details_id ");
		sb.append("left join type as t on t.id = is.type_id ");
		sb.append("left join person as p on p.id = is.person_id ");
		sb.append("where model like '%"+model+"%'");
		if(type_id!=0){
			sb.append(" and type_id = "+type_id);
		}
		if(details_id!=0){
			sb.append(" and details_id = "+details_id);
		}
		if(person_id!=0){
			sb.append(" and person_id = "+person_id);
		}
		if(!"".equals(startTime)){
			if("".equals(stopTime)){
				stopTime = ToolClass.getNowTime("date");
			}
			sb.append(" and in_time between '"+startTime+"' and '"+stopTime+"'");
		}
		String sql = sb.toString();
		System.out.println(sql);
		return Db.find(sql);
	}
}
