package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;

import com.rpframework.website.luoluo.domain.Reportactivity;

public interface IReportactivityDao extends IDao{
	List<Reportactivity> doPager(Map<?, ?> map);
}
