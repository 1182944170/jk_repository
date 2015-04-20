package com.rpframework.website.xtexam.act.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rpframework.core.InitServlet;
import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.FileUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.xtexam.domain.ExamChapterClassify;
import com.rpframework.website.xtexam.domain.ExamCoursesClassify;
import com.rpframework.website.xtexam.domain.ExamSpecialtyClassify;
import com.rpframework.website.xtexam.domain.ExamSubChapterClassify;
import com.rpframework.website.xtexam.service.ExamChapterService;
import com.rpframework.website.xtexam.service.ExamCoursesService;
import com.rpframework.website.xtexam.service.ExamSpecialtyService;
import com.rpframework.website.xtexam.service.ExamSubChapterService;

@Controller
@RequestMapping("/admin/courses")
public class AdminExamCoursesAct extends AdminAct {
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Resource ExamCoursesService examCoursesService;
	@Resource ExamSpecialtyService examSpecialtyService;
	@Resource ExamChapterService examChapterService;
	@Resource ExamSubChapterService examSubChapterService;
	
	@RequestMapping("/list")
	public String list(Map<Object, Object> model, RedirectAttributes attr) {
		List<ExamCoursesClassify> list = examCoursesService.examCoursesClassifyDao.getAll();
		model.put("list", list);
		return this.doPackageURI("courses/list");
	}
	
	@RequestMapping("/{coursesId}/specialtys")
	public String list_specialtys(@PathVariable Integer coursesId, Map<Object, Object> model, RedirectAttributes attr) {
		return this.forward("/admin/specialty/list?coursesId="+coursesId);
	}
	
	@RequestMapping("/{coursesId}/edit")
	public String edit(@PathVariable Integer coursesId, Map<Object, Object> model,RedirectAttributes attr){
		ExamCoursesClassify courses = examCoursesService.examCoursesClassifyDao.select(coursesId);
		if(courses == null) {
			throw new AdminIllegalArgumentException("找不到科目：Id:" + coursesId);
		}
		model.put("courses", courses);
		return this.add(attr);
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr){
		return this.doPackageURI("courses/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute ExamCoursesClassify courses, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		examCoursesService.doSaveOrUpdate(courses);
		if(NumberUtils.isValid(courses.getId())) {//update
			setInfoMsg("更新科目操作成功！", attr);
		} else {//insert
			setInfoMsg("新增科目操作成功！", attr);
		}
		
		return redirect("/admin/courses/list");
	}
	
	@RequestMapping("/sync_static_js")
	public @ResponseBody JsonObject syncStaticJS(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr) throws IOException{
		//文件同步成功
		String filePath = FileUtils.splicePaths(InitServlet.REAL_PATH, "resources/static/data_exam_classify.js");
		File file = new File(filePath);
		if(!file.exists()) {
			boolean mkdirsFlag = file.getParentFile().mkdirs();
			if(! mkdirsFlag) {
				throw new AdminIllegalArgumentException("目录创建失败!" + file.getParentFile().getAbsolutePath());
			}
			file.createNewFile();
		}
		
		JsonArray jsonArray = new JsonArray();
		List<ExamCoursesClassify> list = examCoursesService.examCoursesClassifyDao.getAllEffective();
		if(CollectionUtils.isEmpty(list)) {
			
		} else {
			for (ExamCoursesClassify courses : list) {
				JsonObject jsonObject = new JsonObject();
				JsonArray specialtyJsonArray = new JsonArray();
				jsonArray.add(jsonObject);
				
				jsonObject.add("specialtys", specialtyJsonArray);
				jsonObject.addProperty("name", courses.getName());
				jsonObject.addProperty("id", courses.getId());
				jsonObject.addProperty("thumb", courses.getThumb());
				jsonObject.addProperty("levelNum", courses.getLevelNum());
				
				List<ExamSpecialtyClassify> specialtys = examSpecialtyService.examSpecialtyClassifyDao.getListEffectiveByCoursesId(courses.getId());
				if(CollectionUtils.isNotEmpty(specialtys)) {
					for (ExamSpecialtyClassify specialty : specialtys) {
						JsonObject specialtyJsonObject = new JsonObject();
						JsonArray chapterJsonArray = new JsonArray();
						
						List<ExamChapterClassify> chapters = examChapterService.examChapterClassifyDao.getListEffectiveBySpecialtyId(specialty.getId());
						if(CollectionUtils.isNotEmpty(chapters)) {
							for (ExamChapterClassify chapter : chapters) {
								JsonObject chapterJsonObject = new JsonObject();
								JsonArray subChapterJsonArray = new JsonArray();
								
								
								List<ExamSubChapterClassify> subChapters = examSubChapterService.examSubChapterClassifyDao.getListEffectiveByChapterId(chapter.getId());
								for (ExamSubChapterClassify subChapter : subChapters) {
									JsonObject subChapterJsonObject = new JsonObject();
									subChapterJsonArray.add(subChapterJsonObject);
									
									subChapterJsonObject.addProperty("name", subChapter.getName());
									subChapterJsonObject.addProperty("id", subChapter.getId());
									subChapterJsonObject.addProperty("thumb", subChapter.getThumb());
									subChapterJsonObject.addProperty("levelNum", subChapter.getLevelNum());
									subChapterJsonObject.addProperty("examTime", subChapter.getExamTime());
									subChapterJsonObject.addProperty("totalSubjectNum", subChapter.getTotalSubjectNum());
									subChapterJsonObject.addProperty("totalScore", subChapter.getTotalScore());
									subChapterJsonObject.addProperty("passScore", subChapter.getPassScore());
									subChapterJsonObject.addProperty("price", subChapter.getPrice());
									subChapterJsonObject.addProperty("discount", subChapter.getDiscount());
								}
								chapterJsonArray.add(chapterJsonObject);
								chapterJsonObject.add("subchapters", subChapterJsonArray);
								chapterJsonObject.addProperty("name", chapter.getName());
								chapterJsonObject.addProperty("id", chapter.getId());
								chapterJsonObject.addProperty("thumb", chapter.getThumb());
								chapterJsonObject.addProperty("levelNum", chapter.getLevelNum());
								
							}
						}
						
						specialtyJsonArray.add(specialtyJsonObject);
						specialtyJsonObject.add("chapters", chapterJsonArray);
						specialtyJsonObject.addProperty("name", specialty.getName());
						specialtyJsonObject.addProperty("id", specialty.getId());
						specialtyJsonObject.addProperty("thumb", specialty.getThumb());
						specialtyJsonObject.addProperty("levelNum", specialty.getLevelNum());
					}
				}
				
			}
		}
		
		logger.info("值:{}", jsonArray.toString());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		PrintWriter writer = new PrintWriter(out);
		writer.write("EXAM_STATIC_JSON = " + jsonArray.toString());
		writer.flush();
		writer.close();
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("succ", "同步成功！");
//		setInfoMsg("同步成功！", attr);
//		return this.redirect("/admin/courses/list");
		
		return jsonObject;
	}
}
