package com.rpframework.website.luoluo.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.IActivityDao;
import com.rpframework.website.luoluo.domain.Activity;
import com.rpframework.website.luoluo.domain.Activitypicture;


@Service
public class ActivityService extends BaseService{
	public @Resource IActivityDao iactivitydao;
	@Resource FileService fileService;
	
	public Pager<Activity> getpager(Pager<Activity> pager){
		long startTime = System.currentTimeMillis();
		List<Activity> list = iactivitydao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
/*	public Pager<Activity> selectnamejinwei(Pager<Activity> pager, ){
		long startTime = System.currentTimeMillis();
		List<Activity> list = iactivitydao.selectactivice(this.packageMyBatisParam(pager),lat,lng,city);
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
*/
	public List<Activity> selectactivice(String lat, String lng, String city){
		return iactivitydao.selectactivice(lat, lng, city);
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
		
	public String uploadImg(MultipartFile file, String name) {
		StringBuilder all = new StringBuilder();
		try {
			StringBuilder str = new StringBuilder();
			
			str.append("/luoluo/" + name + "/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) +  NumberUtils.random() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
			this.fileService.saveFile(file.getInputStream(), str.toString());
			all.append(str);
		} catch (Exception e) {
			throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
		}
		return all.toString();
	}
	
	
	public String addPhotos(MultipartFile arr[]) {
		StringBuilder all =new StringBuilder();
		boolean flag = true;
		for(int i=0; i<arr.length; i++){
			try {
				StringBuilder str =new StringBuilder();
				str.append("/luoluo/user/photos/" + DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS) + NumberUtils.random() + "." + FilenameUtils.getExtension(arr[i].getOriginalFilename()));
				fileService.saveFile(arr[i].getInputStream(), str.toString());
				if(flag){
					all.append("\""+str+"\"");
					flag  = !flag;
				}else 
					all.append(","+"\""+str+"\"");
								
			} catch (Exception e) {
				throw new IllegalArgumentException("文件上传失败，原因:" + e.getLocalizedMessage());
			}
		}
		return all.toString();
	}
	public List<Activity> selectacttple(String lat, String lng, String city,
			Integer activitycategoryid) {
		// TODO Auto-generated method stub
		return iactivitydao.selectact(lat, lng, city,activitycategoryid);
	}
	public List<Activity> selectluist(Integer sponsorid) {
		// TODO Auto-generated method stub
		return iactivitydao.selectluist(sponsorid);
	}
	public List<Integer> doActivityIdList(){
		
		return iactivitydao.doActivityIdList();
	}
	public Integer getJoinUserById(Integer id) {
		Integer i = 0;
		i = iactivitydao.doJoinUserById(id);
		return i == null ? 0 : i; 
	}
	public JsonElement getJsonInfo() {
		JsonObject json = new JsonObject();
		json.addProperty("id", "主键id");
		json.addProperty("name", "活动标题");
		json.addProperty("cover", "活动封面图");
		json.addProperty("address", "活动地址");
		json.addProperty("week", "周几");
		json.addProperty("date", "日期");
		json.addProperty("time", "时间");
		json.addProperty("span", "标签 1官方 2多图 3多妹子 4周末 如：13就显示[官方，多妹子]");
		json.addProperty("count", "参加人数");
		json.addProperty("range", "距离");
		
		
		return json;
	}
	/**
	 * 根据time 返回开始时间 结束时间 
	 * @param time
	 * @return
	 * @time 2016年3月4日 上午11:59:12
	 */
	public Long[] getFormatTime(Integer time) {
		if(NumberUtils.isNotValid(time)) return null;
		Long[] arrl = new Long[2];
		Long st = 0l;
		Long et = 0l;
		Date nowDate = DateUtils.getTimesmorning();
		Date endDate = DateUtils.getTimesnight();
		if(time==1){
			st = nowDate.getTime()/1000;
			et = endDate.getTime()/1000;
		}
			if(time==2){
				st = DateUtils.dayAdd(time,nowDate).getTime()/1000;
				et = DateUtils.dayAdd(time,endDate).getTime()/1000;
			}
				if(time==3){
					st = DateUtils.dayAdd(time,nowDate).getTime()/1000;
					et = DateUtils.dayAdd(time,endDate).getTime()/1000;
				}
					if(time>=4){
						st = DateUtils.dayAdd(time+1,nowDate).getTime()/1000;
						et = DateUtils.dayAdd(time+1,endDate).getTime()/1000;
					}
		arrl[0] =st; 
		arrl[1] =et; 
		return arrl;
	}
	public List<Activity> doApiList(String lng, String lat, Integer categoryId,
			Long st, Long et, Long l, Integer baiduCode, Integer page,
			Integer limit) {
		page = Integer.valueOf(page*limit-limit);
		return iactivitydao.doActivityList(lng,lat,categoryId,st,et,l,baiduCode,page,limit);
	}
	/**
	 * 
	 * @param lng
	 * @param lat
	 * @param categoryId
	 * @param st
	 * @param et
	 * @param l
	 * @param days
	 * @param span
	 * @param area
	 * @return
	 * @time 2016年3月4日 下午1:22:04
	 */
	public Integer doApiCount(String lng, String lat, Integer categoryId,
			Long st, Long et, Long l, Integer baiduCode) {
		Integer i = 0;
		i = iactivitydao.doApiListCount(lng,lat,categoryId,st,et,l,baiduCode);
		return i == null ? 0 : i; 
	}
	public Integer doApiCount1() {
		Integer i = 0;
		i = iactivitydao.doApiCount1();
		return i == null ? 0 : i;
	}
	public List<Activity> doApiList1() {
		return iactivitydao.doApiList1();
	}
	public List<Activity> doApiTest() {
		// TODO Auto-generated method stub
		return iactivitydao.doApiTest();
	}
	/**
	 * 当前登录用户发布的
	 * @param userId
	 * @param page
	 * @param limit
	 * @return
	 * @time 2016年3月5日 下午2:13:31
	 */
	public List<Activity> doActivityListByUserId(Integer userId, Integer page,
			Integer limit) {
		page = Integer.valueOf(page*limit-limit);
		return iactivitydao.doActivityListByUserId(userId, page, limit);
	}
	/**
	 * 当前登录用户参加的
	 * @param userId
	 * @param page
	 * @param limit
	 * @return
	 * @time 2016年3月5日 下午2:14:24
	 */
	public List<Activity> doActivityListByUserJoin(Integer userId, Integer page,
			Integer limit) {
		page = Integer.valueOf(page*limit-limit);
		return iactivitydao.doActivityListByUserJoin(userId, page, limit);
	}
	/**
	 * 活动成功举办的
	 * @param page
	 * @param limit
	 * @return
	 * @time 2016年3月5日 下午2:14:34
	 */
	public List<Activity> doActivityListByFinish(Integer page, Integer limit) {
		page = Integer.valueOf(page*limit-limit);
		return iactivitydao.doActivityListByFinish(page, limit);
	}
	public List<Activity> doApiListByCollection(Integer userId, Integer page,
			Integer limit) {
		
		return iactivitydao.doApiListByCollection(userId,page,limit);
	}
	/**
	 * 报名的人数
	 * @param id
	 * @return
	 * @time 2016年3月11日 下午5:43:54
	 */
	public Integer getJoinNumber(Integer id) {
		Integer c = 0;
		c = iactivitydao.doJoinNumber(id);
		return c;
	}
	public Integer getFinishCount() {
		Integer c = 0;
		c = iactivitydao.doFinishCount();
		return c;
	}
	public boolean deleteAll(Integer id) {
		boolean f2 = iactivitydao.delete(id);
		return f2;
		 
	}
	public Integer doIsJoin(Integer activityId, Integer userId) {
		 Integer i = iactivitydao.isJoinByUser(activityId,userId);
		return i > 0 ? 2 : 1;//1可以报名  2不能报名
	}
	public List<Activity> queryAll() {
		// TODO Auto-generated method stub
		return iactivitydao.queryAll();
	}
	public List<Activity> doActivityListSearch(String search, Integer page,
			Integer limit) {
		page = Integer.valueOf(page*limit-limit);
		return iactivitydao.doActivitySearch(search,page,limit);
	}

}
