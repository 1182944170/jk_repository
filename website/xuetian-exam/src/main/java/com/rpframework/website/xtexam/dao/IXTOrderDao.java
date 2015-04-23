package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.XTOrder;

public interface IXTOrderDao extends IDao {
	int getUserSuccOrderCount(Integer userId);
	List<XTOrder> getUserSuccOrder(Integer userId);
}
