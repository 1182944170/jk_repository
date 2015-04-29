package com.rpframework.website.yunpiaopiao.act.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rpframework.core.api.FileService;
import com.rpframework.core.exception.AdminIllegalArgumentException;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.yunpiaopiao.domian.Actor;
import com.rpframework.website.yunpiaopiao.domian.Cinema;
import com.rpframework.website.yunpiaopiao.domian.CinemaMovie;
import com.rpframework.website.yunpiaopiao.domian.Movie;
import com.rpframework.website.yunpiaopiao.domian.MovieActor;
import com.rpframework.website.yunpiaopiao.service.ActorService;
import com.rpframework.website.yunpiaopiao.service.CinemaService;
import com.rpframework.website.yunpiaopiao.service.MovieService;

@Controller
@RequestMapping("/admin/movie")
public class AdminMovieAct extends AdminAct {
	@Resource MovieService movieService;
	@Resource CinemaService cinemaService;
	@Resource ActorService actorService;
	
	@Resource FileService fileService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(@RequestParam(value="pager", required=false) Pager pager, Map<Object, Object> model, RedirectAttributes attr){
		if(pager == null) {
			pager = new Pager();
		}
		pager = movieService.getPager(pager);
		model.put("pager", pager);
		return this.doPackageURI("movie/list");
	}
	
	@RequestMapping("/{movieId}/edit")
	public String edit(@PathVariable Integer movieId, Map<Object, Object> model,RedirectAttributes attr){
		Movie movie = movieService.movieDao.select(movieId);
		if(movie == null) {
			throw new AdminIllegalArgumentException("不存在的ID:" + movieId);
		}
		List<Actor> actors = new ArrayList<Actor>();
		List<Cinema> cinemas = new ArrayList<Cinema>();
		if(CollectionUtils.isNotEmpty(movie.getCinemaMovies())) {
			for (CinemaMovie cinemaMovie : movie.getCinemaMovies()) {
				cinemas.add(cinemaMovie.getCinema());
			}
		}
		
		if(CollectionUtils.isNotEmpty(movie.getMovieActors())) {
			for (MovieActor movieActor : movie.getMovieActors()) {
				actors.add(movieActor.getActor());
			}
		}
		
		model.put("actors", actors);
		model.put("cinemas", cinemas);
		model.put("movie", movie);
		return this.add(attr, model);
	}
	
	@RequestMapping("/{movieId}/delete")
	public String delete(@PathVariable Integer movieId, Map<Object, Object> model,RedirectAttributes attr){
		movieService.delete(movieId);
		setInfoMsg("删除操作成功！", attr);
		return redirect("/admin/movie/list");
	}
	
	@RequestMapping("/add")
	public String add(RedirectAttributes attr, Map<Object, Object> model){
		List<Cinema> cinemaList = cinemaService.queryAll();
		List<Actor> actorList = actorService.queryAll();
		
		model.put("cinemaList", cinemaList);
		model.put("actorList", actorList);
		
		return this.doPackageURI("movie/edit");
	}
	
	@RequestMapping("/dosave")
	public String doSaveOrUpdate(@RequestParam(value="iconFile", required=false) CommonsMultipartFile iconFile,
			@RequestParam(value="actors", required=false) Integer[] actors,
			@RequestParam(value="cinemas", required=false) Integer[] cinemas,
			@RequestParam(value="onlineString") String onlineString,
			@RequestParam(value="offString") String offString,
			@ModelAttribute Movie movie, 
			HttpSession session, HttpServletRequest request,RedirectAttributes attr){
		
		if(iconFile.getSize() > 0) {
			try {
				String relativelyPath = "resources/movie/" + NumberUtils.random(3) + iconFile.getOriginalFilename();
				fileService.saveFile(iconFile.getInputStream(), relativelyPath);
				movie.setIcon(relativelyPath);;
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		} else {
			if(movie == null || NumberUtils.isNotValid(movie.getId())) {
				throw new IllegalArgumentException("新增情况下，必须上传Icon!");
			}
		}
		movie.setOffTime(DateUtils.parse(offString).getTime()/1000);
		movie.setOnlineTime(DateUtils.parse(onlineString).getTime()/1000);
		
		List<CinemaMovie> cinemaMovies = new ArrayList<CinemaMovie>();
		List<MovieActor> movieActors = new ArrayList<MovieActor>();
		if(actors != null) {
			for (Integer cinemaId : cinemas) {
				if(NumberUtils.isValid(cinemaId)) {
					CinemaMovie cinemaMovie = new CinemaMovie();
					Cinema cinema = new Cinema();
					cinema.setId(cinemaId);
					cinemaMovie.setCinema(cinema );
					cinemaMovie.setMovieId(movie.getId());
					cinemaMovie.setOffTime(movie.getOffTime());
					cinemaMovie.setOnlineTime(movie.getOnlineTime());
					
					cinemaMovies.add(cinemaMovie);
				}
			}
		}
		
		if(actors != null) {
			for (Integer actorId : actors) {
				if(NumberUtils.isValid(actorId)) {
					MovieActor movieActor = new MovieActor();
					Actor actor = new Actor();
					actor.setId(actorId);
					movieActor.setActor(actor );
					movieActor.setMovieId(movie.getId());
					
					movieActors.add(movieActor);
				}
			}
		}
		
		movie.setCinemaMovies(cinemaMovies);
		movie.setMovieActors(movieActors);
		
		movieService.doSaveOrUpdate(movie);
		if(NumberUtils.isValid(movie.getId())) {//update
			setInfoMsg("更新操作成功！", attr);
		} else {//insert
			setInfoMsg("新增操作成功！", attr);
		}
		
		return redirect("list");
	}
}
