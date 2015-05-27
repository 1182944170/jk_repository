package com.rpframework.module.common.act.api;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.rpframework.core.utils.GsonUtils;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.utils.cache.CountryCache;

@Controller
@RequestMapping("/api/cfg/common/country")
public class CountryApiAct extends CommonBaseAct {
	
	@RequestMapping
	public @ResponseBody JsonElement execute(@RequestParam(value="provinceCode", required=false) String provinceCode) throws ParserException, InterruptedException{
		CountryCache countryCache = CacheUtils.getIntance().get2(CountryCache.k);
		if(StringUtils.isNotBlank(provinceCode)) {
			for(JsonElement json : countryCache.countrys) {
				String provinceIdCache = GsonUtils.getString(json.getAsJsonObject(), "code");
				if(StringUtils.equals(provinceCode, provinceIdCache)) {
					return json;
				}
			}
			
			throw new IllegalArgumentException("找不到provinceId的缓存数据.");
		}
		return countryCache.countrys;
	}
}