package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.dao.IHouseDao;
import com.rpframework.website.edongwang.domain.House;

@Service
public class HouseService extends BaseService {
	
	@Resource public IHouseDao houseDao;
	@Resource FileService fileService;
	
	public Pager<House> getPager(Pager<House> pager) {
		long startTime = System.currentTimeMillis();
		List<House> itemList = houseDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(House house) {
		if(house == null)  {//TODO:补全验证
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(house.getId())) {
			House houseDB = select(house.getId());
			Assert.notNull(houseDB, "update cannot find id:" + house.getId());
			
			//icon不同，需要删除
			if(StringUtils.isBlank(house.getHouseImg())) {
				house.setHouseImg(houseDB.getHouseImg());
			} else if(!StringUtils.equals(houseDB.getHouseImg(), house.getHouseImg())) {
				try {
					fileService.deleteFile(house.getHouseImg());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}
			
			//icon不同，需要删除
			if(StringUtils.isBlank(house.getHouseTypeImg())) {
				house.setHouseTypeImg(houseDB.getHouseTypeImg());
			} else if(!StringUtils.equals(houseDB.getHouseTypeImg(), house.getHouseTypeImg())) {
				try {
					fileService.deleteFile(house.getHouseTypeImg());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}
			
			return update(house);
		} else {
			house.setRecordCreateTime(System.currentTimeMillis() / 1000);
			return insert(house);
		}
	}
}