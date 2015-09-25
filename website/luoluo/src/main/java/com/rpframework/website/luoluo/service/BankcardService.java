package com.rpframework.website.luoluo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.luoluo.dao.IBankcardDao;
import com.rpframework.website.luoluo.domain.Bankcard;

@Service
public class BankcardService extends BaseService{
@Resource IBankcardDao ibanjcarddao;
	public boolean insertone(Bankcard bankcar) {
		// TODO Auto-generated method stub
		return ibanjcarddao.insert(bankcar);
	}

}
