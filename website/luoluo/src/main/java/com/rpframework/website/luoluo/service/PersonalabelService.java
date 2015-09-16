package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IPersonalabelDao;
import com.rpframework.website.luoluo.domain.Personalabel;


@Service
public class PersonalabelService extends BaseService{

	@Resource IPersonalabelDao iperslabeldao;
	/**
	 * 查询所有标签
	 * @param pager
	 * @return
	 */
	public Pager<Personalabel> labelpager(Pager<Personalabel> pager){
		long startTime=System.currentTimeMillis();
		List<Personalabel>  list=iperslabeldao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 通过Id查询标签
	 * @param pager
	 * @return
	 */

	public Personalabel selectOnlyOne(Integer id){
		Personalabel Plabel = iperslabeldao.select(id);
		return Plabel;
	}
	/**
	 * 修改用户信息
	 * @param Plabel
	 * @return
	 */
	public boolean updatedo(Personalabel Plabel){
		boolean c=iperslabeldao.update(Plabel);
		return c;
	}
	/**
	 * 添加用户
	 * @param Plabel
	 * @return
	 */
	public boolean Newinsert(Personalabel Plabel){
		boolean c=iperslabeldao.insert(Plabel);
		return c;
	}
	/**
	 * 通过标签名查找是否重复
	 * @param pager
	 * @param nameone
	 * @return
	 */
	public Pager<Personalabel> seleNameOrPhone(Pager<Personalabel> pager,String nameone) {
		long startTime = System.currentTimeMillis();
		List<Personalabel> itemList = iperslabeldao.findUserBy(nameone);
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
		
	}
}
