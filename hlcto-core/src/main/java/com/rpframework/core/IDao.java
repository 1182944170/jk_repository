package com.rpframework.core;

/**
 * 
 * title: IDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年3月28日 下午11:00:11
 */
public interface IDao {
	
	/**
	 * 由MyBatis的拦截器自动注入完成
	 * 
	 * 根据主键id查找实体
	 * 
	 * @param idObj 主键
	 * @return T 实体
	 */
	<T> T select(Object idObj);
	
	/**
	 * 由MyBatis的拦截器自动注入完成
	 * 
	 * 更新实体
	 * 
	 * @param t
	 * @return Boolean 是否新增成功
	 */
	<T> boolean update(T t);
	
	/**
	 * 由MyBatis的拦截器自动注入完成
	 * 
	 * @param idObj 主键
	 * @return T 实体
	 */
	boolean delete(Object idObj);
	
	/**
	 * 由MyBatis的拦截器自动注入完成
	 * 
	 * 新增一条实体
	 * 主键会在 新增成功后注入在 t.id 里去
	 * 
	 * @param t
	 * @return Boolean 是否新增成功
	 */
	<T> boolean insert(T t);
}
