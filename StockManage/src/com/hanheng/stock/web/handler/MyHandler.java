package com.hanheng.stock.web.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jfinal.handler.Handler;

public class MyHandler extends Handler{

	@Override
	public void handle(String target, HttpServletRequest req,
			HttpServletResponse resp, boolean[] arg) {
		resp.setHeader("Access-Control-Allow-Origin", "*");
		this.next.handle(target, req, resp, arg);
	}
	
}
