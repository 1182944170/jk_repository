package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Myimpression;


public interface  IMyimpressionDao extends IDao{
	List<Myimpression> doPager(Map<?, ?> map);
}
