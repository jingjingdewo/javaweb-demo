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
	 * Stringתunicode
	 * @param string
	 * @return
	 */
	public static String StringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // ȡ��ÿһ���ַ�
            char c = string.charAt(i);
            // ת��Ϊunicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
	/**
	 * unicodeתString
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
	 * �ж��Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.equals("");
	}
	

	/**
	 * ���strΪĿ��ֵgoal����תΪĬ��ֵdef
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
	 * �õ�������ʱ��
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
		// ��ȡ��ǰʱ��ʱ����
		int strDateH = Integer.parseInt(strDate.substring(11, 13));
		int strDateM = Integer.parseInt(strDate.substring(14, 16));
		int strDateS = Integer.parseInt(strDate.substring(17, 19));
		// ��ȡ��ʼʱ��ʱ����
		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
		// ��ȡ����ʱ��ʱ����
		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
			// ��ǰʱ��Сʱ���ڿ�ʼʱ��ͽ���ʱ��Сʱ��֮��
			if (strDateH > strDateBeginH && strDateH < strDateEndH) {
				return true;
				// ��ǰʱ��Сʱ�����ڿ�ʼʱ��Сʱ�����������ڿ�ʼ�ͽ���֮��
			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
					&& strDateM <= strDateEndM) {
				return true;
				// ��ǰʱ��Сʱ�����ڿ�ʼʱ��Сʱ�������������ڿ�ʼʱ��������������ڿ�ʼ�ͽ���֮��
			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM
					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {
				return true;
			}
			// ��ǰʱ��Сʱ������ڿ�ʼʱ��Сʱ�������ڽ���ʱ��Сʱ����������С���ڽ���ʱ�������
			else if (strDateH >= strDateBeginH && strDateH == strDateEndH
					&& strDateM <= strDateEndM) {
				return true;
				// ��ǰʱ��Сʱ������ڿ�ʼʱ��Сʱ�������ڽ���ʱ��Сʱ�������������ڽ���ʱ�������������С���ڽ���ʱ������
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
	  * ���ڸ�ʽת��yyyy-MM-dd'T'HH:mm:ss.SSSXXX  (yyyy-MM-dd'T'HH:mm:ss.SSSZ) TO  yyyy-MM-dd HH:mm:ss
	  * @throws ParseException 
	  */
	public static String dealDateFormat(String oldDateStr)
			throws ParseException {
		// �˸�ʽֻ�� jdk 1.7��֧�� yyyy-MM-dd'T'HH:mm:ss.SSSXXX
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // yyyy-MM-dd'T'HH:mm:ss.SSSZ
		Date date = df.parse(oldDateStr);
		
		return df.format(date);
	}

	/**
	 * ���strΪnull��תΪĬ��ֵdef
	 * @param str
	 * @param def
	 * @return
	 */
	public static String strNullToDef(String str, String def){
		return str == null ? def : str;
	}
	
	/**
	 * ���strΪnull��תΪ��ֵ
	 * @param str
	 * @return
	 */
	public static String strNullToEmpty(String str){
		return strNullToDef(str, "");
	}
	
	/**
	 * �ж��ַ�����Ϊ��
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
	 * �ж�һ�����鲻Ϊ��
	 * @param array
	 * @return
	 */
	public static boolean strArrayIsNotEmpty(Object[] array){
		return array == null || array.length == 0 ? false : true;
	}
	
	/**
	 * �ж�һ���ַ����Ƿ�����������
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
	 * ���ֽ��루utf-8��
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
	 * ��str����format��ʽת����ʱ��
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
	 * yyyy-MM-dd��ʽת��ʱ��
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str){
		return strToDate(str, "yyyy-MM-dd");
	}
	
	/**
	 * ��date����format��ʽת�����ַ���
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
	 * ת��yyyy-MM-dd��ʽ�ַ���
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date){
		return dateToStr(date, "yyyy-MM-dd");
	}
	
	/**
	 * ת��yyyyMMdd��ʽ�ַ���
	 * @param date
	 * @return
	 */
	public static String dateYYMMDD(Date date){
		return dateToStr(date, "yyyyMMdd");
	}
	
	/**
	 * format1��ʽ��ʱ���ַ���ת��format2��ʽ
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
	 * yyyyMMdd��ʽת��yyyy-MM-dd
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
	 * ͨ�����֤  �õ� ���� 
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
	 * ͨ�����֤  �õ�  ��������  
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
