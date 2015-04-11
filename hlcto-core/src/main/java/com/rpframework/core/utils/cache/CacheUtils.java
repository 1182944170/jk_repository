package com.rpframework.core.utils.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**  
 * title: CacheUtils.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2013-6-5 上午10:45:27 
 */  
public class CacheUtils {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	Map<String, CacheObj> cacheMap = null;
	
	private static CacheUtils cacheUtils;
	
	private void init() {
		cacheMap = new HashMap<String, CacheObj>();
	}
	
	public void reInit() {
		init();
	}
	
	public void reInit(String k) {
		CacheObj obj = get(k);
		
		if(obj != null) {
			obj.setV(null);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get2(String k) {
		CacheObj obj = get(k);
		if(obj != null) {
			return (T) obj;
		}
		return null;
	}
	
	public CacheObj get(String k) {
		if(!cacheMap.containsKey(k)) {
			log.info("cacheMap 不包含key:" + k);
			return null;
		}
		
		CacheObj cacheObj = cacheMap.get(k);
		cacheObj.getV();
		return cacheObj;
	}
	
	public void add(CacheObj o) {
		if(cacheMap.containsKey(o.k)) {
			log.info("cacheMap 已经包含key:" + o.v + ";现将覆盖!");
		}
		cacheMap.put(o.k, o);
	}
	
	private CacheUtils(){
		init();
	}
	
	/**  
	 * 描述
	 * @return  
	 */
	public static CacheUtils getIntance() {
		if(cacheUtils == null) {
			cacheUtils = new CacheUtils();
		}
		return cacheUtils;
	}
}