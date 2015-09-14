package com.rpframework.module.common.exception;

@SuppressWarnings("serial")
public class FTPClientException extends RuntimeException {
	public FTPClientException() {
		super();
	}
	public FTPClientException(String s) {
		super(s);
	}
	public FTPClientException(String s, Exception e) {
		super(s + "e:" + e.getLocalizedMessage(), e);
	}
}