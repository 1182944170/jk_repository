package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.MyActivities;

public interface IMyActivities extends IDao{
	List<MyActivities> doPager(Map<?, ?> map);
}
