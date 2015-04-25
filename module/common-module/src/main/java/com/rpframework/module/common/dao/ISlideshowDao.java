package com.rpframework.module.common.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.Slideshow;

public interface ISlideshowDao extends IDao {
	
	List<Slideshow> queryAll();
	
	List<Slideshow> queryEffectiveSlideshow(Integer type);
}