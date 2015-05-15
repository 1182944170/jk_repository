package com.rpframework.module.common.act.api;

import java.util.List;

import javax.annotation.Resource;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.Notice;
import com.rpframework.module.common.service.NoticeService;
import com.rpframework.utils.CollectionUtils;

@Controller
@RequestMapping("/api/common/notice")
public class NoticeApiAct extends CommonBaseAct {
	Gson gson = new Gson();
	@Resource NoticeService noticeService;
	
	@RequestMapping("list")
	public @ResponseBody JsonElement list() throws ParserException, InterruptedException{
		List<Notice> list = noticeService.queryAllEffectiveNotice();
		JsonArray arr = new JsonArray();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Notice notice: list) {
				arr.add(gson.toJsonTree(notice));
			}
		}
		return arr;
	}
	
	@RequestMapping("{noticeId}")
	public @ResponseBody JsonElement view(@PathVariable Integer noticeId) throws ParserException, InterruptedException{
		Notice notice = noticeService.select(noticeId);
		Assert.notNull(notice);
		return gson.toJsonTree(notice);
	}
}