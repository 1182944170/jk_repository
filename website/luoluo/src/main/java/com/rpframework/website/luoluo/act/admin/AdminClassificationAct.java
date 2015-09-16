package com.rpframework.website.luoluo.act.admin;

import java.util.Map;

import javax.annotation.Resource;

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
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.service.ClassificationService;

@Controller
@RequestMapping("admin/cal")
public class AdminClassificationAct extends AdminAct{
	@Resource ClassificationService classificationService;
	@Resource FileService fileService;
	/**
	 * 查询所有
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value="pager", required=false) Pager<Classification> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Classification>();
		}
		
		pager = classificationService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("calfe/list");
	}
	/**
	 * 跳转 修改页面
	 * @param id
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable Integer id, Map<Object, Object> model,RedirectAttributes attr){
		Classification classificationDO = classificationService.selectcal(id);
		if(classificationDO == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + id);
		}
		model.put("classificationDO", classificationDO);
		return this.add(attr, model);
	}
/**
 * 添加页面
 * @param attr
 * @param model
 * @return
 */
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("calfe/edit");
	}
/**
 * 删除	
 * @param id
 * @param model
 * @param attr
 * @return
 */
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable Integer id, Map<Object, Object> model,RedirectAttributes attr){
		Classification classificationDO =classificationService.selectcal(id);
		if(classificationDO == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + id);
		}
		classificationService.deletecal(id);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/cal/list");
	}
/**
 * 修改信息	
 * @param classificationDO
 * @param iconFile
 * @param attr
 * @param mainFile
 * @return
 */
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute Classification classificationDO,@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile, 
			RedirectAttributes attr,@RequestParam(value="mainFile", required=false) CommonsMultipartFile mainFile){
		if(iconFile.getSize() > 0 &&  mainFile.getSize()>0) { // 判断 icon 大小是否大于0
			try {
				String relativelyPath = "/fenl/" + NumberUtils.random(3) + iconFile.getOriginalFilename(); // new 随即产生随即4位数开头的一个相对路径文件名
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);  // 保存文件
				classificationDO.setClaImg(relativelyPath); // 设置相对路径
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
			try {
				String relaPath = "/fenl/" + NumberUtils.random(3) + mainFile.getOriginalFilename(); // new 随即产生随即4位数开头的一个相对路径文件名
				fileService.saveFile(mainFile.getInputStream(), relaPath);  // 保存文件
				classificationDO.setBigImg(relaPath);
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			
		}
		classificationService.doSaveOrUpdate(classificationDO);
		if(NumberUtils.isValid(classificationDO.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		return redirect("list");
	}
}