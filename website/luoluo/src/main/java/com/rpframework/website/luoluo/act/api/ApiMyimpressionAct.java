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
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Myimpression;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.MyimpressionService;
import com.rpframework.website.luoluo.service.UserService;
@Controller
@RequestMapping("api/myimp")
public class ApiMyimpressionAct extends BaseAct{
	Gson gson = new Gson();
	@Resource MyimpressionService myimpressionService;
	@Resource UserService userService;
	/**
	 * 好感度添加
	 * @param findid
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("add")
	public @ResponseBody JsonElement userlist(	
			@RequestParam(required=false) Integer findid,
			HttpSession session) throws ParserException, InterruptedException{
	
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		Myimpression reporta=new Myimpression();
		reporta.setUserid(currUser.getId());
		reporta.setFindid(findid);
		reporta.setType(1);
		boolean bFlag = myimpressionService.insertdo(reporta);
		if(bFlag == true){ // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("error", false);
		} 
		return json;
	}
	/**
	 * 好感度用户列表
	 * @date 2015年7月13日 下午5:47:24
	 */
	@RequestMapping("/user_myimpression")
	public @ResponseBody JsonElement usermyimpression(@RequestParam(value="pager",required=false) Pager<Myimpression> pager,HttpSession session 
			) throws ParserException, InterruptedException{
		if(pager==null){
			pager=new Pager<Myimpression>();
		}
		User currUser = getSessionUser(session);
	
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		pager.getSearchMap().put("type", String.valueOf(currUser.getId()));
		myimpressionService.Userpager(pager);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Myimpression> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Myimpression myimpression : list) {
			User lUser=userService.selectOnlyOne(myimpression.getFindid());
			JsonObject jsonObj = gson.toJsonTree(myimpression).getAsJsonObject();
			jsonObj.addProperty("id", lUser.getId());
			jsonObj.addProperty("name", lUser.getName());
			jsonObj.addProperty("nameNick", lUser.getNameNick());
			jsonObj.addProperty("phone", lUser.getPhone());
			jsonObj.addProperty("sex", lUser.getSex());
			jsonObj.addProperty("age", lUser.getAge());
			jsonObj.addProperty("marriage", lUser.getMarriage());
			jsonObj.addProperty("hobbues", lUser.getHobbues());
			jsonObj.addProperty("constellation", lUser.getConstellation());
			jsonObj.addProperty("company", lUser.getCompany());
			jsonObj.addProperty("nowlive", lUser.getNowlive());
			jsonObj.addProperty("hometown", lUser.getHometown());
			jsonObj.addProperty("qqaccount", lUser.getQqaccount());
			jsonObj.addProperty("loveStar", lUser.getLoveStar());
			jsonObj.addProperty("lovemuice", lUser.getLovemuice());
			jsonObj.addProperty("loveDeliciousfood", lUser.getLoveDeliciousfood());
			jsonObj.addProperty("signature", lUser.getSignature());
			jsonObj.addProperty("ctiontime", TagUtils.formatDate(lUser.getCtiontime()));
			jsonObj.addProperty("loveFilm", lUser.getLoveFilm());
			jsonObj.addProperty("acnumber", lUser.getAcnumber());
			jsonObj.addProperty("namePic", TagUtils.getFileFullPath(lUser.getNamePic()));
			jsonObj.addProperty("personalMany", lUser.getPersonalMany());
			jsonObj.addProperty("lng", lUser.getLng());
			jsonObj.addProperty("lat", lUser.getLat());
			array.add(jsonObj);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}

}
