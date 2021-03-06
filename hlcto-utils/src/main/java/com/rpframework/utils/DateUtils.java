package com.rpframework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 *  日期帮助类
 * @author rplees
 * @date 2010-10-18
 */
public class DateUtils {
	
	public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String DEFAULT_DATE_FORMAT = YYYY_MM_DD_HH_MM_SS;
	public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYYMMDD = "yyyyMMdd";

	public static void main(String[] args) {
		/*DayRule dr = new DayRule(20130125, 20130726);
		
		HourAndMinRule d3= new HourAndMinRule(330, 1634);
		long s = System.currentTimeMillis();
//		for (int i = 0; i < 100000; i++) {
			boolean verifyDateRule = verifyDateRule(dr);
			System.out.println(verifyDateRule);
//		}
		System.out.println("耗时:" + (System.currentTimeMillis() - s) + " 毫秒");*/
	System.out.println(hourAdd(24));
	
	int dd = NumberUtils.parseInt(DateUtils.nowDate(DateUtils.YYYYMMDD));
	System.out.println(dd);
	}
/*	*//**
	 * 验证是不是有效的日期
	 * @param dys
	 * @param hrs
	 * @return
 * @throws ParseException 
	 *//*
	public static boolean verifyDateRule(DayRule dr, HourAndMinRule ... hrs) {
		DayRule[] drs = new DayRule[1];
		drs[0] = dr;
		
		return verifyDateRule(drs, hrs);
	}*/
	
	
	/**
	 * 将string 格式字符串转换成long型
	 */
	public static Long verifyDateformString(String s ) throws ParseException{
	   return getDateFormat(YYYY_MM_DD_HH_MM_SS).parse(s).getTime();
	}
	
	public static Date getTodayStartDate() {
		return getDayStartDate(new Date());
	}
	
