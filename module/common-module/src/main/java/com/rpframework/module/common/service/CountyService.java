package com.rpframework.module.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.ICountyDao;

@Service
public class CountyService extends BaseService {
	@Resource public ICountyDao countyDao;
}
