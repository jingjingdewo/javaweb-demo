package com.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ʱ��ģ��
 * @author ��η���� 2019/11/5 
 *
 */
public class TimeUtils {
	/**
	 * ��ȡ��ǰʱ��
	 * @return
	 */
	public static String getNowTime() {
		String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		return nowTime;
	}
	
	/**
	 * ����String��ʱ�䣬��ȡlong��ʱ�䣬��λ����
	 * @param inVal ʱ���ַ���
	 * @return long��ʱ��
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
