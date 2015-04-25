package com.rpframework.website.yunpiaopiao.domian;

import com.rpframework.core.Domain;
import com.rpframework.core.mybatis.plugin.annotation.FieldMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.FieldType;
import com.rpframework.core.mybatis.plugin.annotation.TableMapperAnnotation;
import com.rpframework.core.mybatis.plugin.annotation.UniqueKeyType;

@TableMapperAnnotation(tableName = "cinema_movie", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class CinemaMovie extends Domain {

	/**描述*/  
	
	private static final long serialVersionUID = 1L;

	@FieldMapperAnnotation
	Integer id;
	@FieldMapperAnnotation(dbFieldName="cinemaId", fieldType=FieldType.Object)
	Cinema cinema;
	@FieldMapperAnnotation
	Integer movieId;
	@FieldMapperAnnotation
	Long onlineTime;
	@FieldMapperAnnotation
	Long offTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
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
}
