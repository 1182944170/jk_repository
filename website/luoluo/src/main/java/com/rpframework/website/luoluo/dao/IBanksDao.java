package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Banks;

public interface IBanksDao extends IDao{
	List<Banks> doPager(Map<?, ?> map);
	List<Banks> queryAlllesei();
	List<Banks> queryAll();
}
