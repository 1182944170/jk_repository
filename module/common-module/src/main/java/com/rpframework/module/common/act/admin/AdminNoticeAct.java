package com.rpframework.module.common.act.admin;

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
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.Notice;
import com.rpframework.module.common.service.NoticeService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/common/notice")
public class AdminNoticeAct extends CommonBaseAct{
	@Resource NoticeService noticeService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = noticeService.getNoticePager(pager);
		model.put("pager", pager);
		return this.doPackageURI("notice/list");
	}
	
	@RequestMapping("/{noticeId}/edit")
	public String edit(@PathVariable Integer noticeId, Map<Object, Object> model,RedirectAttributes attr){
		Notice notice = noticeService.noticeDao.select(noticeId);
		if(notice == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + noticeId);
		}
		model.put("notice", notice);
		return this.add(attr, model);
	}
	
	@RequestMapping("/{noticeId}/delete")
	public String delete(@PathVariable Integer noticeId, Map<Object, Object> model,RedirectAttributes attr){
		Notice notice = noticeService.noticeDao.select(noticeId);
		if(notice == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + noticeId);
		}
		noticeService.noticeDao.delete(noticeId);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/common/notice/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("notice/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute Notice notice, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		noticeService.doSaveOrUpdate(notice);
		if(NumberUtils.isValid(notice.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
