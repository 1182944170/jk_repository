package com.rpframework.module.common.act.admin;

import java.util.List;
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
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.Slideshow;
import com.rpframework.module.common.service.SlideshowService;
import com.rpframework.utils.NumberUtils;

@Controller
@RequestMapping("/admin/common/slideshow")
public class AdminSlideshowAct extends CommonBaseAct{
	@Resource SlideshowService slideshowService;
	@Resource FileService fileService;
	
	@RequestMapping("/list")
	public String list(Map<Object, Object> model, RedirectAttributes attr){
		List<Slideshow> list = slideshowService.queryAll();
		model.put("list", list);
		return this.doPackageURI("slideshow/list");
	}
	
	@RequestMapping("/{slideshowId}/edit")
	public String edit(@PathVariable Integer slideshowId, Map<Object, Object> model,RedirectAttributes attr){
		Slideshow slideshow = slideshowService.slideshowDao.select(slideshowId);
		if(slideshow == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + slideshowId);
		}
		model.put("slideshow", slideshow);
		return this.add(attr, model);
	}
	
	@RequestMapping("/{slideshowId}/delete")
	public String delete(@PathVariable Integer slideshowId, Map<Object, Object> model,RedirectAttributes attr){
		Slideshow slideshow = slideshowService.slideshowDao.select(slideshowId);
		if(slideshow == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + slideshowId);
		}
		slideshowService.slideshowDao.delete(slideshowId);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/common/slideshow/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("slideshow/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile, @ModelAttribute Slideshow slideshow, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		if(iconFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/slideshow/" + NumberUtils.random(3) + iconFile.getOriginalFilename();
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);
				slideshow.setIcon(relativelyPath);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			if(slideshow == null || NumberUtils.isNotValid(slideshow.getId())) {
				throw new IllegalArgumentException("新增情况下，必须上传Icon!");
			}
		}
		slideshowService.doSaveOrUpdate(slideshow);
		if(NumberUtils.isValid(slideshow.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
