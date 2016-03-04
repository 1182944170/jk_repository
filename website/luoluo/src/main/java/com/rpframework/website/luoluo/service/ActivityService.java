package com.rpframework.website.luoluo.service;

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


@Service
public class ActivityService extends BaseService{
	@Resource IActivityDao iactivitydao;
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
	public List<Activity> doApiList(Integer categoryId, Long st, Long et,
			Integer days, Integer span, Integer area, Integer page,
			Integer limit) {
		
		return iactivitydao.doActivityList();
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
	public Integer doApiList(Integer categoryId, Long st, Long et, Integer days,
			Integer span, Integer area) {
		Integer i = 0;
		i = iactivitydao.doApiListCount();
		return i == null ? 0 : i; 
	}
	

}
