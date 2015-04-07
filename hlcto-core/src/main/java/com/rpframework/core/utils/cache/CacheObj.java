package com.rpframework.core.utils.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class CacheObj {
	public static final long DEFAULT_T = 5 * 60;
	protected final Log log = LogFactory.getLog(this.getClass());
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected String k;
	protected Object v;
	protected long intervalSecond;
	public abstract Object load();

	private long lastExeTime = 0L;
	public CacheObj(String k){
		this(0l, k);
	}
	/**  
	* 构造函数
	* @param intervalSecond 每隔多少时间加载一次,<=0默认为一直不重新加载  
	*/ 
	public CacheObj(long intervalSecond, String k){
		this.intervalSecond = intervalSecond;
		this.k = k;
	}
	public void setV(Object V) {
		this.v = V;
		lastExeTime = System.currentTimeMillis() / 1000l;
	}
	
	/**  
	 * 设置失效，下次将重新加载
	 */
	public void setInvalid() {
		setV(null);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getV() {
		if(v == null) {
			v = load();
		} else if(intervalSecond > 0){
			long now = System.currentTimeMillis() / 1000l;
			if(now - lastExeTime >= intervalSecond) {
				Object _temp = load();
				if(_temp != null) {
					v = _temp;
				} else {
					log.error("load is null.k:" + k);
				}
				lastExeTime = now;
			}
		}
		return (T) v;
		
	}
}