package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.module.common.dao.ISlideshowDao;
import com.rpframework.module.common.domain.Slideshow;
import com.rpframework.utils.NumberUtils;

@Service
public class SlideshowService extends BaseService {
	public @Resource ISlideshowDao slideshowDao;
	@Resource FileService fileService;
	
	public List<Slideshow> queryAll() {
		return slideshowDao.queryAll();
	}
	
	public List<Slideshow> queryEffectiveSlideshow(Integer type) {
		return slideshowDao.queryEffectiveSlideshow(type);
	}

	public boolean doSaveOrUpdate(Slideshow slideshow) {
		if(slideshow == null || StringUtils.isBlank(slideshow.getTitle()) 
				||StringUtils.isBlank(slideshow.getUrl())) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		if(NumberUtils.isValid(slideshow.getId())) {//update
			
			Slideshow slideshowDB = slideshowDao.select(slideshow.getId());
			if(slideshowDB == null) {
				throw new IllegalArgumentException("更新时找不到实体 " + slideshow.getId());
			}
			
			//icon不同，需要删除
			if(!StringUtils.equals(slideshowDB.getIcon(), slideshow.getIcon())) {
				try {
					fileService.deleteFile(slideshowDB.getIcon());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}
			return slideshowDao.update(slideshow);
		} else {
			return slideshowDao.insert(slideshow);
		}
			
	}
}
