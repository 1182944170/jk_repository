package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.service.ClassificationService;

@Controller
@RequestMapping("api/cassifcation")
public class ApiClassificationAct {
	Gson gson = new Gson();
	@Resource ClassificationService classificationService;
	
	/**
	 * 查询前9个
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/cassifcation_list")
	public @ResponseBody JsonElement cassifcationlist(@RequestParam(value="pager",required=false) Pager<Classification> pager 
			) throws ParserException, InterruptedException{
 		if(pager==null){
 			pager=new Pager<Classification>();
 		}
		JsonObject json = new JsonObject();
		List<Classification> list = classificationService.queryAlllesei();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Classification act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	/**
	 * 查询所有
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/cassifcation_listAll")
	public @ResponseBody JsonElement cassifcationlistAll(@RequestParam(value="pager",required=false) Pager<Classification> pager 
			) throws ParserException, InterruptedException{
		if(pager==null){
			pager=new Pager<Classification>();
		}
		classificationService.getPager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Classification> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Classification act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}

}
