package com.rpframework.website.xtexam.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.xtexam.dao.IXTUserSubjectCommentDao;
import com.rpframework.website.xtexam.domain.ExamSubject;
import com.rpframework.website.xtexam.domain.XTUser;
import com.rpframework.website.xtexam.domain.XTUserSubjectComment;
@Service
public class XTUserSubjectCommentService extends BaseService {
	public @Resource IXTUserSubjectCommentDao userSubjectCommentDao;
	
	public List<XTUserSubjectComment> findBySubjectId(Integer subjectId) {
		return userSubjectCommentDao.findBySubjectId(subjectId);
	}
	
	public boolean addComment(Integer userId, Integer subjectId, String content) {
		XTUserSubjectComment comment = new XTUserSubjectComment();
		XTUser user = new XTUser();
		ExamSubject subject = new ExamSubject();
		user.setId(userId);
		subject.setId(subjectId);
		
		
		comment.setContent(content);
		comment.setState(1);
		comment.setUser(user);
		comment.setSubject(subject);
		comment.setRecoreCreateTime(System.currentTimeMillis() / 1000);
		return userSubjectCommentDao.insert(comment);
	}
}