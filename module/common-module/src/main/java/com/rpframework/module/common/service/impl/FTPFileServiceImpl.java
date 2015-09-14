package com.rpframework.module.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.module.common.exception.FTPClientException;
import com.rpframework.module.common.utils.FTPClientTemplate;

/**
 * 
 * title: FTPFileServiceImpl.java 
 * 文件系统 FTP的实现
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月13日 下午1:17:11
 */
public class FTPFileServiceImpl extends FileService {
	private static final int FTP_CONNECTION_RETRY_COUNT = 3;
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	FTPClientTemplate mFTPClientTemplate;
	
	public FTPFileServiceImpl(String host, int port, String username, String password, String uploadfolder, boolean overwrite) {
		mFTPClientTemplate = new FTPClientTemplate();
		mFTPClientTemplate.setBinaryTransfer(true);
		mFTPClientTemplate.setClientTimeout(1000);
		mFTPClientTemplate.setEncoding("UTF-8");
		mFTPClientTemplate.setHost(host);
		mFTPClientTemplate.setPassiveMode(true);
		mFTPClientTemplate.setPassword(password);
		mFTPClientTemplate.setPort(port);
		mFTPClientTemplate.setUsername(username);
		
//		this.host = host;
//		this.port = port;
//		this.username = username;
//		this.password = password;
//		this.uploadfolder = uploadfolder;
//		this.overwrite = overwrite;
		
		int retry = 0;
		while (retry++ < FTP_CONNECTION_RETRY_COUNT){
			try {
				mFTPClientTemplate.getFTPClient();
				break;
			} catch (Exception e) {
			}
		}

		if (retry > 2) {
			logger.error("init Ftp Server error!");
		}
	}
	
	@Override
	public boolean saveFile(File file, String relativelyPath)
			 throws Exception {
		return mFTPClientTemplate.put(file, relativelyPath);
	}

	@Override
	public boolean saveFile(InputStream inputStream, String relativelyPath)  throws Exception{
		return mFTPClientTemplate.put(inputStream, relativelyPath, true);
	}
	@Override
	public boolean saveFile(MultipartFile multipartFile, String relativelyPath) throws Exception {
		return mFTPClientTemplate.put(multipartFile.getInputStream(), relativelyPath, true);
	}

	@Override
	public InputStream retrieveFileStream(String relativelyPath)  throws Exception{
		return mFTPClientTemplate.get(relativelyPath);
	}
	
	public boolean ifNeedCreateDirecroty(String relativelyPath) throws IOException{
		relativelyPath = StringUtils.replace(relativelyPath, "//", "/");
		String directory = StringUtils.substring(relativelyPath, 0, relativelyPath.lastIndexOf("/") + 1);
		if(StringUtils.isBlank(directory)) {
			return true;
		}
		
		return this.createDirecroty(directory);  
	}
	
	@Override
	public boolean deleteFile(String relativelyPath)  throws Exception {
		if (StringUtils.isBlank(relativelyPath)) {
			return false;
		}
		
		return mFTPClientTemplate.delete(relativelyPath);
	}

	@Override
	public String getWebUrl() {
		String webUrl = DictionarySettingUtils.getParameterValue(WEB_URL_KEY);
		Assert.notNull(webUrl, "webUrl cannot be nil.");
		return webUrl;
	}

	@Override
	public List<FTPFile> getData(String remote, boolean isFloder) {
		try {
			return mFTPClientTemplate.getData(remote, isFloder);
		} catch (FTPClientException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean createDirecroty(String directory) throws IOException {
		FTPClient ftpClient;
		try {
			ftpClient = mFTPClientTemplate.getFTPClient();
			mFTPClientTemplate.createDirecroty(directory, ftpClient);
		} catch (FTPClientException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
