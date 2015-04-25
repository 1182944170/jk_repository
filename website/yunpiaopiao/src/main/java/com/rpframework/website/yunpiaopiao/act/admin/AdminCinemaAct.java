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
import com.rpframework.website.yunpiaopiao.domian.Cinema;
import com.rpframework.website.yunpiaopiao.service.CinemaService;

@Controller
@RequestMapping("/admin/cinema")
public class AdminCinemaAct extends AdminAct {
	@Resource CinemaService cinemaService;
	@Resource FileService fileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = cinemaService.doPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("cinema/list");
	}
	
	@RequestMapping("/{cinemaId}/edit")
	public String edit(@PathVariable Integer cinemaId, Map<Object, Object> model,RedirectAttributes attr){
		Cinema cinema = cinemaService.cinemaDao.select(cinemaId);
		if(cinema == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + cinemaId);
		}
		model.put("cinema", cinema);
		return this.add(attr, model);
	}
	
	@RequestMapping("/{cinemaId}/delete")
	public String delete(@PathVariable Integer cinemaId, Map<Object, Object> model,RedirectAttributes attr){
		cinemaService.delete(cinemaId);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/cinema/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("cinema/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile, @ModelAttribute Cinema cinema, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		if(iconFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/actor/" + NumberUtils.random(3) + iconFile.getOriginalFilename();
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);
				cinema.setIcon(relativelyPath);;
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			if(cinema == null || NumberUtils.isNotValid(cinema.getId())) {
				throw new IllegalArgumentException("新增情况下，必须上传Icon!");
			}
		}
		
		cinemaService.doSaveOrUpdate(cinema);
		if(NumberUtils.isValid(cinema.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
