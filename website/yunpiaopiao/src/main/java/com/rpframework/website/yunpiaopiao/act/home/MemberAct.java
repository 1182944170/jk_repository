package com.rpframework.website.yunpiaopiao.act.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.core.BaseAct;

@Controller
@RequestMapping("/member")
public class MemberAct extends BaseAct{

	/**
	 * 会员中心首页
	 * @return
	 */
	@RequestMapping
	public String execute(){
		
		return "/home/member/index";
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping("/modify_pwd")
	public String modifyPwd(){
		
		return "/home/member/pwd";
	}
	
	
	/**
	 * 安全设置
	 * @return
	 */
	@RequestMapping("/secure")
	public String secure(){
		
		return "/home/member/secure";
	}
	
	/**
	 * 我的卡卷
	 * @return
	 */
	@RequestMapping("/myElectronic")
	public String myElectronic(){
		return "/home/member/myElectronic";
	}
	
	/**
	 * 支付密码
	 * @return
	 */
	@RequestMapping("/payment_pwd")
	public String paymentPwd(){
		
		return "/home/member/paymentPwd";
	}
}
