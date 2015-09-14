package com.rpframework.module.common.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.Province;

public interface IProvinceDao extends IDao {
	List<Province> queryAll();
}