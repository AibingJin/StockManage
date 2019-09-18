package com.hanheng.stock.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.hanheng.mysql.MySqlInfo;
import com.hanheng.mysql.MysqlExport;
import com.hanheng.stock.web.model.PersonModel;
import com.hanheng.stock.web.service.PersonService;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

public class PersonController extends Controller {
	public void add() {
		String name = getPara("name");
		if (PersonService.exist(name)) {
			this.setAttr("result", false);
			this.setAttr("reason", name + "已存在！");
		} else {
			new PersonModel().set("name", name).save();
			this.setAttr("result", true);
			this.setAttr("reason", "ok");
		}
		this.setAttr("success", true);
		this.renderJson();
	}

	public void delete() {
		int id = getParaToInt("id");
		PersonModel.dao.deleteById(id);
		this.setAttr("success", true);
		this.renderJson();
	}

	public void edit() {
		int id = getParaToInt("id");
		String name = getPara("name");
		if (PersonService.exist(name)) {
			this.setAttr("result", false);
			this.setAttr("reason", name + "已存在！");
		} else {
			PersonModel.dao.findById(id).set("name", name).update();
			this.setAttr("result", true);
			this.setAttr("reason", "ok");
		}
		this.setAttr("success", true);
		this.renderJson();
	}

	public void query() {
		String sql = "select * from person";
		List<PersonModel> persons = PersonModel.dao.find(sql);
		this.setAttr("persons", persons);
		this.setAttr("success", true);
		this.renderJson();
	}

	public void dbbak() {
		MySqlInfo mySqlInfo = new MySqlInfo(PropKit.get("jdbcUrl"),PropKit.get("username"), PropKit.get("password").trim());
		MysqlExport mysqlExport = new MysqlExport(mySqlInfo);
		try {
			mysqlExport.export();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

		}
		this.setAttr("success", true);
		this.renderJson();
	}
}
