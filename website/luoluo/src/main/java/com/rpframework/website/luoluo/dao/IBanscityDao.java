package com.rpframework.website.luoluo.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Banscity;

public interface IBanscityDao extends IDao{

	List<Banscity> selectall();

}
