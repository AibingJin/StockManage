package com.hanheng.stock.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class MyInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation in) {
		in.invoke();
	}
}
