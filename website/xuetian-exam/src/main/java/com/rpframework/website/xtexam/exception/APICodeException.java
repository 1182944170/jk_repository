package com.rpframework.website.xtexam.exception;

import java.util.Map;

public class APICodeException extends RuntimeException {
	/**描述*/  
	Integer code;
	String msg;
	private static final long serialVersionUID = 1L;
	
	public APICodeException(int code, String msg) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public void put(Map<String, Object> map) {
		map.put("code", code);
		map.put("msg", msg);
		map.put("succ", false);
	}

}
