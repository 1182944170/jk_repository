package com.rpframework.website.luoluo.act.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.service.ActivitypictureSercice;
;

@Controller
@RequestMapping("api/picture")
public class ApiActivitypictureAct extends BaseAct{
	@Resource ActivitypictureSercice activitypictureSercice;
	
	/**
	 * 添加报名信息
	 * @param name
	 * @param phone
	 * @param emergencyphone
	 * @param emergencyname
	 * @param oldboy
	 * @param chindenboy
	 * @param monely
	 * @param mood
	 * @param insure
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public  @ResponseBody JsonElement add(
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String phone,
			@RequestParam(required=false) String emergencyphone,
			@RequestParam(required=false) String emergencyname,
			@RequestParam(required=false) String oldboy,
			@RequestParam(required=false) String chindenboy,
			@RequestParam(required=false) String monely,
			@RequestParam(required=false) String mood,
			@RequestParam(required=false) String[] insure
			)throws Exception{
				Activitypicture Activitypi=new Activitypicture();
				Activitypi.setName(name);
				Activitypi.setPhone(phone);
				Activitypi.setEmergencyname(emergencyname);
				Activitypi.setEmergencyphone(emergencyphone);
				Activitypi.setOldboy(oldboy);
				Activitypi.setChindenboy(chindenboy);
				Activitypi.setMonely(monely);
				Activitypi.setMood(mood);
				Activitypi.setInsure(insure);
		boolean activi=activitypictureSercice.updatedo(Activitypi);
		JsonObject json=new JsonObject();
		if(activi==true){
			json.addProperty("succ", true);
		} else { // 添加失败
			json.addProperty("error", false);
		}
		return json;
	}
}
