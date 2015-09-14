package com.rpframework.module.common.plugin.networksafe.exception;

@SuppressWarnings("serial")
public class RequestFrequencyLimitException extends IllegalArgumentException {
	public RequestFrequencyLimitException(String msg) {
		super(msg + "-请求过于频繁.");
	}
	public RequestFrequencyLimitException() {
		this("请求过于频繁.");
	}
}