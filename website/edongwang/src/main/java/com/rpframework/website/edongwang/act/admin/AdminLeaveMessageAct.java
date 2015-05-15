package com.rpframework.website.edongwang.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.service.LeaveMessageService;

@Controller
@RequestMapping("/admin/leave_msg")
public class AdminLeaveMessageAct extends AdminAct{
	@Resource LeaveMessageService leaveMessageService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		pager = leaveMessageService.getPager(pager);
		model.put("pager", pager);
		return doPackageURI("leave_msg/list");
	}
}
