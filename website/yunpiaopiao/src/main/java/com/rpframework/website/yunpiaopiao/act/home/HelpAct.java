package com.rpframework.website.yunpiaopiao.act.home;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.module.common.domain.Help;
import com.rpframework.module.common.service.HelpSevice;


@Controller
@RequestMapping("/help")
public class HelpAct {
	@Resource HelpSevice helpSevice;
	
	@RequestMapping("/{aliasesTitle}")
	public String view(@PathVariable String aliasesTitle, Map<String, Object> model){
		Help help = helpSevice.getHelpByaliasesTitle(aliasesTitle);
		Assert.notNull(help, "找不到 ：" + aliasesTitle);
		model.put("help", help);
		return "/home/help/view";
	}
}
