package com.rpframework.website.edongwang.springmvc.interceptor;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpframework.core.BaseAct;
import com.rpframework.core.utils.GsonUtils;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.utils.AlgorithmUtils;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.website.edongwang.domain.User;
import com.rpframework.website.edongwang.exception.NoLoginException;
import com.rpframework.website.edongwang.service.UserService;
import com.rpframework.website.edongwang.utils.EConfig;
import com.rpframework.website.edongwang.utils.EConstants;

public class EDongWangApiInterceptor implements HandlerInterceptor {
	EConfig xtExamConfig = null;
	final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(xtExamConfig == null) {
			xtExamConfig = SpringUtils.getBean(EConfig.class);
		}
//		logger.info("preHandle....");
		Object user = request.getSession().getAttribute(BaseAct.SESSION_USER_KEY);
		if(user == null) {
			Cookie cookies[] = request.getCookies();
			Cookie loginEncryptCookie = null;
			for (Cookie c : cookies) {
				if (EConstants.COOKIE_LOGIN_ENCRYPT_KEY.equals(c.getName())) {
					loginEncryptCookie = c;
				}
			}
			
			if(loginEncryptCookie != null) {
				try {
					JsonObject json = new JsonParser().parse(AlgorithmUtils.deBase64(loginEncryptCookie.getValue())).getAsJsonObject();
					int userId = GsonUtils.getInt(json, "id");
					long createTime = GsonUtils.getLong(json, "ct");
					String tokenKey = GsonUtils.getString(json, "tk");
					long endTime = DateUtils.monthAdd(1, new Date(createTime * 1000)).getTime() / 1000;
					long nowTime = System.currentTimeMillis() / 1000;
					if(NumberUtils.isValid(userId) && nowTime <= endTime && StringUtils.equals(tokenKey, xtExamConfig.tokenkey)) {
						//如果userId无效或者 过期 或者 tokenKey不想等，则不自动登陆
						UserService xtUserService = SpringUtils.getBean(UserService.class);
						User xtUser = xtUserService.select(userId);
						if(xtUser != null && xtUser.getState() == 1) {
							
							json.addProperty("ct", nowTime);
							String loginEncrypt = AlgorithmUtils.enBase64(json.toString());
							Cookie cookie = new Cookie(EConstants.COOKIE_LOGIN_ENCRYPT_KEY, loginEncrypt);
							response.addCookie(cookie);
							request.getSession().setAttribute(BaseAct.SESSION_USER_KEY, xtUser);
							xtUserService.doLoginRecord(xtUser, request);
						}
					} else {
						throw new NoLoginException();
					}
				} catch (Exception e) {
					logger.error("cookie 值解析失败：" + e.getLocalizedMessage() + ",value:" + loginEncryptCookie.getValue());
					e.printStackTrace();
					throw new NoLoginException();
				}
			} else {
				throw new NoLoginException();
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
