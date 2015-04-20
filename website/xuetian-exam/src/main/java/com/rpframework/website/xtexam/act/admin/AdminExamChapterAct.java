package com.rpframework.website.xtexam.act.admin;

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

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.xtexam.domain.ExamChapterClassify;
import com.rpframework.website.xtexam.domain.ExamSpecialtyClassify;
import com.rpframework.website.xtexam.service.ExamChapterService;
import com.rpframework.website.xtexam.service.ExamSpecialtyService;

@Controller
@RequestMapping("/admin/chapter")
public class AdminExamChapterAct extends AdminAct {
	@Resource ExamSpecialtyService examSpecialtyService;
	@Resource ExamChapterService examChapterService;
	
	@RequestMapping("/list")
	public String list(@RequestParam Integer specialtyId, Map<Object, Object> model, RedirectAttributes attr) {
		ExamSpecialtyClassify specialty = examSpecialtyService.examSpecialtyClassifyDao.select(specialtyId);
		if(specialty == null) {
			throw new AdminIllegalArgumentException("专业不存在的ID:" + specialtyId);
		}
		List<ExamChapterClassify> list = examChapterService.examChapterClassifyDao.getListBySpecialtyId(specialtyId);
		model.put("list", list);
		model.put("specialty", specialty);
		return this.doPackageURI("chapter/list");
	}
	
	@RequestMapping("/{chapterId}/subchapters")
	public String list_subchapters(@PathVariable Integer chapterId, Map<Object, Object> model, RedirectAttributes attr) {
		return this.forward("/admin/subchapter/list?chapterId="+chapterId);
	}
	
	@RequestMapping("/{chapterId}/edit")
	public String edit(@PathVariable Integer chapterId, Map<Object, Object> model,RedirectAttributes attr){
		ExamChapterClassify chapter = examChapterService.examChapterClassifyDao.select(chapterId);
		if(chapter == null) {
			throw new AdminIllegalArgumentException("章节不存在的ID:" + chapterId);
		}
		model.put("chapter", chapter);
		return this.add(chapter.getParent().getId(), attr, model);
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam Integer specialtyId, RedirectAttributes attr, Map<Object, Object> model){
		ExamSpecialtyClassify specialty = examSpecialtyService.examSpecialtyClassifyDao.select(specialtyId);
		if(specialty == null) {
			throw new AdminIllegalArgumentException("找不到专业：Id:" + specialtyId);
		}
		
		model.put("specialty", specialty);
		return this.doPackageURI("chapter/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute ExamChapterClassify chapter, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		examChapterService.doSaveOrUpdate(chapter);
		if(NumberUtils.isValid(chapter.getId())) {//update
			setInfoMsg("更新章节操作成功！", attr);
		} else {//insert
			setInfoMsg("新增章节操作成功！", attr);
		}
		
		return redirect("/admin/specialty/" + chapter.getParent().getId() +"/chapters");
	}
}
