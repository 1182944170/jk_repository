package com.rpframework.website.yunpiaopiao.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.dao.IActorDao;
import com.rpframework.website.yunpiaopiao.domian.Actor;

@Service
public class ActorService extends BaseService {

	public @Resource IActorDao actorDao;
	@Resource FileService fileService;
	
	public List<Actor> queryAll() {
		return actorDao.queryAll();
	}

	public Pager<Actor> getPager(Pager<Actor> pager) {
		long startTime = System.currentTimeMillis();
		List<Actor> itemList = actorDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(Actor actor) {
		if(actor == null || StringUtils.isBlank(actor.getName())
				|| StringUtils.isBlank(actor.getContent())) {
			throw new IllegalArgumentException();
		}
		
		if(NumberUtils.isValid(actor.getId())) {
			Actor actorDB = actorDao.select(actor.getId());
			if(actorDB == null) {
				throw new IllegalArgumentException("update bot exits Id:" + actor.getId());
			}
			//icon不同，需要删除
			if(!StringUtils.equals(actorDB.getIcon(), actor.getIcon())) {
				try {
					fileService.deleteFile(actorDB.getIcon());
				} catch (Exception e) {
					logger.warn("文件删除失败:" + e.getLocalizedMessage());
				}
			}
			return actorDao.update(actor);
		} else {
			return actorDao.insert(actor);
		}
	}
}
