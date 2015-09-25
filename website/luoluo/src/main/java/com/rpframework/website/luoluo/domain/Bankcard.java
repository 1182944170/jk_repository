package com.rpframework.website.luoluo.domain;

import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="bankcard" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")

public class Bankcard {
	@FieldMapperAnnotation
	private Integer id;   //谁的银行卡
	@FieldMapperAnnotation
	private Integer userid;   //用户id
	@FieldMapperAnnotation
	private Integer cardid;   //银行卡
	@FieldMapperAnnotation
	private String name;  //名字
	@FieldMapperAnnotation
	private String phone;  //电话
	@FieldMapperAnnotation
	private String cardnumber;  //银行卡号
	@FieldMapperAnnotation
	private String address;  //开户地址
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
	public Integer getCardid() {
		return cardid;
	}
	public void setCardid(Integer cardid) {
		this.cardid = cardid;
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
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Bankcard [id=" + id + ", userid=" + userid + ", cardid="
				+ cardid + ", name=" + name + ", phone=" + phone
				+ ", cardnumber=" + cardnumber + ", address=" + address
				+ ", getId()=" + getId() + ", getUserid()=" + getUserid()
				+ ", getCardid()=" + getCardid() + ", getName()=" + getName()
				+ ", getPhone()=" + getPhone() + ", getCardnumber()="
				+ getCardnumber() + ", getAddress()=" + getAddress()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
