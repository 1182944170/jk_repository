package com.rpframework.module.common.act.admin;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.api.FileService;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;

@Controller
@RequestMapping("/admin/upload")
public class AdminUploadAct extends CommonBaseAct{
	@Resource FileService fileService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Map<Object, Object> model, RedirectAttributes attr){
		return this.doPackageURI("upload/test_upload");
	}
	
	@RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
	@ResponseBody
	public JsonElement uploadImg(@RequestParam(value = "file", required = false) MultipartFile file, 
			@RequestParam(required = false) String rootPath,
			HttpServletRequest request,
			Map<Object, Object> model, RedirectAttributes attr) {
		if(StringUtils.isBlank(rootPath)) {
			rootPath = "resources/default/";
		}
		
		logger.info("file.getOriginalFilename()-{}", file.getOriginalFilename());
		JsonObject json = new JsonObject();
		boolean flag = true;
		if(!file.isEmpty()) {
			String relativelyPath = rootPath + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			try {
				fileService.saveFile(file, relativelyPath);
				json.addProperty("path", relativelyPath);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				flag = false;
				json.addProperty("errMsg", e.getLocalizedMessage());
				e.printStackTrace();
			}
		}
		
		json.addProperty("succ", flag);
		return json;
	}
}