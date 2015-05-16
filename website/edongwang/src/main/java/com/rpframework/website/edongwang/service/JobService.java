package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IJobDao;
import com.rpframework.website.edongwang.domain.Job;

@Service
public class JobService extends BaseService {
	
	@Resource public IJobDao jobDao;
	
	public Pager<Job> getPager(Pager<Job> pager) {
		long startTime = System.currentTimeMillis();
		List<Job> itemList = jobDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(Job job) {
		if(job == null)  {
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(job.getId())) {
			Job jobDB = select(job.getId());
			Assert.notNull(jobDB, "update cannot find id:" + job.getId());
			job.setRecordCreateTime(jobDB.getRecordCreateTime());
			return update(job);
		} else {
			job.setRecordCreateTime(System.currentTimeMillis() / 1000);
			return insert(job);
		}
	}
}