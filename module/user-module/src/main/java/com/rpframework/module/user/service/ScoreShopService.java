package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.module.user.dao.IScoreShopDao;
import com.rpframework.module.user.domain.ScoreShop;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Service
public class ScoreShopService extends BaseService {
	public @Resource IScoreShopDao scoreShopDao;
	@Resource FileService fileService;
	
	public Pager<ScoreShop> getPager(Pager<ScoreShop> pager) {
		long startTime = System.currentTimeMillis();
		List<ScoreShop> itemList = scoreShopDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(ScoreShop scoreShop) {
		if(scoreShop == null || StringUtils.isBlank(scoreShop.getName())) {
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(scoreShop.getId())) { //update
			ScoreShop scoreShopDB = select(scoreShop.getId());
			Assert.notNull(scoreShopDB);
			scoreShop.setRecordCreateTime(scoreShopDB.getRecordCreateTime());
			scoreShop.setSalesNumber(scoreShopDB.getSalesNumber());
			
			if(StringUtils.isBlank(scoreShop.getImg())) {
				scoreShop.setImg(scoreShopDB.getImg());
			} else if(!StringUtils.equals(scoreShop.getImg(), scoreShopDB.getImg())) {//删除之前的 img
				try {
					fileService.deleteFile(scoreShopDB.getImg());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return update(scoreShop);
		} else {//insert
			
			if(StringUtils.isBlank(scoreShop.getImg())) {
				throw new IllegalArgumentException("新增情况下需上传icon.");
			}
			scoreShop.setRecordCreateTime(System.currentTimeMillis() / 1000);
			scoreShop.setSalesNumber(0);
			return insert(scoreShop);
		}
	}
}
