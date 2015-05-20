package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.HouseRecommend;

public interface IHouseRecommendDao extends IDao {
	List<HouseRecommend> doPager(Map<?, ?> map);
	List<HouseRecommend> doOverPager(Map<?, ?> map);
}