package com.rpframework.website.edongwang.springmvc.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.google.gson.JsonElement;
import com.rpframework.website.edongwang.exception.APICodeException;

@Controller
public class ApiCodeExceptionHandler implements HandlerExceptionResolver {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		if(ex instanceof APICodeException) {
			Map<String, Object> map = new HashMap<String, Object>();
			((APICodeException) ex).put(map);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		}
		
		if(handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Class<?> returnType = handlerMethod.getMethod().getReturnType();
			if(returnType == JsonElement.class) {//只要action的方法的返回值是JsonElement 说明是 json 格式的，异常的话也返回json
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", 9999);
				map.put("msg", ex.getLocalizedMessage());
				map.put("succ", false);
				return new ModelAndView(new MappingJackson2JsonView(), map);
			}
		}
		return null;
	}

}
