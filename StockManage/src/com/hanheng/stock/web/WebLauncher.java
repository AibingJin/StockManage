package com.hanheng.stock.web;

import java.io.File;

import com.hanheng.mysql.MyTask;
import com.hanheng.stock.web.controller.DetailsController;
import com.hanheng.stock.web.controller.InStockController;
import com.hanheng.stock.web.controller.IndexController;
import com.hanheng.stock.web.controller.OutStockController;
import com.hanheng.stock.web.controller.PersonController;
import com.hanheng.stock.web.controller.ProductController;
import com.hanheng.stock.web.controller.ProjectController;
import com.hanheng.stock.web.controller.StockController;
import com.hanheng.stock.web.controller.TypeController;
import com.hanheng.stock.web.handler.MyHandler;
import com.hanheng.stock.web.interceptor.MyInterceptor;
import com.hanheng.stock.web.model.CartModel;
import com.hanheng.stock.web.model.DetailsModel;
import com.hanheng.stock.web.model.InStockModel;
import com.hanheng.stock.web.model.OutStockModel;
import com.hanheng.stock.web.model.PersonModel;
import com.hanheng.stock.web.model.ProductModel;
import com.hanheng.stock.web.model.ProjectModel;
import com.hanheng.stock.web.model.StockModel;
import com.hanheng.stock.web.model.TypeModel;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class WebLauncher extends JFinalConfig{
	public static void main(String[] args) {
		boolean cmdMode = args!=null && args.length>=1?Boolean.valueOf(args[0]):false;
		if(cmdMode){
			if(args.length>=2){
				JFinal.start(PathKit.getWebRootPath(), Integer.valueOf(args[1]), "/", Integer.MAX_VALUE);
			}else{
				JFinal.start(PathKit.getWebRootPath(), 80, "/", Integer.MAX_VALUE);
			}
		}else{
			JFinal.start(PathKit.getWebRootPath(), 8081, "/", Integer.MAX_VALUE);
		}
	}

	@Override
	public void configConstant(Constants me) {
		PropKit.use(new File(PathKit.getWebRootPath() + "/WEB-INF/config/web.txt"));
		me.setDevMode(PropKit.getBoolean("devMode"));
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/",IndexController.class);
		me.add("/person", PersonController.class);
		me.add("/product", ProductController.class);
		me.add("/project", ProjectController.class);
		me.add("/type", TypeController.class);
		me.add("/details", DetailsController.class);
		me.add("/inStock", InStockController.class);
		me.add("/stock", StockController.class);
		me.add("/outStock", OutStockController.class);
	}
	
	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"),PropKit.get("username"), PropKit.get("password").trim());
		me.add(dp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		me.add(arp);
		arp.setShowSql(false);
		arp.setDevMode(true);
		arp.addMapping("person", PersonModel.class);
		arp.addMapping("product", ProductModel.class);
		arp.addMapping("project", ProjectModel.class);
		arp.addMapping("type", TypeModel.class);
		arp.addMapping("details", DetailsModel.class);
		arp.addMapping("in_stock", InStockModel.class);
		arp.addMapping("stock_list", StockModel.class);
		arp.addMapping("cart", CartModel.class);
		arp.addMapping("out_stock", OutStockModel.class);
		
		Cron4jPlugin cp = new Cron4jPlugin();
		cp.addTask("0 8 * * *", new MyTask());
		me.add(cp);
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new MyHandler());
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new MyInterceptor());
	}

	@Override
	public void afterJFinalStart() {
		new Thread(new MyTask()).start();
	}
	
	@Override
	public void beforeJFinalStop() {
		
	}

	@Override
	public void configEngine(Engine me) {
		
	}

}
