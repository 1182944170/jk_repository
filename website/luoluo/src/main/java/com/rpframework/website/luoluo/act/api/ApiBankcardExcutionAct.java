package com.rpframework.website.luoluo.act.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.rpframework.website.luoluo.domain.BankcardExcution;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.BankcardExcutionService;

@Controller
@RequestMapping("api/bankection")
public class ApiBankcardExcutionAct extends BaseAct{
	@Resource BankcardExcutionService banservier;
	Gson gson = new Gson();
	@RequestMapping("forward")
	public @ResponseBody JsonElement add(	
			@RequestParam(required=false) Integer type,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String phone,
			@RequestParam(required=false) String monlycard,
			@RequestParam(required=false) double extractionMonly,HttpSession session){
			User user=getSessionUser(session);
			boolean bFlag = false;
		if(user==null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		bFlag = banservier.bagPay(user, extractionMonly);
		JsonObject json=new JsonObject();
		if(bFlag == true){
			BankcardExcution bank=new BankcardExcution();
			bank.setUserid(user.getId());
			bank.setExtractionMonly(-extractionMonly);
			bank.setMonlycard(monlycard);
			bank.setType(type);
			bank.setName(name);
			bank.setPhone(phone);
			bank.setNowtime(System.currentTimeMillis()/1000);
			bank.setState(1);
			banservier.insertdo(bank);
		 // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("error", false);
		} 
		return json;
	}
	
	@RequestMapping("/myall")
	public @ResponseBody JsonElement myall(@RequestParam(value="pager",required=false) Pager<BankcardExcution> pager,HttpSession session 
			) throws ParserException, InterruptedException{
		if(pager==null){
			pager=new Pager<BankcardExcution>();
		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		pager.getSearchMap().put("userid",  String.valueOf(currUser.getId()));
		banservier.getPager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<BankcardExcution> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (BankcardExcution act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}

}
