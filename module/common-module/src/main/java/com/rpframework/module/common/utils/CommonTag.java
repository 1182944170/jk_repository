package com.rpframework.module.common.utils;

import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.rpframework.core.freemarker.BaseRegistFreemarker;
import com.rpframework.core.utils.GsonUtils;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.utils.cache.CountryCache;

@Component("commonTag")
public class CommonTag extends BaseRegistFreemarker {
	
	public static JsonObject getCountyPathJson(String countyCode) {
		CountryCache cache = CacheUtils.getIntance().get2(CountryCache.k);
		JsonObject obj = cache.findByCountyCode(countyCode);
		return obj;
	}
	
	public static String getCountyPath(String countyCode) {
		JsonObject obj = getCountyPathJson(countyCode);
		if(obj == null) {
			return "--";
		} else {
			JsonObject county = obj.getAsJsonObject("county");
			JsonObject city = obj.getAsJsonObject("city");
			JsonObject province = obj.getAsJsonObject("province");
			
			return GsonUtils.getString(province, "name") + "/" + GsonUtils.getString(city, "name") + "/" + GsonUtils.getString(county, "name") ;
		}
	}
}
