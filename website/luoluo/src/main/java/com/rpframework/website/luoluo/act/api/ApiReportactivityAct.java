package com.rpframework.website.luoluo.act.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.website.luoluo.domain.Reportactivity;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.ReportactivityService;

@Controller
@RequestMapping("api/repor")
public class ApiReportactivityAct  extends BaseAct{
	@Resource ReportactivityService reportactivityService;
	
	/**
	 * 举报
	 * @param activityid
	 * @param reportContext
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("add")
	public @ResponseBody JsonElement userlist(	
			@RequestParam(required=false) Integer activityid,
			@RequestParam(required=false) String reportContext,
			HttpSession session) throws ParserException, InterruptedException{
	
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(StringUtils.isBlank(reportContext)){
			throw new APICodeException(-1, "举报内容不能为空");
		}
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		Reportactivity reporta=new Reportactivity();
		reporta.setActivityid(activityid);
		reporta.setActtime(System.currentTimeMillis()/1000);
		reporta.setReportContext(reportContext);
		reporta.setUserid(currUser.getId());
		boolean bFlag = reportactivityService.insertdo(reporta);
		if(bFlag == true){ // 修改成功
			json.addProperty("succ", true);
		} else { // 修改失败
			json.addProperty("error", false);
		} 
		return json;
	}
}
