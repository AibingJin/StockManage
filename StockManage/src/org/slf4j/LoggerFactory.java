package org.slf4j;

public class LoggerFactory {
	public static Logger getLogger(String s){
		return new SelfLogger();
	}
}
