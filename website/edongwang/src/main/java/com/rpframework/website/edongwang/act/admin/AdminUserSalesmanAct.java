package com.rpframework.website.edongwang.act.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.website.edongwang.domain.UserSalesman;
import com.rpframework.website.edongwang.service.UserSalesmanService;

@Controller
@RequestMapping("/admin/usersalesman")
public class AdminUserSalesmanAct extends AdminAct {
	
	@Resource UserSalesmanService userSalesmanService;
	
	@RequestMapping("/{userId}/edit")
	public String edit(@PathVariable Integer userId, Map<Object, Object> model,RedirectAttributes attr){
		UserSalesman userSalesman = userSalesmanService.getUserSalesmanByUserId(userId);
		Assert.notNull(userSalesman, "不存在的ID:" + userId);
		model.put("userSalesman", userSalesman);
		return this.add(attr, model);
	}

	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model) {
		return this.doPackageURI("usersalesman/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute UserSalesman userSalesman, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		userSalesmanService.doSaveOrUpdate(userSalesman);
		setInfoMsg("操作成功！", attr);
		return redirect("/admin/user/list");
	}
}