package com.rpframework.website.xtexam.dao;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.XTUserSubjectNote;

public interface IXTUserSubjectNoteDao extends IDao {

	XTUserSubjectNote findByUserIdAndSubjectId(Integer userId, Integer subjectId);
}
