package com.rpframework.utils;

import java.util.Calendar;
import java.util.Date;

public class HourAndMinRule extends DateRule {
	
	/**
	 * @param start like 1830  -1不限制开始
	 * @param end like 2030   -1不限制结束
	 */
	public HourAndMinRule(int start, int end) {
		super(start, end);
	}

	@Override
	public int getFormat(Date d) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(d);
		
		int h = instance.get(Calendar.HOUR_OF_DAY);
		int m = instance.get(Calendar.MINUTE);
		String f = h + "" + m;
		return NumberUtils.parseInt(f);
	}
}
