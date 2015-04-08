package com.rpframework.website.xtexam.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("xTExamConfig")
public class XTExamConfig {
	
	@Value("${xtexam.tokenkey}")
	public String tokenkey;
}
