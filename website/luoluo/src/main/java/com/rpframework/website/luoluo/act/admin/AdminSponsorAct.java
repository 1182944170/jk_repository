package com.rpframework.website.luoluo.act.admin;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.api.FileService;
import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.ClassificationService;
import com.rpframework.website.luoluo.service.SponsorService;

@Controller
@RequestMapping("admin/spons")
public class AdminSponsorAct extends AdminAct{
	@Resource SponsorService sponsorService;
	@Resource ActivityService activityService;
	@Resource ClassificationService classificationService;
	@Resource FileService fileService;
	/**
	 * 显示个人
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("individuallist")
	public String individuallist(@RequestParam(value="pager", required=false) Pager<Sponsorlis> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<Sponsorlis>();
		}
		pager.getSearchMap().put("teyeid", String.valueOf(1));
		pager=sponsorService.getpager(pager);
	
		model.put("pager", pager);
		return this.doPackageURI("sponsor/list");
	}
	/**
	 * 显示公司
	 * @param pager
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("companylist")
	public String companylist(@RequestParam(value="pager", required=false) Pager<Sponsorlis> pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager==null){
			pager=new Pager<Sponsorlis>();
		}
		pager.getSearchMap().put("teyeid", String.valueOf(2));
		pager=sponsorService.getpager(pager);
		
		model.put("se","ff");
		model.put("pager", pager);
		return this.doPackageURI("sponsor/list");
	}
	//单个用户
	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable(value="id") Integer id, Map<Object, Object> model, RedirectAttributes attr){
		if(id==null){
			throw new AdminIllegalArgumentException("不存在该用户");
		}
		Sponsorlis labelOne = sponsorService.seletOne(id);
		model.put("user", labelOne);
		return this.doPackageURI("sponsor/edit");
	}
	@RequestMapping("/{id}/addlis")
	public String addlis(@PathVariable(value="id") Integer id, Map<Object, Object> model, RedirectAttributes attr){
		if(id==null){
			throw new AdminIllegalArgumentException("不存在该用户");
		}
		Sponsorlis labelOne = sponsorService.seletOne(id);
		model.put("user", labelOne);
		return this.doPackageURI("sponsor/addlist");
	}
	
	/**
	 * 修改用户信息
	 * @param sponsor
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping("/dosave")
	public String dosave(@ModelAttribute Sponsorlis sponsor, Map<Object, Object> model, RedirectAttributes attr){
		Sponsorlis sponsorlis=sponsorService.seletOne(sponsor.getId());
		sponsorlis.setUsernowlive(sponsor.getUsernowlive());
		sponsorlis.setUserpicture(sponsor.getUserpicture());
		sponsorlis.setUserphone(sponsor.getUserphone());
		sponsorlis.setUserinformation(sponsor.getUserinformation());
		sponsorlis.setUsertelephone(sponsor.getUsertelephone());
		sponsorlis.setTelephone(sponsor.getTelephone());
		sponsorlis.setResponsibility(sponsor.getResponsibility());
		sponsorlis.setEntintroduction(sponsor.getEntintroduction());
		sponsorlis.setUserinformation(sponsor.getUserinformation());
		sponsorlis.setResponsibility(sponsor.getResponsibility());
		sponsorlis.setCompanyname(sponsor.getCompanyname());
		sponsorlis.setTypeopp(1);
		sponsorService.updatedo(sponsorlis);
		if(sponsorlis.getType()==1){
			setInfoMsg("更新操作成功！", attr);
			return redirect("/admin/spons/individuallist");
		}
		else{
			setInfoMsg("更新操作成功！", attr);
			return redirect("/admin/spons/companylist");
		}
	}
	/**
	 * 添加页面
	 * @param attr
	 * @param model
	 * @return
	 */
		@RequestMapping("/add")
		public String add(Map<Object, Object> model){
			return this.doPackageURI("sponsor/add");
		}
		/**
		 * 添加信息
		 * @param attr
		 * @param model
		 * @return
		 */
		@RequestMapping("/addsavese")
		public String addsavese(@ModelAttribute Sponsorlis sponsor,Map<Object, Object> model, RedirectAttributes attr,
				@RequestParam(value="cardFrontPage", required=false) MultipartFile cardFrontPage ){
			//判断是个人用户还是 公司用户
			if(sponsor.getType()==2){
//公司用户时判断电话以及公司信息是否完整
				if(StringUtils.isBlank(sponsor.getUsertelephone()) || StringUtils.isBlank(sponsor.getResponsibility()) ){
					setInfoMsg("请将公司信息完善", attr);
					return redirect("/admin/spons/add");
				}
				if(cardFrontPage.getSize() > 0) {
					try {
						String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(cardFrontPage.getOriginalFilename());
						fileService.saveFile(cardFrontPage.getInputStream(), relativelyCardFrontPhoto); 
						sponsor.setUserpicture(relativelyCardFrontPhoto);
						sponsor.setActivityTime(System.currentTimeMillis()/1000);
					} catch (Exception e) {
						throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
					}
				} else {
					
				}
				sponsorService.insertsponsor(sponsor);
				setInfoMsg("添加成功", attr);
				return redirect("/admin/spons/companylist");
			}else{
//个人注册
				if(StringUtils.isBlank(sponsor.getUsernowlive()) || StringUtils.isBlank(sponsor.getUserphone())){
					setInfoMsg("请将个人信息完善", attr);
					return redirect("/admin/spons/add");
				}
				if(cardFrontPage.getSize() > 0) {
					try {
						String relativelyCardFrontPhoto = "/luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(cardFrontPage.getOriginalFilename());
						fileService.saveFile(cardFrontPage.getInputStream(), relativelyCardFrontPhoto); 
						sponsor.setUserpicture(relativelyCardFrontPhoto);
						sponsor.setActivityTime(System.currentTimeMillis()/1000);
					} catch (Exception e) {
						throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
					}
				} else {
					
				}
				sponsorService.insertsponsor(sponsor);
				setInfoMsg("添加成功", attr);
				return redirect("/admin/spons/individuallist");
			}
		}
		
		/**
		 * 删除信息页面
		 * @param attr
		 * @param model
		 * @return
		 */
			@RequestMapping("/{deleteid}/delete")
			public String delete(@PathVariable Integer deleteid,RedirectAttributes attr){
				Sponsorlis spon=sponsorService.seletOne(deleteid);
				sponsorService.deletesell(deleteid);
				if(spon.getType()==1){
					setInfoMsg("删除成功！", attr);
					return redirect("/admin/spons/individuallist");
				}
				else{
					setInfoMsg("删除成功！", attr);
					return redirect("/admin/spons/companylist");
				}
			}
			/**
			 * 禁用用户
			 * @param id
			 * @return
			 */
			@RequestMapping("/{id}/saveUserda")
			public String saveUserda(@PathVariable(value="id")Integer id,RedirectAttributes attr){
				Sponsorlis userOne=sponsorService.seletOne(id);
				if(userOne.getTypeopp()==1){
					userOne.setTypeopp(0);
					sponsorService.updatedo(userOne);
				}else if(userOne.getTypeopp()==0){
					userOne.setTypeopp(1);
					sponsorService.updatedo(userOne);
				}
				if(userOne.getType()==1){
					setInfoMsg("审核成功！", attr);
					return redirect("/admin/spons/individuallist");
				}
				else{
					setInfoMsg("审核成功！", attr);
					return redirect("/admin/spons/companylist");
				}
			}
			

}
