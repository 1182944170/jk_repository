package com.rpframework.website.xtexam.dao;

import java.util.List;

import com.rpframework.core.IDao;
import com.rpframework.website.xtexam.domain.XTUserSubjectNote;

public interface IXTUserSubjectNoteDao extends IDao {

	XTUserSubjectNote findByUserIdAndSubjectId(Integer userId, Integer subjectId);

	int getUserFavCount(Integer userId);

	int getUserCommentCount(Integer userId);
	
	List<XTUserSubjectNote> getUserFav(Integer userId);

	List<XTUserSubjectNote> getUserComment(Integer userId);
}
