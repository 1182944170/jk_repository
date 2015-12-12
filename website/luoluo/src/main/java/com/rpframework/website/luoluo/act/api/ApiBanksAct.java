package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Banks;
import com.rpframework.website.luoluo.service.BanksSercice;
@Controller
@RequestMapping("api/bank")
public class ApiBanksAct extends BaseAct{
	Gson gson = new Gson();
	@Resource BanksSercice bankssercice;
	
	/**
	 * 显示银行卡
	 * @param cardid
	 * @return
	 */
	@RequestMapping("listone")
	public  @ResponseBody JsonElement listone(@RequestParam(required=false) Integer cardid){
		JsonObject json=new JsonObject();
		Banks Banks=bankssercice.selectone(cardid);
		json.addProperty("Bankname", Banks.getBankname());
		json.addProperty("Bankprivice", TagUtils.getFileFullPath(Banks.getBankprivice()));
		json.addProperty("id", Banks.getId());
		return json;
	}
	/**
	 * 显示银行卡
	 * @param cardid
	 * @return
	 */
	@RequestMapping("list")
	public  @ResponseBody JsonElement list(@RequestParam(value="pager",required=false) Pager<Banks> pager ){
		if(pager==null){
			pager=new Pager<Banks>();
		}
		bankssercice.getPager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Banks> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Banks act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
}
