package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Activity;

public interface IActivityDao extends IDao{
	List<Activity> doPager(Map<?, ?> map);
}
