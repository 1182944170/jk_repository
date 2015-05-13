package com.rpframework.website.edongwang.exception;

import com.rpframework.website.edongwang.utils.EConstants;

public class NoLoginException extends APICodeException {

	public NoLoginException() {
		super(EConstants.API.NO_LOGIN, "未登录");
	}

	/**描述*/  
	private static final long serialVersionUID = 1L;
}
