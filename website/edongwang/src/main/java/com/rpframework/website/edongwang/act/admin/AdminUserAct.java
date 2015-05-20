package com.rpframework.website.edongwang.act.admin;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.TipsException;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.user.domain.UserBankCard;
import com.rpframework.module.user.service.UserBankCardService;
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.domain.UserSalesman;
import com.rpframework.website.edongwang.service.UserSalesmanService;
import com.rpframework.website.edongwang.service.UserService;

@Controller("edongwangAdminUserAct")
@RequestMapping("/admin/user")
public class AdminUserAct extends AdminAct {
	
	@Resource UserService userService;
	@Resource UserSalesmanService userSalesmanService;
	@Resource UserBankCardService userBankCardService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		pager = userService.getPager(pager);
		model.put("pager", pager);
		return doPackageURI("user/list");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/salesman_list")
	public String salesmanList(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		
		pager.getSearchMap().put("isSalesman", "1");
		pager = userService.getPager(pager);
		model.put("pager", pager);
		return doPackageURI("user/salesman_list");
	}
	
	@RequestMapping("/{userId}/on")
	public String setUserOn(@PathVariable Integer userId,  HttpServletRequest request, RedirectAttributes attr, Map<Object, Object> model){
		User user = userService.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		if(user.getState() != 1) {
			user.setState(1);
			userService.update(user);
			this.setInfoMsg("操作成功!", attr);
		} else {
			this.setInfoMsg("无需更改!", attr);
		}
		
		String redirect = request.getParameter("redirect");
		if(StringUtils.isNotBlank(redirect)) {
			return this.redirect(redirect);
		} else {
			return this.redirect("/admin/user/list");
		}
	}
	
	@RequestMapping("/{userId}/detail")
	public String detail(@PathVariable Integer userId, RedirectAttributes attr, Map<Object, Object> model){
		User user = userService.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		model.put("user", user);
		List<UserBankCard> userBankCardList = userBankCardService.getCardsByUserId(userId);
		if(CollectionUtils.isNotEmpty(userBankCardList)) {
			model.put("userBankCard", userBankCardList.get(0));
		}
		
		return doPackageURI("/user/detail");
	}
	
	@RequestMapping("/{userId}/resetPwd")
	public String resetPwd(@PathVariable Integer userId, HttpSession session, RedirectAttributes attr, Map<Object, Object> model){
		AdminUser adminUser = getSessionAdminUser(session);
		Assert.notNull(adminUser);
		User user = userService.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		String newPassword = String.valueOf(NumberUtils.random(6));
		String tips = user.getRealName() + "会员密码重置成功，新密码为:" + newPassword + " !";
		user.setPassword(AlgorithmUtils.encodePassword(newPassword, AlgorithmEnum.MD5));
		throw new TipsException(tips);
	}
	
	@RequestMapping("/{userId}/off")
	public String setUserOff(@PathVariable Integer userId, HttpServletRequest request, RedirectAttributes attr, Map<Object, Object> model){
		User user = userService.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		if(user.getState() != 0) {
			user.setState(0);
			userService.update(user);
			this.setInfoMsg("操作成功!", attr);
		} else {
			this.setInfoMsg("无需更改!", attr);
		}
		
		String redirect = request.getParameter("redirect");
		if(StringUtils.isNotBlank(redirect)) {
			return this.redirect(redirect);
		} else {
			return this.redirect("/admin/user/list");
		}
	}
	
	@RequestMapping("/{userId}/off_leader")
	public String setUserOffLeader(@PathVariable Integer userId, HttpServletRequest request, RedirectAttributes attr, Map<Object, Object> model){
		User user = userService.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		if(user.getIsSalesman() != 1 || user.getUserSalesman() == null || user.getUserSalesman().getIsLeader() != 1) {
			throw new IllegalArgumentException("该会员错误的状态!");
		}
		
		UserSalesman userSalesman = user.getUserSalesman();
		userSalesman.setIsLeader(0);
		userSalesmanService.update(userSalesman);
		
		this.setInfoMsg("操作成功!", attr);
		
		String redirect = request.getParameter("redirect");
		if(StringUtils.isNotBlank(redirect)) {
			return this.redirect(redirect);
		} else {
			return this.redirect("/admin/user/salesman_list");
		}
	}

	@RequestMapping("/{userId}/on_leader")
	public String setUserOnLeader(@PathVariable Integer userId, HttpServletRequest request, RedirectAttributes attr, Map<Object, Object> model){
		User user = userService.select(userId);
		Assert.notNull(user, "找不到用户：" + userId);
		if(user.getIsSalesman() != 1 || user.getUserSalesman() == null || user.getUserSalesman().getIsLeader() != 0) {
			throw new IllegalArgumentException("该会员错误的状态!");
		}
		
		UserSalesman userSalesman = user.getUserSalesman();
		userSalesman.setIsLeader(1);
		userSalesmanService.update(userSalesman);
		
		this.setInfoMsg("操作成功!", attr);
		
		String redirect = request.getParameter("redirect");
		if(StringUtils.isNotBlank(redirect)) {
			return this.redirect(redirect);
		} else {
			return this.redirect("/admin/user/salesman_list");
		}
	}
}
