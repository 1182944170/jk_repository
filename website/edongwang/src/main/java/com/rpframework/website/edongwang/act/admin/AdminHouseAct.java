package com.rpframework.website.edongwang.act.admin;

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
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.House;
import com.rpframework.website.edongwang.service.HouseService;

@Controller
@RequestMapping("/admin/house")
public class AdminHouseAct extends AdminAct{
	@Resource HouseService houseService;
	@Resource FileService fileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		pager = houseService.getPager(pager);
		model.put("pager", pager);
		return doPackageURI("house/list");
	}
	
	@RequestMapping("/{houseId}/edit")
	public String edit(@PathVariable Integer houseId, Map<Object, Object> model,RedirectAttributes attr){
		House house = houseService.select(houseId);
		Assert.notNull(house, "不存在的ID:" + houseId);
		model.put("house", house);
		return this.add(attr, model);
	}

	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model) {
		return this.doPackageURI("house/edit");
	}

	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@RequestParam(value="houseImgIconFile", required=false) CommonsMultipartFile houseImgIconFile,
			@RequestParam(value="houseTypeImgIconFile", required=false) CommonsMultipartFile houseTypeImgIconFile,
			@RequestParam(value="protocolBeginTimeString") String protocolBeginTimeString,
			@RequestParam(value="protocolEndTimeString") String protocolEndTimeString,
			@ModelAttribute House house, 
			HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		
		if(houseImgIconFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/house/" + NumberUtils.random(3) + houseImgIconFile.getOriginalFilename();
				fileService.saveFile(houseImgIconFile.getInputStream(), relativelyPath);
				house.setHouseImg(relativelyPath);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			if(house == null || NumberUtils.isNotValid(house.getId())) {
				throw new IllegalArgumentException("新增情况下，楼盘图必须上传Icon!");
			}
		}
		
		if(houseTypeImgIconFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/housetype/" + NumberUtils.random(3) + houseTypeImgIconFile.getOriginalFilename();
				fileService.saveFile(houseTypeImgIconFile.getInputStream(), relativelyPath);
				house.setHouseTypeImg(relativelyPath);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			if(house == null || NumberUtils.isNotValid(house.getId())) {
				throw new IllegalArgumentException("新增情况下，户型图必须上传Icon!");
			}
		}
		
		house.setProtocolBeginTime(DateUtils.parse(protocolBeginTimeString).getTime()/1000);
		house.setProtocolEndTime(DateUtils.parse(protocolEndTimeString).getTime()/1000);
		
		houseService.doSaveOrUpdate(house);
		if(NumberUtils.isValid(house.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
	
}