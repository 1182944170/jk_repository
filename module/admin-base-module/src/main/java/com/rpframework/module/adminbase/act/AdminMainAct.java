package com.rpframework.module.adminbase.act;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.adminbase.dao.IAdminAuthResDao;
import com.rpframework.module.adminbase.dao.IAdminMenuDao;
import com.rpframework.module.adminbase.service.AdminUserService;

@Controller
@RequestMapping("/admin/main")
public class AdminMainAct extends AdminBaseAct {
	@Resource AdminUserService adminUserService;
	@Resource IAdminMenuDao adminMenuDao;
	@Resource IAdminAuthResDao adminAuthResDao;
	
	@RequestMapping
	public String execute(HttpSession session, RedirectAttributes attr){
		return this.doPackageURI("main/main");
	}
}
