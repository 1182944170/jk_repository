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
import com.rpframework.website.yunpiaopiao.domian.Movie;
import com.rpframework.website.yunpiaopiao.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieAction {
	@Resource MovieService movieService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping
	public String execute(@RequestParam(required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager<Movie>();
		}
		
		if(!pager.getSearchMap().containsKey("movieState")) {
			pager.getSearchMap().put("movieState", "1");
		}
		
		movieService.getPager(pager);
		model.put("pager", pager);
		return "/home/movie/index";
	}
	
	@RequestMapping("/{movieId}")
	public String view(@PathVariable Integer movieId, Map<String, Object> model){
		Movie movie = movieService.select(movieId);
		Assert.notNull(movie, "找不到通知ID ：" + movieId);
		model.put("movie", movie);
		return "/home/movie/view";
	}
}
