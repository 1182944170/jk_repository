package com.rpframework.module.common.fck.requestcycle.impl;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.requestcycle.UserAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2009-9-2
 * @author rplees
 */
public class MyEnabledUserAction implements UserAction {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean isCreateFolderEnabled(HttpServletRequest request) {
		return true;
	}
	public boolean isEnabledForFileBrowsing(HttpServletRequest request) {
		logger.info("isEnabledForFileBrowsing!{}", request.getRequestURL());
		return true;
	}
	public boolean isEnabledForFileUpload(HttpServletRequest request) {
		return true;
	}
}