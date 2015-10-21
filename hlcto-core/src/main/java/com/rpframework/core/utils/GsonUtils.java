package com.rpframework.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.core.freemarker.BaseRegistFreemarker;
@Component("gsonUtils")
public class GsonUtils extends BaseRegistFreemarker {
	public static long getLong(JsonObject jsonObject, String key, Long defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
			}
		
		return jsonElement.getAsLong();
	}
	
	public static Long getLong(JsonObject jsonObject, String key) {
		return getLong(jsonObject, key, 0L);
	}
	
	public static int getInt(JsonObject jsonObject, String key, Integer defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsInt();
	}
	
	public static int getInt(JsonObject jsonObject, String key) {
		return getInt(jsonObject, key, 0);
	}
	
	public static String getString(JsonObject jsonObject, String key, String defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsString();
	}
	public static String getJsonValue(JsonObject jsonObject, String key) {
		return getJsonValue(jsonObject, key, "");
	}
	public static String getJsonValue(JsonObject jsonObject, String key, String defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.toString();
	}
	
	public static String getString(JsonObject jsonObject, String key) {
		return getString(jsonObject, key, "");
	}
	
	public static Double getDouble(JsonObject jsonObject, String key, Double defaultValue) {
		JsonElement jsonElement = jsonObject.get(key);
		if(jsonElement == null) {
			return defaultValue;
		}
		
		return jsonElement.getAsDouble();
	}
	
	public static Double getDouble(JsonObject jsonObject, String key) {
		return getDouble(jsonObject, key, 0D);
	}
	
	public static List<String> array2List(String string) {
		
		if(StringUtils.isBlank(string)) {
			return null;
		}
		JsonArray array = new JsonParser().parse(string).getAsJsonArray();
		List<String> list = new ArrayList<String>();
		
		for (JsonElement je : array) {
			list.add(je.getAsString());
		}
		
		return list;
	}
	
	public static String getDeepValue(String jsonString, String deepKey) {
		return getDeepValue(jsonString, deepKey, "");
	}
	
	public static String getDeepValue(String jsonString, String deepKey, String defaultValue) {
		if(StringUtils.isBlank(jsonString)) {
			return defaultValue;
		}
		
		JsonElement je = new JsonParser().parse(jsonString);
		return getDeepValueByJson(je, deepKey, defaultValue);
	}
	
	public static String getDeepValueByJson(JsonElement je, String deepKey, String defaultValue) {
		String[] split = StringUtils.split(deepKey, ".");
		
		return getDeepValue(je, split, defaultValue);
	}
	
	public static String getDeepValueByJson(JsonElement je, String deepKey) {
		return getDeepValueByJson(je, deepKey, "");
	}
	
	public static String getDeepValue(JsonElement je, String[] split, String defaultValue) {
		if(split.length < 2) {
			if(je.isJsonObject()) {
				return getString(je.getAsJsonObject(), split[0], defaultValue);
			}
		}
		
		JsonElement tempJE = je;
		
		for (int i=0 ;i<split.length;i++) {
			String string = split[i];
			
			if(tempJE.isJsonObject()) {
				JsonObject asJsonObject = tempJE.getAsJsonObject();
				if(asJsonObject.has(string)) {
					tempJE = asJsonObject.get(string);
				} else {
					return defaultValue;
				}
			}
			
			if(i == split.length - 1) {
				if(tempJE == null) {
					return defaultValue;
				} else {
					if(tempJE.isJsonPrimitive()) {
						if(tempJE.getAsJsonPrimitive().isString()) {
							return tempJE.getAsJsonPrimitive().getAsString();
						} else if(tempJE.getAsJsonPrimitive().isNumber()) {
							return tempJE.getAsJsonPrimitive().getAsNumber().toString();
						} else if(tempJE.getAsJsonPrimitive().isBoolean()) {
							return String.valueOf(tempJE.getAsJsonPrimitive().getAsBoolean());
						}
						
					}
					return tempJE.toString();
				}
			}
		}
		
		return defaultValue;
	}

	public static void main(String[] args) {
		System.out.println(1&3);
		System.out.println(2&3);
		System.out.println(4&3);
	}
}
