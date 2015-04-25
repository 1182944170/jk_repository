package com.rpframework.website.yunpiaopiao.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.yunpiaopiao.domian.MovieActor;

public interface IMovieActorDao extends IDao {
	List<MovieActor> getMovieActorByMovieId(Integer movieId);

	void deleteListByMovieId(Integer movieId);
}
