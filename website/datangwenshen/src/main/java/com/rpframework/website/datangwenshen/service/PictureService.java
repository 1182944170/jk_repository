package com.rpframework.website.datangwenshen.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.datangwenshen.dao.IPictureDao;
import com.rpframework.website.datangwenshen.domain.Picture;

@Service
public class PictureService extends BaseService{

	public @Resource IPictureDao pictureDao;
	
	public List<Integer> getTypesBySource(Integer source) {
		return pictureDao.getTypesBySource(source);
	}
	
	public Pager<Picture> getPager(Pager<Picture> pager) {
		long startTime = System.currentTimeMillis();
		List<Picture> list = pictureDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

}