package com.rpframework.core.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.rpframework.core.InitServlet;
import com.rpframework.core.api.FileService;
import com.rpframework.core.freemarker.BaseRegistFreemarker;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.FileUtils;

@Component("tagUtils")
public class TagUtils extends BaseRegistFreemarker {
	public static String getDomain(HttpServletRequest request) {
		String d = request.getScheme() + "://" + request.getServerName();
		if(request.getServerPort() != 80) {
			d += ":" + request.getServerPort();
		}
		
		d += InitServlet.CTX + "/";
		return d;
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
	
	public static String getWebUrl(){
		FileService fileService = SpringUtils.getBean(FileService.class);
		return fileService.getWebUrl();
	}
	
	public static String getFileFullPath(String absPath) {
		return FileUtils.splicePaths(getWebUrl(), absPath);
	}
}
