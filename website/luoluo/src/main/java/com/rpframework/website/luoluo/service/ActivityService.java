package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IActivityDao;
import com.rpframework.website.luoluo.domain.Activity;


@Service
public class ActivityService extends BaseService{
	@Resource IActivityDao iactivitydao;
	
	
	
	public Pager<Activity> getpager(Pager<Activity> pager){
		long startTime = System.currentTimeMillis();
		List<Activity> list = iactivitydao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 查询单个
	 * @param id
	 * @return
	 */
	public boolean deletesell(Integer id){
		return iactivitydao.delete(id);
	}
	public Activity selectcal(Integer activiid){
		
		Activity cationDO=iactivitydao.select(activiid);
		return cationDO;
	}
	public boolean updatedo(Activity activity){
		return iactivitydao.update(activity);
	}
	public boolean insertone(Activity activity) {
		// TODO Auto-generated method stub
		 iactivitydao.insert(activity);
		 activity.setActivitynumber("100000"+activity.getId());
		 return iactivitydao.update(activity);
	}
	/**
	 * 查询名字 或者 编号
	 * @param search
	 */
	public List<Activity> selectname(String search) {
		List<Activity> fore=iactivitydao.selectname(search);
		
		return fore;
	}
	public List<Activity> selectnumbers(String search) {
			List<Activity> loge=iactivitydao.selectnumbers(search);
			return loge;
	}
		
		
	

}
