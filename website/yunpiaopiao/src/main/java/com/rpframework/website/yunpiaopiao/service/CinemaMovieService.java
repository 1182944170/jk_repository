package com.rpframework.website.yunpiaopiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.dao.ICinemaMovieDao;
import com.rpframework.website.yunpiaopiao.domian.CinemaMovie;

@Service
public class CinemaMovieService extends BaseService {

	public @Resource ICinemaMovieDao cinemaMovieDao;
	
	public List<CinemaMovie> getCinemaMovieByCinema(Integer cinemaId) {
		return cinemaMovieDao.getCinemaMovieByCinema(cinemaId);
	}

	public List<CinemaMovie> getCinemaMovieByMovieId(Integer movieId) {
		return cinemaMovieDao.getCinemaMovieByMovieId(movieId);
	}
	
	
	public Pager<CinemaMovie> doPager(Pager<CinemaMovie> pager) {
		long startTime = System.currentTimeMillis();
		List<CinemaMovie> itemList = cinemaMovieDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public void deleteByCinemaId(Integer cinemaId) {
		cinemaMovieDao.deleteByCinemaId(cinemaId);
	}
	
	public void deleteByMovieId(Integer cinemaId) {
		cinemaMovieDao.deleteByMovieId(cinemaId);
	}

	public void insertOrUpdateByMovie(Integer cinemaId, List<CinemaMovie> cinemaMovies) {
		this.deleteByMovieId(cinemaId);
		if(CollectionUtils.isNotEmpty(cinemaMovies)) {
			for (CinemaMovie cinemaMovie : cinemaMovies) {
				cinemaMovieDao.insert(cinemaMovie);
			}
		}
	}
}
