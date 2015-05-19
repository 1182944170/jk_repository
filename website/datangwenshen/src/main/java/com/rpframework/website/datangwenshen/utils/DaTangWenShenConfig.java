package com.rpframework.website.datangwenshen.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DaTangWenShenConfig {
	@Value("${datangwenshen.style}")
	public String STYLE;
}
