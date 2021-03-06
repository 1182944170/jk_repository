package com.rpframework.website.luoluo.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IActivitypictureDao;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;
import com.rpframework.website.luoluo.domain.Classification;
import com.rpframework.website.luoluo.domain.Monlyjournallist;
import com.rpframework.website.luoluo.domain.Sponsorlis;
import com.rpframework.website.luoluo.domain.User;

@Service
public class ActivitypictureSercice extends BaseService{
	public @Resource IActivitypictureDao tActivitypictureDao;
	@Resource UserService userService;
	@Resource SponsorService sponsorService;
	@Resource MonlyjournallistService monlyjournalsService;
	@Resource ClassificationService classfica;
	@Resource ActivityService activityService;

	
	public boolean updatedo(Activitypicture activitypi) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.update(activitypi);
	}

	public List<Activitypicture> selectlist(Integer activiid) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.selectole(activiid);
	}
	public Activitypicture selectone(Integer id) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.select(id);
	}

	public boolean insertdo(Activitypicture activitypi) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.insert(activitypi);
	}
	
	public Pager<Activitypicture> getpager(Pager<Activitypicture> pager){
		long startTime = System.currentTimeMillis();
		List<Activitypicture> list = tActivitypictureDao.doPager(this.packageMyBatisParam(pager));
		for(Iterator<Activitypicture> iter = list.iterator(); iter.hasNext();){
			Activitypicture item = iter.next();
			if(null == item.getId())
				break;
			Activity t = activityService.select(item.getSponsorld());
			if(t != null ){
				item.setActivity(t);
			}
		}
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 余额支付
	 * @param userId
	 * @param detailId
	 * @return
	 */
	public boolean bagPay(Integer userId , Integer detailId,Activity activity){
		User userMoney = userService.selectOnlyOne(userId);
		Classification cc=classfica.selectcal(activity.getActivitycategory());
		Sponsorlis zzle=sponsorService.seletOne(activity.getSponsorid());
		User tooMoney = userService.selectmonly(zzle.getUserid());
		if(userMoney == null){
			throw new IllegalArgumentException("不存在的用户.");
		}
		Activitypicture detail = tActivitypictureDao.select(detailId);
		if(userMoney.getPersonalMany() - detail.getMonely() >= 0){
			//改变成支付状态
			detail.setTypeMonely(2);
			tActivitypictureDao.update(detail);
			//减去余额宝金额
			userMoney.setPersonalMany(userMoney.getPersonalMany() - detail.getMonely());
			userService.updatedo(userMoney);
			//添加支付方日志文件
			Monlyjournallist mysope=new Monlyjournallist();
			mysope.setMonly(-detail.getMonely());
			mysope.setNewtime(System.currentTimeMillis()/1000);
			mysope.setType(1);
			mysope.setUserid(userMoney.getId());
			mysope.setRemark(cc.getClaName()+"-  支付");
			monlyjournalsService.insertdo(mysope);
			tooMoney.setPersonalMany(tooMoney.getPersonalMany()+ detail.getActualamount());
			boolean bfgl=userService.updatedo(tooMoney);
			//添加收款方日志文件
			Monlyjournallist weifu=new Monlyjournallist();
			weifu.setMonly(+detail.getActualamount());
			weifu.setNewtime(System.currentTimeMillis()/1000);
			weifu.setType(1);
			weifu.setUserid(tooMoney.getId());
			weifu.setRemark(userMoney.getNameNick()+"- 汇款");
			monlyjournalsService.insertdo(weifu);
			if(bfgl){
				return true;
			}else{
				return false;
			}
		}
		throw new IllegalArgumentException("余额不足.");
	}

	public boolean baggo(User currUser, Activitypicture cc,Activity activity) {
		User tooMoney = userService.selectmonly(activity.getSponsorid());
		Classification cdd=classfica.selectcal(activity.getActivitycategory());
		
		if(tooMoney.getPersonalMany() - cc.getMonely() >= 0){
		//用户要求退款
		currUser.setPersonalMany(currUser.getPersonalMany()+cc.getMonely());
		userService.updatedo(currUser);
		Monlyjournallist mysope=new Monlyjournallist();
		mysope.setMonly(+cc.getMonely());
		mysope.setNewtime(System.currentTimeMillis()/1000);
		mysope.setType(1);
		mysope.setUserid(currUser.getId());
		mysope.setRemark(cdd.getClaName()+"退款成功");
		monlyjournalsService.insertdo(mysope);
		//主办活动方退款
		tooMoney.setPersonalMany(currUser.getPersonalMany()+cc.getActualamount());
		boolean bfgl=userService.updatedo(tooMoney);
		Monlyjournallist weifu=new Monlyjournallist();
		weifu.setMonly(+cc.getActualamount());
		weifu.setNewtime(System.currentTimeMillis()/1000);
		weifu.setType(1);
		weifu.setUserid(tooMoney.getId());
		weifu.setRemark(currUser.getNameNick()+"- 退款");
		monlyjournalsService.insertdo(weifu);
		if(bfgl){
			return true;
		}else{
			return false;
		}
	}
		throw new IllegalArgumentException("请等待。。");
	}

	public Activitypicture selecttrade(String out_trade_no) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.selecttradeorled(out_trade_no);
	}

	public Activitypicture selecttwo(Integer id, Integer activityid) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.seletwoe(id,activityid);
	}

	public Activitypicture seletzzle(Integer id, int sponsorlds) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.seletzzleorrle(id,sponsorlds);
	}

	public List<Activitypicture> getListByActivity(Integer activityId) {
		// TODO Auto-generated method stub
		return tActivitypictureDao.doListByActivity(activityId);
	}
	/**
	 * 根据活动id查询报名列表
	 * @param id
	 * @return
	 */
	public List<Activitypicture> queryByAcitvity(Integer id) {
		return tActivitypictureDao.queryByActivity(id);
	}

}
