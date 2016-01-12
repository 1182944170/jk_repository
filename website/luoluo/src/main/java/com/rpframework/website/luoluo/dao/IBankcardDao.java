package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Bankcard;

public interface IBankcardDao extends IDao{

	List<Bankcard> doPager(Map<?, ?> map);

}
