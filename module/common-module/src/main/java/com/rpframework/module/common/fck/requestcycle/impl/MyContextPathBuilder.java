package com.rpframework.module.common.fck.requestcycle.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import net.fckeditor.requestcycle.impl.ContextPathBuilder;

import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.utils.FCKUtils;
import com.rpframework.utils.FileUtils;

/**
 * 2010-03-17
 * @author rplees
 * FCK
 */
public class MyContextPathBuilder extends ContextPathBuilder {
	@Override
	public String getUserFilesPath(final HttpServletRequest request) {
		FileService fileService = SpringUtils.getBean(FileService.class);
		String url = FileUtils.splicePaths(fileService.getWebUrl() ,FCKUtils.getFCKUploadRoot());
		return StringUtils.replace(url, "\\", "/");
	}
}