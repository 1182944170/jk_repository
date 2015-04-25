package com.rpframework.website.yunpiaopiao.domian;

import java.util.List;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "movie", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Movie extends Domain {
	/**描述*/  
	private static final long serialVersionUID = 1L;
	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation
	String name;
	@FieldMapperAnnotation
	Integer type;//类型
	@FieldMapperAnnotation
	Integer countryArea;//国家地区 1-中国大陆 2- 3-
	@FieldMapperAnnotation
	Double mark;
	@FieldMapperAnnotation
	Long onlineTime;
	@FieldMapperAnnotation
	Long offTime;
	@FieldMapperAnnotation
	String icon;
	@FieldMapperAnnotation
	Integer lang;
	@FieldMapperAnnotation
	Integer time;
	@FieldMapperAnnotation
	String content;
	List<MovieActor> movieActors; //主演饰演的角色表
	List<CinemaMovie> cinemaMovies;//
	
	public List<CinemaMovie> getCinemaMovies() {
		return cinemaMovies;
	}
	public void setCinemaMovies(List<CinemaMovie> cinemaMovies) {
		this.cinemaMovies = cinemaMovies;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCountryArea() {
		return countryArea;
	}
	public void setCountryArea(Integer countryArea) {
		this.countryArea = countryArea;
	}
	public Double getMark() {
		return mark;
	}
	public void setMark(Double mark) {
		this.mark = mark;
	}
	public Long getOnlineTime() {
		return onlineTime;
	}
	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}
	public Long getOffTime() {
		return offTime;
	}
	public void setOffTime(Long offTime) {
		this.offTime = offTime;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getLang() {
		return lang;
	}
	public void setLang(Integer lang) {
		this.lang = lang;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<MovieActor> getMovieActors() {
		return movieActors;
	}
	public void setMovieActors(List<MovieActor> movieActors) {
		this.movieActors = movieActors;
	}
}