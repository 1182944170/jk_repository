package com.rpframework.module.common.act.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.Help;
import com.rpframework.module.common.service.HelpSevice;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/common/help")
public class AdminHelpAct extends CommonBaseAct{
	@Resource HelpSevice helpSevice;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Help>();
		}
		helpSevice.doPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("help/list");
	}
	
	@RequestMapping("/{helpId}/edit")
	public String edit(@PathVariable Integer helpId, Map<Object, Object> model,RedirectAttributes attr){
		Help help = helpSevice.select(helpId);
		if(help == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + helpId);
		}
		model.put("help", help);
		return this.add(attr, model);
	}
	
	@RequestMapping("/{helpId}/delete")
	public String delete(@PathVariable Integer helpId, Map<Object, Object> model,RedirectAttributes attr){
		Help help = helpSevice.select(helpId);
		if(help == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + helpId);
		}
		helpSevice.delete(helpId);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/common/help/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("help/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute Help help, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		helpSevice.doSaveOrUpdate(help);
		if(NumberUtils.isValid(help.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
