package com.rpframework.website.luoluo.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.BaseService;
import com.rpframework.core.api.FileService;
import com.rpframework.utils.NumberUtils;
import com.rpframework.utils.Pager;

import com.rpframework.website.luoluo.dao.IBanksDao;
import com.rpframework.website.luoluo.domain.Banks;

@Service
public class BanksSercice extends BaseService{
	@Resource IBanksDao ibanksDao;
	@Resource public 	FileService fileService;
	
	
	public Banks selectone(Integer cardid) {
		// TODO Auto-generated method stub
		return ibanksDao.select(cardid);
	}


		public Pager<Banks> getPager(Pager<Banks> pager) {
			long startTime = System.currentTimeMillis();
			List<Banks> list = ibanksDao.doPager(this.packageMyBatisParam(pager));
			pager.setItemList(list);
			pager.setCostTime(System.currentTimeMillis() - startTime);
			return pager;
		}
	

		public List<Banks> queryAlllesei() {
			return ibanksDao.queryAlllesei();
		}
		public List<Banks> queryAll() {
			return ibanksDao.queryAll();
		}
/**
 * 修改 添加
 * @param classificationDO
 * @return
 */
		public boolean doSaveOrUpdate(Banks banks) {

			if(banks == null || StringUtils.isBlank(banks.getBankprivice())) {
				throw new IllegalArgumentException("非法参数!");
			}
			if(NumberUtils.isValid(banks.getId())) {//update
				Banks claDB = ibanksDao.select(banks.getId());
				Assert.notNull(claDB, "修改Id不能为空" + banks.getId());
				//icon不同，需要删除
				if(!StringUtils.equals(claDB.getBankprivice(),banks.getBankprivice())) { // 判断news的上传icon的地址是否相同
					try {
						fileService.deleteFile(claDB.getBankprivice()); // 删除原来的iocn
					} catch (Exception e) {
						logger.warn("文件删除失败:" + e.getLocalizedMessage());
					}
				}
				if(!StringUtils.equals(claDB.getBankprivice(),banks.getBankprivice())) { // 判断news的上传icon的地址是否相同
					try {
						fileService.deleteFile(claDB.getBankprivice()); // 删除原来的iocn
					} catch (Exception e) {
						logger.warn("文件删除失败:" + e.getLocalizedMessage());
					}
				}
				
				return ibanksDao.update(banks);
			} else {//insert
				if(StringUtils.isBlank(banks.getBankprivice())) {
					throw new IllegalArgumentException("请上传分类图标!");
				}
				if(StringUtils.isBlank(banks.getBankprivice())) {
					throw new IllegalArgumentException("请上传分类主图!");
				}
				return ibanksDao.insert(banks);
			}
		}	
/**
 * 查询单个
 * @param id
 * @return
 */
		public Banks selectcal(Integer  id){
			Banks cationDO=ibanksDao.select(id);
			return cationDO;
		}
		/**
		 * 删除信息
		 * @param id
		 * @return
		 */
		public boolean  deletecal(Integer  id){
			boolean c=	ibanksDao.delete(id);
			return c;
		}
	}



