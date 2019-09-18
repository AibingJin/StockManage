package com.hanheng.stock.web.service;

import java.util.List;

import com.hanheng.stock.web.model.OutStockModel;
import com.hanheng.stock.web.model.StockModel;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class OutStockService {
	public static List<Record> queryByProject(int projectId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select os.*,d.details_name,t.type_name,ps.name,pj.project_name from out_stock as os ");
		sb.append("left join person as ps on ps.id = os.person_id ");
		sb.append("left join details as d on d.id = sl.details_id ");
		sb.append("left join type as t on t.id = sl.type_id ");
		sb.append("left join project as pj on pj.id = os.project_id ");
		sb.append("where project_id = "+projectId);
		String sql = sb.toString();
		System.out.println(sql);
		return Db.find(sql);
	}

	public static void backToStock(int id) {
		OutStockModel osm = OutStockModel.dao.findById(id);
		int details_id = osm.getInt("details_id");
		String model = osm.getStr("model");
		float price = osm.getFloat("price");
		int count = osm.getInt("count");
		String sql = "select * from stock_list where details_id = "+details_id+" and model = '"+model+"' and price = "+price;
		StockModel sm = StockModel.dao.findFirst(sql);
		sm.set("stock", sm.getInt("stock")+count).update();
		osm.delete();
	}
}
