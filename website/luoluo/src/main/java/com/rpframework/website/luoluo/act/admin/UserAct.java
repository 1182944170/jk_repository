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
import com.rpframework.utils.AlgorithmEnum;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.service.UserService;

@Controller
@RequestMapping("admin/user")
public class UserAct extends AdminAct{
	@Resource UserService userservice;
	/**
	 * 显示所有的用户信息
	 * @param pager
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false)Pager<User> pager, Map<String, Pager<User>> model){
		if(pager==null){
			pager=new Pager<User>();
		}
		pager=userservice.Userpager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user/list");
	}
	/**
	 * 修改单个用户信息页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value="id")Integer id,Map<String, User> model){
 		User userOne=userservice.selectOnlyOne(id);
		model.put("user", userOne);
		return this.doPackageURI("user/edit");
	}
	/**
	 * 修改信息
	 * @param user
	 * @return
	 */
	@RequestMapping("dosave")
	public String dosave(@ModelAttribute User user ,@RequestParam(value="pager", required=false)Pager<User> pager,RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<User>();
		}
		User userOne=userservice.selectOnlyOne(user.getId());
		if(userOne==null){
			pager=userservice.seleNameOrPhone(pager ,user.getPhone());
			if(pager.getItemList().size()!=0){
				throw new AdminIllegalArgumentException("用户名已经存在");
			}
			String newPasswordMD5 = AlgorithmUtils.encodePassword(user.getPassword(), AlgorithmEnum.MD5);
			user.setCtiontime(System.currentTimeMillis()/1000);
			user.setPassword(newPasswordMD5);
			user.setType(1);
			userservice.Newinsert(user);
			setInfoMsg("新增操作成功！", attr);
			return redirect("list");
		}
		userOne.setName(user.getName());
		String newPasswordMD5 = AlgorithmUtils.encodePassword(user.getPassword(), AlgorithmEnum.MD5);
		userOne.setPassword(newPasswordMD5);
		userservice.updatedo(userOne);
		setInfoMsg("修改成功！", attr);
		return redirect("list");
	}
	/**
	 * 禁用用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/saveUserda")
	public String saveUserda(@PathVariable(value="id")Integer id){
		User userOne=userservice.selectOnlyOne(id);
		if(userOne.getType()==1){
			userOne.setType(0);
			userservice.updatedo(userOne);
		}else if(userOne.getType()==0){
			userOne.setType(1);
			userservice.updatedo(userOne);
		}
		throw new AdminIllegalArgumentException("修改成功");
	}
	/**
	 * 通过用户名和电话查询
	 * @param nameone
	 * @param model
	 * @param pager
	 * @param attr
	 * @return
	 */
	@RequestMapping("/seleNamePhone")
	public String seleNamePhone(@RequestParam String nameone,Map<Object, Object> model,@RequestParam(value="pager", required=false) Pager<User> pager, RedirectAttributes attr){
		if(StringUtils.isBlank(nameone)){
			throw new AdminIllegalArgumentException("请输入内容");
		}
		if(pager == null) {
			pager = new Pager<User>();
		}
		//通过姓名查询
		pager=userservice.seleNameOrPhone(pager,nameone);
		if(pager.getItemList().size()==0){
		//通过电话查询
			pager=userservice.selephones(pager,nameone);
			if(pager.getItemList().size()==0){
				throw new AdminIllegalArgumentException("该用户不存在");
			}
			model.put("pager", pager);
			return this.doPackageURI("user/list");
		}
		model.put("pager", pager);
		return this.doPackageURI("user/list");
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
	public String state(@PathVariable Integer size  ,@RequestParam(value="pager", required=false) Pager<User> pager,Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<User>();
		}
		pager.getSearchMap().put("typeid", String.valueOf(size));
		pager = userservice.Userpager(pager);
		model.put("pager", pager);
		return this.doPackageURI("user/list");
	}
	/**
	 * 删除用户
	 * @param id
	 * @param attr
	 * @return
	 */
	@RequestMapping("/{id}/deletUser")
	public String deletUser(@PathVariable Integer id,RedirectAttributes attr){
		userservice.deletesell(id);
		setInfoMsg("删除成功！", attr);
		return redirect("list");
	}
	/**
	 * 跳转添加用户
	 * @param id
	 * @param attr
	 * @return
	 */
	@RequestMapping("/add")
	public String add(){
		return this.doPackageURI("user/add");
	}
}
