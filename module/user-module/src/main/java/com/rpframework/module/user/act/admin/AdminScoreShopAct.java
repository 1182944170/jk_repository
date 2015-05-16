package com.rpframework.module.user.act.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.api.FileService;
import com.rpframework.module.user.act.UserModuleBaseAct;
import com.rpframework.module.user.domain.ScoreShop;
import com.rpframework.module.user.service.ScoreShopService;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/score_shop")
public class AdminScoreShopAct extends UserModuleBaseAct {
	@Resource ScoreShopService scoreShopService;
	@Resource FileService fileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = scoreShopService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("score_shop/list");
	}
	
	@RequestMapping("/{scoreShopId}/edit")
	public String edit(@PathVariable Integer scoreShopId, Map<Object, Object> model,RedirectAttributes attr){
		ScoreShop scoreShop = scoreShopService.select(scoreShopId);
		Assert.notNull(scoreShop, "不存在的ID:" + scoreShopId);
		model.put("scoreShop", scoreShop);
		return this.add(attr, model);
	}
	@RequestMapping("/{scoreShopId}/dolottery")
	public String dolottery(@PathVariable Integer scoreShopId, Map<Object, Object> model,RedirectAttributes attr){
		scoreShopService.doLottery(scoreShopId);
		setInfoMsg("操作成功!", attr);
		return redirect("/admin/user_score_shop_log");
	}
	
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("score_shop/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(
			@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile,
			@RequestParam(value="startTimeString") String startTimeString,
			@RequestParam(value="endTimeString") String endTimeString,
			@ModelAttribute ScoreShop scoreShop, HttpSession session, HttpServletRequest request,RedirectAttributes attr) {
		
		if(iconFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/scoreshop/" + NumberUtils.random(6) + iconFile.getOriginalFilename();
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);
				scoreShop.setImg(relativelyPath);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		scoreShop.setStartTime(DateUtils.parse(startTimeString).getTime()/1000);
		scoreShop.setEndTime(DateUtils.parse(endTimeString).getTime()/1000);
		
		scoreShopService.doSaveOrUpdate(scoreShop);
		if(NumberUtils.isValid(scoreShop.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
