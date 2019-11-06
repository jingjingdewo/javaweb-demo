package com.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间模块
 * @author 敬畏人心 2019/11/5 
 *
 */
public class TimeUtils {
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowTime() {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		return nowTime;
	}
	
	/**
	 * 根据String型时间，获取long型时间，单位毫秒
	 * @param inVal 时间字符串
	 * @return long型时间
	 */
	public static long fromDateStringToLong(String inVal) {
	    Date date = null;
	    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
	    try {
	        date = inputFormat.parse(inVal);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return date.getTime();
	}
	
	public static long getThisTime() {
		long thisTime = fromDateStringToLong(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS").format(new Date()));
		return thisTime;
	}
}
