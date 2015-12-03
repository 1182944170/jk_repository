package com.rpframework.website.luoluo.act.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.website.luoluo.domain.Banks;
import com.rpframework.website.luoluo.service.BanksSercice;
@Controller
@RequestMapping("api/bank")
public class ApiBanksAct extends BaseAct{
	@Resource BanksSercice bankssercice;
	
	/**
	 * 显示银行卡
	 * @param cardid
	 * @return
	 */
	@RequestMapping("list")
	public  @ResponseBody JsonElement list(
			@RequestParam(required=false) Integer cardid){
		JsonObject json=new JsonObject();
		Banks Banks=bankssercice.selectone(cardid);
		json.addProperty("Bankname", Banks.getBankname());
		json.addProperty("Bankprivice", TagUtils.getFileFullPath(Banks.getBankprivice()));
		json.addProperty("id", Banks.getId());
		return json;
	}
}
