package com.rpframework.website.yunpiaopiao.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.yunpiaopiao.domian.CinemaMovie;

public interface ICinemaMovieDao extends IDao {
	
	List<CinemaMovie> getCinemaMovieByCinema(Integer cinemaId);
	
	List<CinemaMovie> getCinemaMovieByMovieId(Integer movieId);
	
	List<CinemaMovie> doPager(Map<?, ?> paramMap);

	boolean deleteByCinemaId(Integer cinemaId);

	boolean deleteByMovieId(Integer movieId);
}
