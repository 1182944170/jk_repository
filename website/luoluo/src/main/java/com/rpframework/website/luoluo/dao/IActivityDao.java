package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Activity;

public interface IActivityDao extends IDao{
	List<Activity> doPager(Map<?, ?> map);
	List<Activity> selectname(String name);
	List<Activity> selectnumbers(String tole);
	List<Activity> selectactivice( String lat, String lng, String city);
	List<Activity> selectact(String lat, String lng, String city,
			Integer activitycategoryid);
	List<Activity> selectluist(Integer sponsorid);
	
	List<Integer> doActivityIdList();
	Integer doJoinUserById(Integer id);
	//查记录
	List<Activity> doActivityList(String lng, String lat, Integer categoryId,
			Long st, Long et, Long l, Integer baiduCode, Integer page,
			Integer limit);
	//查总数
	Integer doApiListCount(String lng, String lat, Integer categoryId, Long st,
			Long et, Long l, Integer baiduCode);
	Integer doApiCount1();
	List<Activity> doApiList1();
	List<Activity> doApiTest();
	List<Activity> doPagerTest(Map packageMyBatisParam);
	List<Activity> doActivityListByUserId(Integer userId, Integer page,
			Integer limit);
	List<Activity> doActivityListByUserJoin(Integer userId, Integer page,
			Integer limit);
	List<Activity> doActivityListByFinish(Integer page, Integer limit);
	List<Activity> doApiListByCollection(Integer userId, Integer page,
			Integer limit);
	Integer doJoinNumber(Integer id);
	Integer doFinishCount();
	boolean doDeleteRelative(Integer id);
}
