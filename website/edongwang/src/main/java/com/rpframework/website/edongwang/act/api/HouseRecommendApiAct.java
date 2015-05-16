package com.rpframework.website.edongwang.act.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.rpframework.core.BaseAct;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.HouseRecommend;
import com.rpframework.website.edongwang.domain.HouseRecommendProgress;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.exception.APICodeException;
import com.rpframework.website.edongwang.service.HouseRecommendService;
import com.rpframework.website.edongwang.utils.EConstants;

@Controller
@RequestMapping("/api/recommend")
public  @ResponseBody class HouseRecommendApiAct extends BaseAct {
	Gson gson = new Gson();
	@Resource HouseRecommendService houseRecommendService;
	
	/**
	 * 抢单
	 */
	@RequestMapping("/{houseRecommendId}/grab")
	public  @ResponseBody JsonElement grab(@PathVariable Integer houseRecommendId,
			HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		boolean flag = houseRecommendService.grab(user.getId(), houseRecommendId);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	/**
	 * 单子的第一步，验证有效性
	 */
	@RequestMapping("/{houseRecommendId}/valid")
	public  @ResponseBody JsonElement valid(@PathVariable Integer houseRecommendId,
			@RequestParam Integer state,
			@RequestParam Integer infoStar,
			@RequestParam Integer intentStar,
			@RequestParam(value = "remark", required = false) String remark,
			HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		
		boolean flag = houseRecommendService.valid(user.getId(), houseRecommendId, state, infoStar, intentStar, remark);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	/***
	 * 单子的第二部，回访
	 */
	@RequestMapping("/{houseRecommendId}/visit")
	public  @ResponseBody JsonElement visit(@PathVariable Integer houseRecommendId,
			@RequestParam Integer state,
			@RequestParam(value = "remark", required = false) String remark,
			HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		
		boolean flag = houseRecommendService.visit(user.getId(), houseRecommendId, state, remark);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	/***
	 * 单子的第三部，成交 之业务员提交成功，这时候 如果 state＝1的话，则等待负责人填写确认
	 */
	@RequestMapping("/{houseRecommendId}/deal_stage1")
	public  @ResponseBody JsonElement deal_stage1(@PathVariable Integer houseRecommendId,
			@RequestParam Integer state,
			@RequestParam Long dealTime, 
			@RequestParam Double surface, 
			@RequestParam Double price, 
			HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		
		boolean flag = houseRecommendService.deal_stage1(user.getId(),
				houseRecommendId, state, dealTime, surface, price);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	@RequestMapping("/{houseRecommendId}/deal_stage2")
	public  @ResponseBody JsonElement deal_stage2(@PathVariable Integer houseRecommendId,
			@RequestParam Double recommendPrice, 
			@RequestParam Double commissionPrice,
			HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		boolean flag = houseRecommendService.deal_stage2(user.getId(), houseRecommendId,  recommendPrice, commissionPrice);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}
	
	/***
	 * 单子的第四部，完结
	 */
	/*@RequestMapping("/{houseRecommendId}/over")
	public  @ResponseBody JsonElement over(@PathVariable Integer houseRecommendId,
			@RequestParam Integer state,
			HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		
		boolean flag = houseRecommendService.over(user.getId(), houseRecommendId, state);
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		return json;
	}*/
	
	/**
	 * 抢单列表针对二级会员来说的
	 * @param pager
	 * @param session
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/grab_list")
	public  @ResponseBody JsonElement list(@RequestParam(value = "pager", required = false) Pager<HouseRecommend> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if(user.getIsSalesman() != 0) {//
			if(user.getUserSalesman() == null) {
				throw new APICodeException(-1, "你不是二级会员，无权查看该API.");
			} else {
				throw new APICodeException(-2, "你申请的二级会员的状态为非通过状态.");
			}
		}
		
		if (pager == null) {
			pager = new Pager<HouseRecommend>();
		}
		
//		pager.getSearchMap().put("areaCode", user.getCountyCode());//只搜索本区域的
		pager = houseRecommendService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		
		List<HouseRecommend> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (HouseRecommend houseRecommend : list) {
			array.add(packageHouseRecommend(houseRecommend));
		}
		return json;
	}
	
	private JsonObject packageHouseRecommend(HouseRecommend houseRecommend) {
		
		JsonObject hrJson = new JsonObject();
		hrJson.addProperty("id", houseRecommend.getId());
		hrJson.addProperty("customerName", houseRecommend.getCustomerName());
		hrJson.addProperty("contact", houseRecommend.getContact());
		hrJson.addProperty("areaCode", houseRecommend.getAreaCode());
		hrJson.addProperty("totalPriceType", houseRecommend.getTotalPriceType());
		hrJson.addProperty("state", houseRecommend.getState());//1 -未抢
		
		if(houseRecommend.getState() != EConstants.Recommend.STATE_OPEN) { //进度信息
			List<HouseRecommendProgress> progresses = houseRecommend.getProgresses();
			JsonArray progressArray = new JsonArray();
			hrJson.add("progress", progressArray);
			if(CollectionUtils.isNotEmpty(progresses)) {
				for (HouseRecommendProgress p : progresses) {
					JsonObject pJson = p.getExtJson();
					pJson.addProperty("id", p.getId());
					pJson.addProperty("type", p.getType());
					pJson.addProperty("state", p.getState());
					
					progressArray.add(pJson);
				}
			}
		}
		
		if(houseRecommend.getAcceptSalesman() != null) {
			JsonObject acceptUser = new JsonObject();
			hrJson.add("acceptUser", acceptUser);
			
			acceptUser.addProperty("id", houseRecommend.getAcceptSalesman().getId());
			acceptUser.addProperty("realName", houseRecommend.getAcceptSalesman().getRealName());
		}
		
		return hrJson;
	}
	
	@RequestMapping("/myrecommends")
	public  @ResponseBody JsonElement myRecommends(@RequestParam(value = "pager", required = false) Pager<HouseRecommend> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if(user.getIsSalesman() == 1) {//
			throw new APICodeException(-1, "二级会员没有推荐");
		}
		
		if (pager == null) {
			pager = new Pager<HouseRecommend>();
		}
		
		pager.getSearchMap().put("recommendUserId", String.valueOf(user.getId()));//只搜索本区域的
		pager = houseRecommendService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		
		List<HouseRecommend> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (HouseRecommend houseRecommend : list) {
			JsonObject hrJson = packageHouseRecommend(houseRecommend);
			
			hrJson.add("house", gson.toJsonTree(houseRecommend.getHouse()));
			array.add(hrJson );
		}
		return json;
	}
	
	/**
	 * 
	 * 我的抢单
	 * @param pager
	 * @param session
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/mygrabs")
	public  @ResponseBody JsonElement myGrabs(@RequestParam(value = "pager", required = false) Pager<HouseRecommend> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if(user.getIsSalesman() != 1) {//
			throw new APICodeException(-1, "你没有查看二级会员的api权限");
		}
		
		if (pager == null) {
			pager = new Pager<HouseRecommend>();
		}
		
		pager.getSearchMap().put("acceptSalesmanId", String.valueOf(user.getId()));//只搜索本区域的
		pager = houseRecommendService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		
		List<HouseRecommend> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (HouseRecommend houseRecommend : list) {
			JsonObject hrJson = packageHouseRecommend(houseRecommend);
			
			hrJson.add("house", gson.toJsonTree(houseRecommend.getHouse()));
			array.add(hrJson );
		}
		return json;
	}
	
	/**
	 * 
	 * 如果是该楼盘的负责人，则可以看到该楼盘的所有的抢单
	 * @param pager
	 * @param session
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/grabs")
	public  @ResponseBody JsonElement grabs(@RequestParam(value = "pager", required = false) Pager<HouseRecommend> pager,HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		User user = getSessionUser(session);
		if(user.getIsSalesman() != 1) {//
			throw new APICodeException(-1, "你没有查看二级会员的api权限");
		}
		
		if(user.getUserSalesman().getIsLeader() != 1) {
			throw new APICodeException(-1, "二级会员该楼盘的负责人的api权限");
		}
		
		if (pager == null) {
			pager = new Pager<HouseRecommend>();
		}
		
		pager.getSearchMap().put("houseId", String.valueOf(user.getUserSalesman().getHouse().getId()));
		pager = houseRecommendService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		
		List<HouseRecommend> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (HouseRecommend houseRecommend : list) {
			JsonObject hrJson = packageHouseRecommend(houseRecommend);
			
			hrJson.add("house", gson.toJsonTree(houseRecommend.getHouse()));
			array.add(hrJson );
		}
		return json;
	}
}
