package com.rpframework.website.edongwang.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EConfig {
	
	@Value("${edongwang.tokenkey}")
	public String tokenkey;
}
