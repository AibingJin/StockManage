package com.hanheng.stock.web.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class IndexController extends Controller{
	public void index(){
		render("index.jsp");
	}
	
	@ActionKey("/login")
	public void login(){
		redirect("/login.html");
	}
}
