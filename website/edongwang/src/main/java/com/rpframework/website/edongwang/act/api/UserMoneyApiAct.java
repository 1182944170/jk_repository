package com.rpframework.website.edongwang.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.module.user.domain.UserMoney;
import com.rpframework.module.user.domain.UserMoneyLog;
import com.rpframework.module.user.domain.UserTakeCash;
import com.rpframework.module.user.service.UserMoneyLogService;
import com.rpframework.module.user.service.UserMoneyService;
import com.rpframework.module.user.service.UserTakeCashService;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.service.UserService;

@Controller
@RequestMapping("/api/user_money")
public class UserMoneyApiAct extends BaseAct {
	Gson gson = new Gson();
	
	@Resource UserService userService;
	@Resource UserMoneyService userMoneyService;
	@Resource UserTakeCashService userTakeCashService;
	@Resource UserMoneyLogService userMoneyLogService;
	
	@RequestMapping("/money")
	public @ResponseBody JsonElement myDate(HttpSession session, HttpServletRequest request) {
		User user = getSessionUser(session);
		UserMoney userMoney = userMoneyService.getUserMoney(user.getId());
		return gson.toJsonTree(userMoney);
	}
	
	@RequestMapping("/money_log")
	public @ResponseBody JsonElement moneyLog(@RequestParam(value = "pager", required = false) Pager<UserMoneyLog> pager,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		if (pager == null) {
			pager = new Pager<UserMoneyLog>();
		}
		
		pager.getSearchMap().put("userId", String.valueOf(user.getId()));
		pager = userMoneyLogService.getPager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("totalCount", pager.getTotalCount());
		List<UserMoneyLog> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (UserMoneyLog userMoneyLog : list) {
			array.add(gson.toJsonTree(userMoneyLog));
		}
		return json;
	}
	
	@RequestMapping("/apply_take")
	public @ResponseBody JsonElement applyTake(
			@RequestParam Double money,
			@RequestParam Integer bankCardId,
			@RequestParam(value="remark", required=false) String remark,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		boolean flag = userTakeCashService.applyTakeCash(user.getId(), money, bankCardId, remark);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	@RequestMapping("/apply_take_log")
	public @ResponseBody JsonElement applyTake(@RequestParam(value = "pager", required = false) Pager<UserTakeCash> pager,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		if (pager == null) {
			pager = new Pager<UserTakeCash>();
		}
		pager.getSearchMap().put("userId", String.valueOf(user.getId()));
		pager = userTakeCashService.getPager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("totalCount", pager.getTotalCount());
		List<UserTakeCash> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (UserTakeCash userTakeCash : list) {
			array.add(gson.toJsonTree(userTakeCash));
		}
		return json;
	}
}