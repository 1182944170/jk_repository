package com.rpframework.website.datangwenshen.act.front;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rpframework.core.BaseAct;
import com.rpframework.module.common.domain.Notice;
import com.rpframework.module.common.service.NoticeService;
import com.rpframework.utils.Pager;
import com.rpframework.website.datangwenshen.domain.Message;
import com.rpframework.website.datangwenshen.service.MessageService;
import com.rpframework.website.datangwenshen.service.PictureService;
import com.rpframework.website.datangwenshen.utils.DTWSConstants;
import com.rpframework.website.datangwenshen.utils.DTWSConstants.Picuter;
import com.rpframework.website.datangwenshen.utils.DaTangWenShenConfig;

@Controller
@RequestMapping("/")
public class IndexAct extends BaseAct {
	@Resource DaTangWenShenConfig  daTangWenShenConfig;
	@Resource PictureService pictureService;
	@Resource NoticeService noticeService;
	@Resource MessageService messageService;
	
	@RequestMapping
	public String execute() {
		return index();
	}
	
	@RequestMapping("index")
	public String index() {
		return "/" + daTangWenShenConfig.STYLE + "/index";
	}
	
	//============about start==================
	@RequestMapping("about")
	public String about() {
		return "/" + daTangWenShenConfig.STYLE + "/about/about";
	}
	
	@RequestMapping("about/introduction")
	public String aboutIntroduction() {
		return "/" + daTangWenShenConfig.STYLE + "/about/introduction";
	}
	
	@RequestMapping("about/team")
	public String aboutTeam() {
		return "/" + daTangWenShenConfig.STYLE + "/about/team";
	}
	
	@RequestMapping("about/price")
	public String aboutPrice() {
		return "/" + daTangWenShenConfig.STYLE + "/about/price";
	}
	//============about end==================
	
	
	//============作品相关 start==================
	@SuppressWarnings("rawtypes")
	@RequestMapping("work")
	public String work(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model) {
		this.doSearchPicuter(pager, DTWSConstants.Picuter.SOURCE_WORK, model);
		return "/" + daTangWenShenConfig.STYLE + "/picuter/list";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void doSearchPicuter(Pager pager, int source,Map<Object, Object> model) {
		if(pager == null) {
			pager = new Pager();
		}
		pager.getSearchMap().put("source", String.valueOf(source));
		pictureService.getPager(pager);
		
		model.put("pager", pager);
		model.put("types", pictureService.getTypesBySource(source));
		model.put("source", source);
	}
	@RequestMapping("work/{id}/detail")
	public String workDetail(@PathVariable Integer id,Map<Object, Object> model) {
		Picuter picuter = pictureService.select(id);
		Assert.notNull(picuter, "找不到作品ID:" + id);
		model.put("picuter", picuter);
		return "/" + daTangWenShenConfig.STYLE + "/picuter/detail";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("material")
	public String material(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model) {
		this.doSearchPicuter(pager, DTWSConstants.Picuter.SOURCE_MATERIAL, model);
		return "/" + daTangWenShenConfig.STYLE + "/picuter/list";
	}
	
	@RequestMapping("material/{id}/detail")
	public String materialDetail(@PathVariable Integer id,Map<Object, Object> model) {
		Picuter picuter = pictureService.select(id);
		Assert.notNull(picuter, "找不到作品ID:" + id);
		model.put("picuter", picuter);
		return "/" + daTangWenShenConfig.STYLE + "/picuter/detail";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("piercing")
	public String piercing(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model) {
		this.doSearchPicuter(pager, DTWSConstants.Picuter.SOURCE_PIERCING, model);
		return "/" + daTangWenShenConfig.STYLE + "/picuter/list";
	}
	
	@RequestMapping("piercing/{id}/detail")
	public String piercingDetail(@PathVariable Integer id,Map<Object, Object> model) {
		Picuter picuter = pictureService.select(id);
		Assert.notNull(picuter, "找不到作品ID:" + id);
		model.put("picuter", picuter);
		return "/" + daTangWenShenConfig.STYLE + "/picuter/detail";
	}
	
	//============作品相关 end==================
	
	@RequestMapping("wash")
	public String wash() {
		return "/" + daTangWenShenConfig.STYLE + "/wash";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("news")
	public String news(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model) {
		if(pager == null) {
			pager = new Pager();
		}
		noticeService.getNoticePager(pager);
		model.put("pager", pager);
		return "/" + daTangWenShenConfig.STYLE + "/news/list";
	}
	
	@RequestMapping("news/{newsId}/detail")
	public String newsDetail(@PathVariable Integer newsId,Map<Object, Object> model) {
		Notice notice = noticeService.select(newsId);
		model.put("notice", notice);
		return "/" + daTangWenShenConfig.STYLE + "/news/detail";
	}
	
	@RequestMapping("train")
	public String train() {
		return "/" + daTangWenShenConfig.STYLE + "/train";
	}
	
	@RequestMapping("contact")
	public String contact() {
		return "/" + daTangWenShenConfig.STYLE + "/contact";
	}
	
	@RequestMapping("online")
	public String online() {
		return "/" + daTangWenShenConfig.STYLE + "/online";
	}
	
	@RequestMapping("commit_message")
	public String commitMessage(@ModelAttribute Message message) {
		messageService.insert(message);
		return redirect("/index");
	}
}


