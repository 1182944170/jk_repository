package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IActivitypictureDao;
import com.rpframework.website.luoluo.domain.Activitypicture;

@Service
public class ActivitypictureSercice extends BaseService{
	@Resource IActivitypictureDao tActivitypictureDao;
	
	public boolean updatedo(Activitypicture activitypi) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.update(activitypi);
	}

	public List<Activitypicture> selectlist(Integer activiid) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.selectole(activiid);
	}
	public Activitypicture selectone(Integer id) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.select(id);
	}

	public boolean insertdo(Activitypicture activitypi) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.insert(activitypi);
	}
	public Pager<Activitypicture> getpager(Pager<Activitypicture> pager){
		long startTime = System.currentTimeMillis();
		List<Activitypicture> list = tActivitypictureDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

}
