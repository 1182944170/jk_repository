package com.rpframework.website.xtexam.act.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.website.xtexam.service.XTUserService;
import com.rpframework.website.xtexam.utils.cache.ExamClassifyCache;

@Controller
@RequestMapping("/api/cfg")
public class XTCfgApiAct extends BaseAct {
	@Resource XTUserService xtUserService;
	@Resource SMSService smsService;
	
	@RequestMapping("/classify")
	public @ResponseBody JsonElement classify(HttpSession session, HttpServletRequest request){
		ExamClassifyCache cache = CacheUtils.getIntance().get2(ExamClassifyCache.k);
		return cache.array;
	}
}
