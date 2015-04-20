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
import com.rpframework.website.xtexam.domain.ExamSubChapterClassify;
import com.rpframework.website.xtexam.service.ExamChapterService;
import com.rpframework.website.xtexam.service.ExamSubChapterService;

@Controller
@RequestMapping("/admin/subchapter")
public class AdminExamSubChapterAct extends AdminAct {
	@Resource ExamChapterService examChapterService;
	@Resource ExamSubChapterService examSubChapterService;
	

	@RequestMapping("/list")
	public String list(@RequestParam Integer chapterId, Map<Object, Object> model, RedirectAttributes attr) {
		ExamChapterClassify chapter = examChapterService.examChapterClassifyDao.select(chapterId);
		if(chapter == null) {
			throw new AdminIllegalArgumentException("章节不存在的ID:" + chapterId);
		}
		List<ExamSubChapterClassify> list = examSubChapterService.examSubChapterClassifyDao.getListByChapterId(chapterId);
		model.put("list", list);
		model.put("chapter", chapter);
		return this.doPackageURI("subchapter/list");
	}
	
	
	@RequestMapping("/{subChapterId}/subjects")
	public String list_subject(@PathVariable Integer subChapterId, Map<Object, Object> model,RedirectAttributes attr){
		return this.forward("/admin/subject/list?examClassifyId="+subChapterId);
	}
	
	
	@RequestMapping("/{subChapterId}/edit")
	public String edit(@PathVariable Integer subChapterId, Map<Object, Object> model,RedirectAttributes attr){
		ExamSubChapterClassify subChapter = examSubChapterService.examSubChapterClassifyDao.select(subChapterId);
		if(subChapter == null) {
			throw new AdminIllegalArgumentException("试卷不存在的ID:" + subChapterId);
		}
		model.put("subChapter", subChapter);
		return this.add(subChapter.getParent().getId(), attr, model);
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam Integer chapterId, RedirectAttributes attr, Map<Object, Object> model){
		ExamChapterClassify chapter = examChapterService.examChapterClassifyDao.select(chapterId);
		if(chapter == null) {
			throw new AdminIllegalArgumentException("找不到章节：Id:" + chapterId);
		}
		
		model.put("chapter", chapter);
		return this.doPackageURI("subchapter/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute ExamSubChapterClassify subChapter, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		examSubChapterService.doSaveOrUpdate(subChapter);
		if(NumberUtils.isValid(subChapter.getId())) {//update
			setInfoMsg("更新试卷操作成功！", attr);
		} else {//insert
			setInfoMsg("新增试卷操作成功！", attr);
		}
		
		return redirect("/admin/chapter/" + subChapter.getParent().getId() +"/subchapters");
	}
}
