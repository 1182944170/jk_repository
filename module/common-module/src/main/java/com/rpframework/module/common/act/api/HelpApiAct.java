package com.rpframework.module.common.act.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.Help;
import com.rpframework.module.common.service.HelpSevice;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/api/common/help")
public class HelpApiAct extends CommonBaseAct {
	Gson gson = new Gson();
	@Resource HelpSevice helpSevice;
	@RequestMapping("list")
	public @ResponseBody JsonElement list(@RequestParam(value="pager",required=false) Pager<Help> pager) throws ParserException, InterruptedException{
		if(pager == null) {
			pager = new Pager<Help>();
		}
		helpSevice.doPager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Help> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Help help : list) {
			JsonObject o = gson.toJsonTree(help).getAsJsonObject();
			o.addProperty("typeString", DictionarySettingUtils.getParameterValue("help.type." + help.getType()));
			array.add(o);
		}
		return json;
	}
	
	@RequestMapping("/{aliasesTitle}")
	public @ResponseBody JsonElement view(@PathVariable String aliasesTitle, Map<String, Object> model){
		Help help = helpSevice.getHelpByaliasesTitle(aliasesTitle);
		JsonObject o = gson.toJsonTree(help).getAsJsonObject();
		o.addProperty("typeString", DictionarySettingUtils.getParameterValue("help.type." + help.getType()));
		return o;
	}
	@RequestMapping(value="/hrel/{aliasesTitle}" ,produces = "application/json; charset=utf-8")
	public @ResponseBody String hrel(@PathVariable String aliasesTitle,
			@RequestParam(value = "callback",required = false ) String callback,
			Map<String, Object> model){
		Help help = helpSevice.getHelpByaliasesTitle(aliasesTitle);
		JsonObject o = gson.toJsonTree(help).getAsJsonObject();
		o.addProperty("typeString", DictionarySettingUtils.getParameterValue("help.type." + help.getType()));
		return (callback + "(" + o + ")");
	}
	@RequestMapping("/{aliasesTitle}/page")
	public String viewPage(@PathVariable String aliasesTitle, Map<String, Object> model){
		Help help = helpSevice.getHelpByaliasesTitle(aliasesTitle);
		model.put("help", help);
		return "/api/help/page";
	}
}