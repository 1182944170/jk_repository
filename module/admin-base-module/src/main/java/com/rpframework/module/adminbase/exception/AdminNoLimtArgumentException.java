package com.rpframework.module.adminbase.exception;

public class AdminNoLimtArgumentException extends IllegalArgumentException {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;
	public AdminNoLimtArgumentException() {
		super("No Limit");
	}
	public AdminNoLimtArgumentException(String msg) {
		super(msg);
	}
}
