package com.rpframework.website.luoluo.act.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rpframework.core.BaseAct;

@Controller
@RequestMapping("/qrcode")
public class QrCodeApiAct extends BaseAct{
	
		@RequestMapping("/list")
		public String list(){
			return "/web/upload/upload";
		}
}
