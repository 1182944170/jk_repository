package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.XTUserSubjectComment;

public interface IXTUserSubjectCommentDao extends IDao {

	List<XTUserSubjectComment> findBySubjectId(Integer subjectId);
}
