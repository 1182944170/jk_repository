package com.rpframework.website.yunpiaopiao.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.domian.User;
import com.rpframework.website.yunpiaopiao.service.UserService;

@Controller("admin_user")
@RequestMapping("/admin/user")
public class AdminUserAct extends AdminAct {
	@Resource UserService userService;

	@RequestMapping("/list")
	public String list(@RequestParam(required=false) Pager<User> pager, Map<Object, Object> model, RedirectAttributes attr) {
		if(pager == null) {
			pager = new Pager<User>();
		}
		
		pager = userService.getUserPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user/list");
	}
	
	@RequestMapping("/{userId}/on")
	public String setUserOn(@PathVariable Integer userId, RedirectAttributes attr, Map<Object, Object> model){
		User user = userService.userDao.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		if(user.getState() != 1) {
			user.setState(1);
			userService.userDao.update(user);
			this.setInfoMsg("操作成功!", attr);
		} else {
			this.setInfoMsg("无需更改!", attr);
		}
		
		return this.redirect("/admin/user/list");
	}
	
	@RequestMapping("/{userId}/off")
	public String setUserOff(@PathVariable Integer userId, RedirectAttributes attr, Map<Object, Object> model){
		User user = userService.userDao.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		if(user.getState() != 0) {
			user.setState(0);
			userService.userDao.update(user);
			this.setInfoMsg("操作成功!", attr);
		} else {
			this.setInfoMsg("无需更改!", attr);
		}
		
		return this.redirect("/admin/user/list");
	}
}
