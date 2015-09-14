package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.INewsPluginDao;
import com.rpframework.module.common.domain.NewsPlugin;

@Service
public class NewsPluginService extends BaseService {
	
	@Resource public INewsPluginDao newsPluginDao;
	
	public NewsPlugin getNewsPlugin(String moduleName, Integer modulePri, Integer userId) {
		NewsPlugin newsPlugin = newsPluginDao.getNewsPlugin(moduleName, modulePri, userId);
		if(newsPlugin == null) {
			newsPlugin = new NewsPlugin();
			newsPlugin.setCount(0);
			newsPlugin.setModifyTime(System.currentTimeMillis() / 1000);
			newsPlugin.setModuleName(moduleName);
			newsPlugin.setModulePri(modulePri);
			newsPlugin.setUserId(userId);
			
			boolean insert = insert(newsPlugin);
			Assert.isTrue(insert);
		}
		
		return newsPlugin;
	}
	
	public List<NewsPlugin> getNewsPluginList(String moduleName, Integer userId) {
		return newsPluginDao.getNewsPluginList(moduleName, userId);
	}
	
	public List<NewsPlugin> getNewsPluginList(Integer userId) {
		return newsPluginDao.getNewsPluginListByUserId(userId);
	}
	
	public boolean addNewsPluginCount(String moduleName, Integer modulePri, Integer userId, Integer addCount) {
		NewsPlugin newsPlugin = getNewsPlugin(moduleName, modulePri, userId);
		newsPlugin.setCount(newsPlugin.getCount() + addCount);
		newsPlugin.setModifyTime(System.currentTimeMillis() / 1000);
		return update(newsPlugin);
	}
	
	public boolean resetNewsPlugin(String moduleName,Integer modulePri, Integer userId) {
		NewsPlugin newsPlugin = getNewsPlugin(moduleName, modulePri, userId);
		newsPlugin.setCount(0);
		return update(newsPlugin);
	}
	
	public boolean resetNewsPlugin(String moduleName, Integer userId) {
		return newsPluginDao.resetNewsPlugin(moduleName,userId);
	}
}
