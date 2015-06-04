package com.rpframework.website.edongwang.act.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.module.common.domain.CfgBank;
import com.rpframework.module.common.domain.CfgBankAddress;
import com.rpframework.module.common.service.CfgBankAddressService;
import com.rpframework.module.common.service.CfgBankService;
import com.rpframework.website.edongwang.domain.CfgScore;
import com.rpframework.website.edongwang.service.CfgScoreService;

@Controller
@RequestMapping("/api/cfg")
public class CfgApiAct extends BaseAct {
	Gson gson = new Gson();
	@Resource CfgBankAddressService cfgBankAddressService;
	@Resource CfgBankService cfgBankService;
	@Resource CfgScoreService cfgScoreService;
	
	/**
	 * 面积类型 
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/surface_types")
	public @ResponseBody JsonElement surfaceTypes(Map<Object, Object> model, RedirectAttributes attr) {
		return DictionarySettingUtils.getMapJsonArrayByKey("house.surfaceType");
	}
	
	/**
	 * 面积类型 
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/contactus")
	public @ResponseBody JsonElement contactUs(Map<Object, Object> model, RedirectAttributes attr) {
		return DictionarySettingUtils.getMapJsonArrayByKey("contactus");
	}

	/**
	 * 物业类型 
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/property_types")
	public @ResponseBody JsonElement propertyTypes(Map<Object, Object> model, RedirectAttributes attr) {
		return DictionarySettingUtils.getMapJsonArrayByKey("house.propertyType");
	}
	
	/**
	 * 意向总价
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/total_price_types")
	public @ResponseBody JsonElement totalPriceTypes(Map<Object, Object> model, RedirectAttributes attr) {
		return DictionarySettingUtils.getMapJsonArrayByKey("house.totalPriceType");
	}
	
	@RequestMapping("/bank_list")
	public @ResponseBody JsonElement bankList(Map<Object, Object> model, RedirectAttributes attr) {
		List<CfgBank> list = cfgBankService.queryAllEffective();
		JsonArray array = new JsonArray();
		for (CfgBank cfgBank : list) {
			JsonObject json = new JsonObject();
			json.addProperty("id", cfgBank.getId());
			json.addProperty("name", cfgBank.getName());
			array.add(json);
		}
		return array;
	}
	
	@RequestMapping("/cfg_scores")
	public @ResponseBody JsonElement cfgScores(Map<Object, Object> model, RedirectAttributes attr) {
		List<CfgScore> list = cfgScoreService.queryAll();
		JsonArray array = new JsonArray();
		for (CfgScore cfgScore : list) {
			array.add(gson.toJsonTree(cfgScore));
		}
		return array;
	}
	
	@RequestMapping("/bank_address_list")
	public @ResponseBody JsonElement bakAddressList(Map<Object, Object> model, RedirectAttributes attr) {
		List<CfgBankAddress> list = cfgBankAddressService.queruAllEffective();
		JsonArray array = new JsonArray();
		
		for (CfgBankAddress cfgBankAddress : list) {
			JsonObject json = new JsonObject();
			json.addProperty("id", cfgBankAddress.getId());
			json.addProperty("countyCode", cfgBankAddress.getCountyCode());
			json.addProperty("address", cfgBankAddress.getAddress());
			json.addProperty("bankId", cfgBankAddress.getCfgBank().getId());
			
			array.add(json);
		}
		return array;
	}
}
