package com.rpframework.module.common.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.IProvinceDao;

@Service
public class ProvinceService extends BaseService {
	@Resource public IProvinceDao provinceDao;
}
