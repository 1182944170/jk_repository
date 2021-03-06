package com.rpframework.module.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.core.utils.MessageFormatter;
import com.rpframework.core.utils.cache.KVObj;
import com.rpframework.module.common.service.SMSService;
import com.rpframework.module.user.dao.IUserTakeCashDao;
import com.rpframework.module.user.domain.UserTakeCash;
import com.rpframework.utils.Pager;

@Service
public class UserTakeCashService extends BaseService {
	public @Resource IUserTakeCashDao takeCashDao;
	@Resource UserMoneyService userMoneyService;
	@Resource SMSService smsService;
	
	public Pager<UserTakeCash> getPager(Pager<UserTakeCash> pager) {
		long startTime = System.currentTimeMillis();
		List<UserTakeCash> itemList = takeCashDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	
	public boolean doDealTakeCash(UserTakeCash userTakeCash, KVObj kvObj) {
		UserTakeCash userTakeCashDB = select(userTakeCash.getId());
		Assert.notNull(userTakeCashDB);
		Integer userId = userTakeCashDB.getUserId();
		
		if(userTakeCashDB.getState() == 0) { //未处理状态
			userTakeCashDB.setState(userTakeCash.getState());
			userTakeCashDB.setVerifyUId(userTakeCash.getVerifyUId());
			userTakeCashDB.setVerifyRemark(userTakeCash.getVerifyRemark());
			userTakeCashDB.setRecordModifyTime(System.currentTimeMillis() / 1000);
			
			//释放冻结金额
			userMoneyService.unFreezeMoney(userId, userTakeCashDB.getMoney());
			String sendContent = "";
			if(userTakeCash.getState() == 1) { //如果是成功处理的话 则扣除冻结的金额
				userMoneyService.costMoney(userId, userTakeCashDB.getMoney(), kvObj, null);
				
				sendContent = DictionarySettingUtils.getParameterValue("sendsms.takeCash.success");
				if(StringUtils.isBlank(sendContent)) {
					sendContent =  "取现成功，金额:{}.";
				}
				
				sendContent = MessageFormatter.format(sendContent, userTakeCash.getMoney());
			} else {
				sendContent = DictionarySettingUtils.getParameterValue("sendsms.takeCash.fail");
				if(StringUtils.isBlank(sendContent)) {
					sendContent =  "取现失败.";
				}
			}
			
			smsService.sendSMS(1000, userTakeCashDB.getUser().getContact(), "", sendContent);
			
			return update(userTakeCashDB);
		} else {//
			throw new IllegalArgumentException("已经处理过的提现不能再操作！");
		}
	}
	
	public boolean applyTakeCash(Integer userId, Double money, Integer userBankCardId, String remark) {
		if(userMoneyService.checkCanFreezeMoney(userId, money)) {
			userMoneyService.freezeMoney(userId, money);
			UserTakeCash userTakeCash = new UserTakeCash();
			userTakeCash.setMoney(money);
			userTakeCash.setUserBankCardId(userBankCardId);
			userTakeCash.setRecordCreateTime(System.currentTimeMillis() / 1000);
			userTakeCash.setRecordModifyTime(System.currentTimeMillis() / 1000);
			userTakeCash.setRemark(remark);
			userTakeCash.setState(0);
			userTakeCash.setUserId(userId);
			
			return insert(userTakeCash);
		} else {
			return false;
		}
	}
}
