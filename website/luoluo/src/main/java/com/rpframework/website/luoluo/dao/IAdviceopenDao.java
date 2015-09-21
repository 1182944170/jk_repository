package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Adviceopen;

public interface IAdviceopenDao extends IDao{
	List<Adviceopen> doPager(Map<?, ?> map);
}
