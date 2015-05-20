package com.rpframework.website.edongwang.act.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.user.domain.UserBankCard;
import com.rpframework.module.user.service.UserBankCardService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.HouseRecommend;
import com.rpframework.website.edongwang.service.HouseRecommendService;

@Controller
@RequestMapping("/admin/house_recommend")
public class AdminHouseRecommendAct extends AdminAct{
	@Resource HouseRecommendService houseRecommendService;
	@Resource UserBankCardService userBankCardService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		pager = houseRecommendService.getPager(pager);
		model.put("pager", pager);
		return doPackageURI("house_recommend/list");
	}

	@RequestMapping("/over_list")
	public String overList(@RequestParam(value = "pager", required = false) Pager<HouseRecommend> pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager<HouseRecommend>();
		}
		
		pager = houseRecommendService.getOverPager(pager);
		model.put("pager", pager);
		
		List<UserBankCard> userBankCards = new ArrayList<UserBankCard>();
		if(CollectionUtils.isNotEmpty(pager.getItemList())) {
			for (HouseRecommend hr : pager.getItemList()) {
				UserBankCard c = null;
				List<UserBankCard> list = userBankCardService.getCardsByUserId(hr.getAcceptSalesmanId());
				if(CollectionUtils.isNotEmpty(list)) {
					c = list.get(0);
				}
				
				userBankCards.add(c);
			}
		}
		model.put("userBankCards", userBankCards);
		return doPackageURI("house_recommend/over_list");
	}
	
	/*@RequestMapping("/{houseRecommendId}/edit")
	public String edit(@PathVariable Integer houseRecommendId, Map<Object, Object> model,RedirectAttributes attr){
		HouseRecommend houseRecommend = houseRecommendService.select(houseRecommendId);
		Assert.notNull(houseRecommend, "不存在的ID:" + houseRecommendId);
		model.put("houseRecommend", houseRecommend);
		return this.doPackageURI("house_recommend/edit");
	}*/
	
	@RequestMapping("/{houseRecommendId}/over")
	public String over(@PathVariable Integer houseRecommendId,
			HttpSession session, Map<Object, Object> model, RedirectAttributes attr) {
		HouseRecommend houseRecommend = houseRecommendService.select(houseRecommendId);
		Assert.notNull(houseRecommend, "不存在的ID:" + houseRecommendId);
		houseRecommendService.over(houseRecommendId);
		setInfoMsg("操作成功", attr);
		return redirect("/admin/house_recommend/over_list");
	}
}