package com.rpframework.module.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.module.common.dao.ICommentDao;
import com.rpframework.module.common.domain.Comment;
import com.rpframework.utils.Pager;

@Service
public class CommentService extends BaseService {
	
	@Resource public ICommentDao commentDao;
	
	public Pager<Comment> getPager(String moduleName,Pager<Comment> pager) {
		pager.getSearchMap().put("moduleName", moduleName);
		return getPager(pager);
	}
	
	public Pager<Comment> getPager(String moduleName, Integer modulePri, Pager<Comment> pager) {
		pager.getSearchMap().put("modulePri", String.valueOf(modulePri));
		return getPager(moduleName, pager);
	}
	
	public Pager<Comment> getPager(Pager<Comment> pager) {
		long startTime = System.currentTimeMillis();
		List<Comment> itemList = commentDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public Comment buildComment(String moduleName, Integer modulePri, Integer userId, String nickName, String content, String ip) {
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setIp(ip);
		comment.setModuleName(moduleName);
		comment.setModulePri(modulePri);
		comment.setNickName(nickName);
		comment.setRecordCreateTime(System.currentTimeMillis() / 1000);
		comment.setState(1);
		comment.setUserId(userId);
		return comment;
	}
	
	public boolean addComment(String moduleName, Integer modulePri, Integer userId, String nickName, String content, String ip) {
		Comment comment = buildComment(moduleName, modulePri, userId, nickName, content, ip);
		boolean insert = insert(comment);
		return insert;
	}
	
	public boolean replayComment(Integer replayId, Integer userId, String nickName, String content, String ip) {
		Comment comment = select(replayId);
		Assert.notNull(comment, "找不到评论ID:" + replayId);
		
		Comment buildComment = buildComment(comment.getModuleName(), comment.getModulePri(), userId, nickName, content, ip);
		buildComment.setParent(comment);
		boolean insert = insert(buildComment);
		
		comment.setReplyCount(comment.getReplyCount() + 1);
		update(comment);
		return insert;
	}
	
	public boolean likeComment(Integer commentId) {
		Comment comment = select(commentId);
		Assert.notNull(comment, "找不到评论ID:" + commentId);
		
		comment.setLikeCount(comment.getLikeCount() + 1);
		
		return update(comment);
	}
	
	public boolean unLikeComment(Integer commentId) {
		Comment comment = select(commentId);
		Assert.notNull(comment, "找不到评论ID:" + commentId);
		comment.setUnLikeCount(comment.getUnLikeCount() + 1);
		return update(comment);
	}
	
}
