package com.rpframework.website.luoluo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HConfig {
	
	@Value("${edongwang.tokenkey}")
	public String tokenkey;
}
