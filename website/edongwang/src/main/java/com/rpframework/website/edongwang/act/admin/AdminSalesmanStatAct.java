package com.rpframework.website.edongwang.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.service.SalesmanStatService;

@Controller
@RequestMapping("/admin/salesman_stat")
public class AdminSalesmanStatAct extends AdminAct {
	@Resource SalesmanStatService salesmanStatService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr) {
		if(pager == null) {
			pager = new Pager();
			pager.getSearchMap().put("order", "grabCount desc"); //默认排序
		}
		pager = salesmanStatService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("salesman_stat/list");
	}
}
