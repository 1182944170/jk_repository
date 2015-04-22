package com.rpframework.website.xtexam.domain;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.utils.GsonUtils;

/**
 * 
 * title: ExamCourses.java 
 * 科目分类
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月15日 下午5:45:00
 */
public class ExamCoursesClassify extends ExamClassify {
	/**描述*/  
	private static final long serialVersionUID = 1L;
	
	private Integer isOpen;
	
	public void wenSetPrivate(){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("isOpen", isOpen);
		super.ext = jsonObject.toString();
	}
	
	@Override
	public void setExt(String ext) {
		super.setExt(ext);
		
		if(StringUtils.isNotBlank(super.getExt())) {
			JsonObject jsonObject = new JsonParser().parse(super.getExt()).getAsJsonObject();
			this.isOpen = GsonUtils.getInt(jsonObject, "isOpen");
		}
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
		
		wenSetPrivate();
	}
}