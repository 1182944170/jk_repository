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
import com.rpframework.website.luoluo.dao.IClassificationDao;
import com.rpframework.website.luoluo.domain.Classification;

@Service
public class ClassificationService extends BaseService{
	@Resource IClassificationDao ictionDao;
	@Resource public 	FileService fileService;
/**
 * 
 * 集合
 */
		public Pager<Classification> getPager(Pager<Classification> pager) {
			long startTime = System.currentTimeMillis();
			List<Classification> list = ictionDao.doPager(this.packageMyBatisParam(pager));
			pager.setItemList(list);
			pager.setCostTime(System.currentTimeMillis() - startTime);
			return pager;
		}

		public List<Classification> queryAll() {
			return ictionDao.queryAll();
		}
/**
 * 修改 添加
 * @param classificationDO
 * @return
 */
		public boolean doSaveOrUpdate(Classification classificationDO) {

			if(classificationDO == null || StringUtils.isBlank(classificationDO.getClaName())) {
				throw new IllegalArgumentException("非法参数!");
			}
			if(NumberUtils.isValid(classificationDO.getId())) {//update
				Classification claDB = ictionDao.select(classificationDO.getId());
				Assert.notNull(claDB, "修改Id不能为空" + classificationDO.getId());
				//icon不同，需要删除
				if(!StringUtils.equals(claDB.getClaImg(),classificationDO.getClaImg())) { // 判断news的上传icon的地址是否相同
					try {
						fileService.deleteFile(claDB.getClaImg()); // 删除原来的iocn
					} catch (Exception e) {
						logger.warn("文件删除失败:" + e.getLocalizedMessage());
					}
				}
				if(!StringUtils.equals(claDB.getClaImg(),classificationDO.getClaImg())) { // 判断news的上传icon的地址是否相同
					try {
						fileService.deleteFile(claDB.getClaImg()); // 删除原来的iocn
					} catch (Exception e) {
						logger.warn("文件删除失败:" + e.getLocalizedMessage());
					}
				}
				
				return ictionDao.update(classificationDO);
			} else {//insert
				if(StringUtils.isBlank(classificationDO.getClaImg())) {
					throw new IllegalArgumentException("请上传分类图标!");
				}
				if(StringUtils.isBlank(classificationDO.getClaImg())) {
					throw new IllegalArgumentException("请上传分类主图!");
				}
				return ictionDao.insert(classificationDO);
			}
		}	
/**
 * 查询单个
 * @param id
 * @return
 */
		public Classification  selectcal(Integer  id){
			Classification cationDO=ictionDao.select(id);
			return cationDO;
		}
		/**
		 * 删除信息
		 * @param id
		 * @return
		 */
		public boolean  deletecal(Integer  id){
			boolean c=	ictionDao.delete(id);
			return c;
		}
	}





