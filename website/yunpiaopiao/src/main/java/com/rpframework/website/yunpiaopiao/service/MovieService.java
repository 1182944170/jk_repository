package com.rpframework.website.yunpiaopiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.dao.IMovieDao;
import com.rpframework.website.yunpiaopiao.domian.Cinema;
import com.rpframework.website.yunpiaopiao.domian.Movie;

@Service
public class MovieService extends BaseService {

	public @Resource IMovieDao movieDao;
	@Resource FileService fileService;
	@Resource CinemaMovieService cinemaMovieService;
	@Resource MovieActorService movieActorService;
	
	public Pager<Movie> getPager(Pager<Movie> pager) {
		long startTime = System.currentTimeMillis();
		List<Movie> itemList = movieDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(Movie movie) {
		if(movie == null || StringUtils.isBlank(movie.getName())
				|| StringUtils.isBlank(movie.getContent())) {
			throw new IllegalArgumentException();
		}
		
		boolean flag = true;
		if(NumberUtils.isValid(movie.getId())) {
			Movie movieDB = movieDao.select(movie.getId());
			if(movieDB == null) {
				throw new IllegalArgumentException("update bot exits Id:" + movie.getId());
			}
			
			//icon不同，需要删除
			if(!StringUtils.equals(movieDB.getIcon(), movie.getIcon())) {
				try {
					fileService.deleteFile(movieDB.getIcon());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}
			
			flag = movieDao.update(movie);
		} else {
			flag = movieDao.insert(movie);
		}
		
		movieActorService.insertOrUpdateByMovie(movie.getId(), movie.getMovieActors());
		cinemaMovieService.insertOrUpdateByMovie(movie.getId(), movie.getCinemaMovies());
		return flag;
	}

	public boolean delete(Integer movieId) {
		
		
		Cinema cinema = movieDao.select(movieId);
		if(cinema == null) {
			throw new IllegalArgumentException("不存在的ID:" + movieId);
		}
		
		cinemaMovieService.deleteByMovieId(movieId);
		movieActorService.deleteListByMovieId(movieId);
		return movieDao.delete(movieId);
		
	}
}
