package com.rpframework.website.yunpiaopiao.act.home;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.module.common.domain.Notice;
import com.rpframework.module.common.service.NoticeService;


@Controller
@RequestMapping("/notice")
public class NoticeAct {
	@Resource NoticeService noticeService;
	
	@RequestMapping("/{noticeId}")
	public String view(@PathVariable Integer noticeId, Map<String, Object> model){
		Notice notice = noticeService.select(noticeId);
		Assert.notNull(notice, "找不到通知ID ：" + noticeId);
		model.put("notice", notice);
		return "/home/notice/view";
		
	}
}
