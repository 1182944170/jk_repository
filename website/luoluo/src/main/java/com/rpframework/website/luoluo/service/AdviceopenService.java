package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IAdviceopenDao;
import com.rpframework.website.luoluo.domain.Adviceopen;
import com.rpframework.website.luoluo.domain.User;

@Service
public class AdviceopenService  extends BaseService{
		@Resource IAdviceopenDao iadviceopenDao;
	//查询所有
	public  Pager<Adviceopen> getPager(Pager<Adviceopen> pager) {
		long startTime = System.currentTimeMillis();
		List<Adviceopen> list = iadviceopenDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Adviceopen selectOnlyOne(Integer id){
		Adviceopen user = iadviceopenDao.select(id);
		return user;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean deletesell(Integer id){
		boolean c=iadviceopenDao.delete(id);
		return c;
	}
	
	/**
	 *修改用户
	 * @param user
	 * @return
	 */
	public boolean updatedo(Adviceopen adviceopen){
		boolean c=iadviceopenDao.update(adviceopen);
		return c;
	}
	
}
