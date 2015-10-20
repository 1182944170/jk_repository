package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.BankcardExcution;

public interface IBankcardExcutionDao extends IDao{
	List<BankcardExcution> doPager(Map<?, ?> map);
}
