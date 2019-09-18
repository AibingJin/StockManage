package com.hanheng.stock.web.service;

import java.util.List;

import com.hanheng.stock.web.model.OutStockModel;
import com.hanheng.stock.web.model.StockModel;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class StockService {
	public static List<Record> queryById(int type_id, int details_id,
			String model) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sl.*,d.details_name,t.type_name,p.name from stock_list as sl ");
		sb.append("left join details as d on d.id = sl.details_id ");
		sb.append("left join type as t on t.id = sl.type_id ");
		sb.append("left join person as p on p.id = sl.person_id ");
		sb.append("where model like '%" + model + "%' and stock > 0 ");
		if (type_id != 0) {
			sb.append(" and type_id = " + type_id);
		}
		if (details_id != 0) {
			sb.append(" and details_id = " + details_id);
		}

		String sql = sb.toString();
		System.out.println(sql);
		return Db.find(sql);
	}

	public static List<Record> queryByName(String details_name, String model) {
		StringBuffer sb = new StringBuffer();
		sb.append("select sl.*,d.details_name,t.type_name,p.name from stock_list as sl ");
		sb.append("left join details as d on d.id = sl.details_id ");
		sb.append("left join type as t on t.id = sl.type_id ");
		sb.append("left join person as p on p.id = sl.person_id ");
		sb.append("where model like '%" + model + "%' and stock > 0 ");
		if (!"".equals("details_name")) {
			sb.append(" and details_name like '%" + details_name + "%'");
		}
		String sql = sb.toString();
		System.out.println(sql);
		return Db.find(sql);
	}

	public static List<Record> showCart() {
		StringBuffer sb = new StringBuffer();
		sb.append("select c.*,sl.*,d.details_name,t.type_name from cart as c ");
		sb.append("left join stock_list as sl on c.stock_id = sl.id ");
		sb.append("left join details as d on d.id = sl.details_id ");
		sb.append("left join type as t on t.id = sl.type_id ");
		String sql = sb.toString();
		System.out.println(sql);
		return Db.find(sql);
	}

	public static void outStock(int personId, int projectId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select c.*,sl.* from cart as c ");
		sb.append("left join stock_list as sl on c.stock_id = sl.id ");
		String sql = sb.toString();
		List<Record> records = Db.find(sql);
		for (Record r : records) {
			r.remove("id").remove("stock_id").remove("stock").remove("in_time")
					.remove("position").remove("update_time")
					.set("person_id", personId).set("project_id", projectId);
			new OutStockModel().put(r).save();
			String sql1 = "select * from stock_list where id = "+r.getInt("stock_id");
			StockModel sm = StockModel.dao.findFirst(sql1);
			sm.set("stock", sm.getInt("stock")-r.getInt("count")).update();
		}
		Db.delete("truncate table cart");
	}
}
