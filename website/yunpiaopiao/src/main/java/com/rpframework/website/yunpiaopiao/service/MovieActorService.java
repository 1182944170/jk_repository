package com.rpframework.website.yunpiaopiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.website.yunpiaopiao.dao.IMovieActorDao;
import com.rpframework.website.yunpiaopiao.domian.MovieActor;

@Service
public class MovieActorService extends BaseService {

	public @Resource IMovieActorDao movieActorDao;
	
	public List<MovieActor> getMovieActorByMovieId(Integer movieId) {
		return movieActorDao.getMovieActorByMovieId(movieId);
	}
	
	public boolean insertOrUpdateByMovie(Integer movieId, List<MovieActor> movieActors) {
		this.deleteListByMovieId(movieId);
		if(CollectionUtils.isEmpty(movieActors)) {//clear
		} else {
			for (MovieActor movieActor : movieActors) {
				movieActorDao.insert(movieActor);
			}
		}
		return true;
	}

	public void deleteListByMovieId(Integer movieId) {
		movieActorDao.deleteListByMovieId(movieId);
	}
}
