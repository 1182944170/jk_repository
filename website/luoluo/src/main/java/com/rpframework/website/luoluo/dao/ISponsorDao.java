package com.rpframework.website.luoluo.dao;

import java.util.List;
import java.util.Map;




import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.Sponsorlis;

public interface ISponsorDao extends IDao{
	List<Sponsorlis> doPager(Map<?, ?> map);

	Sponsorlis selectsponsorid(Integer sponsorid);
}
