package com.rpframework.website.edongwang.act.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.House;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.service.HouseRecommendService;
import com.rpframework.website.edongwang.service.HouseService;

@Controller
@RequestMapping("/api/house")
public  @ResponseBody class HouseApiAct extends BaseAct {
	Gson gson = new Gson();
	@Resource HouseService houseService;
	@Resource HouseRecommendService houseRecommendService;
	
	@RequestMapping("/list")
	public  @ResponseBody JsonElement list(@RequestParam(value = "pager", required = false) Pager<House> pager,HttpServletRequest request,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager<House>();
		}
		pager.getSearchMap().put("queryVaildData", "1");
		String areaCode = request.getParameter("areaCode");
		String keyword = request.getParameter("keyword");
		if(StringUtils.isNotBlank(areaCode)) {
			int parseInt = NumberUtils.parseInt(areaCode);
			if(parseInt % 100 == 0) {
				parseInt = parseInt/100;
				pager.getSearchMap().put("areaCodeLike", String.valueOf(parseInt));//杭州 市
			} else {
				pager.getSearchMap().put("areaCode", areaCode); //市下面的区
			}
			
		}
		
		if(StringUtils.isNotBlank(keyword)) {
			pager.getSearchMap().put("houseName", keyword.toUpperCase());//搜索都大写
		}
		
		pager = houseService.getPager(pager);
		
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<House> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (House house : list) {
			JsonObject jsonObj = gson.toJsonTree(house).getAsJsonObject();
			List<String> houseImgArrayList = house.getHouseImgArrayList();
			JsonArray houseImgArray = new JsonArray();
			for (String s : houseImgArrayList) {
				houseImgArray.add(new JsonPrimitive(TagUtils.getFileFullPath(s)));
			}
			jsonObj.add("houseImgArray", houseImgArray);
			
			List<String> houseTypeImgArrayList = house.getHouseTypeImgArrayList();
			JsonArray houseTypeImgArray = new JsonArray();
			for (String s : houseTypeImgArrayList) {
				houseTypeImgArray.add(new JsonPrimitive(TagUtils.getFileFullPath(s)));
			}
			jsonObj.add("houseTypeImgArray", houseImgArray);
			
			array.add(jsonObj);
		}
		return json;
	}
	
	@RequestMapping("/{houseId}/recommend")
	public @ResponseBody JsonElement recommend(@PathVariable Integer houseId,
			HttpServletRequest request,
			HttpSession session,
			Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		String customerName = request.getParameter("customerName");
		String contact = request.getParameter("contact");
		Integer propertyType = NumberUtils.parseInt(request.getParameter("propertyType"));
		Integer surfaceType = NumberUtils.parseInt(request.getParameter("surfaceType"));
		Integer totalPriceType = NumberUtils.parseInt(request.getParameter("totalPriceType"));
		String areaCode = request.getParameter("areaCode");
		Integer firstHouseId = NumberUtils.parseInt(request.getParameter("firstHouseId"));
		Integer secondHouseId = NumberUtils.parseInt(request.getParameter("secondHouseId"));
		String customerInfo = request.getParameter("customerInfo");
		
		if(NumberUtils.isValid(secondHouseId) && secondHouseId.equals(firstHouseId)) {
			throw new IllegalArgumentException("首选楼盘与复选楼盘不能相同!");
		}
		
		boolean flag = houseRecommendService.addRecommend(user.getId(), firstHouseId, customerName, contact, propertyType, surfaceType, totalPriceType, areaCode, customerInfo);
		
		if(NumberUtils.isValid(secondHouseId)) {
			//新增另外一个
			flag = houseRecommendService.addRecommend(user.getId(), secondHouseId, customerName, contact, propertyType, surfaceType, totalPriceType, areaCode, customerInfo);
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
}
