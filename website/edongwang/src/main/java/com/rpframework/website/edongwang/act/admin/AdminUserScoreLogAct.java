package com.rpframework.website.edongwang.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.service.UserScoreLogService;

@Controller
@RequestMapping("/admin/user_score_log")
public class AdminUserScoreLogAct extends AdminAct{
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