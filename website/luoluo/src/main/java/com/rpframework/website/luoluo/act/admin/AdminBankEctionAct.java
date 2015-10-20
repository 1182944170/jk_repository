package com.rpframework.website.luoluo.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.BankcardExcution;
import com.rpframework.website.luoluo.service.BankcardExcutionService;

@Controller
@RequestMapping("admin/bankextion")
public class AdminBankEctionAct extends AdminAct{
	
	@Resource BankcardExcutionService bankcardExcutionService;
	
	
	@RequestMapping("list")
	public String list(@RequestParam(value="pager", required=false) Pager<BankcardExcution> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<BankcardExcution>();
		}
		pager.getSearchMap().put("stateid", String.valueOf(1));
		pager = bankcardExcutionService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("bankextion/list");
	}
	
	
	//查询已处理的
	@RequestMapping("{sid}/onelist")
	public String onelist(@PathVariable (value="sid") String sid,
			@RequestParam(value="pager", required=false) Pager<BankcardExcution> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<BankcardExcution>();
		}
		pager.getSearchMap().put("stateid", String.valueOf(2));
		pager = bankcardExcutionService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("bankextion/list");
	}
	
	
	
	@RequestMapping("{uid}/edit")
	public String edit(@PathVariable (value="uid") Integer uid,Map<Object, Object> model, RedirectAttributes attr){
		BankcardExcution Bfgl=bankcardExcutionService.selectone(uid);
		if(Bfgl==null){
			setInfoMsg("没有该用户！", attr);
		}else{
			Bfgl.setState(2);
			bankcardExcutionService.updatedo(Bfgl);
			setInfoMsg("处理成功！", attr);
		}
		
		return redirect("/admin/bankextion/list");
	}
}
