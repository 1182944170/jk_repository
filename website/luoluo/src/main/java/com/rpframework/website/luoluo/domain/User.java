package com.rpframework.website.luoluo.domain;


import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="user" ,uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class User {
	@FieldMapperAnnotation
	private Integer id;
	@FieldMapperAnnotation
	private String name;		//名字
	@FieldMapperAnnotation
	private String acnumber;		//账号
	@FieldMapperAnnotation
	private String nameNick;	//昵称
	@FieldMapperAnnotation
	private String password;    //密码
	@FieldMapperAnnotation
	private String phone;		//电话
	@FieldMapperAnnotation
	private Integer sex;		//性别
	@FieldMapperAnnotation
	private Integer age;			//年龄
	@FieldMapperAnnotation
	private Integer  marriage;	//婚姻
	@FieldMapperAnnotation
	private String constellation;	//星座
	@FieldMapperAnnotation
	private String hobbues;		//兴趣
	@FieldMapperAnnotation
	private String company;		//工作单位
	@FieldMapperAnnotation
	private String nowlive;     //常驻地
	@FieldMapperAnnotation
	private String hometown;	//家乡
	@FieldMapperAnnotation
	private String qqaccount;	//qq号码
	@FieldMapperAnnotation
	private String  namePic;	//头像
	@FieldMapperAnnotation
	private String  loveStar;	//喜欢明星
	@FieldMapperAnnotation
	private String lovemuice;	//喜欢音乐
	@FieldMapperAnnotation
	private String loveDeliciousfood; //喜欢美食
	@FieldMapperAnnotation
	private String loveFilm; //喜欢电影
	@FieldMapperAnnotation
	private String signature;	//个人签名
	@FieldMapperAnnotation
	private Long ctiontime;//注册时间
	@FieldMapperAnnotation
	private Integer type;     //状态
	
	
	//get  set方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameNick() {
		return nameNick;
	}
	public void setNameNick(String nameNick) {
		this.nameNick = nameNick;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getMarriage() {
		return marriage;
	}
	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}
	public String getHobbues() {
		return hobbues;
	}
	public void setHobbues(String hobbues) {
		this.hobbues = hobbues;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNowlive() {
		return nowlive;
	}
	public void setNowlive(String nowlive) {
		this.nowlive = nowlive;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	
	public String getQqaccount() {
		return qqaccount;
	}
	public void setQqaccount(String qqaccount) {
		this.qqaccount = qqaccount;
	}
	public String getLoveStar() {
		return loveStar;
	}
	public void setLoveStar(String loveStar) {
		this.loveStar = loveStar;
	}
	public String getLovemuice() {
		return lovemuice;
	}
	public void setLovemuice(String lovemuice) {
		this.lovemuice = lovemuice;
	}
	public String getLoveDeliciousfood() {
		return loveDeliciousfood;
	}
	public void setLoveDeliciousfood(String loveDeliciousfood) {
		this.loveDeliciousfood = loveDeliciousfood;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public Long getCtiontime() {
		return ctiontime;
	}
	public void setCtiontime(Long ctiontime) {
		this.ctiontime = ctiontime;
	}
	
	public String getAcnumber() {
		return acnumber;
	}
	public void setAcnumber(String acnumber) {
		this.acnumber = acnumber;
	}
	public String getLoveFilm() {
		return loveFilm;
	}
	public void setLoveFilm(String loveFilm) {
		this.loveFilm = loveFilm;
	}
	
	public String getNamePic() {
		return namePic;
	}
	public void setNamePic(String namePic) {
		this.namePic = namePic;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", acnumber=" + acnumber
				+ ", nameNick=" + nameNick + ", password=" + password
				+ ", phone=" + phone + ", sex=" + sex + ", age=" + age
				+ ", marriage=" + marriage + ", constellation=" + constellation
				+ ", hobbues=" + hobbues + ", company=" + company
				+ ", nowlive=" + nowlive + ", hometown=" + hometown
				+ ", qqaccount=" + qqaccount + ", loveStar=" + loveStar
				+ ", lovemuice=" + lovemuice + ", loveDeliciousfood="
				+ loveDeliciousfood + ", loveFilm=" + loveFilm + ", signature="
				+ signature + ", ctiontime=" + ctiontime + ", type=" + type
				+ "]";
	}

	
	
	
	
	
	
	
	
}
