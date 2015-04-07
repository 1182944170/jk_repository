package com.rpframework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * 关于json与Object的帮助类
 * 
 * @author rplees
 * @date 2013-02-21
 */
public class JsonObjUtils {

	public static HashMap<String, Class<?>> classMap;
	public static Class<?> getClass(String className) {
		if (classMap == null) {
//			System.out.println("创建一次");
			classMap = new HashMap<String, Class<?>>();
		}
		if (classMap.containsKey(className)) {
			// System.out.println("返回已有");
			return classMap.get(className);
		} else {
			try {
				Class<?> userClass = Class.forName(className);
				classMap.put(className, userClass);
				return userClass;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 *  List convert to JSONArray
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static JSONArray listToJsonArr(List<?> list) throws Exception {
		JSONArray jsonArray = new JSONArray();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Object obj : list) {
				if(obj instanceof String || obj instanceof Number || obj instanceof Character) {
					jsonArray.put(obj.toString());
				} else {
					jsonArray.put(parseObjToJson(obj));
				}
			}
		}
		
		return jsonArray;
	}
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws Exception
	 */
	public static JSONObject parseObjToJson(Object obj) throws Exception {
		if (null == obj)
			return null;
		
		JSONObject reObject = new JSONObject();
		for(Class<?> clazz = obj.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			
//			Class<?> userClass = getClass(obj.getClass().getName());// 加载类
			Class<?> userClass = clazz;
			Field[] fields = userClass.getDeclaredFields();
			String fdname = null;
			Method metd = null;
			for (Field field : fields) {
				if ( (field.getModifiers() & Modifier.STATIC) != 0
						|| (field.getModifiers() & Modifier.FINAL) != 0 )
					continue;
				fdname = field.getName();
				metd = userClass.getMethod("get" + change(fdname), null);
				Object name = metd.invoke(obj, null);
				if (name != null) {
					reObject.put(fdname, name);
				} else {
					reObject.put(fdname, "");
				}
			}
		}
		return reObject;
	}

	private static String change(String src) {
		if (src != null) {
			StringBuffer sb = new StringBuffer(src);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		} else {
			return null;
		}
	}

	
	public static <T> List<T> strings2Clazzs(List<String> drList, Class<?> clazz) {
		List<T> list = null;
		if(CollectionUtils.isNotEmpty(drList)) {
			list = new ArrayList<T>();
			T t = null;
			for (String s : drList) {
				t = JSON2Class(s, clazz);
				list.add(t);
			}
		}
		return list;
	}
	
	/**  
	 * 描述
	 * @param arr arr里的元素为JSONObject
	 * @param clazz
	 * @return  
	 * @throws JSONException 
	 */
	public static <T> List<T> jsonArray2Clazzs(JSONArray arr, Class<?> clazz) throws JSONException {
		List<T> list = null;
		if(arr != null && arr.length() > 0) {
			list = new ArrayList<T>();
			T t = null;
			for (int i = 0; i < arr.length(); i++) {
				t = JSON2Class(arr.getJSONObject(i), clazz);
				list.add(t);
			}
		}
		return list;
	}
	
	public static <T> T JSON2Class(JSONObject json, Class<?> clazz) {
		return JSON2Class(json.toString(), clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T JSON2Class(String jsonString, Class<?> clazz) {
		Gson gson = new Gson();
		return (T) gson.fromJson(jsonString, clazz);
	}
}
