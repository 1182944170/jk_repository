package com.rpframework.website.edongwang.act;

import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.module.adminbase.act.AdminBaseAct;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.Member;
import com.rpframework.website.edongwang.service.AdminMemberService;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberAct extends AdminBaseAct {
	
	@Resource AdminMemberService adminMemberService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		pager = adminMemberService.getMemberPager(pager);
		model.put("pager", pager);
		return "member/list";
	}

	@RequestMapping("/doSave")
	public void doSaveOrUpdate() {
		
		//以下为模拟数据,需要在改正	
		Member member = new Member();
		member.setBankAdrees("杭州市三墩支行");
		member.setBankName("中国农业银行");
		member.setBankNumber("6222222222222");
		member.setCallingCardImg("http://www.baidu.com/mingpian.jpg");
		member.setHeadImg("http://www.badiu.com/touxian.jpg");
		member.setIsLeader(Member.ISLEADER_NO);
		member.setPassword("123456");
		member.setRealName("三墩");
		member.setRegisterDate(Calendar.getInstance().getTimeInMillis());
		member.setSex(Member.SEX_WOMAN);
		member.setStatus(Member.STATUS_NORMAL);
		member.setTelNumber("13568689898");
		
		boolean isSuccess = adminMemberService.adminMemberDao.insert(member);
		System.out.println("*********************:"+isSuccess);
		
	}

}
