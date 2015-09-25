package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Activitypicture;

public interface IActivitypictureDao extends IDao{
	List<Activitypicture> selectole(Integer activiid);
	List<Activitypicture> doPager(Map<?, ?> map);
}
