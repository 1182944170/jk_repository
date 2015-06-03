package com.rpframework.website.edongwang.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.ScoreShop;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.service.ScoreShopService;
import com.rpframework.website.edongwang.service.UserScoreService;
import com.rpframework.website.edongwang.service.UserScoreShopLogService;
import com.rpframework.website.edongwang.service.UserService;
import com.rpframework.website.edongwang.utils.EConstants;

@Controller
@RequestMapping("/api/user_score_shop")
public class UserScoreShopApiAct extends BaseAct {
	Gson gson = new Gson();
	
	@Resource UserService userService;
	@Resource UserScoreService userScoreService;
	@Resource ScoreShopService scoreShopService;
	@Resource UserScoreShopLogService userScoreShopLogService;
	
	@RequestMapping("list")
	public @ResponseBody JsonElement list(@RequestParam(value = "pager", required = false) Pager<ScoreShop> pager, HttpSession session, HttpServletRequest request) {
		User user = getSessionUser(session);
		if (pager == null) {
			pager = new Pager<ScoreShop>();
		}
		
		pager.getSearchMap().put("checkVaildTime", String.valueOf(System.currentTimeMillis() / 1000));
		pager = scoreShopService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<ScoreShop> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (ScoreShop scoreShop : list) {
			JsonObject ssJson = new JsonObject();
			ssJson.addProperty("id", scoreShop.getId());
			ssJson.addProperty("img", TagUtils.getFileFullPath(scoreShop.getImg()));
			ssJson.addProperty("name", scoreShop.getName());
			ssJson.addProperty("remark", scoreShop.getRemark());
			ssJson.addProperty("score", scoreShop.getScore());
			ssJson.addProperty("type", scoreShop.getType());
			ssJson.addProperty("scoreShopRule", scoreShopService.checkScoreShopRule(scoreShop, user.getId()));
			array.add(ssJson);
		}
		
		return json;
	}
	
	@RequestMapping("/{scoreShopId}/buy")
	public @ResponseBody JsonElement buy(@PathVariable Integer scoreShopId,
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="concact", required=false) String concact,
			@RequestParam(value="areaCode", required=false) String areaCode,
			@RequestParam(value="address", required=false) String address,
			HttpSession session, HttpServletRequest request) {
		
		User user = getSessionUser(session);
		boolean flag = scoreShopService.buy(scoreShopId, user.getId(), name, concact, areaCode, address, EConstants.ScoreChannel.BUY_SCORE_SHOP);
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
}