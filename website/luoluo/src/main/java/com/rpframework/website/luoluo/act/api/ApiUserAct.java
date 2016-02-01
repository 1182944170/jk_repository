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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
import com.rpframework.website.luoluo.domain.Monlyjournallist;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.exception.APICodeException;
import com.rpframework.website.luoluo.service.MonlyjournallistService;
import com.rpframework.website.luoluo.service.MypersonalitylabelService;
import com.rpframework.website.luoluo.service.UserService;


@Controller
@RequestMapping("api/user")
public class ApiUserAct extends BaseAct{
	Gson gson = new Gson();
	@Resource UserService userservice;
	@Resource FileService fileService;
	@Resource MypersonalitylabelService mypersonalitylabelService;
	@Resource MonlyjournallistService monlyjournalsService;
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
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
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
	public @ResponseBody JsonElement userlistid( @RequestParam(required=false) Integer userid,HttpSession session
			) throws ParserException, InterruptedException{
		User user = userservice.selectOnlyOne(userid);
		JsonObject json = new JsonObject();
		json.addProperty("id", user.getId());
		json.addProperty("name", user.getName());
		json.addProperty("nameNick", user.getNameNick());
		json.addProperty("phone", user.getPhone());
		json.addProperty("sex", user.getSex());
		json.addProperty("age", user.getAge());
		json.addProperty("marriage", user.getMarriage());
		json.addProperty("hobbues", user.getHobbues());
		json.addProperty("constellation", user.getConstellation());
		json.addProperty("company", user.getCompany());
		json.addProperty("nowlive", user.getNowlive());
		json.addProperty("hometown", user.getHometown());
		json.addProperty("qqaccount", user.getQqaccount());
		json.addProperty("loveStar", user.getLoveStar());
		json.addProperty("lovemuice", user.getLovemuice());
		json.addProperty("loveDeliciousfood", user.getLoveDeliciousfood());
		json.addProperty("signature", user.getSignature());
		json.addProperty("ctiontime", TagUtils.formatDate(user.getCtiontime()));
		json.addProperty("loveFilm", user.getLoveFilm());
		json.addProperty("acnumber", user.getAcnumber());
		json.addProperty("namePic", user.getNamePic());
		json.addProperty("personalMany", user.getPersonalMany());
		json.addProperty("lng", user.getLng());
		json.addProperty("lat", user.getLat());
		return json;
	}
	/**
	 * 通过电话查询用户
	 * @param pager
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/user_listphone")
	public @ResponseBody JsonElement userlistphone(@RequestParam(required=false) String phone,HttpSession session
			) throws ParserException, InterruptedException{

		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		User user = userservice.findUserByPhone(phone);
		JsonObject json = new JsonObject();
		json.addProperty("id", user.getId());
		json.addProperty("name", user.getName());
		json.addProperty("nameNick", user.getNameNick());
		json.addProperty("phone", user.getPhone());
		json.addProperty("sex", user.getSex());
		json.addProperty("age", user.getAge());
		json.addProperty("marriage", user.getMarriage());
		json.addProperty("hobbues", user.getHobbues());
		json.addProperty("constellation", user.getConstellation());
		json.addProperty("company", user.getCompany());
		json.addProperty("nowlive", user.getNowlive());
		json.addProperty("hometown", user.getHometown());
		json.addProperty("qqaccount", user.getQqaccount());
		json.addProperty("loveStar", user.getLoveStar());
		json.addProperty("lovemuice", user.getLovemuice());
		json.addProperty("loveDeliciousfood", user.getLoveDeliciousfood());
		json.addProperty("signature", user.getSignature());
		json.addProperty("ctiontime", TagUtils.formatDate(user.getCtiontime()));
		json.addProperty("loveFilm", user.getLoveFilm());
		json.addProperty("acnumber", user.getAcnumber());
		json.addProperty("namePic", TagUtils.getFileFullPath(user.getNamePic()));
		json.addProperty("personalMany", user.getPersonalMany());
		json.addProperty("lng", user.getLng());
		json.addProperty("lat", user.getLat());
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
			json.addProperty("succ", true);
		}else{
			json.addProperty("error", false);
		}
		return json;
	}
	/**
	 * 经纬度
	 * @param type
	 * @param session
	 * @return
	 * @throws ParserException
	 * @throws InterruptedException
	 */
	@RequestMapping("/latitude")
	public @ResponseBody JsonElement latitude(
			@RequestParam(required=false) String lat,
			@RequestParam(required=false) String lng,
			HttpSession session) throws ParserException, InterruptedException{
		JsonObject json = new JsonObject();
		User currUser = getSessionUser(session);
		if(currUser == null){
			throw new APICodeException(-4, "你还没登陆!");
		}
		currUser.setLat(lat);
		currUser.setLng(lng);
		boolean bfgl= userservice.updatedo(currUser);
		if(bfgl){
			session.setAttribute(SESSION_USER_KEY, currUser);
			json.addProperty("succ", true);
		}else{
			json.addProperty("succ", false);
		}
		return json;
	}
	/**
	 * 附近的人
	 * @param pager
	 * @param session
	 * @return
	 */
	@RequestMapping("/usernearby")
	public @ResponseBody JsonElement jintweidu(@RequestParam(value="pager",required=false) Pager<User> pager,
			@RequestParam(required=false) String lat,
			@RequestParam(required=false) String lng,
			HttpSession session){
		List<User> list = userservice.selectactivice(lat,lng);
		if(pager==null){
			pager = new Pager<User>();
		}
		long startTime = System.currentTimeMillis();
		pager.setItemList(list);
		pager.setTotalCount(list.size());
		pager.setCostTime(System.currentTimeMillis() - startTime);
		JsonObject json = new JsonObject();
		json.addProperty("totalPages", pager.getTotalPages());
		json.addProperty("currentPage", pager.getCurrentPage());
		json.addProperty("totalCount", pager.getTotalCount());
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for (User act : list) {
			JsonObject jsonObj = gson.toJsonTree(act).getAsJsonObject();
				array.add(jsonObj);
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
		
		if(sex==null){
			sex="0";
		}else if(age==null){
			age="0";
		}
		int sexs = Integer.parseInt(sex);
		
		int ages=Integer.parseInt(age);
		
		currUser.setName("hjejnd");
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
			json.addProperty("id", currUser.getId());
			json.addProperty("userPic", TagUtils.getFileFullPath(currUser.getNamePic()));
			json.addProperty("nickName", currUser.getNameNick());
			json.addProperty("sex", currUser.getSex());
			json.addProperty("age", currUser.getAge());
			json.addProperty("phone", currUser.getPhone());
			json.addProperty("marriage", currUser.getMarriage());
			json.addProperty("constellation", currUser.getConstellation());
			json.addProperty("company", currUser.getCompany());
			json.addProperty("nowlive", currUser.getNowlive());
			json.addProperty("hometown", currUser.getHometown());
			json.addProperty("qqaccount", currUser.getQqaccount());
			json.addProperty("hobbues", currUser.getHobbues());
			json.addProperty("loveStar", currUser.getLoveStar());
			json.addProperty("lovemuice", currUser.getLovemuice());
			json.addProperty("loveDeliciousfood", currUser.getLoveDeliciousfood());
			json.addProperty("loveFilm", currUser.getLoveFilm());
			json.addProperty("signature", currUser.getSignature());
			json.addProperty("ctiontime", currUser.getCtiontime());
			json.addProperty("acnumber", currUser.getAcnumber());
			session.setAttribute(SESSION_USER_KEY, currUser);
			json.addProperty("succ", true);
		}else{
			json.addProperty("succ", false);
		}
		return json;
	}
	
