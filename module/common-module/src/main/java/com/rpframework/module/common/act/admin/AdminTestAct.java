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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rpframework.core.api.FileService;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;

@Controller
@RequestMapping("/admin/test")
public class AdminTestAct extends CommonBaseAct{
	@Resource FileService fileService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload(Map<Object, Object> model, RedirectAttributes attr){
		return this.doPackageURI("test/upload");
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public JsonElement upload(@RequestParam(value = "files", required = false) MultipartFile[] files, 
			@RequestParam(required = false) String rootPath,
			HttpServletRequest request,
			Map<Object, Object> model, RedirectAttributes attr) {
		JsonArray paths = new JsonArray();
		if(StringUtils.isNotBlank(rootPath)) {
			rootPath = "resources/default/";
		}
		
		for (MultipartFile file : files) {
			if(file.isEmpty()) continue;
			String relativelyPath = rootPath + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
			try {
				fileService.saveFile(file, relativelyPath);
				paths.add(new JsonPrimitive(relativelyPath));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		JsonObject json = new JsonObject();
		json.addProperty("succ", true);
		json.add("paths", paths);
		return json;
	}
}