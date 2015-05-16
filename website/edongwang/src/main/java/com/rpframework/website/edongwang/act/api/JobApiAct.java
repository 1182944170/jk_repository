package com.rpframework.website.edongwang.act.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.Job;
import com.rpframework.website.edongwang.service.JobService;

@Controller
@RequestMapping("/api/job")
public  @ResponseBody class JobApiAct extends BaseAct {
	Gson gson = new Gson();
	@Resource JobService jobService;
	
	@RequestMapping("/list")
	public  @ResponseBody JsonElement list(@RequestParam(value = "pager", required = false) Pager<Job> pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager<Job>();
		}
		pager = jobService.getPager(pager);
		
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		List<Job> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (Job job : list) {
			array.add(packageJson(job));
		}
		return json;
	}
	
	private JsonObject packageJson(Job job){
		JsonObject jsonObject = gson.toJsonTree(job).getAsJsonObject();
		jsonObject.addProperty("ageTypeString", DictionarySettingUtils.getParameterValue("job.ageType." + job.getAgeType()));
		jsonObject.addProperty("expTypeString", DictionarySettingUtils.getParameterValue("job.expType." + job.getExpType()));
		jsonObject.addProperty("eduTypeString", DictionarySettingUtils.getParameterValue("job.eduType." + job.getEduType()));
		jsonObject.addProperty("jobTypeString", DictionarySettingUtils.getParameterValue("job.jobType." + job.getJobType()));
		jsonObject.addProperty("moneyTypeString", DictionarySettingUtils.getParameterValue("job.moneyType." + job.getMoneyType()));
		
		return jsonObject;
	}
	@RequestMapping("{jobId}")
	public @ResponseBody JsonElement view(@PathVariable Integer jobId) throws ParserException, InterruptedException{
		Job job = jobService.select(jobId);
		Assert.notNull(job);
		return packageJson(job);
	}
}
