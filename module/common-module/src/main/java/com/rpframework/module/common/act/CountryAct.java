package com.rpframework.module.common.act;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.utils.cache.CountryCache;

@Controller
@RequestMapping("/common/country")
public class CountryAct extends CommonBaseAct {
	
	@RequestMapping
	public @ResponseBody JsonArray execute() throws ParserException, InterruptedException{
		CountryCache countryCache = CacheUtils.getIntance().get2(CountryCache.k);
		return countryCache.countrys;
	}
}