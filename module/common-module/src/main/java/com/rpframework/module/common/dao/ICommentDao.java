package com.rpframework.module.common.dao;

import java.util.List;
import java.util.Map;

import com.rpframework.core.IDao;
import com.rpframework.module.common.domain.Comment;

/**
 * title: ICommentDao.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月10日 下午3:48:26
 */
public interface ICommentDao extends IDao {
	
	List<Comment> doPager(Map<?, ?> map);
	
	List<Comment> getListByParentId(Integer parentId);
}