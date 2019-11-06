package com.demo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.apache.commons.collections.map.LinkedMap;

public class StrUtils {
	/**
	 * String转unicode
	 * @param string
	 * @return
	 */
	public static String StringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
	/**
	 * unicode转String
	 * @param str
	 * @return
	 */
	public static String unicodeToString(String str) {  
	    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");      
	    Matcher matcher = pattern.matcher(str);  
	    char ch;  
	    while (matcher.find()) {  
	        ch = (char) Integer.parseInt(matcher.group(2), 16);  
	        str = str.replace(matcher.group(1), ch + "");      
	    }  
	    return str;  
	}  
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}
	

	/**
	 * 如果str为目标值goal，则转为默认值def
	 * @param str
	 * @param goal
	 * @param def
	 * @return
	 */
	public static String strGoalToDef(String str, String goal, String def){
		if(goal == null){
			return str == null ? def : str;
		}else{
			return str.equals(goal) ? def : str;
		}
	}
	
	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
	
	
	public static boolean isInDate(Date date, String strDateBegin,
			String strDateEnd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate = sdf.format(date);
		// 截取当前时间时分秒
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		// 截取开始时间时分秒
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// 截取结束时间时分秒
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// 当前时间小时数在开始时间和结束时间小时数之间
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			}
			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
			else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM <= strDateEndM) {
				return true;
				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM == strDateEndM && strDateS <= strDateEndS) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	  * 日期格式转换yyyy-MM-dd'T'HH:mm:ss.SSSXXX  (yyyy-MM-dd'T'HH:mm:ss.SSSZ) TO  yyyy-MM-dd HH:mm:ss
	  * @throws ParseException 
	  */
	public static String dealDateFormat(String oldDateStr)
			throws ParseException {
		// 此格式只有 jdk 1.7才支持 yyyy-MM-dd'T'HH:mm:ss.SSSXXX
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // yyyy-MM-dd'T'HH:mm:ss.SSSZ
		Date date = df.parse(oldDateStr);
		
		return df.format(date);
	}

	/**
	 * 如果str为null，转为默认值def
	 * @param str
	 * @param def
	 * @return
	 */
	public static String strNullToDef(String str, String def){
		return str == null ? def : str;
	}
	
	/**
	 * 如果str为null，转为空值
	 * @param str
	 * @return
	 */
	public static String strNullToEmpty(String str){
		return strNullToDef(str, "");
	}
	
	/**
	 * 判断字符串不为空
	 * @param str
	 * @return
	 */
	public static boolean strIsNotEmpty(String str){
		if(str == null || str.equals("")){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断一个数组不为空
	 * @param array
	 * @return
	 */
	public static boolean strArrayIsNotEmpty(Object[] array){
		return array == null || array.length == 0 ? false : true;
	}
	
	/**
	 * 判断一个字符串是否在数组里面
	 * @param str
	 * @param array
	 * @return
	 */
	public static boolean strIsInArrary(String str, String[] array){
		if(StrUtils.strIsNotEmpty(str) && StrUtils.strArrayIsNotEmpty(array)){
			for(int i = 0; i < array.length; i ++){
				if(str.equals(array[i]))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * 汉字解码（utf-8）
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String strDecodeUTF8(String str) throws UnsupportedEncodingException{
		if(StrUtils.strIsNotEmpty(str)){
			return URLDecoder.decode(str, "utf-8");
		}
		return str;
	}
	
	/**
	 * 将str根据format格式转化成时间
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date strToDate(String str, String format){
		if(str == null){
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			return sf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * yyyy-MM-dd格式转成时间
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str){
		return strToDate(str, "yyyy-MM-dd");
	}
	
	/**
	 * 将date根据format格式转化成字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToStr(Date date, String format){
		if(date == null){
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}
	
	/**
	 * 转成yyyy-MM-dd格式字符串
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date){
		return dateToStr(date, "yyyy-MM-dd");
	}
	
	/**
	 * 转成yyyyMMdd格式字符串
	 * @param date
	 * @return
	 */
	public static String dateYYMMDD(Date date){
		return dateToStr(date, "yyyyMMdd");
	}
	
	/**
	 * format1格式的时间字符串转成format2格式
	 * @param str
	 * @param format1
	 * @param format2
	 * @return
	 */
	public static String  dateFormat(String str, String format1, String format2){
		if(str == null){
			return null;
		}
		SimpleDateFormat sf = new SimpleDateFormat(format1);
		SimpleDateFormat sf1 = new SimpleDateFormat(format2);
		try {
			Date date = sf.parse(str);
			return sf1.format(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * yyyyMMdd格式转成yyyy-MM-dd
	 * @param str
	 * @return
	 */
	public static String  dateFormat(String str){
		return dateFormat(str, "yyyyMMdd", "yyyy-MM-dd");
	}
	
	public static boolean isNull(String str) {
		return str == null || str.equals("");
	}
	
	public static boolean inScope(String str, String scope) {
		boolean r = false;
		String[] values = scope.split(",");
		for(String value: values) {
			if(str.equals(value)) {
				r = true;
				break;
			}
		}
		return r;
	}
	
	/**
	 * 通过身份证  得到 年龄 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String getAgeByIdnum(String s) throws Exception {
		int leh = s.length();
        if (leh != 18 && leh != 15) {
            // '' 
        	return "0"; 
        }
        else {
            if (leh == 18) {
                 String dates = s.substring(6, 10) + "-" + s.substring(10, 12) + "-" + s.substring(12, 14);
                 Date dateOfBirth  =StrUtils.strToDate(dates) ; 
                 int age = 0;
         		 Calendar born = Calendar.getInstance();
         		 Calendar now = Calendar.getInstance();
         		 if (dateOfBirth != null) {
         			 now.setTime(new Date());
         			 born.setTime(dateOfBirth);
         			 if (born.after(now)) {
         				 //throw new IllegalArgumentException( "Can't be born in the future");
         				return "0"; 
         			 }
         			 age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
         			 if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
         				 age -= 1;
         			 }
         			return age+"";
         		 }
         		return "0"; 
            }else{
            	// ''
            	return "0"; 
            }
        }
		
	}
	/**
	 * 通过身份证  得到  出生日期  
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String geBirthByIdnum(String s) throws Exception {
		int leh = s.length();
		if (leh != 18 && leh != 15) {
			// '' 
			return ""; 
		}
		else {
			if (leh == 18) {
				String dates = s.substring(6, 10) + "-" + s.substring(10, 12) + "-" + s.substring(12, 14);
        		return dates; 
			}else{
				// ''
				return ""; 
			}
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		String s = StrUtils.dealDateFormat("2016-11-15T00:00:00+08:00"); 
		System.out.println(s);
	}
}
