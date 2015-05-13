package com.rpframework.module.user.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.user.act.UserModuleBaseAct;
import com.rpframework.module.user.service.UserScoreLogService;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/user_score_log")
public class AdminUserScoreLogAct extends UserModuleBaseAct{
	@Resource UserScoreLogService userScoreLogService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = userScoreLogService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user_score_log/list");
	}
}
