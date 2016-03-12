package com.rpframework.website.luoluo.act.admin;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;

@Controller
@RequestMapping("admin/actcyitypic")
public class AdminActivitypictureAct extends AdminAct{
	@Resource  ActivitypictureSercice activitypictureSercice;
	/**
	 * 透过活动id查询用户表内的信息
	 * @param id
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("{id}/list")
	public String list(@PathVariable (value="id") String id,
			@RequestParam(value="pager", required=false) Pager<Activitypicture> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<Activitypicture>();
		}
		pager.getSearchMap().put("sponserid", String.valueOf(id));
		//pager.getSearchMap().put("typeOrder", 2+"");
		pager=activitypictureSercice.getpager(pager);
		model.put("pager", pager);
		return this.doPackageURI("activity/userlist");
	}

	
	
	@RequestMapping("/list")
	public String alllist(
			@RequestParam(value="pager", required=false) Pager<Activitypicture> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<Activitypicture>();
		}
		pager=activitypictureSercice.getpager(pager);
		
		model.put("pager", pager);
		return this.doPackageURI("management/list");
	}
	
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable (value="id") int id ,Map<Object, Object> model){
		Activitypicture activitypicture=activitypictureSercice.selectone(id);
		if(activitypicture == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + id);
		}
		String [] arr = activitypicture.getInsure().split(",");
		String [] forname = activitypicture.getInsurenName().split(",");
		model.put("acp", activitypicture);
		model.put("num", arr);
		model.put("name", forname);
		return this.doPackageURI("management/edit");
	}
	
	
}
