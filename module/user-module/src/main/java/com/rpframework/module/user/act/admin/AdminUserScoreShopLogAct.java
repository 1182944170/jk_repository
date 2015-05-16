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

import com.rpframework.module.user.act.UserModuleBaseAct;
import com.rpframework.module.user.domain.ScoreShop;
import com.rpframework.module.user.domain.UserScoreShopLog;
import com.rpframework.module.user.service.ScoreShopService;
import com.rpframework.module.user.service.UserScoreShopLogService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/user_score_shop_log")
public class AdminUserScoreShopLogAct extends UserModuleBaseAct{
	@Resource UserScoreShopLogService userScoreShopLogService;
	@Resource ScoreShopService scoreShopService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		
		if(pager.getSearchMap().containsKey("scoreShopId")) {
			int scoreShopId = NumberUtils.parseInt(pager.getSearchMap().get("scoreShopId").toString());
			ScoreShop scoreShop = scoreShopService.select(scoreShopId);
			model.put("scoreShop", scoreShop);
		}
		pager = userScoreShopLogService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user_score_shop_log/list");
	}

	@RequestMapping("/{userScoreShopLogId}/edit")
	public String edit(@PathVariable Integer userScoreShopLogId, Map<Object, Object> model,RedirectAttributes attr){
		UserScoreShopLog userScoreShopLog = userScoreShopLogService.select(userScoreShopLogId);
		Assert.notNull(userScoreShopLog, "不存在的ID:" + userScoreShopLogId);
		model.put("userScoreShopLog", userScoreShopLog);
		return this.add(attr, model);
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("user_score_shop_log/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute UserScoreShopLog userScoreShopLog, HttpSession session, HttpServletRequest request,RedirectAttributes attr) {
		userScoreShopLogService.deal(userScoreShopLog.getId(), userScoreShopLog.getSendShopState());
		setInfoMsg("操作成功！", attr);
		return redirect("list");
	}
}
