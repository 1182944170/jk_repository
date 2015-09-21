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
import com.rpframework.website.luoluo.domain.Mypersonalitylabel;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.MypersonalitylabelService;
import com.rpframework.website.luoluo.service.UserService;


@Controller
@RequestMapping("api/user")
public class ApiUserAct extends BaseAct{
	Gson gson = new Gson();
	@Resource UserService userservice;
	@Resource FileService fileService;
	@Resource MypersonalitylabelService mypersonalitylabelService;
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
	public @ResponseBody JsonElement userone(@RequestParam(required=false) String type,HttpSession session) throws ParserException, InterruptedException{
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		if(type.equals(0)){
			currUser.setType(2);
		}else if(type.equals(2)){
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
			@RequestParam(required=false) String nameNick,
			@RequestParam(required=false) String sex,
			@RequestParam(required=false) String age,
			@RequestParam(required=false) String marriage ,
			@RequestParam(required=false) String hobbues,
			@RequestParam(required=false) String constellation,
			@RequestParam(required=false) String company,
			@RequestParam(required=false) String nowlive,
			@RequestParam(required=false) String hometown,
			@RequestParam(required=false) String qqaccount,
			@RequestParam(required=false) String loveStar,
			@RequestParam(required=false) String lovemuice,
			@RequestParam(required=false) String loveDeliciousfood,
			@RequestParam(required=false) String loveFilm,
			@RequestParam(required=false) String signature,
			HttpSession session){
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		int sexs = Integer.parseInt(sex);
		int ages=Integer.parseInt(age);
		
		currUser.setNameNick(nameNick);
		currUser.setSex(sexs);
		currUser.setAge(ages);
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
		currUser.setSignature(signature);
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
		json.addProperty("age", user.getAge());
		json.addProperty("phone", user.getPhone());
		json.addProperty("marriage", user.getMarriage());
		json.addProperty("constellation", user.getConstellation());
		json.addProperty("company", user.getCompany());
		json.addProperty("nowlive", user.getNowlive());
		json.addProperty("hometown", user.getHometown());
		json.addProperty("qqaccount", user.getQqaccount());
		json.addProperty("hobbues", user.getHobbues());
		json.addProperty("loveStar", user.getLoveStar());
		json.addProperty("lovemuice", user.getLovemuice());
		json.addProperty("loveDeliciousfood", user.getLoveDeliciousfood());
		json.addProperty("loveFilm", user.getLoveFilm());
		json.addProperty("signature", user.getSignature());
		json.addProperty("ctiontime", user.getCtiontime());
		json.addProperty("acnumber", user.getAcnumber());
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
				String relativelyPath = "luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(userPic.getOriginalFilename());
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
	
	/**
	 * 好友相信信息
	 * @time 2015年7月30日 下午3:39:57
	 */
	@RequestMapping("/change_user_find")
	public @ResponseBody JsonElement changeUserfind(
			@RequestParam(required=false) Integer userid,
			HttpSession session, HttpServletRequest request){
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");		
		}
		JsonObject json = new JsonObject();
		//用户
		User user=userservice.selectOnlyOne(userid);
		json.addProperty("id", user.getId());
		json.addProperty("userPic", TagUtils.getFileFullPath(user.getNamePic()));
		json.addProperty("nickName", user.getNameNick());
		json.addProperty("sex", user.getSex());
		json.addProperty("age", user.getAge());
		json.addProperty("phone", user.getPhone());
		json.addProperty("marriage", user.getMarriage());
		json.addProperty("constellation", user.getConstellation());
		json.addProperty("company", user.getCompany());
		json.addProperty("nowlive", user.getNowlive());
		json.addProperty("hometown", user.getHometown());
		json.addProperty("qqaccount", user.getQqaccount());
		json.addProperty("hobbues", user.getHobbues());
		json.addProperty("loveStar", user.getLoveStar());
		json.addProperty("lovemuice", user.getLovemuice());
		json.addProperty("loveDeliciousfood", user.getLoveDeliciousfood());
		json.addProperty("loveFilm", user.getLoveFilm());
		json.addProperty("signature", user.getSignature());
		json.addProperty("ctiontime", user.getCtiontime());
		json.addProperty("acnumber", user.getAcnumber());
		//标签
		Mypersonalitylabel Myperson=mypersonalitylabelService.selectOnlyOne(userid);
		json.addProperty("Mylabel1", Myperson.getMylabel1());
		json.addProperty("Mylabel2", Myperson.getMylabel2());
		json.addProperty("Mylabel3", Myperson.getMylabel3());
		json.addProperty("Mylabel4", Myperson.getMylabel4());
		json.addProperty("Mylabel5", Myperson.getMylabel5());
		json.addProperty("Mylabel6", Myperson.getMylabel6());
		json.addProperty("Mylabel7", Myperson.getMylabel7());
		json.addProperty("Mylabel8", Myperson.getMylabel8());
		json.addProperty("Userid", Myperson.getUserid());
		return json;
	}
	
	
	
	
}


