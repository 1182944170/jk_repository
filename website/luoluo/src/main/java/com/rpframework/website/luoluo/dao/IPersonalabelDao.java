package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Personalabel;

public interface IPersonalabelDao extends IDao{
	List<Personalabel> doPager(Map<?, ?> map);
	List<Personalabel> findUserBy(String name);
}
