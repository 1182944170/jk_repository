package com.rpframework.website.luoluo.act.api;

import java.util.List;









import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.sql.parser.ParserException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.TagUtils;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.UserService;


@Controller
@RequestMapping("api/user")
public class ApiUserAct extends BaseAct{
	Gson gson = new Gson();
	@Resource UserService userservice;
	@Resource FileService fileService;
	
	/**
	 * 用户列表
	 * @date 2015年7月13日 下午5:47:24
	 */
	@RequestMapping("/user_list")
	public @ResponseBody JsonElement userlist(@RequestParam(value="pager",required=false) Pager<User> pager 
			) throws ParserException, InterruptedException{
 		if(pager==null){
 			pager=new Pager<User>();
 		}
	
		pager.getSearchMap().put("type", String.valueOf(0));
		userservice.Userpager(pager);
		JsonObject json = new JsonObject();
		List<User> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (User User : list) {
			JsonObject o = gson.toJsonTree(User).getAsJsonObject();
			array.add(o);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
/**
 * 通过id查询用户
 * @param pager
 * @return
 * @throws ParserException
 * @throws InterruptedException
 */
	@RequestMapping("/user_listid")
	public @ResponseBody JsonElement userlistid(@RequestParam(value="pager",required=false) Pager<User> pager, @RequestParam(required=false) String userid,HttpSession session
			) throws ParserException, InterruptedException{
		if(pager==null){
 			pager=new Pager<User>();
 		}
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		pager.getSearchMap().put("se", String.valueOf(userid));
		userservice.Userpager(pager);
		JsonObject json = new JsonObject();
		List<User> list = pager.getItemList();
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (User User : list) {
			JsonObject o = gson.toJsonTree(User).getAsJsonObject();
			array.add(o);
		}
		System.out.println("user_list: "+json.toString());
		return json;
	}
	
	/**
	 * 修改用户是否可见
	 * @param type
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/user_one")
	public @ResponseBody JsonElement userone(HttpSession session) throws ParserException, InterruptedException{
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		if(currUser.getType()==0){
			currUser.setType(2);
		}else if(currUser.getType()==2){
			currUser.setType(0);
		}
			
		boolean bFlag = userservice.updatedo(currUser);
		if(bFlag){
			json.addProperty("succ", bFlag);
		}else{
			json.addProperty("error", bFlag);
		}
		return json;
	}
	
	/**
	 * 修改用户资料
	 * @time 2015年7月20日 下午2:07:13
	 */
	@RequestMapping("/update_data")
	public @ResponseBody JsonElement updateData(
			@RequestParam(required=false) String nameNick ,
			@RequestParam(required=false) Integer sex ,
			@RequestParam(required=false) Integer age ,
			@RequestParam(required=false) Integer marriage ,
			@RequestParam(required=false) String hobbues ,
			@RequestParam(required=false) String constellation ,
			@RequestParam(required=false) String company ,
			@RequestParam(required=false) String nowlive ,
			@RequestParam(required=false) String hometown ,
			@RequestParam(required=false) String qqaccount ,
			@RequestParam(required=false) String loveStar ,
			@RequestParam(required=false) String lovemuice ,
			@RequestParam(required=false) String loveDeliciousfood ,
			@RequestParam(required=false) String loveFilm ,
			@RequestParam(required=false) String signature ,
			HttpSession session){
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		currUser.setNameNick(nameNick);
		currUser.setSex(sex);
		currUser.setAge(age);
		currUser.setMarriage(marriage);
		currUser.setHobbues(hobbues);
		currUser.setConstellation(constellation);
		currUser.setCompany(company);
		currUser.setNowlive(nowlive);
		currUser.setHometown(hometown);
		currUser.setQqaccount(qqaccount);
		currUser.setLoveStar(loveStar);
		currUser.setLovemuice(lovemuice);
		currUser.setLoveDeliciousfood(loveDeliciousfood);
		currUser.setLoveFilm(loveFilm);
		currUser.setNameNick(signature);
		boolean bFlag = userservice.updatedo(currUser);
		if(bFlag){
			json.addProperty("succ", bFlag);
		}else{
			json.addProperty("error", bFlag);
		}
		return packageUpdateDataJson(currUser);
	}
	public JsonElement packageUpdateDataJson(User user) {
		JsonObject json = new JsonObject();
		json.addProperty("id", user.getId());
		json.addProperty("userPic", TagUtils.getFileFullPath(user.getNamePic()));
		json.addProperty("nickName", user.getNameNick());
		json.addProperty("sex", user.getSex());
		json.addProperty("phone", user.getPhone());
		json.addProperty("marriage", user.getNameNick());
		json.addProperty("constellation", user.getSex());
		json.addProperty("company", user.getPhone());
		json.addProperty("nowlive", user.getNameNick());
		json.addProperty("hometown", user.getSex());
		json.addProperty("qqaccount", user.getPhone());
		json.addProperty("loveStar", user.getAcnumber());
		json.addProperty("loveStar", user.getPhone());
		json.addProperty("lovemuice", user.getNameNick());
		json.addProperty("loveDeliciousfood", user.getSex());
		json.addProperty("loveFilm", user.getPhone());
		json.addProperty("signature", user.getPhone());
		return json;
	}
	/**
	 * 修改头像
	 * @time 2015年7月30日 下午3:39:57
	 */
	@RequestMapping("/change_user_img")
	public @ResponseBody JsonElement changeHeadImg(@RequestParam(required=false) MultipartFile userPic,
			HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		if(userPic != null && !userPic.isEmpty()) {
			try {
				String relativelyPath = "kuale/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(userPic.getOriginalFilename());
				fileService.saveFile(userPic.getInputStream(), relativelyPath);
				boolean bFlag = userservice.changeUserPic(user, relativelyPath);
				JsonObject json = new JsonObject();
				
				if(bFlag){
					json.addProperty("succ", bFlag);
					json.addProperty("userPic", TagUtils.getFileFullPath(relativelyPath));
				}else{
					json.addProperty("error", bFlag);
				}
				
				return json;
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		throw new IllegalArgumentException("参数错误!");
	}
}


