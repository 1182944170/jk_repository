package com.rpframework.core;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class BaseAct {
	public static final String SESSION_ADMIN_USER_KEY = "sessionAdminUser";
	public static final String SESSION_USER_KEY = "sessionUser";
	
	@SuppressWarnings("unchecked")
	public <T> T getSessionAdminUser(HttpSession session){
		Object attribute = session.getAttribute(SESSION_ADMIN_USER_KEY);
		if(attribute == null) {
			return null;
		}
		
		return (T) attribute;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getSessionUser(HttpSession session){
		Object attribute = session.getAttribute(SESSION_USER_KEY);
		if(attribute == null) {
			return null;
		}
		
		return (T) attribute;
	}
	
	private String errorMsg = "";
	private String infoMsg = "";
	
	public ModelAndView redirect2(String uri) {
		return new ModelAndView(redirect(uri));
	}
	
	public String redirect(String uri){
		return "redirect:" + uri;
	}
	
	public ModelAndView forward2(String uri) {
		return new ModelAndView(forward(uri));
	}
	
	public String forward(String uri){
		return "forward:" + uri;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}
	
	public void setErrorMsg(String string, Map<Object, Object> model) {
		this.setErrorMsg(string);
		model.put("errorMsg", this.errorMsg);
	}
	
	public void setErrorMsg(String string, RedirectAttributes attr) {
		this.setErrorMsg(string);
		attr.addFlashAttribute("errorMsg", this.errorMsg);
	}
	
	public void setInfoMsg(String string, Map<Object, Object> model) {
		this.setInfoMsg(infoMsg);
		model.put("infoMsg", this.infoMsg);
	}
	
	public void setInfoMsg(String string, RedirectAttributes attr) {
		this.setInfoMsg(string);
		attr.addFlashAttribute("infoMsg", this.infoMsg);
	}
}
