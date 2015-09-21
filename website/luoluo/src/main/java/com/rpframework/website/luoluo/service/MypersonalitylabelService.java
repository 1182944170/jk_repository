package com.rpframework.website.luoluo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.website.luoluo.dao.IMypersonalitylabelDao;
import com.rpframework.website.luoluo.domain.Mypersonalitylabel;
@Service 
public class MypersonalitylabelService extends BaseService{
	
	@Resource IMypersonalitylabelDao imydao;
	/**
	 * 
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Mypersonalitylabel selectOnlyOne(Integer id){
		Mypersonalitylabel user = imydao.select(id);
		if(user==null){
			user=new Mypersonalitylabel();
			
		}
		return user;
	}
	/**
	 * 修改用户信息
	 * @param Plabel
	 * @return
	 */
	public boolean updatedo(Mypersonalitylabel Plabel){
		boolean c=imydao.update(Plabel);
		return c;
	}
	public boolean insertdo(Mypersonalitylabel myperson) {
		// TODO Auto-generated method stub
		boolean c=imydao.insert(myperson);
		return c;
	}
}
