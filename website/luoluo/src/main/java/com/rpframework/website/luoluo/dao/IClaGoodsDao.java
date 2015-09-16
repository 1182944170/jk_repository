package com.rpframework.website.luoluo.dao;



import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.luoluo.domain.ClaGoods;


public interface IClaGoodsDao extends IDao{
	
      public List<ClaGoods> getGoodsByclaId(Integer goodId);	
	
      public void deleteByGoodsId(Integer goodId);

}
