package com.rpframework.module.common.act;

import java.util.List;

import javax.annotation.Resource;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.rpframework.module.common.domain.Notice;
import com.rpframework.module.common.service.NoticeService;
import com.rpframework.utils.CollectionUtils;

@Controller
@RequestMapping("/common/notice")
public class NoticeAct extends CommonBaseAct {
	@Resource NoticeService noticeService;
	
	@RequestMapping("list")
	public @ResponseBody JsonElement list() throws ParserException, InterruptedException{
		Gson gson = new Gson();
		List<Notice> list = noticeService.queryAllEffectiveNotice();
		JsonArray arr = new JsonArray();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Notice notice: list) {
				arr.add(gson.toJsonTree(notice));
			}
		}
		return arr;
	}
}