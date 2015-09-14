package com.rpframework.module.common.domain;

import java.util.List;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 
 * title: Comment.java 
 * 公用评论模块
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年6月10日 下午3:26:54
 */
@TableMapperAnnotation(tableName = "comment", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Comment extends Domain {
	private static final long serialVersionUID = 6655728830977114078L;
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String moduleName;
	@FieldMapperAnnotation
	Integer modulePri;
	@FieldMapperAnnotation
	Integer userId;//null为匿名用户
	@FieldMapperAnnotation
	String nickName;//用户名
	@FieldMapperAnnotation(dbFieldName="parentId", fieldType=FieldType.Object)
	Comment parent;
	@FieldMapperAnnotation
	String content;
	@FieldMapperAnnotation
	Integer replyCount;//回复数
	@FieldMapperAnnotation
	Integer likeCount;
	@FieldMapperAnnotation
	Integer unLikeCount;
	
	@FieldMapperAnnotation
	Integer state;
	@FieldMapperAnnotation
	String ip;
	@FieldMapperAnnotation
	Long recordCreateTime;
	
	List<Comment> children;

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getUnLikeCount() {
		return unLikeCount;
	}

	public void setUnLikeCount(Integer unLikeCount) {
		this.unLikeCount = unLikeCount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Integer getModulePri() {
		return modulePri;
	}

	public void setModulePri(Integer modulePri) {
		this.modulePri = modulePri;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Comment getParent() {
		return parent;
	}

	public void setParent(Comment parent) {
		this.parent = parent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getRecordCreateTime() {
		return recordCreateTime;
	}

	public void setRecordCreateTime(Long recordCreateTime) {
		this.recordCreateTime = recordCreateTime;
	}

	public List<Comment> getChildren() {
		return children;
	}

	public void setChildren(List<Comment> children) {
		this.children = children;
	}
}