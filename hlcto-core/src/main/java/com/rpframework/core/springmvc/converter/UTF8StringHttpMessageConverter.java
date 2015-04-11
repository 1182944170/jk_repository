package com.rpframework.core.springmvc.converter;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

/**
 * 
 * title: UTF8StringHttpMessageConverter.java 用于处理中文乱码问题： Spring bug In
 * StringHttpMessageConverter, the default char set is ISO-8859-1(西欧字符集)
 * 
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0
 * @created 2015年4月11日 下午8:04:44
 */
public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {
	private static final MediaType UTF8 = new MediaType("text", "plain",
			Charset.forName("UTF-8"));
	  
    @Override  
    protected MediaType getDefaultContentType(String dumy) {  
        return UTF8;  
    }
}
