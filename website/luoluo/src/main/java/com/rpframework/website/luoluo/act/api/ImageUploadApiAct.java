package com.rpframework.website.luoluo.act.api;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseAct;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.luoluo.domain.User;
import com.rpframework.website.luoluo.service.ActivityService;
import com.rpframework.website.luoluo.service.UserService;


@Controller
@RequestMapping("/api/image/")
public class ImageUploadApiAct extends BaseAct {
	@Resource ActivityService activityService;
	@Resource UserService userService;
	
	@RequestMapping(value="/upload",produces = "application/json; charset=utf-8")
	public @ResponseBody JsonElement dosave(@RequestParam(required=false) MultipartFile file ,//图片对象
			@RequestParam(value="folder",required=false) String folder,//文件夹名			
			@RequestParam(value="userId",required=false) Integer userId,//用户id 更改头像用的		
			HttpServletRequest request){
		JsonObject json = new JsonObject();
		try {
			if(file !=null && file.getSize()>0){
				folder = (folder==null || folder == "") ? "folder" : folder;//如果上传的文件夹名为空，则默认为folder
				String url =activityService.uploadImg(file,folder);//保存图片
				if(NumberUtils.isValid(userId)){
					User u = userService.selectOnlyOne(userId);
					if(u!=null){
						u.setNamePic(url);
						boolean flag = userService.updatedo(u);
						if(!flag)
							json.addProperty("msg", "图片地址未更新成功!");
					}
				}
				json.addProperty("succ", true);
				json.addProperty("url", url);//返回物理存储地址
				json.addProperty("suffix", FilenameUtils.getExtension(file.getOriginalFilename()));//返回后缀
			}else{
				json.addProperty("succ", false);
				json.addProperty("msg", "不存在的图片!");
			}
		} catch (Exception e) {
			json.addProperty("succ", false);
			json.addProperty("msg", "图片上传发生异常!");
		}
		return json;
	}
}