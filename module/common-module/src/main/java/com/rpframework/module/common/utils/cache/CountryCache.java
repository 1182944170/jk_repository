package com.rpframework.module.common.utils.cache;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.core.utils.cache.CacheObj;
import com.rpframework.module.common.domain.City;
import com.rpframework.module.common.domain.County;
import com.rpframework.module.common.domain.Province;
import com.rpframework.module.common.service.CityService;
import com.rpframework.module.common.service.CountyService;
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
	public Map<String, Province> provinceMap = new LinkedHashMap<String, Province>();
	public Map<String, List<City>> cityMap = new LinkedHashMap<String, List<City>>();
	public Map<String, List<County>> countyMap = new LinkedHashMap<String, List<County>>();
	public JsonArray countrys = null;
	public Gson gson = new Gson();
	
	public JsonObject findByCountyCode(String countyCode) {
		for(Map.Entry<String, List<County>> entry : countyMap.entrySet()) {
			List<County> list = entry.getValue();
			for (County county : list) {
				if(StringUtils.equals(county.getCode(), countyCode)) { //find
					
					String cityCode = county.getProvinceCode();
					JsonObject obj = findByCityCode(cityCode);
					obj.add("county", gson.toJsonTree(county));
					return obj;
				}
			}
		}
		
		return null;
	}
	
	public JsonObject findByCityCode(String cityCode) {
		for(Map.Entry<String, List<City>> entry : cityMap.entrySet()) {
			List<City> list = entry.getValue();
			for (City city : list) {
				if(StringUtils.equals(city.getCode(), cityCode)) { //find
					Province province = provinceMap.get(city.getCountryCode());
					JsonObject obj = new JsonObject();
					obj.add("city", gson.toJsonTree(city));
					obj.add("province", gson.toJsonTree(province));
					return obj;
				}
			}
		}
		
		return null;
	}
	
	public CountryCache() {
		super(300 * 60L, k);
	}
	
	@Override
	public Object load() {
		
		Map<String, Province> provinceMap = new LinkedHashMap<String, Province>();
		Map<String, List<City>> cityMap = new LinkedHashMap<String, List<City>>();
		Map<String, List<County>> countyMap = new LinkedHashMap<String, List<County>>();
		JsonArray countrys = new JsonArray();

		ProvinceService provinceService = SpringUtils.getBean(ProvinceService.class);
		CityService cityService = SpringUtils.getBean(CityService.class);
		CountyService countyService = SpringUtils.getBean(CountyService.class);
		
		List<County> tempCityList = null;
		List<County> cityList = countyService.countyDao.queryAll();
		for (County city : cityList) {
			if(countyMap.containsKey(city.getProvinceCode())) {
				tempCityList = countyMap.get(city.getProvinceCode());
			} else {
				tempCityList = new ArrayList<County>();
				countyMap.put(city.getProvinceCode(), tempCityList);
			}
			
			tempCityList.add(city);
		}
		
		List<City> provinceList = cityService.cityDao.queryAll();
		List<City> tempProvinceList = null;
		for (City province : provinceList) {
			if(cityMap.containsKey(province.getCountryCode())) {
				tempProvinceList = cityMap.get(province.getCountryCode());
			} else {
				tempProvinceList = new ArrayList<City>();
				cityMap.put(province.getCountryCode(), tempProvinceList);
			}
			tempProvinceList.add(province);
		}
		
		List<Province> countryList = provinceService.provinceDao.queryAll();
		for (Province country : countryList) {
			provinceMap.put(country.getCode(), country);
			
			JsonObject jsonObject = new JsonObject();
			JsonArray citys = new JsonArray();
			jsonObject.addProperty("code", country.getCode());
			jsonObject.addProperty("name", country.getName());
			jsonObject.add("citys", citys);
			
			List<City> pList = cityMap.get(country.getCode());
			if(CollectionUtils.isNotEmpty(pList)) {
				for (City province : pList) {
					JsonObject provinceJson = new JsonObject();
					JsonArray countys = new JsonArray();
					provinceJson.addProperty("code", province.getCode());
					provinceJson.addProperty("name", province.getName());
					provinceJson.add("countys", countys);
					
					List<County> cityList2 = countyMap.get(province.getCode());
					if(CollectionUtils.isNotEmpty(cityList2)) {
						for (County city : cityList2) {
							JsonObject cityJson = new JsonObject();
							cityJson.addProperty("code", city.getCode());
							cityJson.addProperty("name", city.getName());
							
							countys.add(cityJson);
						}
					}
					
					citys.add(provinceJson);
				}
			}
			
			countrys.add(jsonObject);
		}
		
		this.provinceMap.clear();
		this.cityMap.clear();
		this.countyMap.clear();
		
		this.provinceMap = provinceMap;
		this.cityMap = cityMap;
		this.countyMap = countyMap;
		this.countrys = countrys;
		return new Object();
	}

}
