package com.rpframework.website.yunpiaopiao.act.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.api.FileService;
import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.domian.Actor;
import com.rpframework.website.yunpiaopiao.service.ActorService;

@Controller
@RequestMapping("/admin/actor")
public class AdminActorAct extends AdminAct {
	@Resource ActorService actorService;
	@Resource FileService fileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = actorService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("actor/list");
	}
	
	@RequestMapping("/{actorId}/edit")
	public String edit(@PathVariable Integer actorId, Map<Object, Object> model,RedirectAttributes attr){
		Actor actor = actorService.actorDao.select(actorId);
		if(actor == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + actorId);
		}
		model.put("actor", actor);
		return this.add(attr, model);
	}
	
	@RequestMapping("/{actorId}/delete")
	public String delete(@PathVariable Integer actorId, Map<Object, Object> model,RedirectAttributes attr){
		Actor actor = actorService.actorDao.select(actorId);
		if(actor == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + actorId);
		}
		actorService.actorDao.delete(actorId);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/actor/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("actor/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile, @ModelAttribute Actor actor, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		if(iconFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/actor/" + NumberUtils.random(3) + iconFile.getOriginalFilename();
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);
				actor.setIcon(relativelyPath);;
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			if(actor == null || NumberUtils.isNotValid(actor.getId())) {
				throw new IllegalArgumentException("新增情况下，必须上传Icon!");
			}
		}
		
		actorService.doSaveOrUpdate(actor);
		if(NumberUtils.isValid(actor.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
