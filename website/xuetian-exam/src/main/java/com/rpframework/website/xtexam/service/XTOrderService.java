package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.xtexam.dao.IXTOrderDao;
import com.rpframework.website.xtexam.domain.XTOrder;

@Service
public class XTOrderService extends BaseService{
	public @Resource IXTOrderDao orderDao;
	
	public int getUserSuccOrderCount(Integer userId) {
		return orderDao.getUserSuccOrderCount(userId);
	}
	public List<XTOrder> getUserSuccOrder(Integer userId) {
		return orderDao.getUserSuccOrder(userId);
	}
}
