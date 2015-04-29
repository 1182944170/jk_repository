package com.rpframework.module.common.act.admin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.InitServlet;
import com.rpframework.core.utils.cache.CacheUtils;
import com.rpframework.module.common.act.CommonBaseAct;
import com.rpframework.module.common.utils.cache.CountryCache;
import com.rpframework.utils.FileUtils;

@Controller
@RequestMapping("/admin/common/country")
public class AdminCountryAct extends CommonBaseAct {
	@RequestMapping("sync_static_js")
	public @ResponseBody JsonElement syncStaticJs(HttpServletRequest request,HttpServletResponse response, RedirectAttributes attr) throws IOException {
		String filePath = FileUtils.splicePaths(InitServlet.REAL_PATH, "resources/static/data_country_static.js");
		File file = new File(filePath);
		if(!file.exists()) {
			boolean mkdirsFlag = file.getParentFile().mkdirs();
			if(! mkdirsFlag) {
				throw new IllegalArgumentException("目录创建失败!" + file.getParentFile().getAbsolutePath());
			}
			file.createNewFile();
		}
		
		CountryCache countryCache = CacheUtils.getIntance().get2(CountryCache.k);
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
		PrintWriter writer = new PrintWriter(out);
		writer.write("COUNTRY_STATIC_JSON = " + countryCache.countrys.toString());
		writer.flush();
		writer.close();
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("succ", "同步成功！");
		return jsonObject;
	}
}