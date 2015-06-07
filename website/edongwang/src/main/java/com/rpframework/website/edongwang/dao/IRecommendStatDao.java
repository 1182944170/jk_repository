package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.RecommendStat;

/**
 * title: RecommendStatDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午5:44:50
 */
public interface IRecommendStatDao extends IDao {
	List<RecommendStat> doPager(Map<?, ?> map);
}