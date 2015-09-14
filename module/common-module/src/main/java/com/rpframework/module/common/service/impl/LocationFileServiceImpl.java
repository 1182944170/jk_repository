package com.rpframework.module.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.utils.CollectionUtils;
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
	public boolean saveFile(MultipartFile multipartFile, String relativelyPath) throws Exception {
		return saveFile(multipartFile.getInputStream(), relativelyPath);
	}
	
	@Override
	public boolean deleteFile(String relativelyPath)  throws Exception{
		relativelyPath = getFullFilePath(relativelyPath);
		
		File file = new File(relativelyPath);
		Assert.isTrue(file.exists(), "file cannot exists.relativelyPath:" + relativelyPath);
		return file.delete();
	}
	public boolean ifNeedCreateDirecroty(String relativelyPath) throws IOException{
		relativelyPath = StringUtils.replace(relativelyPath, "//", "/");
		String directory = StringUtils.substring(relativelyPath, 0, relativelyPath.lastIndexOf("/") + 1);
		if(StringUtils.isBlank(directory)) {
			return true;
		}
		
		return this.createDirecroty(directory);
	}
	
	public String getFullFilePath(String relativelyPath) {
		return locationPath + File.separator + relativelyPath;
	}
	
	@Override
	public String getWebUrl() {
		String webUrl = DictionarySettingUtils.getParameterValue(WEB_URL_KEY);
		Assert.notNull(webUrl, "webUrl cannot be nil.");
		return webUrl;
	}

	@Override
	public List<FTPFile> getData(String remote, boolean isFloder) {
		File file = new File(getFullFilePath(remote));
		File[] listFiles = file.listFiles();
		
		List<FTPFile> list = new ArrayList<FTPFile>();
		if(CollectionUtils.isNotEmpty(listFiles)) {
			for (File f : listFiles) {
				if(isFloder) {
					if(f.isDirectory()) {
						list.add(fileConvert2FtpFile(f));
					}
				} else {
					if(!f.isDirectory()) {
						list.add(fileConvert2FtpFile(f));
					}
				}
			}
		}
		return list;
	}

	private FTPFile fileConvert2FtpFile(File f) {
		FTPFile ftpFile = new FTPFile();
		ftpFile.setName(f.getName());
		ftpFile.setTimestamp(Calendar.getInstance());
		
		if(! f.isDirectory() && f.exists()) {
			FileInputStream in = null;
			try {
				in = new FileInputStream(f);
				ftpFile.setSize(in.available());
			} catch (IOException e) {
				e.printStackTrace();
				ftpFile.setSize(0);
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return ftpFile;
	}

	@Override
	public boolean createDirecroty(String floderPath) throws IOException {
		File floderFile = new File(getFullFilePath(floderPath));
		return floderFile.mkdirs();
	}
}
