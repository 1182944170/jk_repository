package com.rpframework.website.luoluo.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;



import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IUserDao;
import com.rpframework.website.luoluo.domain.User;
import com.sun.xml.internal.txw2.IllegalAnnotationException;

@Service
public class UserService extends BaseService{
	
	@Resource IUserDao iuserdao;
	@Resource UserService userService;
	
	public Pager<User> Userpager(Pager<User> pager){
		long startTime = System.currentTimeMillis();
		List<User> list = iuserdao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public User selectOnlyOne(Integer id){
		User user = iuserdao.select(id);
		return user;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public boolean deletesell(Integer id){
		boolean c=iuserdao.delete(id);
		return c;
	}
	
	/**
	 * 查询用户
	 * @param id
	 * @return
	 */
	public User selectmonly(Integer id){
		User c=iuserdao.selectmonly(id);
		return c;
	}
	
	/**
	 *修改用户
	 * @param user
	 * @return
	 */
	public boolean updatedo(User user){
		boolean c=iuserdao.update(user);
		return c;
	}
	/**
	 *添加用户
	 * @param user
	 * @return
	 */
	public boolean Newinsert(User user){
		boolean c=iuserdao.insert(user);
		return c;
	}
	/**
	 * 查询电话
	 * @param pager
	 * @param nameone
	 * @return
	 */
	public Pager<User> seleNameOrPhone(Pager<User> pager,String nameone) {
		long startTime = System.currentTimeMillis();
		List<User> itemList = iuserdao.findUserBy(nameone);
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
		
	}
		/**
		 * 查询名字
		 * @param pager
		 * @param nameone
		 * @return
		 */
	public Pager<User> selephones(Pager<User> pager,String nameone) {
		long startTime = System.currentTimeMillis();
		List<User> itemList=iuserdao.findUserByName(nameone);
		pager.setItemList(itemList);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 根据手机号查询用户
	 * @date 2015年7月3日 下午2:41:28
	 */
	public User findUserByPhone(String phone) {
		if(null != phone){ // 判断手机号是否为空
			return iuserdao.findUserByPhone(phone);
		}
		return null;
	}
	/**
	 * 用户注册
	 * @time 2015年7月17日 下午5:04:22
	 */
	public boolean registUser(String phone, String passWord) {
	if(StringUtils.isBlank(phone) || StringUtils.isBlank(passWord)){
		throw new IllegalAnnotationException("手机号或者密码不能为空.");
	}
	User user=iuserdao.findUserByPhone(phone);
	/*Assert.isNull(user, "存在的手机号用户:" + phone);
	
	User rUser = userDao.findUserByPhone(phone); */
	
	if(user!=null){
		Assert.isNull(user, "存在的手机号用户:" + phone);
		throw new IllegalAnnotationException("该用户已存在");
	}
	user = new User();
	user.setPhone(phone);
	user.setPassword(passWord);
	user.setType(0); // 默认为0 可以使用
	user.setCtiontime(System.currentTimeMillis() / 1000);
		return iuserdao.insert(user);
	}
	
	/**
	 * 用户登录
	 * @time 2015年7月14日 下午7:32:37
	 */
	public boolean userLogin(User user){
		if(StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getPassword())) {
			throw new IllegalArgumentException("非法参数!");
		}
		
		User lUser = userService.findUserByPhone(user.getPhone());
		if(lUser != null){
			
			if(lUser.getPhone().equals(user.getPhone()) && lUser.getPassword().equals(user.getPassword())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 修改密码
	 * @time 2015年7月15日 下午7:10:36
	 */
	public boolean changePwd(String phone , String nPassWord){
		User user = iuserdao.findUserByPhone(phone); 
		user.setPassword(nPassWord); //　设置新密码
		return iuserdao.update(user);
	}
	public User seleAccout(String uid){
		User AccoutUser=iuserdao.AccoutSelect(uid);
		if(AccoutUser!=null){
			return AccoutUser;
		}else{
			return null;
		}
			
	}
	public boolean changeUserPic(User user , String relativelyPath){
		if(user != null && user.getType() != 1){
			user.setNamePic(relativelyPath);
			return iuserdao.update(user);
		}
		return false;
	}
	public List<User> selectactivice(String lat, String lng) {
		// TODO Auto-generated method stub
		return iuserdao.selectact(lat,lng);
	}	
}
