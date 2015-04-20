package com.rpframework.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonUtils {
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
}
