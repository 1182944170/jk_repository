package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IMyActivities;
import com.rpframework.website.luoluo.domain.MyActivities;

@Service
public class MyActivitiesSercice extends BaseService{
	
	@Resource IMyActivities iMyActivities;
	
	public Pager<MyActivities> getpager(Pager<MyActivities> pager){
		long startTime = System.currentTimeMillis();
		List<MyActivities> list = iMyActivities.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public boolean insertongl(MyActivities myactivities) {
		// TODO Auto-generated method stub
		return iMyActivities.insert(myactivities);
	}
	public void delecto(Integer id) {
		// TODO Auto-generated method stub
		iMyActivities.delete(id);
	}

	public MyActivities selectone(Integer id) {
		// TODO Auto-generated method stub
		return iMyActivities.select(id);
	}

	public boolean updatedo(MyActivities c) {
		// TODO Auto-generated method stub
		return iMyActivities.update(c);
	}
	

}
