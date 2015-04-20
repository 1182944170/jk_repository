package com.rpframework.website.xtexam.act.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.xtexam.domain.ExamSubChapterClassify;
import com.rpframework.website.xtexam.domain.ExamSubject;
import com.rpframework.website.xtexam.domain.ExamSubjectOption;
import com.rpframework.website.xtexam.service.ExamSubChapterService;
import com.rpframework.website.xtexam.service.ExamSubjectService;

@Controller
@RequestMapping("/admin/subject")
public class AdminExamSubjectAct extends AdminAct {
	@Resource ExamSubjectService subjectService;
	@Resource ExamSubChapterService examSubChapterService;

	@RequestMapping("/list")
	public String list(@RequestParam(required=false) Integer examClassifyId,@RequestParam(required=false) Pager<ExamSubject> pager, Map<Object, Object> model, RedirectAttributes attr) {
		
		if(pager == null) {
			pager = new Pager<ExamSubject>();
		}
		
		if(examClassifyId != null) {
			ExamSubChapterClassify subChapter = examSubChapterService.examSubChapterClassifyDao.select(examClassifyId);
			if(subChapter == null) {
				throw new AdminIllegalArgumentException("试卷不存在的ID:" + examClassifyId);
			}
			model.put("subChapter", subChapter);
			pager.getSearchMap().put("examClassifyId", String.valueOf(examClassifyId));
		}
		
		
		pager = subjectService.getExamSubjectPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("subject/list");
	}
	
	@RequestMapping("/{subjectId}/edit")
	public String edit(@PathVariable Integer subjectId, Map<Object, Object> model,RedirectAttributes attr){
		ExamSubject subject = subjectService.examSubjectDao.select(subjectId);
		if(subject == null) {
			throw new AdminIllegalArgumentException("试题不存在的ID:" + subjectId);
		}
		model.put("subject", subject);
		return this.add(subject.getExamClassify().getId(), attr, model);
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam(required=false) Integer subChapterId, RedirectAttributes attr, Map<Object, Object> model){
		if(subChapterId != null) {
			ExamSubChapterClassify subChapter = examSubChapterService.examSubChapterClassifyDao.select(subChapterId);
			if(subChapter == null) {
				throw new AdminIllegalArgumentException("试卷不存在的ID:" + subChapterId);
			}
			
			model.put("subChapter", subChapter);
		}
		return this.doPackageURI("subject/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@RequestParam(value="optionArray") String optionArrayString, @ModelAttribute ExamSubject subject, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		JsonArray optionArray = new JsonParser().parse(optionArrayString).getAsJsonArray();
		if(optionArray.size() != 4) {
			throw new IllegalArgumentException("试题的选项只允许4个.");
		}
		
		Gson gson = new Gson();
		List<ExamSubjectOption> options = new ArrayList<ExamSubjectOption>();
		for (JsonElement optionJson : optionArray) {
			ExamSubjectOption option = gson.fromJson(optionJson, ExamSubjectOption.class);
			options.add(option);
		}
		subject.setOptions(options);
		
		subjectService.doSaveOrUpdate(subject);
		if(NumberUtils.isValid(subject.getId())) {//update
			setInfoMsg("操作成功！", attr);
		} else {//insert
			subjectService.examSubjectDao.insert(subject);
			setInfoMsg("操作成功！", attr);
		}
		
		return redirect("/admin/subchapter/" + subject.getExamClassify().getId() +"/subjects");
	}
}
