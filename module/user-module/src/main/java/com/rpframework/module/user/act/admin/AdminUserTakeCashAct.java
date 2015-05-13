package com.rpframework.module.user.act.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.module.adminbase.domain.AdminUser;
import com.rpframework.module.user.act.UserModuleBaseAct;
import com.rpframework.module.user.domain.UserTakeCash;
import com.rpframework.module.user.service.UserTakeCashService;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/user_take_cash")
public class AdminUserTakeCashAct extends UserModuleBaseAct{
	@Resource UserTakeCashService userTakeCashService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = userTakeCashService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user_take_cash/list");
	}
	
	@RequestMapping("/{userTakeCashId}/edit")
	public String edit(@PathVariable Integer userTakeCashId, Map<Object, Object> model,RedirectAttributes attr){
		UserTakeCash userTakeCash = userTakeCashService.select(userTakeCashId);
		Assert.notNull(userTakeCash, "不存在的ID:" + userTakeCashId);
		model.put("userTakeCash", userTakeCash);
		return this.doPackageURI("user_take_cash/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute UserTakeCash userTakeCash,@RequestParam String userMoneyKVObj, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		AdminUser adminUser = getSessionAdminUser(session);
		userTakeCash.setVerifyUId(adminUser.getId());
		KVObj kvObj = new KVObj(userMoneyKVObj, DictionarySettingUtils.getParameterValue("userMoney.kvObj." + userMoneyKVObj));
		userTakeCashService.doDealTakeCash(userTakeCash, kvObj);
		setInfoMsg("操作成功！", attr);
		return redirect("list");
	}
}
