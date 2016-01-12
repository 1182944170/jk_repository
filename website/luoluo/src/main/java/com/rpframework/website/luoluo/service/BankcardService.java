package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IBankcardDao;
import com.rpframework.website.luoluo.domain.Bankcard;

@Service
public class BankcardService extends BaseService{
@Resource IBankcardDao ibanjcarddao;

public Pager<Bankcard> getpager(Pager<Bankcard> pager){
	long startTime = System.currentTimeMillis();
	List<Bankcard> list = ibanjcarddao.doPager(this.packageMyBatisParam(pager));
	pager.setItemList(list);
	pager.setCostTime(System.currentTimeMillis() - startTime);
	return pager;
}

	public boolean insertone(Bankcard bankcar) {
		return ibanjcarddao.insert(bankcar);
	}
	public boolean delectdo(Integer cardid) {
		return ibanjcarddao.delete(cardid);
	}

	public Bankcard selectdo(int parseInt) {
		// TODO Auto-generated method stub
		return ibanjcarddao.select(parseInt);
	}
	

}
