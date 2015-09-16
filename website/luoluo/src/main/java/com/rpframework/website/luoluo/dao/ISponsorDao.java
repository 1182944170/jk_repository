package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;



import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Sponsor;

public interface ISponsorDao extends IDao{
	List<Sponsor> doPager(Map<?, ?> map);
}
