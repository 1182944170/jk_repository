package com.rpframework.module.common.utils.cache;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.cache.CacheObj;
import com.rpframework.module.common.domain.City;
import com.rpframework.module.common.domain.Country;
import com.rpframework.module.common.domain.Province;
import com.rpframework.module.common.service.CityService;
import com.rpframework.module.common.service.CountryService;
import com.rpframework.module.common.service.ProvinceService;
import com.rpframework.utils.CollectionUtils;

/**  
 * title: CountryCache.java 
 * 省镇区的数据缓存
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2013-6-5 上午11:18:50 
 */  
public class CountryCache extends CacheObj {
	public static final String k = "_country_cache_";
	public Map<String, Country> countryMap = new LinkedHashMap<String, Country>();
	public Map<String, List<Province>> provinceMap = new LinkedHashMap<String, List<Province>>();
	public Map<String, List<City>> cityMap = new LinkedHashMap<String, List<City>>();
	public JsonArray countrys = null;
	
	public CountryCache() {
		super(300 * 60L, k);
	}
	
	@Override
	public Object load() {
		countryMap.clear();
		provinceMap.clear();
		cityMap.clear();
		countrys = new JsonArray();
		
		CountryService countryService = SpringUtils.getBean(CountryService.class);
		ProvinceService provinceService = SpringUtils.getBean(ProvinceService.class);
		CityService cityService = SpringUtils.getBean(CityService.class);
		
		List<City> tempCityList = null;
		List<City> cityList = cityService.cityDao.queryAll();
		for (City city : cityList) {
			if(cityMap.containsKey(city.getProvinceCode())) {
				tempCityList = cityMap.get(city.getProvinceCode());
			} else {
				tempCityList = new ArrayList<City>();
				cityMap.put(city.getProvinceCode(), tempCityList);
			}
			
			tempCityList.add(city);
		}
		
		List<Province> provinceList = provinceService.provinceDao.queryAll();
		List<Province> tempProvinceList = null;
		for (Province province : provinceList) {
			if(provinceMap.containsKey(province.getCountryCode())) {
				tempProvinceList = provinceMap.get(province.getCountryCode());
			} else {
				tempProvinceList = new ArrayList<Province>();
				provinceMap.put(province.getCountryCode(), tempProvinceList);
			}
			tempProvinceList.add(province);
		}
		
		List<Country> countryList = countryService.countryDao.queryAll();
		for (Country country : countryList) {
			countryMap.put(country.getCode(), country);
			
			JsonObject jsonObject = new JsonObject();
			JsonArray provinces = new JsonArray();
			jsonObject.addProperty("code", country.getCode());
			jsonObject.addProperty("name", country.getName());
			jsonObject.add("provinces", provinces);
			
			List<Province> pList = provinceMap.get(country.getCode());
			if(CollectionUtils.isNotEmpty(pList)) {
				for (Province province : pList) {
					JsonObject provinceJson = new JsonObject();
					JsonArray citys = new JsonArray();
					provinceJson.addProperty("code", province.getCode());
					provinceJson.addProperty("name", province.getName());
					provinceJson.add("citys", citys);
					
					List<City> cityList2 = cityMap.get(province.getCode());
					if(CollectionUtils.isNotEmpty(cityList2)) {
						for (City city : cityList2) {
							JsonObject cityJson = new JsonObject();
							cityJson.addProperty("code", city.getCode());
							cityJson.addProperty("name", city.getName());
							
							citys.add(cityJson);
						}
					}
					
					provinces.add(provinceJson);
				}
			}
			
			countrys.add(jsonObject);
		}
		return new Object();
	}

}
