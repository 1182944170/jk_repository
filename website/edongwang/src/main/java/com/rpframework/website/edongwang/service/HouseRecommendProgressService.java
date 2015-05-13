package com.rpframework.website.edongwang.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.edongwang.dao.IHouseRecommendProgressDao;

@Service
public class HouseRecommendProgressService extends BaseService {
	
	@Resource public IHouseRecommendProgressDao recommendProgressDao;
}
