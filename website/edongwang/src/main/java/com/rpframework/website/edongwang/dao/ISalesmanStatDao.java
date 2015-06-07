package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.SalesmanStat;
/**
 * title: SalesmanStatDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月7日 下午5:44:43
 */
public interface ISalesmanStatDao extends IDao {
	List<SalesmanStat> doPager(Map<?, ?> map);
	
	SalesmanStat selectByUserIdAndHouseId(Integer userId, Integer houseId);
}