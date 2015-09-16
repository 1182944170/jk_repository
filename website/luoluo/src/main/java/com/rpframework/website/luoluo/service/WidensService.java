package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IWidensDao;

import com.rpframework.website.luoluo.domain.Widens;

@Service
public class WidensService extends BaseService{
	@Resource IWidensDao iwidensdao;
	/**
	 * 查询所有标签
	 * @param pager
	 * @return
	 */
	public Pager<Widens> widenpager(Pager<Widens> pager){
		long startTime=System.currentTimeMillis();
		List<Widens>  list=iwidensdao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Widens selectOnlyOne(Integer id){
		Widens user = iwidensdao.select(id);
		return user;
	}
	/**
	 *修改用户
	 * @param user
	 * @return
	 */
	public boolean updatewiden(Widens widens){
		boolean c=iwidensdao.update(widens);
		return c;
	}
	/**
	 *添加用户
	 * @param user
	 * @return
	 */
	public boolean wideninsert(Widens widens){
		boolean c=iwidensdao.insert(widens);
		return c;
	}
	/**
	 *删除用户
	 * @param user
	 * @return
	 */
	public boolean deletesell(Integer id){
		iwidensdao.delete(id);
		return true;
	}
}
