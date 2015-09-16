package com.rpframework.website.luoluo.domain;

import java.util.List;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="myimpression" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Myimpression {
	@FieldMapperAnnotation
	private Integer id;
	@FieldMapperAnnotation
	private Integer userid;
	@FieldMapperAnnotation
	private Integer findid;
	@FieldMapperAnnotation
	private Integer type;
	
	private List<User> user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getFindid() {
		return findid;
	}
	public void setFindid(Integer findid) {
		this.findid = findid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Myimpression [id=" + id + ", userid=" + userid + ", findid="
				+ findid + ", type=" + type + "]";
	}
	
	
}
