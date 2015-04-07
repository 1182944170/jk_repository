package com.rpframework.module.adminbase.act;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.module.adminbase.dao.IAdminAuthResDao;
import com.rpframework.module.adminbase.dao.IAdminMenuDao;
import com.rpframework.module.adminbase.dao.IRoleAdminAuthResDao;
import com.rpframework.module.adminbase.dao.ITestDao;
import com.rpframework.module.adminbase.domain.AdminAuthRes;
import com.rpframework.module.adminbase.domain.AdminMenu;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.adminbase.domain.TestVO;
import com.rpframework.module.adminbase.service.AdminUserService;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/test")
public class TestAct extends AdminBaseAct{
	@Resource ITestDao testDao;
	@Resource AdminUserService adminUserService;
	@Resource IAdminMenuDao adminMenuDao;
	@Resource IAdminAuthResDao adminAuthResDao;
	@Resource IRoleAdminAuthResDao roleAdminAuthResDao;
	
	@RequestMapping("/t")
	public String t(Map<Object, Object> Model){
		
		roleAdminAuthResDao.select(new Integer(1));
		roleAdminAuthResDao.getRoleAdminAuthResList(new Integer(1));
		
		
		Pager<AdminUser> pager = new Pager<AdminUser>();
		pager.getSearchMap().put("oo", "GGGG");
		adminUserService.getAdminUserPager(pager);
		
		
		AdminUser select = adminUserService.adminUserDao.select(new Integer(2));
		select.setId(2);
		select.getAdminRole().setId(2);
		select.setLastLoginTime(System.currentTimeMillis()/1000);
		select.setContact("dddd");
		boolean update = adminUserService.adminUserDao.update(select);
		System.out.println("更新是否成功："  + update);
		
		
		AdminUser select2 = adminUserService.adminUserDao.select(new Integer(2));
		System.out.println("查找成功："  + select2);
		
		select2.setId(null);
		select2.setAdminRole(select2.getAdminRole());
		select2.setContact("2345");
		select2.setLastLoginIp("fff");
		select2.setPwd("ddd");
		select2.setState(1);
		select2.setUserName("test_auto_add");
		
		
		Boolean insert = adminUserService.adminUserDao.insert(select2);
		System.out.println("insert === " + insert);


		List<AdminMenu> menuListByParentId = adminMenuDao.getMenuListByParentId(10107);
		System.out.println(menuListByParentId);
		
		AdminAuthRes adminAuthRes = adminAuthResDao.select(new Integer(5));
		System.out.println(adminAuthRes);
		
		TestVO testVO = testDao.getTestVO();
		System.out.println("ID:" + testVO.getId());
		return this.doPackageURI("t");
	}
}
