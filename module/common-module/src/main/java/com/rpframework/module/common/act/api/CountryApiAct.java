package com.rpframework.module.common.act.api;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.utils.cache.CountryCache;

@Controller
@RequestMapping("/api/common/country")
public class CountryApiAct extends CommonBaseAct {
	
	@RequestMapping
	public @ResponseBody JsonArray execute() throws ParserException, InterruptedException{
		CountryCache countryCache = CacheUtils.getIntance().get2(CountryCache.k);
		return countryCache.countrys;
	}
}