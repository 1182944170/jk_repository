package com.rpframework.utils;

import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:rplees.i.ly@gmail.com">rplees</a> date 2010-03-29
 *         {@code}自定义表情库，提供一些基本的函数
 */
public class InfoTag {

    /**
     * "----"
     */
    public static final String SLASH = "----";
    public static final DecimalFormat df3 = new DecimalFormat("#0.000");

    public static String timestamp2Str(Integer t) {
		Date d = new Date(t * 1000L);
		return DateUtils.format(DateUtils.YYYY_MM_DD_HH_MM_SS, d);
    }

    public static String timelong2Str(Long t) {
    	return DateUtils.getDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS).format(t);
    }
    
    public static Integer logic(Integer r , Integer r2) {
    	if(r == null || r2 == null) return 0;
    	return r & r2;
    }
    /**
     * 自定义文件 将资源表的大文件path转向小文件的path
     * 
     * @param path
     * @return
     */
    public static String res2s(String path) {
	if (StringUtils.isBlank(path))
	    return path;
	return path.replace("_B", "_S");
    }

    /**
     * 自定义文件 将资源表的大文件path转向中文件的path
     * 
     * @param path
     * @return
     */
    public static String res2m(String path) {
	if (StringUtils.isBlank(path))
	    return path;
	return path.replace("_B", "_M");
    }

    /**
     * 把日期转换 月日 2010-05-19 12:30:20 -> 05-19 2010-05-19 -> 2010-05-19
     * 
     * @return
     */
    public static String date2md(String date) {
	if (StringUtils.isBlank(date))
	    return null;
	int s = date.indexOf("-");
	int s1 = date.indexOf(" ");
	if (s1 == -1)
	    s1 = date.length();
	else if (s == -1)
	    s = 0;

	return date.substring(s + 1, s1);
    }

    /**
     * 格式化数字
     * 
     * @param num
     *            数字
     * @return 保留两位小数
     */
    public static String formatDouble(Double num) {
	if (num == null || num == 0 || num.isNaN()) {
	    return SLASH;
	}
	try {
	    String unit = "";
	    if (num > 100000000) {
		num = num / 100000000;
		unit = "亿";
	    } else if (num > 10000) {
		num = num / 10000;
		unit = "万";
	    }
	    return df3.format(num) + unit;
	} catch (Exception e) {
	    return SLASH;
	}
    }

    /**
     * 过滤html标签并按指定长度截取
     * 
     * @param html
     * @param length
     * @return
     */
    public static String cutHtml(String html, Integer length) {
		String str = CodeUtils.splitAndFilterString(html);
		if (StringUtils.isBlank(str))
			return "";
		if (str.length() <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "...";
		}
		return str;
    }
    
    public static String txt2htm(String txt) {
    	return CodeUtils.txt2htm(txt);
    }
}
