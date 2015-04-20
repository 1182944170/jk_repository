package com.rpframework.module.adminbase.act;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.module.adminbase.domain.AdminRole;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.adminbase.service.AdminRoleService;
import com.rpframework.module.adminbase.service.AdminUserService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/adminuser")
public class AdminUserAct extends AdminBaseAct {
	@Resource AdminUserService adminUserService;
	@Resource AdminRoleService adminRoleService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = adminUserService.getAdminUserPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("adminuser/list");
	}
	
	@RequestMapping("/{adminUserId}/edit")
	public String edit(@PathVariable Integer adminUserId, Map<Object, Object> model,RedirectAttributes attr){
		AdminUser adminUser = adminUserService.adminUserDao.select(adminUserId);
		if(adminUser == null) {
			throw new AdminIllegalArgumentException("找不到用户：Id:" + adminUserId);
		}
		model.put("adminUser", adminUser);
		return this.add(attr);
	}
	
	@RequestMapping("/{adminUserId}/delete")
	public String delete(@PathVariable Integer adminUserId, Map<Object, Object> model,RedirectAttributes attr){
		AdminUser adminUser = adminUserService.adminUserDao.select(adminUserId);
		if(adminUser != null) {
			adminUserService.adminUserDao.delete(adminUserId);
			setInfoMsg("删除操作成功！", attr);
		}
		return redirect("/admin/adminuser/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr){
		return this.doPackageURI("adminuser/edit");
	}
	
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute AdminUser adminUser, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		
		if(adminUser == null || StringUtils.isBlank(adminUser.getUserName())
				||adminUser.getAdminRole() == null ||NumberUtils.isNotValid(adminUser.getAdminRole().getId())) {
			throw new AdminIllegalArgumentException("参数异常!");
		}
		//验证角色
		AdminRole adminRole = adminRoleService.adminRoleDao.select(adminUser.getAdminRole().getId());
		if(adminRole == null) {
			throw new AdminIllegalArgumentException("找不到用户角色：Id:" + adminUser.getAdminRole().getId());
		}
		
		AdminUser adminUserDB = adminUserService.findAdminUserByName(adminUser.getUserName());
		if(NumberUtils.isValid(adminUser.getId())) {//update
			if(adminUserDB == null) {
				throw new AdminIllegalArgumentException("更新用户时不存在的ID:" + adminUser.getId());
			}
			adminUserDB.setAdminRole(adminUser.getAdminRole());
			adminUserDB.setContact(adminUser.getContact());
			adminUserDB.setEmail(adminUser.getEmail());
			adminUserDB.setState(adminUser.getState());
			adminUserService.adminUserDao.update(adminUserDB);
		} else {//insert
			if(adminUserDB != null) {
				throw new AdminIllegalArgumentException("新增用户时已经存在的用户名:" + adminUser.getUserName());
			}
			String pwd = adminUser.getPwd();
			String md5Pwd = AlgorithmUtils.encodePassword(pwd, AlgorithmEnum.MD5);
			adminUser.setPwd(md5Pwd);
			
			adminUser.setRecordCreateTime(System.currentTimeMillis() / 1000);
			adminUser.setSelfAdmin(1);
			adminUser.setViewOnlyAdmin(1);
			adminUserService.adminUserDao.insert(adminUser);
		}
		
		setInfoMsg("操作成功！", attr);
		return redirect("/admin/adminuser/list");
	}
}