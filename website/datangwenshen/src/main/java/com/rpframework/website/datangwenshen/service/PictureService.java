package com.rpframework.website.datangwenshen.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.datangwenshen.dao.IPictureDao;
import com.rpframework.website.datangwenshen.domain.Picture;

@Service
public class PictureService extends BaseService{

	public @Resource IPictureDao pictureDao;
	@Resource FileService fileService;
	
	public List<Integer> getTypesBySource(Integer source) {
		return pictureDao.getTypesBySource(source);
	}
	
	public Pager<Picture> getPager(Pager<Picture> pager) {
		long startTime = System.currentTimeMillis();
		List<Picture> list = pictureDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	
	public boolean doSaveOrUpdate(Picture picture) {
		if(picture == null || StringUtils.isBlank(picture.getName())) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		if(NumberUtils.isValid(picture.getId())) {//update
			
			Picture pictureDB = pictureDao.select(picture.getId());
			Assert.notNull(pictureDB, "更新时找不到实体 " + picture.getId());
			//icon不同，需要删除
			if(!StringUtils.equals(pictureDB.getAddress(), picture.getAddress())) {
				try {
					fileService.deleteFile(pictureDB.getAddress());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}
			return pictureDao.update(picture);
		} else {
			if(StringUtils.isBlank(picture.getAddress())) {
				throw new IllegalArgumentException("新增情况下，必须上传Address!");
			}
			return pictureDao.insert(picture);
		}
	}
}