package com.rpframework.module.adminbase.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rpframework.core.BaseService;
import com.rpframework.module.adminbase.dao.IDictionaryDao;
import com.rpframework.module.adminbase.domain.Dictionary;

/**
 * 
 * title: DictionaryService.java 
 * description
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月9日 下午4:28:35
 */
@Service("dictionaryService")
public class DictionaryService extends BaseService {

	@Resource public IDictionaryDao dictionaryDao;
	
	public List<Dictionary> quertAll(){
		return dictionaryDao.queryAll();
	}
}