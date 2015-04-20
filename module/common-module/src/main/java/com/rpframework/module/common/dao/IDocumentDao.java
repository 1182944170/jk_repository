package com.rpframework.module.common.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.Document;

public interface IDocumentDao extends IDao {
	List<Document> queryAllByParentId(Integer parentId);

	List<Document> doPager(@SuppressWarnings("rawtypes") Map packageMyBatisParam);
}