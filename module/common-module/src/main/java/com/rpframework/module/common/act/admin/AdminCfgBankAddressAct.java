package com.rpframework.module.common.act.admin;

import java.util.List;
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
import com.rpframework.module.common.domain.CfgBankAddress;
import com.rpframework.module.common.service.CfgBankAddressService;
import com.rpframework.module.common.service.CfgBankService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/common/bank_address")
public class AdminCfgBankAddressAct extends CommonBaseAct {
	@Resource CfgBankAddressService cfgBankAddressService;
	@Resource CfgBankService cfgBankService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = cfgBankAddressService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("bank_address/list");
	}
	
	@RequestMapping("/{bankAddressId}/edit")
	public String edit(@PathVariable Integer bankAddressId, Map<Object, Object> model,RedirectAttributes attr){
		CfgBankAddress cfgBankAddress = cfgBankAddressService.select(bankAddressId);
		Assert.notNull(cfgBankAddress, "不存在的ID:" + bankAddressId);
		model.put("cfgBankAddress", cfgBankAddress);
		return this.add(attr, model);
	}
	
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		List<CfgBank> cfgBanks = cfgBankService.queryAllEffective();
		model.put("cfgBanks", cfgBanks);
		return this.doPackageURI("bank_address/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute CfgBankAddress cfgBankAddress, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		cfgBankAddressService.doSaveOrUpdate(cfgBankAddress);
		if(NumberUtils.isValid(cfgBankAddress.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
