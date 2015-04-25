package com.rpframework.website.yunpiaopiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.dao.ICinemaDao;
import com.rpframework.website.yunpiaopiao.domian.Cinema;

@Service
public class CinemaService extends BaseService {

	public @Resource ICinemaDao cinemaDao;
	@Resource FileService fileService;
	@Resource CinemaMovieService cinemaMovieService;
	
	public List<Cinema> queryAll() {
		return cinemaDao.queryAll();
	}
	
	public Pager<Cinema> doPager(Pager<Cinema> pager) {
		long startTime = System.currentTimeMillis();
		List<Cinema> itemList = cinemaDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public List<Cinema> queryRecommend() {
		return cinemaDao.queryRecommend();
	}

	public boolean doSaveOrUpdate(Cinema cinema) {
		if(cinema == null || StringUtils.isBlank(cinema.getName())
				|| StringUtils.isBlank(cinema.getAddress())) {
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(cinema.getId())) {
			Cinema cinemaDB = cinemaDao.select(cinema.getId());
			if(cinemaDB == null) {
				throw new IllegalArgumentException("update bot exits Id:" + cinema.getId());
			}
			
			//icon不同，需要删除
			if(!StringUtils.equals(cinemaDB.getIcon(), cinema.getIcon())) {
				try {
					fileService.deleteFile(cinemaDB.getIcon());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}
			return cinemaDao.update(cinema);
		} else {
			return cinemaDao.insert(cinema);
		}
	}

	public boolean delete(Integer cinemaId) {
		Cinema cinema = cinemaDao.select(cinemaId);
		if(cinema == null) {
			throw new IllegalArgumentException("不存在的ID:" + cinemaId);
		}
		
		cinemaMovieService.deleteByCinemaId(cinemaId);
		return cinemaDao.delete(cinemaId);
	}
}
