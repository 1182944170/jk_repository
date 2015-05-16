package com.rpframework.website.edongwang.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.edongwang.domain.Job;

public interface IJobDao extends IDao{

	List<Job> doPager(Map<?,?> paramMap);
}
