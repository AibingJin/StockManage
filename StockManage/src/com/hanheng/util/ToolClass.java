package com.hanheng.util;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class ToolClass {
	public static DecimalFormat df = new DecimalFormat(".00");
	public final static String HEX = "0123456789ABCDEF";
	public static boolean isLog = true;
	
	/**
	 * 正常打印内容（带时间戳）
	 * @param obj
	 */
	public static void log(Object... obj){
		if(isLog){
			String objs = getNowDateTime()+"---";
			for(Object o : obj){
				objs+=o+"  ";
			}
			System.out.println(objs);
		}
	}
	
	/**
	 * 以错误类型打印内容（带时间戳）
	 * @param obj
	 */
	public static void err(Object... obj){
		if(isLog){
			String objs = getNowDateTime()+"---";
			for(Object o : obj){
				objs+=o+"  ";
			}
			System.err.println(objs);
		}
	}
	
	/**
	 * 获取当前时间，格式为yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDateTime(){
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	public static String getTimeToStr(long time){
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateTime = sdf.format(new Date(time));
		return dateTime;
	}
	
	/**
	 * 获取当前时间，格式由type决定
	 * @param type 返回时间格式
	 * @return type="date"：yyyy-MM-dd   type="time"：HH:mm:ss  否则返回yyyy-MM-dd HH:mm:ss
	 */
	public static String getNowTime(String type){
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateTime = sdf.format(new Date());
		if("date".equals(type)){
			return dateTime.split(" ")[0];
		}else if("time".equals(type)){
			return dateTime.split(" ")[1];
		}else{
			return dateTime;
		}
	}
	
	/**
	 * 获取格式为yyyy-MM-dd HH:mm:ss的时间的毫秒值
	 * @param time
	 * @return 时间格式错误返回-1
	 */
	public static long getTime(String time){
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(time);
			return date.getTime();
		} catch (ParseException e) {
			err("时间格式错误！");
			return -1;
		}
	}
	
	/**
	 * 字节转化为16进制字符串
	 * @param bt
	 * @return
	 */
	public static String getHexString(byte bt){
		StringBuffer sb = new StringBuffer();
		int i = (bt & 0xFF);
		sb.append(HEX.charAt(i>>4));
		sb.append(HEX.charAt(i%16));
		return sb.toString();
	}
	
	/**
	 * 字节数组转化为16进制字符串
	 * @param bt
	 * @return
	 */
	public static String getHexString(byte[] bt, boolean lsb){
		StringBuffer sb = new StringBuffer();
		for(int i=0,len=bt.length;i<len;i++){
			int num = 0;
			if(lsb){
				num = (bt[len-i-1] & 0xFF);
			}else{
				num = (bt[i] & 0xFF);
			}
			sb.append(HEX.charAt(num>>4));
			sb.append(HEX.charAt(num%16));
		}
		return sb.toString();
	}
	
	public static byte[] hexToString(String str){
		int len = str.length()/2;
		byte[] bt = new byte[len];
		for(int i=0;i<len;i++){
			bt[i] = (byte) (HEX.indexOf(str.charAt(i*2))*16+HEX.indexOf(str.charAt(i*2+1)));
		}
		return bt;
	}
	
	/**
	 * 程序休眠l毫秒
	 * @param l
	 */
	public static void sleep(long l){
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 字节数组转整数
	 * @param bt
	 * @return
	 */
	public static int bytesToInt(byte... bt){
		return new BigInteger(1,bt).intValue();
	}
	
	/**
	 * 字节数组转小数
	 * @param bt
	 * @param lsb 
	 * @return
	 */
	public static float bytesToFloat(byte[] bt, boolean lsb){
		String hexStr = getHexString(bt, lsb);
		int i = 1;
		String first = String.valueOf(hexStr.charAt(0));
		if(HEX.indexOf(first)>7){
			hexStr = hexStr.replaceFirst(first, String.valueOf(HEX.charAt(HEX.indexOf(first)-8)));
			i = -1;
		}
		int intValue = Integer.parseInt(hexStr,16);
		float value = Float.intBitsToFloat(intValue);
		return value*i;
	}
	
	/**
	 * 小数转字节数组
	 * @param f
	 * @param lsb
	 * @return
	 */
	public static byte[] floatToBytes(float f, boolean lsb){
		int intNum = Float.floatToIntBits(f);
		String hexStr = Integer.toHexString(intNum).toUpperCase();
		int len = hexStr.length()/2;
		byte[] bt = new byte[len];
		for(int i=0;i<len;i++){
			if(lsb){
				bt[len-i-1] += HEX.indexOf(hexStr.charAt(i*2))<<4;
				bt[len-i-1] += HEX.indexOf(hexStr.charAt(i*2+1));
			}else{
				bt[i] += HEX.indexOf(hexStr.charAt(i*2))<<4;
				bt[i] += HEX.indexOf(hexStr.charAt(i*2+1));
			}
		}
		return bt;
	}
	
	public static String getString(Date date){
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	public static Date getNowTime(){
		String time = getNowDateTime();
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			date = sdf.parse(time);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	public static Date getPointTime(String dateTime){
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String getRandomString(int len){
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<len;i++){
			char c = rand.nextInt(2)==1?(char) (rand.nextInt(26)+65):(char) (rand.nextInt(8)+50);
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String str = "12121/2wqewqe21/212";
		System.out.println(Pattern.matches("^\\w+/\\w+/\\d+$", str));
	}
}
