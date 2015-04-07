package com.rpframework.core.freemarker;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewRendererServlet;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.rpframework.core.InitServlet;

public class FreemarkerFilter implements Filter {
	 private Locale locale;
	public void init(FilterConfig filterConfig) throws ServletException {
		String localeStr = filterConfig.getInitParameter("locale");
        if(StringUtils.isNotBlank(localeStr)){
            locale = new Locale(localeStr);
        }else {
            locale = Locale.getDefault();
        }
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 try {
	            HttpServletRequest req = (HttpServletRequest)request;
	            HttpServletResponse res = (HttpServletResponse)response;
	            String name = req.getRequestURI();
	            int startIndex = 1;
	            if(StringUtils.contains(name, InitServlet.CTX)) {
	            	startIndex = InitServlet.CTX.length() + 1;
	            }
	            name = StringUtils.substring(name, startIndex,name.lastIndexOf(".ftl"));
	            ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
	            FreeMarkerViewResolver viewResolver = applicationContext.getBean(FreeMarkerViewResolver.class);
	            View view = viewResolver.resolveViewName(name, locale);
	            @SuppressWarnings({ "unchecked", "unused" })
				Map<String, Object> model = (Map<String, Object>) request.getAttribute(ViewRendererServlet.MODEL_ATTRIBUTE);
	            view.render(null, req, res);
	        } catch (Exception e) {
	            throw new ServletException(e);
	        }
	}

	public void destroy() {
		
	}

}
