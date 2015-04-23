package com.rpframework.website.xtexam.act.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.core.BaseAct;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.GsonUtils;
import com.rpframework.website.xtexam.domain.ExamSubChapterClassify;
import com.rpframework.website.xtexam.domain.ExamSubject;
import com.rpframework.website.xtexam.domain.XTOrder;
import com.rpframework.website.xtexam.domain.XTUser;
import com.rpframework.website.xtexam.domain.XTUserExamLog;
import com.rpframework.website.xtexam.domain.XTUserExamSubjectLog;
import com.rpframework.website.xtexam.domain.XTUserSubjectNote;
import com.rpframework.website.xtexam.exception.APICodeException;
import com.rpframework.website.xtexam.service.ExamSubjectService;
import com.rpframework.website.xtexam.service.XTOrderService;
import com.rpframework.website.xtexam.service.XTUserExamLogService;
import com.rpframework.website.xtexam.service.XTUserExamSubjectLogService;
import com.rpframework.website.xtexam.service.XTUserService;
import com.rpframework.website.xtexam.service.XTUserSubjectNoteService;
import com.rpframework.website.xtexam.utils.XTConstants;

@Controller
@RequestMapping("/api/user")
public class XTUserApiAct extends BaseAct {
	@Resource XTUserService xtUserService;
	@Resource SMSService smsService;
	@Resource XTUserExamLogService xtUserExamLogService;
	@Resource ExamSubjectService examSubjectService;
	@Resource XTUserSubjectNoteService xtUserSubjectNoteService;
	@Resource XTUserExamSubjectLogService xtUserExamSubjectLogService;
	@Resource XTOrderService xtOrderService;
	
	@RequestMapping("/userdata")
	public @ResponseBody JsonElement getUserData(HttpSession session, HttpServletRequest request) {
		XTUser xtUser = getSessionUser(session);
		Integer userId = xtUser.getId();
		
		//已购买试题
		int buyExamCount = xtOrderService.getUserSuccOrderCount(userId);
		//我的错题
		int wrongAnswerCount = xtUserExamSubjectLogService.getUserWrongAnswerCount(userId);
		
		//我的收藏
		int favCount = xtUserSubjectNoteService.getUserFavCount(userId);
		
		//我的笔记
		int commentCount = xtUserSubjectNoteService.getUserCommentCount(userId);
		
		//练习记录
		int reportCount = xtUserExamLogService.getUserExamLogCount(userId);
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("userId", xtUser.getId());
		jsonObject.addProperty("areaPath", xtUser.getAreaPath());
		jsonObject.addProperty("contact", xtUser.getContact());
		jsonObject.addProperty("userName", xtUser.getUserName());
		jsonObject.addProperty("examClassifyIds", xtUser.getExamClassifyIds());
		jsonObject.addProperty("succ", true);
		jsonObject.addProperty("buyExamCount", buyExamCount);
		jsonObject.addProperty("wrongAnswerCount", wrongAnswerCount);
		jsonObject.addProperty("favCount", favCount);
		jsonObject.addProperty("commentCount", commentCount);
		jsonObject.addProperty("reportCount", reportCount);//report/list
		return jsonObject;
	}
	
	@RequestMapping("/userdata/buy_log")
	public @ResponseBody JsonElement getUserBuyLogData(HttpSession session, HttpServletRequest request) {
		XTUser xtUser = getSessionUser(session);
		Integer userId = xtUser.getId();
		
		List<XTOrder> list = xtOrderService.getUserSuccOrder(userId);
		JsonArray array = new JsonArray();
		
		if(CollectionUtils.isNotEmpty(list)) {
			for (XTOrder order : list) {
				JsonObject json = new JsonObject();
				array.add(json);
				
				json.addProperty("orderNo", order.getOrderNo());
				json.addProperty("costMoney", order.getCostMoney());
				json.addProperty("rechargeExamSubchapterId", order.getRechargeExamSubchapterId());
				json.addProperty("lastModifyTime", order.getLastModifyTime());
			}
		}
		return array;
	}
	
