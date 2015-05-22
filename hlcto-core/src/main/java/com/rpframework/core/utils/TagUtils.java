package com.rpframework.core.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.InitServlet;
import com.rpframework.core.api.FileService;
import com.rpframework.core.freemarker.BaseRegistFreemarker;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.FileUtils;

@Component("tagUtils")
public class TagUtils extends BaseRegistFreemarker {
	public static Object getNull() {
		return null;
	}
	
	public static String cutString(String s, int len) {
		if(StringUtils.isBlank(s)) {
			return s;
		}
		
		if(s.length() <= len) {
			return s;
		}
		
		return StringUtils.substring(s, 0, len) + "...";
	}
	public static String getDomain(HttpServletRequest request) {
		String d = request.getScheme() + "://" + request.getServerName();
		if(request.getServerPort() != 80) {
			d += ":" + request.getServerPort();
		}
		
		d += InitServlet.CTX + "/";
		return d;
	}
	
	public static String formatDate(String timestampString) {
		return formatDate(Long.parseLong(timestampString));
	}
	
	public static String formatDate(long timestamp) {
		return formatDate(timestamp, DateUtils.DEFAULT_DATE_FORMAT);
	}
	
	public static boolean logic2(Integer t, Integer t2) {
		if(t == null) return false;
		return (t & t2) > 0;
	}
	
	/**
	 * 
	 * 描述
	 * @param timestamp 秒  不是毫秒
	 * @param format
	 * @return
	 */
	public static String formatDate(long timestamp, String format) {
		return DateUtils.format(format, new Date(timestamp*1000L));
	}
	
	
	public static long getLong(JsonObject jsonObject, String key, Long defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsLong();
	}
	
	public static Long getLong(JsonObject jsonObject, String key) {
		return getLong(jsonObject, key, 0L);
	}
	
	public static int getInt(JsonObject jsonObject, String key, Integer defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsInt();
	}
	
	public static int getInt(JsonObject jsonObject, String key) {
		return getInt(jsonObject, key, 0);
	}
	
	public static String getString(JsonObject jsonObject, String key, String defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsString();
	}
	
	public static String getString(JsonObject jsonObject, String key) {
		return getString(jsonObject, key, "");
	}
	
	public static Double getDouble(JsonObject jsonObject, String key, Double defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsDouble();
	}
	
	public static Double getDouble(JsonObject jsonObject, String key) {
		return getDouble(jsonObject, key, 0D);
	}
	
	public static String getWebUrl(){
		FileService fileService = SpringUtils.getBean(FileService.class);
		return fileService.getWebUrl();
	}
	
	public static String getFileFullPath(String absPath) {
		String url = FileUtils.splicePaths(getWebUrl(), absPath);
		return StringUtils.replace(url, "\\", "/");
	}
}
