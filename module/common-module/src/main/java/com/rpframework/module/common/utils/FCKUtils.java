package com.rpframework.module.common.utils;

import org.apache.commons.lang.StringUtils;

import net.fckeditor.handlers.PropertiesLoader;

public class FCKUtils {
	public static final String DEFAULT_FCK_UPLOAD_PATH = "fck";
	public static String getFCKUploadRoot (){
		String path = PropertiesLoader.getProperty("fck.ftpUploadRoot");
		if(StringUtils.isBlank(path)) {
			return DEFAULT_FCK_UPLOAD_PATH;
		}
		return path;
	}
}
