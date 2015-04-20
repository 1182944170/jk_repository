package com.rpframework.website.xtexam.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.xtexam.domain.XTUser;
import com.rpframework.website.xtexam.service.XTUserService;

@Controller
@RequestMapping("/admin/user")
public class AdminXTUserAct extends AdminAct {
	@Resource XTUserService userService;

	@RequestMapping("/list")
	public String list(@RequestParam(required=false) Pager<XTUser> pager, Map<Object, Object> model, RedirectAttributes attr) {
		if(pager == null) {
			pager = new Pager<XTUser>();
		}
		
		pager = userService.getXTUserPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user/list");
	}
	
	@RequestMapping("/{userId}/on")
	public String setUserOn(@PathVariable Integer userId, RedirectAttributes attr, Map<Object, Object> model){
		XTUser xtUser = userService.xtUserDao.select(userId);
		Assert.notNull(xtUser, "找不到用户：" + userId);
		if(xtUser.getState() != 1) {
			xtUser.setState(1);
			userService.xtUserDao.update(xtUser);
			this.setInfoMsg("操作成功!", attr);
		} else {
			this.setInfoMsg("无需更改!", attr);
		}
		
		return this.redirect("/admin/user/list");
	}
	
	@RequestMapping("/{userId}/off")
	public String setUserOff(@PathVariable Integer userId, RedirectAttributes attr, Map<Object, Object> model){
		XTUser xtUser = userService.xtUserDao.select(userId);
		Assert.notNull(xtUser, "找不到用户：" + userId);
		if(xtUser.getState() != 0) {
			xtUser.setState(0);
			userService.xtUserDao.update(xtUser);
			this.setInfoMsg("操作成功!", attr);
		} else {
			this.setInfoMsg("无需更改!", attr);
		}
		
		return this.redirect("/admin/user/list");
	}
}
