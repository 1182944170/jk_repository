package com.rpframework.module.adminbase.act;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.adminbase.domain.AdminAuthRes;
import com.rpframework.module.adminbase.exception.AdminIllegalArgumentException;
import com.rpframework.module.adminbase.service.AdminAuthResService;
import com.rpframework.utils.NumberUtils;

@Controller
@RequestMapping("/admin/adminauthres")
public class AdminAuthResAct extends AdminBaseAct {
	@Resource AdminAuthResService adminAuthResService;
	
	@RequestMapping("/list")
	public String list(Map<Object, Object> model, RedirectAttributes attr){
		List<AdminAuthRes> list = adminAuthResService.adminAuthResDao.getAdminAuthResByParentId(new Integer(0));
		model.put("list", list);
		return this.doPackageURI("adminauthres/list");
	}
	
	@RequestMapping("/{adminAuthResId}/edit")
	public String edit(@PathVariable Integer adminAuthResId, Map<Object, Object> model,RedirectAttributes attr){
		AdminAuthRes adminAuthRes = adminAuthResService.adminAuthResDao.select(adminAuthResId);
		if(adminAuthRes == null) {
			throw new AdminIllegalArgumentException("找不到adminAuthRes：Id:" + adminAuthResId);
		}
		model.put("adminAuthRes", adminAuthRes);
		return this.add(attr);
	}
	
	@RequestMapping("/{adminAuthResId}/delete")
	public String delete(@PathVariable Integer adminAuthResId, Map<Object, Object> model,RedirectAttributes attr){
		if(adminAuthResService.canDeleteAdminAuthRes(adminAuthResId)) {
			boolean falg = adminAuthResService.deleteAdminAuthRes(adminAuthResId);
			if(falg) {
				setInfoMsg("删除操作成功！", attr);
			} else {
				setErrorMsg("删除操作失败！", attr);
			}
			
		} else {
			setErrorMsg("含有子类，请删删除子类！", attr);
		}
		return redirect("/admin/adminauthres/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr){
		return this.doPackageURI("adminauthres/edit");
	}
	
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute AdminAuthRes adminAuthRes, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		
		if(adminAuthRes == null || StringUtils.isBlank(adminAuthRes.getName()) || StringUtils.isBlank(adminAuthRes.getPath())) {
			throw new AdminIllegalArgumentException("参数异常!");
		}
		
		if(NumberUtils.isValid(adminAuthRes.getId())) {//update
			adminAuthResService.adminAuthResDao.update(adminAuthRes);
		} else {
			adminAuthResService.adminAuthResDao.insert(adminAuthRes);
		}
		setInfoMsg("操作成功！", attr);
		return redirect("/admin/adminauthres/list");
	}
}