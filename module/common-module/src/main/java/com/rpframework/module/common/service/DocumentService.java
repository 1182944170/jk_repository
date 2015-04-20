package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.IDocumentDao;
import com.rpframework.module.common.domain.Document;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Service
public class DocumentService extends BaseService {
	@Resource public IDocumentDao documentDao;
	
	public List<Document> queryAllByParentId(Integer parentId){
		return documentDao.queryAllByParentId(parentId);
	}
	
	public Pager<Document> getDocumentPager(Pager<Document> pager) {
		long startTime = System.currentTimeMillis();
		List<Document> itemList = documentDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}

	public boolean doSaveOrUpdate(Document document) {
		if(document == null || StringUtils.isBlank(document.getName()) || StringUtils.isBlank(document.getContent())) {
			throw new IllegalArgumentException("add Document arg error.");
		}
		
		if(document.getParent() == null) {
			document.setParent(new Document());
		}
		
		if(NumberUtils.isValid(document.getId())) {//update
			Document documentDB = documentDao.select(document.getId());
			Assert.notNull(documentDB, "更新知识库时找不到ID:" + document.getId());
			return documentDao.update(document);
		} else {//insert
			return documentDao.insert(document);
		}
	}
}
