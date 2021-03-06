package com.rpframework.core.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rpframework.core.freemarker.BaseRegistFreemarker;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.utils.CollectionUtils;

@Component("dicSetting")
public class DictionarySettingUtils extends BaseRegistFreemarker {
	
	private static Log log = LogFactory.getLog(DictionarySettingUtils.class);
	
	private static Map<String, String> allConstantsMap = new LinkedHashMap<String, String>();
	private static List<KVObj> allConstantsList = new ArrayList<KVObj>();
	public static Map<String, Object> allSettings = new LinkedHashMap<String, Object>();
	
	/**
	 * @param key like all.a.b
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getParameterListWithOutLog(String key) {
		if(!allSettings.containsKey(key)) {
			return null;
		}
		
		Object o = allSettings.get(key);
		if(!(o instanceof List)) {
			log.warn("getParameterList is not a List;Ϊkey:" + key + ";List:" + o);
			return null;
		}
		
		return (List<String>)o;
	}
	
	/**
	 * @param key like all.a.b
	 * @return
	 */
	public static List<String> getParameterList(String key) {
		List<String> obj = getParameterListWithOutLog(key);
		if(obj != null) {
			return (List<String>) obj;
		}
		
		return obj;
	}
	
	/**
	 * @param settings eg:{ftp,ftp1} --> Object Map.toString()
	 * 					  {ftp,ftp1,port} --> Object String
	 * @return String
	 */
	public static String getParameterValue(String... settings){
		return (String)getParameter(settings);
	}
	
	public static String getParameterValue(String setting){
		List<String> l;
		if((l = getParameterListWithOutLog(setting)) != null) {
			return l.get(0);
		}
		return (String)getParameterValue(StringUtils.split(setting, "."));
	}
	
	/**
	 * @param settings eg:{ftp,ftp1} --> Object Map
	 * 					  {ftp,ftp1,port} --> Object String
	 * @return Object
	 */
	@SuppressWarnings("rawtypes")
	public static Object getParameter(String... settings) {
		if (settings == null || settings.length < 1)
			return null;
		if (settings.length == 1)
			return allSettings.get(settings[0]);
		Map tempMap = (Map) allSettings.get(settings[0]);
		for (int i = 1; i < settings.length - 1; i++) {
			
			if(tempMap == null){
				//System.out.println();
				return null;
			}
			tempMap = (Map) tempMap.get(settings[i]);
			if(tempMap == null) {
				return null;
			}
		}
		
		if(tempMap == null) {
			return null;
		}
		return tempMap.get(settings[settings.length - 1]);
	}
	
	/**
	 * @param settings eg:{ftp,ftp1} --> Object Map
	 * 					  {ftp,ftp1,port} --> throw exception
	 * @return Map<String, String>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameterMap(String... settings){
		return (Map<String, String>) getParameter(settings);
	}
	
	public static Map<String, String> getParameterMap(String setting){
		return (Map<String, String>) getParameterMap(StringUtils.split(setting, "."));
	}
	
	public static void reInit() {
		if (CollectionUtils.isEmpty(allConstantsMap) && CollectionUtils.isEmpty(allConstantsList)) {
			throw new IllegalAccessError("allConstants collection is empty.");
		}
		allSettings.clear();
		
		initList();
		initMap();
	}
	
	@SuppressWarnings("unchecked")
	private static void initList() {
		if(CollectionUtils.isEmpty(allConstantsList)) {
			return;
		}
		
		for (int i = 0; i < allConstantsList.size(); i++) {
			KVObj ss = allConstantsList.get(i);
			List<String> list = null;
			String key = ss.k;
			if (allSettings.containsKey(key)) {
				list = (List<String>) allSettings.get(key);
			} else {
				list = new ArrayList<String>();
				allSettings.put(key, list);
			}

			list.add((String) ss.v);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void initMap() {
		if(CollectionUtils.isEmpty(allConstantsMap)) {
			return;
		}
		for (Map.Entry<String, String> set : allConstantsMap.entrySet()) {
			String variable = set.getKey();
			String value = set.getValue();
			if (StringUtils.isBlank(variable)) {
				continue;
			}

			String[] keys = StringUtils.split(variable, '.');
			Map<String, Object> supMap = null;
			Map<String, Object> subMap = null;
			Object obj = null;
			int len = keys.length - 1;
			if (len < 1) { // only root node
				allSettings.put(variable, value);
				continue;
			}

			supMap = (Map<String, Object>) allSettings.get(keys[0]);
			if (supMap == null) { // new root node
				supMap = new LinkedHashMap<String, Object>();
				allSettings.put(keys[0], supMap);
			}

			for (int i = 1; i < len; i++) {
				String key = keys[i];
				obj = supMap.get(key);

				if (obj != null && obj instanceof Map) {
					supMap = (Map) obj;
				} else {
					subMap = new LinkedHashMap<String, Object>();
					// subMap = new LinkedHashMap<String, Object>();
					supMap.put(key, subMap);
					supMap = subMap;
				}
			}
			supMap.put(keys[len], value);
		}
	}
	
	public static void setAllConstantsMap(Map<String, String> allConstants){
		DictionarySettingUtils.allConstantsMap = allConstants;
	}
	
	public static void setAllConstantsList(List<KVObj> allConstantsList){
		DictionarySettingUtils.allConstantsList = allConstantsList;
	}
	
	/**
	 * 方法 已经过时了，
	 * 使用 like
	 * 	Object parameter = DictionarySettingUtils.getParameter("scoreRule", "cfg");
	 *	return gson.toJsonTree(parameter);
	 * @return
	 */
	@Deprecated
	public static JsonArray getMapJsonArrayByKey(String key) {
		Map<String, String> map = DictionarySettingUtils.getParameterMap(key);
		JsonArray array = new JsonArray();
		
		if(CollectionUtils.isNotEmpty(map)) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				JsonObject json = new JsonObject();
				json.addProperty("id", entry.getKey());
				json.addProperty("value", entry.getValue());
				array.add(json);
			}
		}
		return array;
	}
}