	/**
	 * 修改头像
	 * @time 2015年7月30日 下午3:39:57
	 */
	@RequestMapping("/change_user_img")
	public @ResponseBody JsonElement changeHeadImg(
			@RequestParam(value="userPic", required=false) CommonsMultipartFile userPic,
			HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		if(user == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		if(userPic != null && !userPic.isEmpty()) {
			try {
				String relativelyPath = "luoluo/user/userPic/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(userPic.getOriginalFilename());
				fileService.saveFile(userPic.getInputStream(), relativelyPath);
				boolean bFlag = userservice.changeUserPic(user, relativelyPath);
				JsonObject json = new JsonObject();
				if(bFlag){
					json.addProperty("succ", true);
					json.addProperty("userPic", TagUtils.getFileFullPath(relativelyPath));
				}else{
					json.addProperty("error", false);
				}
				
				return json;
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		throw new IllegalArgumentException("参数错误!");
	}
	/**
	 * 日志文件
	 * @time 2015年7月30日 下午3:39:57
	 */
	@RequestMapping("/monlyjournallist")
	public @ResponseBody JsonElement monlyjournallist(
			HttpSession session, HttpServletRequest request){
		User user = getSessionUser(session);
		if(user == null){
			throw new APICodeException(-4, "你还没登陆!");
		}	
		JsonObject json = new JsonObject();
		json.addProperty("personalMany", user.getPersonalMany());
		List<Monlyjournallist>  list =monlyjournalsService.selectdoole(user.getId());
		JsonArray array = new JsonArray();
		json.add("arrays", array);
		for(Monlyjournallist act :list){
			JsonObject o = gson.toJsonTree(act).getAsJsonObject();
			array.add(o);
		}
		return json;
	}
	
	/**
	 * 好友相信信息
	 * @time 2015年7月30日 下午3:39:57
	 */
	@RequestMapping("/change_user_find")
	public @ResponseBody JsonElement changeUserfind(
			@RequestParam(required=false) Integer userid,
			HttpSession session, HttpServletRequest request){

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

		return json;
	}	
}


