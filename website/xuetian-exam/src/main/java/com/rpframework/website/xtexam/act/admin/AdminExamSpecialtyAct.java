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
import com.rpframework.website.xtexam.domain.ExamCoursesClassify;
import com.rpframework.website.xtexam.domain.ExamSpecialtyClassify;
import com.rpframework.website.xtexam.service.ExamCoursesService;
import com.rpframework.website.xtexam.service.ExamSpecialtyService;

@Controller
@RequestMapping("/admin/specialty")
public class AdminExamSpecialtyAct extends AdminAct {
	@Resource ExamSpecialtyService examSpecialtyService;
	@Resource ExamCoursesService examCoursesService;
	
	@RequestMapping("/list")
	public String list(@RequestParam Integer coursesId, Map<Object, Object> model, RedirectAttributes attr) {
		ExamCoursesClassify courses = examCoursesService.examCoursesClassifyDao.select(coursesId);
		if(courses == null) {
			throw new AdminIllegalArgumentException("科目不存在的ID:" + coursesId);
		}
		List<ExamSpecialtyClassify> list = examSpecialtyService.examSpecialtyClassifyDao.getListByCoursesId(coursesId);
		model.put("list", list);
		model.put("courses", courses);
		return this.doPackageURI("specialty/list");
	}
	
	@RequestMapping("/{specialtyId}/chapters")
	public String list_chapters(@PathVariable Integer specialtyId, Map<Object, Object> model, RedirectAttributes attr) {
		return this.forward("/admin/chapter/list?specialtyId="+specialtyId);
	}
	
	@RequestMapping("/{specialtyId}/edit")
	public String edit(@PathVariable Integer specialtyId, Map<Object, Object> model,RedirectAttributes attr){
		ExamSpecialtyClassify specialty = examSpecialtyService.examSpecialtyClassifyDao.select(specialtyId);
		if(specialty == null) {
			throw new AdminIllegalArgumentException("找不到专业：Id:" + specialtyId);
		}
		model.put("specialty", specialty);
		return this.add(specialty.getParent().getId(), attr, model);
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam Integer coursesId, RedirectAttributes attr, Map<Object, Object> model){
		ExamCoursesClassify courses = examCoursesService.examCoursesClassifyDao.select(coursesId);
		if(courses == null) {
			throw new AdminIllegalArgumentException("找不到科目：Id:" + coursesId);
		}
		
		model.put("courses", courses);
		return this.doPackageURI("specialty/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute ExamSpecialtyClassify specialty, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		examSpecialtyService.doSaveOrUpdate(specialty);
		if(NumberUtils.isValid(specialty.getId())) {//update
			setInfoMsg("更新专业操作成功！", attr);
		} else {//insert
			setInfoMsg("新增专业操作成功！", attr);
		}
		
		return redirect("/admin/courses/" + specialty.getParent().getId() +"/specialtys");
	}
}
