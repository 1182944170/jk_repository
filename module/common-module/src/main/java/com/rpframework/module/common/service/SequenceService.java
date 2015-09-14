package com.rpframework.module.common.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rpframework.module.common.dao.ISequenceDao;
import com.rpframework.utils.NumberUtils;

@Service
public class SequenceService {
	public final Logger logger = LoggerFactory.getLogger(getClass());
	public @Resource ISequenceDao sequenceDao;
	
	public Integer nextVal(String seqName) {
		
		Integer nextSeq = sequenceDao.nextVal(seqName);
		
		if(NumberUtils.isNotValid(nextSeq)) {
			logger.warn("sequence nextSeq is not valid." + nextSeq);
			throw new IllegalArgumentException("sequence nextSeq is not valid." + nextSeq);
		}
		
		return nextSeq;
	}
	
	public Integer currVal(String seqName) {
		int currSeq = sequenceDao.currVal(seqName);
		
		if(NumberUtils.isNotValid(currSeq)) {
			logger.warn("sequence currVal is not valid." + currSeq);
		}
		
		return currSeq;
	}
}
