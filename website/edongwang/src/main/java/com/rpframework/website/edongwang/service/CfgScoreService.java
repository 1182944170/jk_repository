package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.edongwang.dao.ICfgScoreDao;
import com.rpframework.website.edongwang.domain.CfgScore;

@Service
public class CfgScoreService extends BaseService {
	public @Resource ICfgScoreDao cfgScoreDao;
	
	public List<CfgScore> queryAll() {
		return cfgScoreDao.queryAll();
	}

	public boolean doSaveOrUpdate(CfgScore cfgScore) {
		if(cfgScore == null || StringUtils.isBlank(cfgScore.getName()) || NumberUtils.isNotValid(cfgScore.getLevel())) {
			throw new IllegalArgumentException("error arg.");
		}
		
		CfgScore cfgScoreDB = select(cfgScore.getLevel());
		if(cfgScoreDB == null) {//insert
			return insert(cfgScore);
		} else {
			return update(cfgScore);
		}
	}
}
