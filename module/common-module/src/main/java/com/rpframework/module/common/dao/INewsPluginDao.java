package com.rpframework.module.common.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.NewsPlugin;

/**
 * title: INewPluginDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月10日 下午10:01:21
 */
public interface INewsPluginDao extends IDao {
	
	NewsPlugin getNewsPlugin(String moduleName, Integer modulePri, Integer userId);
	
	List<NewsPlugin> getNewsPluginList(String moduleName, Integer userId);
	
	List<NewsPlugin> getNewsPluginListByUserId(Integer userId);

	boolean resetNewsPlugin(String moduleName, Integer userId);
}