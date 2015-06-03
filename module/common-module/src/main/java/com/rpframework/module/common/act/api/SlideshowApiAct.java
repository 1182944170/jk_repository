package com.rpframework.module.common.act.api;

import java.util.List;

import javax.annotation.Resource;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.Slideshow;
import com.rpframework.module.common.service.SlideshowService;
import com.rpframework.utils.CollectionUtils;

@Controller
@RequestMapping("/api/common/slideshow")
public class SlideshowApiAct extends CommonBaseAct {
	Gson gson = new Gson();
	@Resource SlideshowService slideshowService;
	
	private JsonObject packageSlideshow(Slideshow slideshow){
		JsonObject asJsonObject = gson.toJsonTree(slideshow).getAsJsonObject();
		asJsonObject.addProperty("icon", TagUtils.getFileFullPath(slideshow.getIcon()));
		return asJsonObject;
	}
	@RequestMapping("list")
	public @ResponseBody JsonElement list(@RequestParam Integer type) throws ParserException, InterruptedException{
		List<Slideshow> list = slideshowService.queryEffectiveSlideshow(type);
		JsonArray arr = new JsonArray();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Slideshow slideshow: list) {
				arr.add(packageSlideshow(slideshow));
			}
		}
		return arr;
	}
	
	@RequestMapping("{slideshowId}")
	public @ResponseBody JsonElement view(@PathVariable Integer noticeId) throws ParserException, InterruptedException{
		Slideshow slideshow = slideshowService.select(noticeId);
		Assert.notNull(slideshow);
		return packageSlideshow(slideshow);
	}
}