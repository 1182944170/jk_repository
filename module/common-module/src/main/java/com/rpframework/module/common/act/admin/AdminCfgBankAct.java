package com.rpframework.module.common.act.admin;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.CfgBank;
import com.rpframework.module.common.service.CfgBankService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/common/bank")
public class AdminCfgBankAct extends CommonBaseAct {
	@Resource CfgBankService cfgBankService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = cfgBankService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("bank/list");
	}
	
	@RequestMapping("/{bankId}/edit")
	public String edit(@PathVariable Integer bankId, Map<Object, Object> model,RedirectAttributes attr){
		CfgBank cfgBank = cfgBankService.select(bankId);
		Assert.notNull(cfgBank, "不存在的ID:" + bankId);
		model.put("cfgBank", cfgBank);
		return this.add(attr, model);
	}
	
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("bank/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute CfgBank cfgBank, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		cfgBankService.doSaveOrUpdate(cfgBank);
		if(NumberUtils.isValid(cfgBank.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
