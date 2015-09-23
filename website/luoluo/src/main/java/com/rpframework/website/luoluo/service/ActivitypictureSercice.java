package com.rpframework.website.luoluo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.luoluo.dao.IActivitypictureDao;
import com.rpframework.website.luoluo.domain.Activitypicture;

@Service
public class ActivitypictureSercice extends BaseService{
	@Resource IActivitypictureDao tActivitypictureDao;
	
	public boolean updatedo(Activitypicture activitypi) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.update(activitypi);
	}

}
