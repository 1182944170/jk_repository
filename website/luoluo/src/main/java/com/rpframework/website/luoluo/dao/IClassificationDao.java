package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Classification;


public interface IClassificationDao  extends IDao{
	List<Classification> doPager(Map<?, ?> map);
	
	List<Classification> queryAll();
}
