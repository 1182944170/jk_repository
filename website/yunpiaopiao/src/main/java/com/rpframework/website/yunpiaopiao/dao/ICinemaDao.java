package com.rpframework.website.yunpiaopiao.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.yunpiaopiao.domian.Cinema;

public interface ICinemaDao extends IDao {
	List<Cinema> queryAll();
	
	List<Cinema> doPager(Map<?, ?> paramMap);
	
	List<Cinema> queryRecommend();
}
