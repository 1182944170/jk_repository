package com.rpframework.module.adminbase.act;

import java.util.ArrayList;
import java.util.List;
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

import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.adminbase.domain.AdminRole;
import com.rpframework.module.adminbase.exception.AdminIllegalArgumentException;
import com.rpframework.module.adminbase.service.AdminRoleService;
import com.rpframework.module.adminbase.service.RoleAdminAuthResService;
import com.rpframework.module.adminbase.utils.cache.RoleAdminAuthResCache;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.NumberUtils;

@Controller
@RequestMapping("/admin/adminrole")
public class AdminRoleAct extends AdminBaseAct {
	@Resource AdminRoleService adminRoleService;
	@Resource RoleAdminAuthResService roleAdminAuthResService;
	
	@RequestMapping("/list")
	public String list(Map<Object, Object> model, RedirectAttributes attr){
		List<AdminRole> list = adminRoleService.findAll();
		model.put("list", list);
		return this.doPackageURI("adminrole/list");
	}
	
	@RequestMapping("/{adminRoleId}/edit")
	public String edit(@PathVariable Integer adminRoleId, Map<Object, Object> model,RedirectAttributes attr){
		AdminRole adminRole = adminRoleService.adminRoleDao.select(adminRoleId);
		if(adminRole == null) {
			throw new AdminIllegalArgumentException("找不到用户角色：Id:" + adminRoleId);
		}
		model.put("adminRole", adminRole);
		return this.add(attr);
	}
	
	@RequestMapping("/{adminRoleId}/delete")
	public String delete(@PathVariable Integer adminRoleId, Map<Object, Object> model,RedirectAttributes attr){
		AdminRole adminRole = adminRoleService.adminRoleDao.select(adminRoleId);
		if(adminRole != null) {
			if(!adminRoleService.checkCanDelete(adminRoleId)) {
				throw new AdminIllegalArgumentException("当存在有用户关联角色时 不能被删除 ！");
			}
			
			adminRoleService.deleteAdminRole(adminRoleId);
			setInfoMsg("删除操作成功！", attr);
		}
		return redirect("/admin/adminrole/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr){
		return this.doPackageURI("adminrole/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute AdminRole adminRole, @RequestParam(value="adminRoleCheck", required=false) List<Integer> roleAdminAuthList, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		if(adminRole == null || StringUtils.isBlank(adminRole.getName())) {
			throw new AdminIllegalArgumentException("参数异常!");
		}
		
		if(NumberUtils.isValid(adminRole.getId())) {//update
			adminRoleService.adminRoleDao.update(adminRole);
			
			//如果修改的是正在登陆的，则同步
			if(adminRole.getId().equals(super.getSessionAdminUser(session).getAdminRole().getId())) {
				super.getSessionAdminUser(session).setAdminRole(adminRole);
			}
		} else {
			adminRoleService.adminRoleDao.insert(adminRole);
		}
		
		if(CollectionUtils.isEmpty(roleAdminAuthList)) {
			roleAdminAuthList = new ArrayList<Integer>();
		}
		
		roleAdminAuthResService.resetRoleAdminAuthRes(adminRole, roleAdminAuthList);
		CacheUtils.getIntance().reInit(RoleAdminAuthResCache.k);
		
		setInfoMsg("操作成功！", attr);
		return redirect("/admin/adminrole/list");
	}
}