package com.rpframework.module.common.act.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.htmlparser.util.ParserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.domain.Document;
import com.rpframework.module.common.service.DocumentService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

@Controller
@RequestMapping("/admin/common/document")
public class AdminDocumentAct extends CommonBaseAct {
	@Resource DocumentService documentService;
	
	@RequestMapping("list")
	public String list(@RequestParam(required=false) Pager<Document> pager, Map<Object, Object> model) throws ParserException, InterruptedException {
		if(pager == null) {
			pager = new Pager<Document>();
		}
		
		pager = documentService.getDocumentPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("document/list");
	}
	
	@RequestMapping("/{documentId}/edit")
	public String edit(@PathVariable Integer documentId, Map<Object, Object> model,RedirectAttributes attr){
		Document document = documentService.documentDao.select(documentId);
		if(document == null) {
			throw new AdminIllegalArgumentException("知识库不存在的ID:" + documentId);
		}
		model.put("document", document);
		return this.add(attr, model);
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		return this.doPackageURI("document/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@ModelAttribute Document document, HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		documentService.doSaveOrUpdate(document);
		if(NumberUtils.isValid(document.getId())) {//update
			setInfoMsg("更新知识库操作成功！", attr);
		} else {//insert
			setInfoMsg("新增知识库操作成功！", attr);
		}
		
		return redirect("list");
	}
}