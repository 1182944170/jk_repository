package com.rpframework.module.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.rpframework.module.common.service.FileService;
import com.rpframework.utils.FileUtils;

/**
 * 
 * title: LocationFileServiceImpl.java 
 * 本地文件
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月13日 下午12:20:37
 */
public class LocationFileServiceImpl extends FileService {
	String locationPath = null;
	public LocationFileServiceImpl(String locationPath){
		this.locationPath = locationPath;
	}

	@Override
	public boolean saveFile(File file, String relativelyPath)
			 throws Exception {
		this.ifNeedCreateDirecroty(relativelyPath);
		FileUtils.saveToFile(getFullFilePath(relativelyPath), new FileInputStream(file));
		return true;
	}

	@Override
	public boolean saveFile(InputStream inputStream, String relativelyPath)  throws Exception{
		this.ifNeedCreateDirecroty(relativelyPath);
		FileUtils.saveToFile(getFullFilePath(relativelyPath), inputStream);
		return true;
	}

	@Override
	public InputStream retrieveFileStream(String relativelyPath)  throws Exception{
		relativelyPath = getFullFilePath(relativelyPath);
		File file = new File(relativelyPath);
		Assert.isTrue(file.exists(), "file cannot exists.relativelyPath:" + relativelyPath);
		return new FileInputStream(file);
	}

	@Override
	public boolean deleteFile(String relativelyPath)  throws Exception{
		relativelyPath = getFullFilePath(relativelyPath);
		
		File file = new File(relativelyPath);
		Assert.isTrue(file.exists(), "file cannot exists.relativelyPath:" + relativelyPath);
		return file.delete();
	}

	public boolean ifNeedCreateDirecroty(String relativelyPath){
		relativelyPath = StringUtils.replace(relativelyPath, "//", "/");
		String directory = StringUtils.substring(relativelyPath, 0, relativelyPath.lastIndexOf("/") + 1);
		if(StringUtils.isBlank(directory)) {
			return true;
		}
		
		File floderFile = new File(getFullFilePath(directory));
		return floderFile.mkdirs();
	}
	
	public String getFullFilePath(String relativelyPath) {
		return locationPath + File.separator + relativelyPath;
	}
}
