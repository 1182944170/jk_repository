package com.rpframework.website.edongwang.domain;


import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

/**
 * 会员类
 * @author hzyuanyong@126.com
 *
 */

@TableMapperAnnotation(tableName = "e_member", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id") 
public class Member extends Domain{

	public static Integer ISLEADER_NO = 0; //不是负责人
	
	public static Integer ISLEADER_YES = 1; //是负责人
	
	public static Integer SEX_WOMAN = 1; //女士
	
	public static Integer MSEX_GENTLEMAN = 2 ; //男士
	
	public static Integer STATUS_NORMAL = 0; //激活
	
	public static Integer STATUS_DISABLE = 1; //未激活
	
	public static Integer AUDIT_STATUS_PASS = 2 ; //审核通过
	
	public static Integer AUDIT_STATUS_ING = 1 ; //审核中  提交过申请
	
	public static Integer AUDIT_STATUS_NORMAL = 0 ; //默认不需要审核
	
	private static final long serialVersionUID = 1L;
	
	@FieldMapperAnnotation
	Integer id;
	
	@FieldMapperAnnotation
	String telNumber; //手机号码
	
	@FieldMapperAnnotation
	String password;  //密码
	
	@FieldMapperAnnotation
	Integer sex;  //性别

	@FieldMapperAnnotation
	String realName ; //真实姓名
	
	@FieldMapperAnnotation
	String headImg; //头像图片
	
	@FieldMapperAnnotation 
	String callingCardImg ;  //名片图片
	
	@FieldMapperAnnotation
	String bankName; //银行名称
	
	@FieldMapperAnnotation
	String bankAdrees ; //银行 开户行
	
	@FieldMapperAnnotation
	String bankNumber; //银行卡号

	@FieldMapperAnnotation
	Long registerDate ; //注册时间
	
	@FieldMapperAnnotation
	Integer status = Member.STATUS_NORMAL ;//状态
	
	@FieldMapperAnnotation
	Integer isLeader = Member.ISLEADER_NO ; //是否是负责人  默认不是负责人
	
	@FieldMapperAnnotation
	Integer auditStatus = Member.AUDIT_STATUS_NORMAL ; //审核状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	
	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getCallingCardImg() {
		return callingCardImg;
	}

	public void setCallingCardImg(String callingCardImg) {
		this.callingCardImg = callingCardImg;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAdrees() {
		return bankAdrees;
	}

	public void setBankAdrees(String bankAdrees) {
		this.bankAdrees = bankAdrees;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public Long getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Long registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	
	
}
