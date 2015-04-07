package com.rpframework.utils;

import java.util.Calendar;
import java.util.Date;

public class MinRule extends DateRule {
	
	/**
	 * @param start like 18  -1不限制开始
	 * @param end like 20   -1不限制结束
	 */
	public MinRule(int start, int end) {
		super(start, end);
	}

	@Override
	public int getFormat(Date d) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(d);
		return instance.get(Calendar.MINUTE);
	}
}
