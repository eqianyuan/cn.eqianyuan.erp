package cn.eqianyuan.erp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 日期时间工具类
 *  
 */

public class CalendarUtil {
	/**
	 * 常用时间格式
	 */
	public static final String Format_Date = "yyyy-MM-dd";
	public static final String Format_Time = "HH:mm:ss";
	public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";
	public static final String Format_DateNum = "yyyyMMddHHmmss";
	/**
	 * 默认的日期格式化器，格式为yyyy-MM-dd
	 */
	private final static SimpleDateFormat DEFAULT_DATE_FORMATER = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 默认的时间格式化器，格式为yyyy-MM-dd HH:mm:ss
	 */
	private final static SimpleDateFormat DEFAULT_DATETIME_FORMATER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	
	/**
	 * 取得当前日期（只有日期，没有时间，或者可以说是时间为0点0分0秒）
	 * @return
	 * @throws ParseException 
	 */
	public static Date getCurrentDate() throws ParseException{
		Date date = new Date();
		date = DEFAULT_DATE_FORMATER.parse(DEFAULT_DATE_FORMATER.format(date));//
		return date;
	}
	
	/**
	 * 取得当前时间（包括日期和时间）
	 * @return
	 */
	public static Date getCurrentDateTime(){
		Date date = new Date();
		return date;
	}
	
	/**
	 * 用默认的日期格式，格式化一个Date对象
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){	
		return date == null ? "" : DEFAULT_DATE_FORMATER.format(date);
	}
	
	/**
	 * 根据传入的格式，将日期对象格式化为日期字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date,String format){
		String s = "";
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			s = sdf.format(date);
		}
		
		return s;
	}
	
	/**
	 * 用默认的日期时间格式，格式化一个Date对象
	 * @param date
	 * @return
	 */
	public static String formatTime(Date date) {
		return date == null ? "" : DEFAULT_DATETIME_FORMATER.format(date);
	}
	
	/**
	 * 根据传入的格式，将日期对象格式化为时间字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatTime(Date date,String format){
		String s = "";
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			s = sdf.format(date);
		}
		
		return s;
	}
	
	/**
	 * 日期后推
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
	
	/**
	 * 利用默认的格式（yyyy-MM-dd）将一个表示日期的字符串解析为日期对象
	 * @param s
	 * @return
	 * @throws RuntimeException
	 */
	public static Date parseDate(String s){
		Date date = null;
		try{
			date = DEFAULT_DATE_FORMATER.parse(s);
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
	    return date;    
	}
	
	/**
	 * 将一个字符串，按照特定格式，解析为日期对象
	 * @param s
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String s,String format) throws ParseException {
		Date date = null;
		try{
			date = (new SimpleDateFormat(format)).parse(s);
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
		return date;
	}
	
	
	/**
	 * 利用默认的格式（yyyy-MM-dd HH:mm:ss）将一个表示时间的字符串解析为日期对象
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String s) throws ParseException {    
	    return DEFAULT_DATETIME_FORMATER.parse(s);    
	}
	
	/**
	 * 得到当前年份
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * 得到当前月份（1至12）
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取yyyy-MM-dd格式的当前系统日期
	 * 
	 * @return
	 */
	public static String getCurrentDateAsString() {
		return new SimpleDateFormat(Format_Date).format(new Date());
	}

	/**
	 * 获取指定格式的当前系统日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 获取HH:mm:ss格式的当前系统时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat(Format_Time).format(new Date());
	}

	/**
	 * 获取指定格式的当前系统时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * 获取格式为yyyy-MM-dd HH:mm:ss的当前系统日期时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTimeAsString() {
		return getCurrentDateTime(Format_DateTime);
	}
	public static String getCurrentDateNumAsString() {
		return getCurrentDateTime(Format_DateNum)+(int)(Math.random()*10);
	}
	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取星期X中文名称
	 * 
	 * @param date
	 * @return
	 */
	public static String getChineseDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return getChineseDayOfWeek(cal.getTime());
	}

	/**
	 * 获取星期X中文名称
	 * 
	 * @param date
	 * @return
	 */
	public static String getChineseDayOfWeek(String date) {
		return getChineseDayOfWeek(parseDate(date));
	}

	/**
	 * 获取星期X中文名称
	 * 
	 * @param date
	 * @return
	 */
	public static String getChineseDayOfWeek(Date date) {
		int dateOfWeek = getDayOfWeek(date);
		if (dateOfWeek == Calendar.MONDAY) {
			return "星期一";
		} else if (dateOfWeek == Calendar.TUESDAY) {
			return "星期二";
		} else if (dateOfWeek == Calendar.WEDNESDAY) {
			return "星期三";
		} else if (dateOfWeek == Calendar.THURSDAY) {
			return "星期四";
		} else if (dateOfWeek == Calendar.FRIDAY) {
			return "星期五";
		} else if (dateOfWeek == Calendar.SATURDAY) {
			return "星期六";
		} else if (dateOfWeek == Calendar.SUNDAY) {
			return "星期日";
		}
		return null;
	}

	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMaxDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static String getFirstDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return new SimpleDateFormat(Format_Date).format(cal.getTime());
	}

	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getDayOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	public static int getDayOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfMonth(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear(String date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseDate(date));
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取指定格式的当前系统日期时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	public static String toString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_Date).format(date);
	}

	public static String toDateTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_DateTime).format(date);
	}

	public static String toString(Date date, String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(date);
	}

	public static String toTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_Time).format(date);
	}
	/**
	 * 时间戳转换
	 * @param time
	 * @return
	 */
	public static String longTimeToDateTimeString(Long time) {
		SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
		String d = format.format(time);
		return d;
	}

	/**
	 * 时间戳转换
	 * @param time
	 * @return
	 */
	public static Date longTimeToDateTime(Long time) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
		String d = format.format(time);
		return parseTime(d);
	}

	/**
	 * 秒数时间转为日期格式字符串
	 * @param time
	 * @return
	 */
	public static String secondsTimeToDateTimeString(Long time){
		SimpleDateFormat format = new SimpleDateFormat(Format_DateTime);
		String d = format.format(time * 1000);
		return d;
	}

	/**
	 * 获取系统当前秒数时间
	 * @return
	 */
	public static long getSystemSeconds(){
		return System.currentTimeMillis() / 1000;
	}

}
