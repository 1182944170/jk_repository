package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;


@TableMapperAnnotation(tableName="bank_extraction" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class BankcardExcution {
	@FieldMapperAnnotation
	private Integer id;   // id
	@FieldMapperAnnotation
	private Integer userid;   // 用户id
	@FieldMapperAnnotation
	private String name;   // 用户姓名
	@FieldMapperAnnotation
	private String phone;   // 用户电话
	@FieldMapperAnnotation
	private double extractionMonly;   //提取金额
	@FieldMapperAnnotation
	private String monlycard;   //卡号
	@FieldMapperAnnotation
	private Integer type;   //银行卡类型
	@FieldMapperAnnotation
	private long nowtime;   //时间
	@FieldMapperAnnotation
	private Integer state;   //状态
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
	public double getExtractionMonly() {
		return extractionMonly;
	}
	public void setExtractionMonly(double extractionMonly) {
		this.extractionMonly = extractionMonly;
	}
	public String getMonlycard() {
		return monlycard;
	}
	public void setMonlycard(String monlycard) {
		this.monlycard = monlycard;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getNowtime() {
		return nowtime;
	}
	public void setNowtime(long nowtime) {
		this.nowtime = nowtime;
	}
	
}
