package com.rpframework.website.yunpiaopiao.act.home;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.domian.Cinema;
import com.rpframework.website.yunpiaopiao.domian.Movie;
import com.rpframework.website.yunpiaopiao.service.CinemaService;


@Controller
@RequestMapping("/cinema")
public class CinemaAct {
	@Resource CinemaService cinemaService;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping
	public String execute(@RequestParam(required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Movie>();
		}
		
		cinemaService.doPager(pager);
		model.put("pager", pager);
		return "/home/cinema/index";
	}
	
	@RequestMapping("/{cinemaId}/view")
	public String view(@PathVariable Integer cinemaId, Map<String, Object> model){
		Cinema cinema = cinemaService.select(cinemaId);
		Assert.notNull(cinema, "找不到通知ID ：" + cinemaId);
		model.put("cinema", cinema);
		return "/home/movie/view";
	}
}
