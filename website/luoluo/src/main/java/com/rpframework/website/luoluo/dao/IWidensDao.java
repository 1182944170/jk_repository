package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Widens;

public interface IWidensDao extends IDao{
	List<Widens> doPager(Map<?, ?> map);
	List<Widens> findUserByName(String name);
	List<Widens> findUserBy(String name);
}
