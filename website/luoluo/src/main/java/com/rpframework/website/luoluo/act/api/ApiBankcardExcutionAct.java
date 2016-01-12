package com.rpframework.website.luoluo.act.api;



import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.website.luoluo.domain.Bankcard;
import com.rpframework.website.luoluo.domain.BankcardExcution;
import com.rpframework.website.luoluo.domain.Monlyjournals;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.BankcardExcutionService;
import com.rpframework.website.luoluo.service.BankcardService;
import com.rpframework.website.luoluo.service.MonlyjournalsService;

@Controller
@RequestMapping("api/bankection")
public class ApiBankcardExcutionAct extends BaseAct{
	@Resource BankcardExcutionService banservier;
	@Resource BankcardService bandcardService;
	
	@Resource MonlyjournalsService monlyjournalsService;
	Gson gson = new Gson();
	
	@RequestMapping("reflect")
	public @ResponseBody JsonElement add(	
		
			@RequestParam(required=false) Integer bankcardid,
			@RequestParam(required=false) double extractionMonly,HttpSession session){
			User user=getSessionUser(session);
			boolean bFlag = false;
		if(user==null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		bFlag = banservier.bagPay(user, extractionMonly);
		Bankcard bb= bandcardService.selectdo(bankcardid);
		JsonObject json=new JsonObject();
		if(bFlag == true){
			BankcardExcution bank=new BankcardExcution();
			bank.setUserid(user.getId());
			bank.setExtractionMonly(extractionMonly);
			bank.setMonlycard(bb.getCardnumber());
			bank.setType(bb.getCardid());
			bank.setName(bb.getName());
			bank.setPhone(bb.getPhone());
			bank.setNowtime(System.currentTimeMillis()/1000);
			bank.setState(1);
			banservier.insertdo(bank);
			Monlyjournals mysope=new Monlyjournals();
			mysope.setMonly(-extractionMonly);
			mysope.setNewtime(System.currentTimeMillis()/1000);
			mysope.setType(1);
			mysope.setUserid(user.getId());
			mysope.setRemark("提现");
			monlyjournalsService.insertdo(mysope);
		 // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("error", false);
		} 
		return json;
	}
	


}