	@RequestMapping("/userdata/fav")
	public @ResponseBody JsonElement getUserFavData(HttpSession session, HttpServletRequest request) {
		XTUser xtUser = getSessionUser(session);
		Integer userId = xtUser.getId();
		JsonArray array = new JsonArray();
		
		List<XTUserSubjectNote> list = xtUserSubjectNoteService.getUserFav(userId);
		if(CollectionUtils.isNotEmpty(list)) {
			for (XTUserSubjectNote xtUserSubjectNote : list) {
				array.add(xtUserSubjectNote.getSubject().getJson());
			}
		}
		return array;
	}
	
	@RequestMapping("/userdata/comment")
	public @ResponseBody JsonElement getUserCommentData(HttpSession session, HttpServletRequest request) {
		XTUser xtUser = getSessionUser(session);
		Integer userId = xtUser.getId();
		JsonArray array = new JsonArray();
		
		List<XTUserSubjectNote> list = xtUserSubjectNoteService.getUserComment(userId);
		if(CollectionUtils.isNotEmpty(list)) {
			for (XTUserSubjectNote xtUserSubjectNote : list) {
				JsonObject json = xtUserSubjectNote.getSubject().getJson();
				json.addProperty("comment", xtUserSubjectNote.getContent());
				array.add(json);
			}
		}
		return array;
	}
	
	@RequestMapping("/userdata/wrong_answer")
	public @ResponseBody JsonElement getUserWrongAnswerData(HttpSession session, HttpServletRequest request) {
		XTUser xtUser = getSessionUser(session);
		Integer userId = xtUser.getId();
		JsonArray array = new JsonArray();
		
		List<XTUserExamSubjectLog> wrongAnswer = xtUserExamSubjectLogService.getUserWrongAnswer(userId);
		if(CollectionUtils.isNotEmpty(wrongAnswer)) {
			for (XTUserExamSubjectLog subjectLog : wrongAnswer) {
				List<XTUserExamSubjectLog> examSubjectLogs = new ArrayList<XTUserExamSubjectLog>();
				List<ExamSubject> subjects = new ArrayList<ExamSubject>();
				examSubjectLogs.add(subjectLog);
				subjects.add(subjectLog.getSubject());
				
				JsonArray jsonArray = packageSubjectOption(userId, subjects , examSubjectLogs);
				array.addAll(jsonArray);
			}
		}
		return array;
	}
	
	@RequestMapping("/subject/{subjectId}/add_fav")
	public @ResponseBody JsonElement addFavSubject(@PathVariable Integer subjectId, HttpSession session, HttpServletRequest request) {
		ExamSubject subject = examSubjectService.examSubjectDao.select(subjectId);
		XTUser xtUser = getSessionUser(session);
		if(subject == null) {
			throw new APICodeException(-1, "找不到试题ID:" + subjectId);
		}
		boolean flag = xtUserSubjectNoteService.addFavSubject(xtUser.getId(), subjectId);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("succ", flag);
		return jsonObject;
	}
	
	@RequestMapping("/subject/{subjectId}/add_note")
	public @ResponseBody JsonElement addNoteSubject(@PathVariable Integer subjectId, @RequestParam String content, HttpSession session, HttpServletRequest request) {
		ExamSubject subject = examSubjectService.examSubjectDao.select(subjectId);
		XTUser xtUser = getSessionUser(session);
		if(subject == null) {
			throw new APICodeException(-1, "找不到试题ID:" + subjectId);
		}
		boolean flag = xtUserSubjectNoteService.addNoteSubject(xtUser.getId(), subjectId, content);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("succ", flag);
		return jsonObject;
	}
	
	@RequestMapping("/subject/{subjectId}/add_comment")
	public @ResponseBody JsonElement addCommentSubject(@PathVariable Integer subjectId, @RequestParam String content, HttpSession session, HttpServletRequest request) {
		ExamSubject subject = examSubjectService.examSubjectDao.select(subjectId);
		XTUser xtUser = getSessionUser(session);
		if(subject == null) {
			throw new APICodeException(-1, "找不到试题ID:" + subjectId);
		}
		boolean flag = examSubjectService.addCommentToSubject(xtUser.getId(), subjectId, content);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("succ", flag);
		return jsonObject;
	}
	
