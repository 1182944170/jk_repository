package com.rpframework.website.edongwang.act.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.edongwang.domain.Job;
import com.rpframework.website.edongwang.service.JobService;

@Controller
@RequestMapping("/admin/job")
public class AdminJobAct extends AdminAct{
	@Resource JobService jobService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pager", required = false) Pager pager,Map<Object, Object> model, RedirectAttributes attr) {
		if (pager == null) {
			pager = new Pager();
		}
		pager = jobService.getPager(pager);
		model.put("pager", pager);
		return doPackageURI("job/list");
	}
	
	@RequestMapping("/{jobId}/edit")
	public String edit(@PathVariable Integer jobId, Map<Object, Object> model,RedirectAttributes attr){
		Job job = jobService.select(jobId);
		Assert.notNull(job, "不存在的ID:" + jobId);
		model.put("job", job);
		return this.add(attr, model);
	}

	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model) {
		return this.doPackageURI("job/edit");
	}

	@RequestMapping("/dosave")
	public String doSaveOrUpdate(
			@ModelAttribute Job job, 
			HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		jobService.doSaveOrUpdate(job);
		if(NumberUtils.isValid(job.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
	
}
