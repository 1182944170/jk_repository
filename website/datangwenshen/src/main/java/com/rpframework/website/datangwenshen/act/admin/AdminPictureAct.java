package com.rpframework.website.datangwenshen.act.admin;

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
import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.datangwenshen.domain.Picture;
import com.rpframework.website.datangwenshen.service.PictureService;

@Controller
@RequestMapping("/admin/picture")
public class AdminPictureAct extends AdminAct {
	@Resource PictureService pictureService;
	@Resource FileService fileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = pictureService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("picture/list");
	}
	
	@RequestMapping("/{pictureId}/edit")
	public String edit(@PathVariable Integer pictureId, Map<Object, Object> model,RedirectAttributes attr){
		
		Picture picture = pictureService.pictureDao.select(pictureId);
		Assert.notNull(picture,this.getClass().getName() + " Id 异常");
		
		model.put("picture", picture);
		return this.add(picture.getSource(), attr, model);
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam Integer source, RedirectAttributes attr, Map<Object, Object> model){
		model.put("source", source);
		return this.doPackageURI("picture/edit");
	}
	
	@RequestMapping("/{pictureId}/delete")
	public String delete(@PathVariable Integer pictureId, Map<Object, Object> model,RedirectAttributes attr){
		Picture picture = pictureService.select(pictureId);
		if(picture == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + pictureId);
		}
		pictureService.delete(pictureId);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/picture/list");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile, 
			@ModelAttribute Picture picture, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		if(iconFile.getSize() > 0) {
			try {
				String relativelyPath = "/picture/" + NumberUtils.random(3) + iconFile.getOriginalFilename();
				fileService.saveFile(iconFile.getInputStream(), relativelyPath); 
				picture.setAddress(relativelyPath);
				picture.setName(picture.getName());
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			
		}
		pictureService.doSaveOrUpdate(picture);
		if(NumberUtils.isValid(picture.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}

}