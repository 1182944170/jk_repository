package com.rpframework.utils;

import java.util.Calendar;
import java.util.Date;

public class HourRule extends DateRule {
	
	/**
	 * @param start like 1830  -1不限制开始
	 * @param end like 2030   -1不限制结束
	 */
	public HourRule(int start, int end) {
		super(start, end);
	}

	@Override
	public int getFormat(Date d) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(d);
		return instance.get(Calendar.HOUR_OF_DAY);
	}
}
