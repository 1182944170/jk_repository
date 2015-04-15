package com.rpframework.core.utils;

import java.util.Date;

import com.rpframework.core.api.FileService;
import com.rpframework.utils.DateUtils;

public class TagUtils {
	public static String formatDate(long timestamp) {
		return formatDate(timestamp, DateUtils.DEFAULT_DATE_FORMAT);
	}
	
	public static String formatDate(long timestamp, String format) {
		return DateUtils.format(format, new Date(timestamp));
	}
	
	public static String getWebUrl(){
		FileService fileService = SpringUtils.getBean(FileService.class);
		return fileService.getWebUrl();
	}
}