	@RequestMapping("/subject/{subjectId}/add_like")
	public @ResponseBody JsonElement addLikeSubject(@PathVariable Integer subjectId, HttpSession session, HttpServletRequest request) {
		ExamSubject subject = examSubjectService.examSubjectDao.select(subjectId);
		XTUser xtUser = getSessionUser(session);
		if(subject == null) {
			throw new APICodeException(-1, "找不到试题ID:" + subjectId);
		}
		boolean flag = examSubjectService.likeSubject(xtUser.getId(), subjectId);
		if(!flag) {
			throw new APICodeException(-2, "你已经点赞过了.");
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("succ", flag);
		return jsonObject;
	}
	
	@RequestMapping("/exam/{subchaperId}/start")
	public @ResponseBody JsonElement startExam(@PathVariable Integer subchaperId, HttpSession session, HttpServletRequest request) {
		if(subchaperId == null || subchaperId < 1) {
			throw new APICodeException(-1, "非法参数");
		}
		XTUser xtUser = getSessionUser(session);
		
		XTUserExamLog log = new XTUserExamLog();
		ExamSubChapterClassify subchaper = new ExamSubChapterClassify();
		subchaper.setId(subchaperId);
		log.setExamClassify(subchaper);
		log.setRecordCreateTime(System.currentTimeMillis() / 1000);
		log.setScore(0);
		log.setState(XTConstants.XTUserExamLog.EXAMING);
		log.setUser(xtUser);
		
		boolean flag = xtUserExamLogService.userExamLogDao.insert(log);
		JsonObject json = new JsonObject();
		json.addProperty("succ", flag);
		json.addProperty("examLogId", log.getId());
		List<ExamSubject> subjects = examSubjectService.getListBySubChapterId(subchaper.getId());
		json.add("subjects", this.packageSubjectOption(xtUser.getId(), subjects , null));
		//返回这试卷的试题
		return json;
	}
	
	@RequestMapping("/report/list")
	public @ResponseBody JsonElement listReport(HttpSession session, HttpServletRequest request) {
		XTUser xtUser = getSessionUser(session);
		JsonArray array = new JsonArray();
		List<XTUserExamLog> examLogList = xtUserExamLogService.getUserExamLog(xtUser.getId());
		if(CollectionUtils.isNotEmpty(examLogList)) {
			for (XTUserExamLog xtUserExamLog : examLogList) {
				List<ExamSubject> subjects = examSubjectService.getListBySubChapterId(xtUserExamLog.getExamClassify().getId());
				JsonObject logJson = packageXTUserExamLog(xtUser.getId(), subjects, xtUserExamLog);
				array.add(logJson);
			}
		}
		return array;
	}
	
	
	@RequestMapping("/report/{examLogId}/restart")
	public @ResponseBody JsonElement restartReport(@PathVariable Integer examLogId, HttpSession session, HttpServletRequest request) {
		if(examLogId == null || examLogId < 1) {
			throw new APICodeException(-1, "非法参数");
		}
		XTUser xtUser = getSessionUser(session);
		
		XTUserExamLog log = xtUserExamLogService.userExamLogDao.select(examLogId);
		if(log == null) {
			throw new APICodeException(-3, "找不到log." + examLogId);
		}
		
		if(log.getUser().getId() != xtUser.getId()) {
			throw new APICodeException(-2, "不能查看别人的报告");
		}
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		json.addProperty("examLogId", log.getId());
		List<ExamSubject> subjects = examSubjectService.getListBySubChapterId(log.getExamClassify().getId());
		json.add("subjects", this.packageSubjectOption(xtUser.getId(), subjects , log.getExamSubjectLogs()));
		//返回这试卷的试题
		return json;
	}
	
	@RequestMapping("/report/{examLogId}/submit")
	public @ResponseBody JsonElement submitReport(@PathVariable Integer examLogId, @RequestParam String options, HttpSession session, HttpServletRequest request) {
		if(examLogId == null || examLogId < 1) {
			throw new APICodeException(-1, "非法参数");
		}
		
		XTUser xtUser = getSessionUser(session);
		JsonArray optionArray = new JsonParser().parse(options).getAsJsonArray();
		List<XTUserExamSubjectLog> optionList = new ArrayList<XTUserExamSubjectLog>();
		XTUserExamLog examLog = new XTUserExamLog();
		examLog.setId(examLogId);
		
		for (JsonElement jsonElement : optionArray) {
			JsonObject json = jsonElement.getAsJsonObject();
			XTUserExamSubjectLog subjectLog = new XTUserExamSubjectLog();
			ExamSubject subject = new ExamSubject();
			
			subject.setId(GsonUtils.getInt(json, "subjectId"));
			subjectLog.setSubject(subject);
			subjectLog.setExamLog(examLog);
			subjectLog.setUserAnswer(GsonUtils.getString(json, "userAnswer"));
			
			optionList.add(subjectLog);
		}
		JsonObject json = new JsonObject();
		
		boolean flag = xtUserExamLogService.submitExam(xtUser.getId(), examLogId, optionList);
		//试卷提交成功，获取该试卷的报告内容
		if(flag) {
			XTUserExamLog xtUserExamLog = xtUserExamLogService.userExamLogDao.select(examLogId);
			
			List<ExamSubject> subjects = examSubjectService.getListBySubChapterId(xtUserExamLog.getExamClassify().getId());
			JsonObject logJson = packageXTUserExamLog(xtUser.getId(), subjects, xtUserExamLog);
			json.add("report", logJson);
		}
		
		json.addProperty("succ", flag);
		return json;
	}
	
	private JsonObject packageXTUserExamLog(Integer userId, List<ExamSubject> subjects, XTUserExamLog xtUserExamLog) {
		JsonObject json = new JsonObject();
		json.addProperty("examLogId", xtUserExamLog.getId());
		json.addProperty("score", xtUserExamLog.getScore());
		json.addProperty("recordCreateTime", xtUserExamLog.getRecordCreateTime());
		json.addProperty("finishedTime", xtUserExamLog.getFinishedTime());
		json.addProperty("totalScore", xtUserExamLog.getExamClassify().getTotalScore());
		JsonArray subjectLogArray = packageSubjectOption(userId , subjects, xtUserExamLog.getExamSubjectLogs());
		json.add("subjects", subjectLogArray);
		return json;
	}

	private JsonArray packageSubjectOption(Integer userId, List<ExamSubject> subjects, List<XTUserExamSubjectLog> subjectLogs) {
		JsonArray array = new JsonArray();
		for (ExamSubject subject: subjects) {
			JsonObject json = subject.getJson();
			array.add(json);
			XTUserExamSubjectLog xtUserExamSubjectLog = find(subjectLogs, subject.getId());
			if(xtUserExamSubjectLog != null) {
				if(StringUtils.isBlank(xtUserExamSubjectLog.getUserAnswer())) {
					json.addProperty("wasChoiced", false);
				} else {
					json.addProperty("wasChoiced", true);
					json.addProperty("choicedWasAnswerRight", xtUserExamSubjectLog.getIsAnswerRight());
					json.addProperty("userAnswer", xtUserExamSubjectLog.getUserAnswer());
					
					XTUserSubjectNote note = xtUserSubjectNoteService.findByUserIdAndSubjectId(userId, subject.getId());
					json.addProperty("answeredTotal", note.getWrongNum() + note.getRightNum());
					json.addProperty("answeredWrongCount", note.getWrongNum());
					json.addProperty("note", note.getContent());
				}
				
			} else {
				json.addProperty("wasChoiced", false);
			}
			
		}
		return array;
	}
	
	private XTUserExamSubjectLog find(List<XTUserExamSubjectLog> subjectLogs, Integer subjectId) {
		if(CollectionUtils.isEmpty(subjectLogs)) {
			return null;
		}
		
		for (XTUserExamSubjectLog xtUserExamSubjectLog : subjectLogs) {
			if(xtUserExamSubjectLog.getSubject().getId() == subjectId) {
				return xtUserExamSubjectLog;
			}
		}
		
		return null;
	}
	
}
