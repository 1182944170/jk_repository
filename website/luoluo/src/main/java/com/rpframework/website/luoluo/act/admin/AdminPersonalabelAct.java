package com.rpframework.website.luoluo.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Personalabel;
import com.rpframework.website.luoluo.service.PersonalabelService;

@Controller
@RequestMapping("admin/lable")
public class AdminPersonalabelAct extends AdminAct{
	
	@Resource PersonalabelService   labelService ;
	
	/**
	 * 显示全部的标签
	 * @param pager
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value="pager", required=false)Pager<Personalabel> pager, Map<String, Pager<Personalabel>> model){
		if(pager==null){
			pager=new Pager<Personalabel>();
		}
		pager=labelService.labelpager(pager);
		model.put("pager", pager);
		return this.doPackageURI("listylabel/list");
	}
	/**
	 * 编辑标签
	 * @return
	 * @time 2015年7月23日 下午1:58:35
	 */
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value="id")Integer id,Map<String, Personalabel> model){
		if(id==null){
			throw new AdminIllegalArgumentException("不存在该用户");
		}
		Personalabel labelOne = labelService.selectOnlyOne(id);
		model.put("user", labelOne);
		return this.doPackageURI("listylabel/edit");
	}
	/**
	 * 修改信息
	 * @param user
	 * @return
	 */
	@RequestMapping("dosave")
	public String dosave(@ModelAttribute Personalabel label ,@RequestParam(value="pager", required=false)Pager<Personalabel> pager,RedirectAttributes attr){
		if(StringUtils.isBlank(label.getLabel())){
			throw new AdminIllegalArgumentException("请输入内容，重新添加");
		}
		if(pager == null) {
			pager = new Pager<Personalabel>();
		}
		Personalabel userOne=labelService.selectOnlyOne(label.getId());
		if(userOne==null){
			pager=labelService.seleNameOrPhone(pager ,label.getLabel());
			if(pager.getItemList().size()!=0){
				throw new AdminIllegalArgumentException("该标签已经存在");
			}
			labelService.Newinsert(label);
			setInfoMsg("新增操作成功！", attr);
			return redirect("list");
		}
		userOne.setLabel(label.getLabel());
		labelService.updatedo(userOne);
		
			setInfoMsg("更新操作成功！", attr);
		
		return redirect("list");
	}
	/**
	 * 添加老师
	 * @return
	 * @time 2015年7月23日 下午1:58:35
	 */
	@RequestMapping("add")
	public String add(){
		return this.doPackageURI("listylabel/add");
	}
	/**
	 * 删除信息页面
	 * @param attr
	 * @param model
	 * @return
	 */
		@RequestMapping("/{deleteid}/delete")
		public String delete(@PathVariable Integer deleteid,RedirectAttributes attr){
			
			labelService.deletesell(deleteid);
			
				setInfoMsg("删除成功！", attr);
				return redirect("/admin/lable/list");
		
		}
}
