package com.rpframework.website.luoluo.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Monlyjournallist;

public interface IMonlyjournallistDao extends IDao{

	List<Monlyjournallist> selecttole(Integer id);

}
