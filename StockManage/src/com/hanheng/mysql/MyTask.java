package com.hanheng.mysql;

import java.io.IOException;
import java.sql.SQLException;

import com.jfinal.kit.PropKit;

public class MyTask implements Runnable{
	public void run() {
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
	}
}
