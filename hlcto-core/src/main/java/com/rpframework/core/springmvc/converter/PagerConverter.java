package com.rpframework.core.springmvc.converter;

import org.springframework.core.convert.converter.Converter;

import com.rpframework.utils.Pager;
@SuppressWarnings("rawtypes")
public class PagerConverter implements Converter<String, Pager> {

	public Pager convert(String value) {
		return Pager.convertStringToPager(value);
	}
}
