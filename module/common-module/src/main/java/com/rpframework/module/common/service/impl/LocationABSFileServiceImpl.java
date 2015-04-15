package com.rpframework.module.common.service.impl;

import java.io.File;

import org.apache.commons.lang.StringUtils;

import com.rpframework.core.InitServlet;
import com.rpframework.core.utils.DictionarySettingUtils;

public class LocationABSFileServiceImpl extends LocationFileServiceImpl {
	public LocationABSFileServiceImpl() {
		super("");
	}

	@Override
	public String getFullFilePath(String relativelyPath) {
		return InitServlet.REAL_PATH + File.separator + relativelyPath;
	}
	
	@Override
	public String getWebUrl() {
		String webUrl = DictionarySettingUtils.getParameterValue(WEB_URL_KEY);
		if(StringUtils.isNotBlank(webUrl)) {
			// 这个参数在这个情况下是无用的
		}
		return InitServlet.CTX;
	}
}