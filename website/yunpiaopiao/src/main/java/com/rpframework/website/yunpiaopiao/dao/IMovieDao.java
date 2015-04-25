package com.rpframework.website.yunpiaopiao.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.yunpiaopiao.domian.Movie;

public interface IMovieDao extends IDao {

	@SuppressWarnings("rawtypes")
	List<Movie> doPager(Map packageMyBatisParam);
}
