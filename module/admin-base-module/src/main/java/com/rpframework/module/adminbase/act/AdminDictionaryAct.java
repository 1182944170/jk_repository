package com.rpframework.module.adminbase.act;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.adminbase.domain.Dictionary;
import com.rpframework.module.adminbase.event.AdminBaseModuleEvent;
import com.rpframework.module.adminbase.service.DictionaryService;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/dictionary")
public class AdminDictionaryAct extends AdminBaseAct {
	
	@Resource DictionaryService dictionaryService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = dictionaryService.getDictionaryPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("dictionary/list");
	}
	
	@RequestMapping("/{dictionaryVariable}/edit")
	public String edit(@PathVariable String dictionaryVariable, Map<Object, Object> model,RedirectAttributes attr){
		Dictionary dictionary = dictionaryService.dictionaryDao.select(dictionaryVariable);
		if(dictionary == null) {
			throw new AdminIllegalArgumentException("找不到 dictionaryVariable:" + dictionaryVariable);
		}
		
		if(dictionary.getCanUpdate() != 1) {
			throw new AdminIllegalArgumentException("不能被修改的 dictionaryVariable:" + dictionaryVariable);
		}
		model.put("dictionary", dictionary);
		return this.add(attr);
	}
	
	@RequestMapping("/{dictionaryVariable}/delete")
	public String delete(@PathVariable String dictionaryVariable, Map<Object, Object> model,RedirectAttributes attr){
		Dictionary dictionary = dictionaryService.dictionaryDao.select(dictionaryVariable);
		if(dictionary == null) {
			throw new AdminIllegalArgumentException("找不到 dictionaryVariable:" + dictionaryVariable);
		}
		
		if(dictionary.getCanDelete() != 1) {
			throw new AdminIllegalArgumentException("不能被删除的 dictionaryVariable:" + dictionaryVariable);
		}
		
		dictionaryService.dictionaryDao.delete(dictionaryVariable);
		setInfoMsg("删除操作成功，请记得 ‘同步字典’！", attr);
		return redirect("/admin/dictionary/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr){
		return this.doPackageURI("dictionary/edit");
	}
	
	@RequestMapping("/sync")
	public String sync(RedirectAttributes attr){
		AdminBaseModuleEvent adminBaseModuleEvent = SpringUtils.getBean(AdminBaseModuleEvent.class);
		adminBaseModuleEvent.initDictionary();
		setInfoMsg("字典同步成功!", attr);
		return redirect("/admin/dictionary/list");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute Dictionary dictionary, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		if(dictionary == null || StringUtils.isBlank(dictionary.getVariable())
				|| StringUtils.isBlank(dictionary.getValue())) {
			throw new AdminIllegalArgumentException("参数异常!");
		}
		
		Dictionary dbDictionary = dictionaryService.dictionaryDao.select(dictionary.getVariable());
		if(dbDictionary != null) { // update
			if(dbDictionary.getCanUpdate() != 1) {
				throw new AdminIllegalArgumentException("不能被修改的 dictionaryVariable:" + dictionary.getVariable());
			}
			
			dictionaryService.dictionaryDao.update(dictionary);
			setInfoMsg("修改操作成功，请记得 ‘同步字典’！", attr);
		} else {
			dictionaryService.dictionaryDao.insert(dictionary);
			setInfoMsg("新增操作成功，请记得 ‘同步字典’！", attr);
		}
		
		return redirect("/admin/dictionary/list");
	}
}