package com.rpframework.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.rpframework.core.mybatis.plugin.ClassFieldBuilder;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

public class BaseService implements IService {
	
	public static final double R = 6371.004; // 地球半径
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	
	private IDao dao;
	
	@SuppressWarnings("rawtypes")
	public Map packageMyBatisParam(Pager<?> pager) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("page", pager);
		return m;
	}
	
	protected IDao getIDao() {
		if(dao == null) {
			try {
				Field[] declaredFields = this.getClass().getDeclaredFields();
				for (Field field : declaredFields) {
					if(Modifier.isPublic(field.getModifiers()) || Modifier.isProtected(field.getModifiers())) { //按照约定 Service的Dao 是public的 或者 protected
						if(field.get(this) instanceof IDao) {
							this.dao = (IDao) field.get(this);
						}
					}
				}
				
				if(this.dao == null) {
					declaredFields = this.getClass().getSuperclass().getDeclaredFields();
					for (Field field : declaredFields) {
						if(Modifier.isPublic(field.getModifiers())) { //按照约定 Service的Dao 是public的 或者 protected
							if(field.get(this) instanceof IDao) {
								this.dao = (IDao) field.get(this);
							}
						}
					}
				}
				Assert.notNull(dao, "找不到dao属性!");
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
		
		return dao;
	}
	
	@Override
	public <T> T select(Object idObj) {
		return getIDao().select(idObj);
	}
	@Override
	public <T> boolean update(T t) {
		return getIDao().update(t);
	}
	@Override
	public boolean delete(Object idObj) {
		return getIDao().delete(idObj);
	}
	
	@Override
	public <T> boolean insert(T t) {
		return getIDao().insert(t);
	}

	@Override
	public <T> boolean upadteWithField(ClassFieldBuilder classFieldBuilder) {
		return getIDao().upadteWithField(classFieldBuilder);
	}

	@Override
	public <T> T selectForLock(Object idObj) {
		return getIDao().selectForLock(idObj);
	}
	/** 
     *  从一个以逗号分隔的字符串中解析成数组
     *  this method enter parameter is (String) and return (Integer[])
     * @param  
     * @version 2015.09.14 
     * @author zhangli 
     * 
     */
	public List<Integer> getIdsByStr(String s){
		List<Integer> i =new ArrayList<Integer>();
		if(StringUtils.isBlank(s)){
			return i;
		}
		if(s.indexOf(",")<0){
			i.add(Integer.valueOf(s));
			return i;
		}
		String[] a = s.split(",");
		for(String l : a){
			i.add(Integer.valueOf(l));
		}
		return i;
	}
	
	public List<String> getStringByStr(String s){
		List<String> str =new ArrayList<String>();
		if(StringUtils.isBlank(s)){
			return str;
		}
		if(s.indexOf(",")<0){
			str.add(s);
			return str;
		}
		String[] a = s.split(",");
		for(String l : a){
			str.add(l);
		}
		return str;
	}
	
	 /** 
     * 从某个以逗号分隔的字符串中移除某个元素 
     * this method enter parameter is (String,String) and return (String)
     * @param  
     * @version 2015.09.14 
     * @author zhangli 
     */  
	public String getStrRemoveById(String id,String ids){
		StringBuilder str = new StringBuilder();
		if(ids.indexOf(",")<0){
			str.append(id.equals(ids) ? "" : ids);
			return str.toString();
		}else{
			String[] arr = ids.split(",");
			for(int i = 0 ; i < arr.length; i++){
				str = id.equals(arr[i]) ? 
						str.toString().equals("") ? str.append("") : str.append("") 
								: str.toString().equals("") ?str.append(arr[i]) :str.append(","+arr[i]);
			}
			return str.toString();
		}
	}
	
	/** 
     * 判断一个字符串是否存在一串字符串中
     * this method enter parameter is (String,String) and return boolean
     * @param  
     * @version 2015.09.15 
     * @author zhangli 
	 * @param citycode 
     * 不存在返回true 存在返回false
     */ 
	public boolean isExist(String id,String ids) {
		boolean b = true;
		if(StringUtils.isBlank(ids)){
			return !b;
		}
		if(ids.indexOf(",")<0 && StringUtils.isBlank(ids)){
			return id.equals(ids) ? b : !b;
		}else{
			String[] arr = ids.split(",");
			for(int i = 0 ; i < arr.length; i++){
				 if(id.equals(arr[i])){
					 return !b;
				 }
			}
			return b;
		}
	}
	
	 /** 
     * pager 排序
     * 根据 页面搜索顺序，价格时间升序降序，地址的code 排序
     * this method enter parameter is (Pager,Integer,Integer,String) and return (Pager)
     * @param  
     * @version 2015.09.15 
     * @author zhangli 
	 * @param citycode 
     * 
     */  
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Pager getSortByCol(Pager pager, Integer searchType, Integer sortType, String citycode) {
		if(NumberUtils.isValid(searchType)){
			switch (searchType) {
				case 1: pager.getSearchMap().put("createTime", "createTime"); break;//默认
				case 2: pager.getSearchMap().put("price", "price");//价格 有升降
						if(sortType == 1) pager.getSearchMap().put("sortType", String.valueOf(sortType));
						if(sortType == 0) pager.getSearchMap().put("sortType", null);
						break;
				case 3:	pager.getSearchMap().put("createTime", "createTime"); //时间 有升降
						if(sortType == 1) pager.getSearchMap().put("sortType", String.valueOf(sortType));
						if(sortType == 0) pager.getSearchMap().put("sortType", null);
						break;
				case 4: pager.getSearchMap().put("createTime", "createTime"); break;//综合
			default:
			break;
			}
		}
		if(!"".equals(citycode)) pager.getSearchMap().put("citycode", citycode);
		return pager;
	}
	 /** 
     * 计算两个时间差startTime endTime
     * this method enter parameter is (String,String) and return (int)
     * @param  
     * @version 2015.09.14 
     * @author zhangli 
     */
	public Integer betweenDate(String startTime, String endTime) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date d1 = sdf.parse(startTime);
	        Date d2 = sdf.parse(endTime);
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(d1);
	        long time1 = cal.getTimeInMillis();
	        cal.setTime(d2);
	        long time2 = cal.getTimeInMillis();
	        Long between_days = (time2 - time1) / (1000 * 3600 * 24);
	        return between_days.intValue();
	    } catch (java.text.ParseException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/** 
     * 传入db的图片对象  po的图片对象  删除的对象 解析为最终数据
     * this method enter parameter is (String,String,String) and return (String)
     * @param  
     * @version 2015.09.22 
     * @author zhangli 
     */
	public String getPhotoArr(String dbStr, String poStr, String arrays) {
		StringBuilder sbr = new StringBuilder();
		@SuppressWarnings("rawtypes")
		List list = null;
		String[] newStr = null;
		String[] newPoStr=null;
		if("".equals(dbStr) && "".equals(poStr) && "".equals(arrays)) return "";
		if("".equals(arrays)){
			return (!"".equals(dbStr) ? sbr.append(dbStr+(!"".equals(poStr) ? ","+poStr : "")) : sbr.append(!"".equals(poStr) ? poStr : "")).toString();
		}
		list = getArrays(arrays,",");
		newStr = getStringArrayByStr(dbStr, ",");
		newPoStr = getStringArrayByStr(poStr, ",");
		int n=0;
		for(int i = 0;i<newStr.length;i++){
			boolean flag = true;
			for(int j =0;j<list.size();j++){
				if(list.get(j).toString().equals(String.valueOf(i))){
					if(n<newPoStr.length){
						if(!"".equals(newPoStr[n])){
							sbr = !"".equals(sbr.toString()) ? sbr.append(","+newPoStr[n]) : sbr.append(newPoStr[n]);
							n++;
						}
					}
					flag = !flag;
					break;
				}
			}
			if(flag){
				if(!"".equals(newStr[i])){
					sbr = "".equals(sbr.toString()) ? sbr.append(newStr[i]) : sbr.append(","+newStr[i]);
				}
			}
		}
		for(int i = 0 ; i< newPoStr.length;i++){
			if(n<=i){
				if(!"".equals(newPoStr[i]))
				sbr = "".equals(sbr.toString()) ? sbr.append(newPoStr[i]) : sbr.append(","+newPoStr[i]);
			}
		}
		return sbr.toString();
	}
	// 对页面上的多次删除点击 进行排序去重复处理
	/** 
     * 将一个字符串以特定符号隔开 进行排序去重复处理 返回List
     * this method enter parameter is (String str,String identifier) and return (List list)
     * @param  
     * @version 2015.09.22 
     * @author zhangli 
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getArrays(String string, String identifier) {
		String[] arr = getStringArrayByStr(string,identifier);
		if(string.indexOf(identifier)>0){
			Set s = new HashSet();
			for(int i=0;i<arr.length;i++){
				s.add(arr[i]);
			}
			List<String> l = new ArrayList<String>();  
			l.addAll(s);  
			Collections.sort(l);
			return l;
		}
		List l = new ArrayList();
		for(int i=0;i<arr.length;i++){
			l.add(arr[i]);
		}
		return l;
	}
	/** 
     * 将任何数组对象转为List
     * this method enter parameter is (Object[] obj) and return (List list)
     * @param  
     * @version 2015.09.22 
     * @author zhangli 
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getListByObjArray(Object[] obj){
		List list =new ArrayList();
		for(int i=0;i<obj.length;i++){
			list.add(obj[0]);
		}
		return list;
	}


	/** 
     * 将一个字符串以特定符号隔开 转为String数组 如果字符串为空则返回arr[""]
     * this method enter parameter is (String str,String identifier) and return (String[] array)
     * @param  
     * @version 2015.09.22 
     * @author zhangli 
     */
	public String[] getStringArrayByStr(String arrays,String identifier) {
		if(arrays.indexOf(identifier)<0){
			String[] arr =new String[1];
			arr[0] = "".equals(arrays) ? "" : arrays;
			return arr;
		}
		return arrays.split(identifier);
	}
	
	/** 
     * 将角度转化为弧度 
     * @param angle 角度 
     * @return  弧度 
     */  
    public static double changeToRad(double angle) {  
        return angle / 180 * Math.PI;  
    } 

	/**
	 * 根据两个经纬度计算距离
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
	   double x = changeToRad(lat1);  
       double y = changeToRad(lng1);  
       double a = changeToRad(lat2);  
       double b = changeToRad(lng2);  
       double rad = Math.acos(Math.cos(y) * Math.cos(b) * Math.cos(x - a) + Math.sin(y) * Math.sin(b));  
       if (rad > Math.PI)  
           rad = Math.PI * 2 - rad;  
       return R * rad;  
	}
	/**
	 * 对urlList处理
	 * @return urlList
	 */
	public String UrlReplace(String urlList) {
		try {
			
			if(urlList.indexOf(",,")>0){
				urlList = urlList.replaceAll(",,", ",");
			}
			if(urlList.indexOf("//")>0){
				urlList = urlList.replaceAll("//", "/");
			}
			if(urlList.indexOf(",")==0)urlList = urlList.substring(1, urlList.length());
			if(urlList.indexOf(",")==urlList.length())urlList = urlList.substring(0, urlList.length()-1);
			return urlList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return urlList;
	}
}
