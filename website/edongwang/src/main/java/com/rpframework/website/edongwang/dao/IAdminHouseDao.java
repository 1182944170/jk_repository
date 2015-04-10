package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.Houses;

public interface IAdminHouseDao extends IDao{

	List<Houses> doPager(Map<?,?> paramMap);
}
