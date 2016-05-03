package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;





import org.springframework.web.multipart.MultipartFile;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;
import com.rpframework.website.luoluo.dao.ISponsorDao;
import com.rpframework.website.luoluo.domain.Sponsorlis;

@Service
public class SponsorService extends BaseService{
	public @Resource ISponsorDao isponsorDao;
	@Resource FileService fileService;
	/**
	 * 查询整个表
	 * @param pager
	 * @return
	 */
	public Pager<Sponsorlis> getpager(Pager<Sponsorlis> pager){
		long startTime = System.currentTimeMillis();
		List<Sponsorlis> list = isponsorDao.doPager(this.packageMyBatisParam(pager));
		pager.setItemList(list);
		pager.setCostTime(System.currentTimeMillis() - startTime);
		return pager;
	}
	/**
	 * 查询单个数据
	 * @param id
	 * @return
	 */
	public Sponsorlis seletOne(Integer id){
		Sponsorlis spor=isponsorDao.select(id);
		return spor;
	}
	
	public boolean updatedo(Sponsorlis sponsorlis) {
		return isponsorDao.update(sponsorlis);
	}
	public boolean insertsponsor(Sponsorlis sponsor) {
		// TODO Auto-generated method stub
		return isponsorDao.insert(sponsor);
	}
	public boolean deletesell(Integer deleteid) {
		// TODO Auto-generated method stub
		return isponsorDao.delete(deleteid);
	}
	public Sponsorlis seletOnesponsor(Integer sponsorid) {
		// TODO Auto-generated method stub
		return isponsorDao.selectsponsorid(sponsorid);
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
}
