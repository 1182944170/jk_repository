package com.rpframework.module.common.service.impl;

import java.io.File;

import com.rpframework.core.InitServlet;

public class LocationABSFileServiceImpl extends LocationFileServiceImpl {
	public LocationABSFileServiceImpl() {
		super("");
	}

	@Override
	public String getFullFilePath(String relativelyPath) {
		return InitServlet.REAL_PATH + File.separator + relativelyPath;
	}
}