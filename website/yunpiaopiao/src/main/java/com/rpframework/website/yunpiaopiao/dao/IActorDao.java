package com.rpframework.website.yunpiaopiao.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.yunpiaopiao.domian.Actor;

public interface IActorDao extends IDao {
	List<Actor> queryAll();

	@SuppressWarnings("rawtypes")
	List<Actor> doPager(Map packageMyBatisParam);
}
