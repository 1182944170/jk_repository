package com.rpframework.module.user.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.user.act.UserModuleBaseAct;
import com.rpframework.module.user.service.UserScoreService;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/user_score")
public class AdminUserScoreAct extends UserModuleBaseAct{
	@Resource UserScoreService userScoreService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = userScoreService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user_score/list");
	}
}
