package com.hanheng.stock.web.controller;

import java.util.List;

import com.hanheng.stock.web.model.ProjectModel;
import com.jfinal.core.Controller;

public class ProjectController extends Controller {
	public void add() {
		System.out.println(getParaMap());
		String project_name = getPara("project_name");
		String agent_name = getPara("agent_name");
		String demand_name = getPara("demand_name");
		new ProjectModel().set("project_name", project_name)
				.set("agent_name", agent_name).set("demand_name", demand_name)
				.save();
		this.setAttr("success", true);
		this.renderJson();
	}

	public void delete() {
		int id = getParaToInt("id");
		ProjectModel.dao.deleteById(id);
		this.setAttr("success", true);
		this.renderJson();
	}

	public void edit() {
		int id = getParaToInt("id");
		ProjectModel pm = ProjectModel.dao.findById(id);
		String project_name = getPara("project_name")!=null?getPara("project_name"):pm.getStr("project_name");
		String agent_name = getPara("agent_name")!=null?getPara("agent_name"):pm.getStr("agent_name");;
		String demand_name = getPara("demand_name")!=null?getPara("demand_name"):pm.getStr("demand_name");;
		ProjectModel.dao.findById(id).set("project_name", project_name)
				.set("agent_name", agent_name).set("demand_name", demand_name)
				.update();
		this.setAttr("success", true);
		this.renderJson();
	}

	public void query() {
		String sql = "select * from project";
		List<ProjectModel> projects = ProjectModel.dao.find(sql);
		this.setAttr("projects", projects);
		this.setAttr("success", true);
		this.renderJson();
	}
}
