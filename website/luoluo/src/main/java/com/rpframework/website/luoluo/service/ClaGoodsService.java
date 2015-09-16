package com.rpframework.website.luoluo.service;



import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.website.luoluo.dao.IClaGoodsDao;
import com.rpframework.website.luoluo.domain.ClaGoods;

@Service
public class ClaGoodsService extends BaseService{
	
	@Resource
	public IClaGoodsDao claGoodsDao;
	
	public List<ClaGoods> getMovieActorByMovieId(Integer goodId) {
		return claGoodsDao.getGoodsByclaId(goodId);
	}
	
	public boolean insertOrUpdateByMovie(Integer goodId, List<ClaGoods> claGoods) {
		this.deleteListByMovieId(goodId);
		if(CollectionUtils.isEmpty(claGoods)) {//clear
		} else {
			for (ClaGoods cla : claGoods) {
				claGoodsDao.insert(cla);
			}
		}
		return true;
	}

	public void deleteListByMovieId(Integer goodId) {
		claGoodsDao.deleteByGoodsId(goodId);
	}
}
