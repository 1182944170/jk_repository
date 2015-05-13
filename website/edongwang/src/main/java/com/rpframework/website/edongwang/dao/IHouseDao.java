package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.House;

public interface IHouseDao extends IDao{

	List<House> doPager(Map<?,?> paramMap);
}
