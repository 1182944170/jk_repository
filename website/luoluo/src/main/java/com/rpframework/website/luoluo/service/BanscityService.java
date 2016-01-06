package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.luoluo.dao.IBanscityDao;
import com.rpframework.website.luoluo.domain.Banscity;

@Service
public class BanscityService extends BaseService{
@Resource IBanscityDao ibao;
	public List<Banscity> selectall() {
		// TODO Auto-generated method stub
		return ibao.selectall();
	}

}
