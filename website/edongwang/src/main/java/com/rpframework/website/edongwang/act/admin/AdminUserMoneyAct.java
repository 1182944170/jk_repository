package com.rpframework.website.edongwang.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.UserMoney;
import com.rpframework.website.edongwang.service.UserMoneyService;

@Controller
@RequestMapping("/admin/user_money")
public class AdminUserMoneyAct extends AdminAct{
	@Resource UserMoneyService userMoneyService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = userMoneyService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user_money/list");
	}
	
	@RequestMapping("/{userId}/transfer")
	public String transfer(@PathVariable Integer userId, Map<Object, Object> model,RedirectAttributes attr){
		UserMoney userMoney = userMoneyService.getUserMoney(userId);
		model.put("userMoney", userMoney);
		return this.doPackageURI("user_money/transfer");
	}
	
	@RequestMapping("/{userId}/dotransfer")
	public String doTransfer(@ModelAttribute UserMoney userMoney, @RequestParam(value="ext", required=false) String ext, @RequestParam String userMoneyKVObj, Map<Object, Object> model,RedirectAttributes attr){
		KVObj kvObj = new KVObj(userMoneyKVObj, DictionarySettingUtils.getParameterValue("userMoney.kvObj." + userMoneyKVObj));
		JsonObject json = new JsonObject();
		json.addProperty("remark", ext);
		userMoneyService.operateMoney(userMoney.getUserId(), userMoney.getMoney(), kvObj, json.toString());
		setInfoMsg("操作成功!", attr);
		return redirect("/admin/user_money/list");
	}
	
}
