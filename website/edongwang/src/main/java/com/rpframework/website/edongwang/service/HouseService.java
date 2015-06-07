package com.rpframework.website.edongwang.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.CollectionUtils;
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
		if(house == null || StringUtils.isBlank(house.getName()))  {//TODO:补全验证
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(house.getId())) {
			House houseDB = select(house.getId());
			Assert.notNull(houseDB, "update cannot find id:" + house.getId());
			if(!StringUtils.equals(houseDB.getHouseImgArray(), house.getHouseImgArray())) {
				List<String> houseImgArrayList = house.getHouseImgArrayList();
				List<String> houseImgArrayListDB = houseDB.getHouseImgArrayList();
				for (String houseImgDB : houseImgArrayListDB) {
					boolean isFind = false;
					for (String houseImg : houseImgArrayList) {
						if(StringUtils.equals(houseImg, houseImgDB)) {
							isFind = true; break;
						}
					}
					
					if(!isFind) { //删除
						try {
							fileService.deleteFile(houseImgDB);
						} catch (Exception e) {
							logger.warn("文件删除失败:" + e.getLocalizedMessage());
						}
					}
				}
			}
			
			if(!StringUtils.equals(houseDB.getHouseTypeImgArray(), house.getHouseTypeImgArray())) {
				List<String> houseTypeImgArrayList = house.getHouseTypeImgArrayList();
				List<String> houseTypeImgArrayListDB = houseDB.getHouseTypeImgArrayList();
				for (String houseImgDB : houseTypeImgArrayListDB) {
					boolean isFind = false;
					for (String houseImg : houseTypeImgArrayList) {
						if(StringUtils.equals(houseImg, houseImgDB)) {
							isFind = true; break;
						}
					}
					
					if(!isFind) { //删除
						try {
							fileService.deleteFile(houseImgDB);
						} catch (Exception e) {
							logger.warn("文件删除失败:" + e.getLocalizedMessage());
						}
					}
				}
			}
			
			/*//icon不同，需要删除
			if(StringUtils.isBlank(house.getHouseTypeImg())) {
				house.setHouseTypeImg(houseDB.getHouseTypeImg());
			} else if(!StringUtils.equals(houseDB.getHouseTypeImg(), house.getHouseTypeImg())) {
				try {
					fileService.deleteFile(house.getHouseTypeImg());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}*/
			
			return update(house);
		} else {
			if(StringUtils.isBlank(house.getHouseImgArray()) || CollectionUtils.isEmpty(house.getHouseImgArrayList())|| CollectionUtils.isEmpty(house.getHouseTypeImgArrayList())) {
				throw new IllegalArgumentException("新增时需要上传户型图或主图!");
			}
			house.setRecordCreateTime(System.currentTimeMillis() / 1000);
			return insert(house);
		}
	}
}