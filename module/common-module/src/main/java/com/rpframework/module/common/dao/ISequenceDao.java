package com.rpframework.module.common.dao;



/**
 * 
 * title: ISequenceDao.java 
 * 提供序列函数
 * 相见数据库 表 sequence
 * 	函数：currval
 * 		nextval
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月9日 上午10:48:53
 */
public interface ISequenceDao {
	Integer nextVal(String seqName);
	Integer currVal(String seqName);
}