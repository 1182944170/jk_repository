package com.rpframework.website.yunpiaopiao.act.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.BaseAct;
import com.rpframework.website.yunpiaopiao.domian.User;

@Controller
@RequestMapping("/member")
public class MemberAct extends BaseAct{

	/**
	 * 会员中心首页
	 * @return
	 */
	@RequestMapping
	public String execute(HttpServletRequest request, RedirectAttributes attr,
			HttpSession session){
		
		if(null != session){
			User user = (User) session.getAttribute(SESSION_FRONT_USER);
			if(null == user){
				return redirect("/login");
			}
		}
		
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
