package com.rpframework.core.exception;

public class TipsException extends IllegalArgumentException {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;

	public TipsException(String msg) {
		super(msg);
	}
}
