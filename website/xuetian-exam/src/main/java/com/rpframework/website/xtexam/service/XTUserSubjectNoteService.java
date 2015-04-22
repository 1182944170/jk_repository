package com.rpframework.website.xtexam.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.xtexam.dao.IXTUserSubjectNoteDao;
import com.rpframework.website.xtexam.domain.ExamSubject;
import com.rpframework.website.xtexam.domain.XTUser;
import com.rpframework.website.xtexam.domain.XTUserSubjectNote;
@Service
public class XTUserSubjectNoteService extends BaseService {
	public @Resource IXTUserSubjectNoteDao userSubjectNoteDao;
	
	public XTUserSubjectNote findByUserIdAndSubjectId(Integer userId, Integer subjectId) {
		XTUserSubjectNote note = userSubjectNoteDao.findByUserIdAndSubjectId(userId, subjectId);
		if(note == null) {//一对一关系，初始化
			note = new XTUserSubjectNote();
			
			XTUser user = new XTUser();
			ExamSubject subject = new ExamSubject();
			user.setId(userId);
			subject.setId(subjectId);
			
			note.setContent(null);
			note.setIsFav(0);
			note.setIsLike(0);
			note.setLastAnswer("");
			note.setRightNum(0);
			note.setSubject(subject);
			note.setUser(user);
			note.setWrongNum(0);
			userSubjectNoteDao.insert(note);
		}
		
		return note;
	}
	
	public boolean hasLikeSubject(Integer userId, Integer subjectId) {
		return findByUserIdAndSubjectId(userId, subjectId).getIsLike() == 1;
	}
	
	public boolean addFavSubject(Integer userId, Integer subjectId) {
		XTUserSubjectNote note = findByUserIdAndSubjectId(userId, subjectId);
		if(note.getIsFav() != 1) {
			note.setIsFav(1);
			return userSubjectNoteDao.update(note);	
		}
		
		return true;
	}
	
	public boolean addRightWrongLastAnswerSubject(Integer userId, Integer subjectId, Integer rightNum, Integer wrongNum, String lastAnswer) {
		XTUserSubjectNote note = findByUserIdAndSubjectId(userId, subjectId);
		note.setRightNum(note.getRightNum() + rightNum);
		note.setWrongNum(note.getWrongNum() + wrongNum);
		note.setLastAnswer(lastAnswer);
		return userSubjectNoteDao.update(note);	
	}
	
	public boolean addLikeSubject(Integer userId, Integer subjectId) {
		XTUserSubjectNote note = findByUserIdAndSubjectId(userId, subjectId);
		if(note.getIsLike() != 1) {
			note.setIsLike(1);
			return userSubjectNoteDao.update(note);	
		}
		
		return true;
	}
	public boolean addNoteSubject(Integer userId, Integer subjectId, String content) {
		XTUserSubjectNote note = findByUserIdAndSubjectId(userId, subjectId);
		note.setContent(content);
		return userSubjectNoteDao.update(note);	
	}
}