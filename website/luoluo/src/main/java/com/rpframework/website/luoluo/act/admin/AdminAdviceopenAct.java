package com.rpframework.website.luoluo.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Adviceopen;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.service.AdviceopenService;

@Controller
@RequestMapping("admin/advice")
public class AdminAdviceopenAct  extends AdminAct{
	@Resource AdviceopenService adviceopenservice;
	/**
	 * 查询所有
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("list")
	public String list(@RequestParam(value="pager", required=false) Pager<Adviceopen> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Adviceopen>();
		}
		pager = adviceopenservice.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("advice/list");
	}
	
	/**
	 * 修改单个用户信息页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value="id")Integer id,Map<String, Adviceopen> model){
		Adviceopen userOne=adviceopenservice.selectOnlyOne(id);
		model.put("user", userOne);
		return this.doPackageURI("advice/edit");
	}
	
	/**
	 * 禁用用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/saveUserda")
	public String saveUserda(@PathVariable(value="id")Integer id,RedirectAttributes attr){
		Adviceopen userOne=adviceopenservice.selectOnlyOne(id);
		if(userOne.getType()==1){
			userOne.setType(0);
			adviceopenservice.updatedo(userOne);
		}else if(userOne.getType()==0){
			userOne.setType(1);
			adviceopenservice.updatedo(userOne);
		}
		setInfoMsg("修改成功！", attr);
		return redirect("/admin/advice/list");
	}
	/**
	 * 查询用户是否被禁用
	 * @param size
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/{size}/state")
	public String state(@PathVariable Integer size  ,@RequestParam(value="pager", required=false) Pager<Adviceopen> pager,Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Adviceopen>();
		}
		pager.getSearchMap().put("typeid", String.valueOf(size));
		pager = adviceopenservice.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("advice/list");
	}
}
