package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Bankcard;
import com.rpframework.website.luoluo.domain.Banks;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.BankcardService;
import com.rpframework.website.luoluo.service.BanksSercice;
@Controller
@RequestMapping("api/bankcar")
public class ApiBankcardAct extends  BaseAct{
	Gson gson =new Gson();
	@Resource BankcardService bankcardService;
	@Resource BanksSercice bankssercice;
	
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
	//我的银行卡
	@RequestMapping("/myall")
	public @ResponseBody JsonElement myall(@RequestParam(value="pager",required=false) Pager<Bankcard> pager,HttpSession session 
			) throws ParserException, InterruptedException{
		if(pager==null){
			pager=new Pager<Bankcard>();
		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		pager.getSearchMap().put("userid",  String.valueOf(currUser.getId()));
		bankcardService.getpager(pager);
		JsonObject json = new JsonObject();
		
		List<Bankcard> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Bankcard act : list) {
			Banks Banks=bankssercice.selectone(act.getCardid());
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			jsonObj.addProperty("bankname", Banks.getBankname());
			jsonObj.addProperty("bankpic", Banks.getBankprivice());
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	
	//删除
	@RequestMapping("delectdo")
	public  @ResponseBody JsonElement delectdo(
			@RequestParam(required=false) String cardid){
		JsonObject json=new JsonObject();
		if(StringUtils.isBlank(cardid)){
			json.addProperty("mag", "不存在该卡");
			return json;
		}
		boolean  ban =bankcardService.delectdo(Integer.parseInt(cardid));
		if(ban == true){ // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("succ", false);
		} 
		return json;
	}
	@RequestMapping("seledo")
	public  @ResponseBody JsonElement seledo(
			@RequestParam(required=false) String cardid){
		JsonObject json=new JsonObject();
		if(StringUtils.isBlank(cardid)){
			json.addProperty("mag", "不存在该卡");
			return json;
		}
		Bankcard  ban =bankcardService.selectdo(Integer.parseInt(cardid));
		Banks  bane =bankssercice.selectcal(ban.getCardid());
		json.addProperty("cardnumber", ban.getCardnumber());
		json.addProperty("address", ban.getAddress());
		json.addProperty("phone", ban.getPhone());
		json.addProperty("name", ban.getName());
		json.addProperty("cardid", ban.getCardid());
		json.addProperty("bankname", bane.getBankname());
		return json;
	}
}
