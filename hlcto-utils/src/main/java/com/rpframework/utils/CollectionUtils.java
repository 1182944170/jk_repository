package com.rpframework.utils ;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
/**
 * @author <a href="mailto:rplees.i.ly@gmail.com">rplees</a>
 * date 2010-04-06
 * {@code} 一些常用的集合有关的帮助方法
 */
public class CollectionUtils {
	
	/** 判断集合是否为空
	 * @param collection
	 * @return true为空，false不为空
	 */
	public static boolean isEmpty(Collection<?> collection){
		return collection == null || collection.isEmpty();
	}
	/**
	 * 判断Map集合是否为空
	 * @param m
	 * @return true为空，false不为空
	 */ 
	public static boolean isEmpty(Map<?, ?> m){
		return m == null || m.size() < 1;
	}
	
	public static boolean isEmpty(Object[] o) {
		return o == null || o.length < 1;
	}
	
	public static boolean isNotEmpty(Object[] o){
		return !isEmpty(o);
	}
	/** 判断集合是否不为空
	 * @param collection
	 * @return true为非空，false为空
	 */
	public static boolean isNotEmpty(Map<?, ?> m){
		return m != null && m.size() > 0;
	}
	/**
	 * 判断Map集合是否不为空
	 * @param m
	 * @return true为非空，false为空
	 */
	public static boolean isNotEmpty(Collection<?> coll){
		return coll != null && !coll.isEmpty();
	}
	
	/**
	 * 将集合中的元素压平 1,2,3 
	 * @param coll
	 * @return
	 */
	public static String paserIdsListToString(Collection<?> coll){
		StringBuffer sb = new StringBuffer();
		if(!coll.isEmpty()){
			for(Iterator<?> iter = coll.iterator(); iter.hasNext();){
				Long id = (Long) iter.next();
				if(null != id){
					sb.append(id);
				}
				if(iter.hasNext()){
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}
	
}