	public static Date getDayStartDate(Date date) {
		Calendar currentDate = new GregorianCalendar();   
		currentDate.setTime(date);
		
		currentDate.set(Calendar.HOUR_OF_DAY, 0);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);  
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTime();
	}
	
	public static Date getDayEndDate(Date date) {
		Calendar currentDate = new GregorianCalendar();   
		currentDate.setTime(date);
		
		currentDate.set(Calendar.HOUR_OF_DAY, 24);  
		currentDate.set(Calendar.MINUTE, 0);  
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);
		return currentDate.getTime();
	}
	
	public static Date getTodayEndDate() {
		return getDayEndDate(new Date());
	}
	
	
	public static Date getWeekStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return  cal.getTime();
	}
	
	public static Date getWeekEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getWeekStartDate());
		cal.add(Calendar.DAY_OF_WEEK, 7);
		return cal.getTime();
	}
	
	public static Date getMonthStartDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return  cal.getTime();
	}
	
	public static Date getMonthEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
	}
	
	/**
	 * 验证是不是有效的日期
	 * @param dys
	 * @param hrs
	 * @return
	 */
	public static boolean verifyDateRule(DayRule dr, DateRule ... hrs) {
		DayRule[] drs = new DayRule[1];
		drs[0] = dr;
		
		return verifyDateRule(drs, hrs);
	}
	
	/**
	 * 验证是不是有效的日期
	 * @param dys
	 * @param hrs
	 * @return
	 */
	public static boolean verifyDateRule(DayRule dr) {
		DayRule[] drs = new DayRule[1];
		drs[0] = dr;
		
		return verifyDateRule(drs, null);
	}
	
	/**
	 * 验证是不是有效的日期
	 * @param dys
	 * @param hrs
	 * @return
	 */
	public static boolean verifyDateRule(DayRule[] dys, DateRule ... hrs) {
		Date d = new Date();
		
		boolean f = false;
		if(dys != null && dys.length > 0) {
			for (DayRule dr : dys) {
				if(dr.between(d)) f = true;
				if(f) break;
			}
		} else {
			f = true;
		}
		
		//天数验证不通过，就没必要验证小时了
		if(! f) return f;
		
		if(hrs == null || hrs.length < 1) return true;
		for (DateRule hr : hrs) {
			if(hr.between(d)) return true;
		}
		
		return false;
	}
	/**
	 * 在现在时间的基础上增加 days 后的日期
	 * @param days
	 * @return
	 */
	public static Date dayAdd(int days) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        return calendar.getTime();
    }
	/**
	 * 在指定时间的基础上增加 hour 后的日期
	 * @param hour
	 * @return
	 */
	public static Date hourAdd(int hour, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, hours + hour);
		return calendar.getTime();
	}
	/**
	 * 在现在时间的基础上增加 days 后的日期
	 * @param days
	 * @return
	 */
	public static Date hourAdd(int hour) {
		Calendar calendar = Calendar.getInstance();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, hours + hour);
		return calendar.getTime();
	}
	/**
	 * 在指定时间的基础上增加 days 后的日期
	 * @param days
	 * @return
	 */
	public static Date dayAdd(int days, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + days);
		return calendar.getTime();
	}
	/**
	 * 在指定时间的基础上增加 days 后的日期
	 * @param days
	 * @return
	 */
	public static String dayAdd(int days, String date) {
		try {
			return getDateFormat().format(DateUtils.dayAdd(days, parse(date))) ;
		} catch (Exception e) {
			return null;
		}
	}
	
    public static Date parse(String date) {
    	try {
    		return getDateFormat().parse(date);
		} catch (Exception e) {
			try {
	    		return getDateFormat(YYYY_MM_DD_HH_MM_SS).parse(date);
			} catch (Exception e1) {
				try {
					return getDateFormat(YYYY_MM_DD_HH_MM).parse(date);
				} catch (ParseException e2) {
					try {
			    		return getDateFormat(YYYYMMDDHHMMSS).parse(date);
					} catch (Exception e3) {
						try {
				    		return getDateFormat(YYYY_MM_DD).parse(date);
						} catch (Exception e4) {
							return null;
						}
					}
				}
			}
		}
	}
    
    public static int getNowDayOfWeek() {
    	return getDayOfWeek(new Date());
    }
    /**  
     * 描述
     * @param dt
     * @return  0 - 礼拜7
     */
    public static int getDayOfWeek(Date dt) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w;
    }
	//月数操作
    public static Date monthAdd(int months) {
	    Calendar calendar = Calendar.getInstance();
	    int m = calendar.get(Calendar.MONTH);
	    calendar.set(Calendar.MONTH, m + months );
	    return calendar.getTime();
	}
    
    //月数操作
    public static Date monthAdd(int months, Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    int m = calendar.get(Calendar.MONTH);
	    calendar.set(Calendar.MONTH, m + months );
	    return calendar.getTime();
	}
    
	public static Date minAdd(int mins) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(12);
		calendar.set(12, day + mins);
		return calendar.getTime();
	}

	public static Date minAdd(int hour, Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hours = calendar.get(12);
		calendar.set(12, hours + hour);
		return calendar.getTime();
	}

    /**
     * 根据DateFormat 格式化现在时间
     * @param df 
     * @return 如:2012-12-21
     */
    public static String nowDate(DateFormat df){
		if (null == df) {
			df = getDateFormat();
		}
		return df.format(new Date());
    }
    /**
     * 默认的格式化时间
     * @return 2012-12-21 12:31:20
     */
    public static String nowDate(){
    	return getDateFormat().format(new Date());
    }
    /**
     * 默认的格式化时间
     * @return 2012-12-21 12:31:20
     */
    public static String nowDate(String format){
    	return getDateFormat(format).format(new Date());
    }
    
    public static DateFormat getDateFormat(){
    	return new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    }
    
    public static DateFormat getDateFormat(String format){
    	return new SimpleDateFormat(format);
    }
	public static String format(String format, Date date) {
		return getDateFormat(format).format(date);
	}
	
	/**  
	 * 是否为同一个月份
	 * @param d1
	 * @param d2
	 * @return  Boolean.True 同一个
	 */
	public static boolean isSameMonth(Date d1, Date d2) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(d1);
		int i1 = instance.get(Calendar.MONTH);
		
		instance.setTime(d2);
		int i2 = instance.get(Calendar.MONTH);
		
		return i1 == i2;
	}
	
	/**  
	 * 是否为同一个天数
	 * @param d1
	 * @param d2
	 * @return  Boolean.True 同一个
	 */
	public static boolean isSameDay(Date d1, Date d2) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(d1);
		int i1 = instance.get(Calendar.DAY_OF_YEAR);
		
		instance.setTime(d2);
		int i2 = instance.get(Calendar.DAY_OF_YEAR);
		
		return i1 == i2;
	}
	/**  
	 * 格式化时间
	 * YYYY_MM_DD_HH_MM_SS  2015-11-24 17:12:00
	 * YYYY_MM_DD_HH_MM     2015-11-24 17:12
	 * data 2015-11-24 17:12
	 * @return 2015-11-24 17:12:00格式的时间
	 */
	public static String timeDate(String seconds,String format) {  
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || format.isEmpty());  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
   }
	/**  
	 * 格式化时间为周 星期几
	 * @param long l 时间戳
	 * return 周几
	 */
	public static String getWeekOfDate(Long l) {
		Date dt = new Date(l);
	    String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    if (w < 0)
	        w = 0;
	    return weekDays[w];
	}
	
	// 获得当天0点时间
		public static Date getTimesmorning() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}

		// 获得当天24点时间
		public static Date getTimesnight() {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 24);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		}

		// 获得本周一0点时间
		public static Date getTimesWeekmorning() {
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			return cal.getTime();
		}
		
		// 获得本周日24点时间
		public static Date getTimesWeeknight() {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getTimesWeekmorning());
			cal.add(Calendar.DAY_OF_WEEK, 7);
			return cal.getTime();
		}

		// 获得本月第一天0点时间
		public static Date getTimesMonthmorning() {
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			return cal.getTime();
		}
		/**
		 * 获得本月最后一天24点时间
		 * @return
		 * @time 2016年3月4日 上午11:47:53
		 */
		public static Date getTimesMonthnight() {
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal.set(Calendar.HOUR_OF_DAY, 24);
			return cal.getTime();
		}
		/**
		 * 	System.out.println("当前时间："+ new Date().toLocaleString());
			System.out.println("当天0点时间："+ getTimesmorning().toLocaleString());
			System.out.println("当天24点时间："+ getTimesnight().toLocaleString());
			System.out.println("本周周一0点时间："+ getTimesWeekmorning().toLocaleString());
			System.out.println("本周周日24点时间："+ getTimesWeeknight().toLocaleString());
			System.out.println("本月初0点时间："+ getTimesMonthmorning().toLocaleString());
			System.out.println("本月未24点时间："+ getTimesMonthnight().toLocaleString());
		 */
}