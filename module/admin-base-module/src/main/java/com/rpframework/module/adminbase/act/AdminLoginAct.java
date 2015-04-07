package com.rpframework.module.adminbase.act;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.adminbase.dao.IAdminAuthResDao;
import com.rpframework.module.adminbase.dao.IAdminMenuDao;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.adminbase.service.AdminUserService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;

@Controller
@RequestMapping("/admin")
public class AdminLoginAct extends AdminBaseAct {
	@Resource AdminUserService adminUserService;
	@Resource IAdminMenuDao adminMenuDao;
	@Resource IAdminAuthResDao adminAuthResDao;
	
	@RequestMapping
	public String execute(HttpSession session, Map<Object, Object> model, RedirectAttributes attr){
		if(this.getSessionAdminUser(session) != null) {
			return redirect("/admin/main");
		}
		
		return redirect("/admin/login");
	}
	
	@RequestMapping("logout")
	public String execute(HttpSession session){
		if(this.getSessionAdminUser(session) != null) {
			session.removeAttribute(SESSION_ADMIN_USER_KEY);
		}
		
		return redirect("/admin/login");
	}
	
	@RequestMapping("login")
	public String login(HttpSession session, Map<Object, Object> model, RedirectAttributes attr){
		if(this.getSessionAdminUser(session) != null) {
			return redirect("/admin/main");
		}
		
		return this.doPackageURI("login");
	}
	
	@RequestMapping("/dologin")
	public String doLogin(@ModelAttribute AdminUser adminUser, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		
		if(this.getSessionAdminUser(session) != null) {
			return redirect("/admin/main");
		}
		attr.addFlashAttribute("adminUser", adminUser);
		
		if(StringUtils.isBlank(adminUser.getUserName())) {
			this.setErrorMsg("用户名不能为空!", attr);
			return redirect("/admin/login");
		}
		
		AdminUser adminUser2 = adminUserService.findAdminUserByName(adminUser.getUserName());
		if(adminUser2 == null) {
			this.setErrorMsg("用户名不存在!", attr);
			return redirect("/admin/login");
		}
		
		String password = AlgorithmUtils.encodePassword(adminUser.getPwd(), AlgorithmEnum.MD5) ;
		if (!StringUtils.equals(password, adminUser2.getPwd())) {
			super.setErrorMsg("密码不正确！", attr);
			return redirect("/admin/login");
		}
		
		if (adminUser2.getState() != 1) {
			super.setErrorMsg("您的帐户不是启用状态！", attr);
			return redirect("/admin/login");
		}
		
		if (adminUser2.getAdminRole().getState() != 1) {
			super.setErrorMsg("您的用户所在的角色不是启用状态！", attr);
			return redirect("/admin/login");
		}
		
		adminUser2.setLastLoginIp(adminUser2.getLoginIp()) ;
		adminUser2.setLoginIp(request.getRemoteAddr());
		
		adminUser2.setLastLoginTime(adminUser2.getLoginTime());
		adminUser2.setLoginTime(new Date().getTime()/1000);
		
		adminUserService.adminUserDao.update(adminUser2);
		
		session.setAttribute(SESSION_ADMIN_USER_KEY, adminUser2);
		return redirect("/admin/main");
	}
}
