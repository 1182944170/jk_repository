package com.rpframework.website.luoluo.domain;


import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName="sponsor" , uniqueKeyType=UniqueKeyType.Single,uniqueKey = "id")
public class Sponsorlis {
	@FieldMapperAnnotation
	private Integer id;   // id
	@FieldMapperAnnotation
	private Integer userid;  // 用户id
	@FieldMapperAnnotation
	private String name; //主办方姓名
	@FieldMapperAnnotation
	private String username; //领队姓名
	@FieldMapperAnnotation
	private String userpicture;  //真实头像
	@FieldMapperAnnotation
	private String usernowlive;  //常驻地
	@FieldMapperAnnotation
	private String userphone;  //领队电话
	@FieldMapperAnnotation
	private String userinformation;  //领队信息
	@FieldMapperAnnotation
	private String usertelephone;  //公司负责手机
	@FieldMapperAnnotation
	private String telephone;  //公司电话
	@FieldMapperAnnotation
	private String companyname;  //公司名称
	@FieldMapperAnnotation
	private String responsibility;  // 企业负责任信息
	@FieldMapperAnnotation
	private String responname;  // 企业负姓名
	@FieldMapperAnnotation
	private String entintroduction;  // 公司介绍
	@FieldMapperAnnotation
	private Integer type;  // 类型
	@FieldMapperAnnotation
	private long activityTime;  // 时间
	@FieldMapperAnnotation
	private Integer goint;  // 成功次数
	@FieldMapperAnnotation
	private Integer typeopp;  // 成功次数
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
	public String getUserpicture() {
		return userpicture;
	}
	public void setUserpicture(String userpicture) {
		this.userpicture = userpicture;
	}
	public String getUsernowlive() {
		return usernowlive;
	}
	public void setUsernowlive(String usernowlive) {
		this.usernowlive = usernowlive;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUserinformation() {
		return userinformation;
	}
	public void setUserinformation(String userinformation) {
		this.userinformation = userinformation;
	}
	public String getUsertelephone() {
		return usertelephone;
	}
	public void setUsertelephone(String usertelephone) {
		this.usertelephone = usertelephone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	public String getEntintroduction() {
		return entintroduction;
	}
	public void setEntintroduction(String entintroduction) {
		this.entintroduction = entintroduction;
	}
	public Integer getType() {
		return type;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Long activityTime) {
		this.activityTime = activityTime;
	}
	public Integer getGoint() {
		return goint;
	}
	public void setGoint(Integer goint) {
		this.goint = goint;
	}
	public Integer getTypeopp() {
		return typeopp;
	}
	public void setTypeopp(Integer typeopp) {
		this.typeopp = typeopp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getResponname() {
		return responname;
	}
	public void setResponname(String responname) {
		this.responname = responname;
	}
	public void setActivityTime(long activityTime) {
		this.activityTime = activityTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
/*	private List<ClaGoods>  classList;//商品分类集合
	

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

	public String getUserpicture() {
		return userpicture;
	}

	public void setUserpicture(String userpicture) {
		this.userpicture = userpicture;
	}

	public String getUsernowlive() {
		return usernowlive;
	}

	public void setUsernowlive(String usernowlive) {
		this.usernowlive = usernowlive;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getUserinformation() {
		return userinformation;
	}

	public void setUserinformation(String userinformation) {
		this.userinformation = userinformation;
	}

	public String getUsertelephone() {
		return usertelephone;
	}

	public void setUsertelephone(String usertelephone) {
		this.usertelephone = usertelephone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public String getEntintroduction() {
		return entintroduction;
	}

	public void setEntintroduction(String entintroduction) {
		this.entintroduction = entintroduction;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGoint() {
		return goint;
	}

	public void setGoint(Integer goint) {
		this.goint = goint;
	}

	public Long getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Long activityTime) {
		this.activityTime = activityTime;
	}

	public List<ClaGoods> getClassList() {
		return classList;
	}

	public void setClassList(List<ClaGoods> classList) {
		this.classList = classList;
	}

	public List<String> getImgList(){
		if(StringUtils.isBlank(getUserpicture())) {
			return null;
		}
		JsonArray array = new JsonParser().parse(getUserpicture()).getAsJsonArray();
		List<String> list = new ArrayList<String>();
		
		for (JsonElement je : array) {
			list.add(je.getAsString());
		}
		return list;
	}
	
	public List<String> getImgsList() {
		return GsonUtils.array2List(getUserpicture());
	}
	*/
}
