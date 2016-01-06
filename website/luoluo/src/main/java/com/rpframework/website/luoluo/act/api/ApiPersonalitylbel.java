package com.rpframework.website.luoluo.act.api;

import java.util.List;
import java.util.Map;

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
import com.rpframework.website.luoluo.domain.Personalabel;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.PersonalabelService;

@Controller
@RequestMapping("api/personal")
public class ApiPersonalitylbel extends BaseAct{
	Gson gson = new Gson();
	@Resource PersonalabelService   labelService ;
	
	/**
	 * 显示数据库所有标签
	 * @param pager
	 * @param model
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("personal_list")
	public @ResponseBody JsonElement personallist(@RequestParam(value="pager", required=false)Pager<Personalabel> pager, Map<String, Pager<Personalabel>> model,HttpSession session)
			throws ParserException, InterruptedException{
		if(pager==null){
			pager=new Pager<Personalabel>();
		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		labelService.labelpager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Personalabel> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Personalabel User : list) {
			JsonObject o = gson.toJsonTree(User).getAsJsonObject();
			array.add(o);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	@RequestMapping("listone")
	public @ResponseBody JsonElement listone(@RequestParam String pore,HttpSession session)
			throws ParserException, InterruptedException{
		JsonObject json = new JsonObject();
		Personalabel pon= labelService.selectOnlyOne(Integer.parseInt(pore));
		json.addProperty("ponname", pon.getLabel());
		json.addProperty("ponid", pon.getId());
		return json;
	}
}
