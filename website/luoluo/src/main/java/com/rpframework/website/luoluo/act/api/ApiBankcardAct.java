package com.rpframework.website.luoluo.act.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.website.luoluo.domain.Bankcard;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.BankcardService;
@Controller
@RequestMapping("api/bankcar")
public class ApiBankcardAct extends  BaseAct{
	@Resource BankcardService bankcardService;
	
	/**
	 * 添加银行卡
	 * @param cardid
	 * @param name
	 * @param phone
	 * @param cardnumber
	 * @param address
	 * @param session
	 * @return
	 */
	@RequestMapping("add")
	public  @ResponseBody JsonElement add(
			@RequestParam(required=false) Integer cardid,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String phone,
			@RequestParam(required=false) String cardnumber,
			@RequestParam(required=false) String address,
			HttpSession session){
		User currUser = getSessionUser(session);	
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		Bankcard bankcar=new Bankcard();
		bankcar.setAddress(address);
		bankcar.setCardid(cardid);
		bankcar.setCardnumber(cardnumber);
		bankcar.setName(name);
		bankcar.setPhone(phone);
		bankcar.setUserid(currUser.getId());
		boolean blfg=bankcardService.insertone(bankcar);
		JsonObject json=new JsonObject();
		if(blfg == true){ // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("error", false);
		} 
		return json;
		
	}
}
