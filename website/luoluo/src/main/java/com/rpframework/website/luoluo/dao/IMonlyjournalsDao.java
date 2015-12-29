package com.rpframework.website.luoluo.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Monlyjournals;

public interface IMonlyjournalsDao extends IDao{

	List<Monlyjournals> selecttole(Integer id);

}
