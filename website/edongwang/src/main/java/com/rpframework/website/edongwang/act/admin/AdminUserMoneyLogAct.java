package com.rpframework.website.edongwang.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.service.UserMoneyLogService;

@Controller
@RequestMapping("/admin/user_money_log")
public class AdminUserMoneyLogAct extends AdminAct{
	@Resource UserMoneyLogService userMoneyLogService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = userMoneyLogService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user_money_log/list");
	}
}
