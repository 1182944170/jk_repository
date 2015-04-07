package com.rpframework.module.test.act;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rpframework.module.test.dao.ITestDao2;

@Controller
@RequestMapping("/testmodule")
public class TestModuleAct {

	@Resource ITestDao2 iTestDao2;
	@RequestMapping("/ttt")
	public ModelAndView ttt() {
		System.out.println("ModelAndView......");
		
		System.out.println(iTestDao2.getTestVO().getId());
		return new ModelAndView("testmodule/t");
	}
}
