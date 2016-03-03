package com.rpframework.website.luoluo.act.admin;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.api.FileService;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Widens;
import com.rpframework.website.luoluo.service.WidensService;

@Controller
@RequestMapping("admin/windens")
public class AdminWidensAct extends AdminAct{
	
	@Resource WidensService widenserivece;
	@Resource public FileService fileService;
	
	
	@RequestMapping("list")
	public String list(@RequestParam(value="pager", required=false)Pager<Widens> pager, Map<String, Pager<Widens>> model){
		if(pager==null){
			pager=new Pager<Widens>();
		}
		pager.getSearchMap().put("state", String.valueOf(1));
		pager=widenserivece.widenpager(pager);
		model.put("pager", pager);
		return this.doPackageURI("widens/list");
	}
/*	
	
	*//**
	 * 修改单个用户信息页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value="id")Integer id,Map<String, Widens> model){
		Widens userOne=widenserivece.selectOnlyOne(id);
		model.put("user", userOne);
		return this.doPackageURI("widens/edit");
	}
	/**
	 * 修改信息
	 * @param user
	 * @return
	 */
	@RequestMapping("dosave")
	public String dosave(@ModelAttribute Widens widen ,@RequestParam(value="pager", required=false)Pager<Widens> pager,RedirectAttributes attr,
			@RequestParam(value="mainFile",required=false)MultipartFile eoppos){
		
		Widens widenOne=widenserivece.selectOnlyOne(widen.getId());
		if(widenOne==null){
//添加内容
			if(!eoppos.isEmpty()){
				try{
					//重写图片路径
					String relativelyVideoPath = "/luoluo/user/headImage/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(eoppos.getOriginalFilename());
					//将图片，id，密码放入user对象中
					fileService.saveFile(eoppos.getInputStream(), relativelyVideoPath); 
					widen.setSyimages(relativelyVideoPath);
				} catch(Exception e) {
					throw new IllegalArgumentException(e.getLocalizedMessage());
				}
			}
			
			widen.setSytime(System.currentTimeMillis()/1000);
			widen.setState(1);
			widenserivece.wideninsert(widen);
			setInfoMsg("新增操作成功！", attr);
			return redirect("/admin/windens/list");
		}else{
//修改内容
		if(!eoppos.isEmpty()){
			try{
				//重写图片路径
				String relativelyVideoPath = "/luoluo/user/headImage/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(eoppos.getOriginalFilename());
				//将图片，id，密码放入user对象中
				fileService.saveFile(eoppos.getInputStream(), relativelyVideoPath); 
				widen.setSyimages(relativelyVideoPath);
			
			} catch(Exception e) {
				throw new IllegalArgumentException(e.getLocalizedMessage());
			}
		}
		widenserivece.updatewiden(widen);
		setInfoMsg("更新操作成功！", attr);
		
		}
		return redirect("/admin/windens/list");
	}
	/**
	 * 禁用用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/saveUserda")
	public String saveUserda(@PathVariable(value="id")Integer id,RedirectAttributes attr){
		Widens widenOne=widenserivece.selectOnlyOne(id);
		if(widenOne.getType()==1){
			setInfoMsg("可以使用！", attr);
			
			widenOne.setType(0);
			widenserivece.updatewiden(widenOne);
		}else if(widenOne.getType()==0){
			setInfoMsg("禁用成功！", attr);
			widenOne.setType(1);
			widenserivece.updatewiden(widenOne);
		}
		
		return redirect("/admin/windens/list");
	}
/**
	 * 删除用户
	 * @param id
	 * @param attr
	 * @return
	 */
	@RequestMapping("/{id}/deletUser")
	public String deletUser(@PathVariable Integer id,RedirectAttributes attr){
		widenserivece.deletesell(id);
		setInfoMsg("删除成功！", attr);
		return redirect("/admin/windens/list");
	}
	/**
	 * 跳转添加用户
	 * @param id
	 * @param attr
	 * @return
	 */
	@RequestMapping("/add")
	public String add(){
		return this.doPackageURI("widens/add");
	}
}
