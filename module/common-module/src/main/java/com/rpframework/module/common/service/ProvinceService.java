package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.IProvinceDao;
import com.rpframework.module.common.domain.Province;

@Service
public class ProvinceService extends BaseService {
	@Resource public IProvinceDao provinceDao;

}
