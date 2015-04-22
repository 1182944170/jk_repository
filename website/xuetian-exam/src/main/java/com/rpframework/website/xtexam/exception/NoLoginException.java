package com.rpframework.website.xtexam.exception;

import com.rpframework.website.xtexam.utils.XTConstants;

public class NoLoginException extends APICodeException {

	public NoLoginException() {
		super(XTConstants.API.NO_LOGIN, "未登录");
	}

	/**描述*/  
	private static final long serialVersionUID = 1L;
}
