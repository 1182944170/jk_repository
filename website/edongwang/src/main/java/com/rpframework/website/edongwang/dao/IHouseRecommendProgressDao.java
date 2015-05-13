package com.rpframework.website.edongwang.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.HouseRecommendProgress;

public interface IHouseRecommendProgressDao extends IDao {
	List<HouseRecommendProgress> getProgressByHouseRecommendId(Integer houseRecommendId);
}