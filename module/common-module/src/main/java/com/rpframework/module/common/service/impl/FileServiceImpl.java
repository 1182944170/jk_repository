package com.rpframework.module.common.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.DictionarySettingUtils;
import com.rpframework.utils.NumberUtils;

@Service("fileService")
public class FileServiceImpl extends FileService {
	protected FileService proxy = null;
	
	public FileService getFileServiceInstance() {
		if(proxy == null) {
			String type = DictionarySettingUtils.getParameterValue(ROOT + ".type");
			if(StringUtils.equals(TYPE_LOCATION, type)) {
				String locationPath = DictionarySettingUtils.getParameterValue(ROOT + "." + TYPE_LOCATION + ".path");
				Assert.notNull(locationPath, "dictionary fileService.location.path cannot be nil.");
				proxy = new LocationFileServiceImpl(locationPath);
			} else if(StringUtils.equals(TYPE_FTP, type)) {
				Map<String, String> ftpMap = DictionarySettingUtils.getParameterMap(ROOT + "." + TYPE_FTP);
				Assert.notEmpty(ftpMap, "无法获得 ftpService.ftp 类型的Map");
				
				String host = ftpMap.get("host");
				int port = NumberUtils.parseInt(ftpMap.get("port"));
				String username = ftpMap.get("username");
				String password = ftpMap.get("password");
				String uploadfolder = ftpMap.get("uploadfolder");
				boolean orverwrite = BooleanUtils.toBoolean(ftpMap.get("overwrite"));
				
				proxy = new FTPFileServiceImpl(host, port, username, password, uploadfolder, orverwrite);
			} else {//default
				proxy = new LocationABSFileServiceImpl();
			}
		}
		
		return proxy;
	}

	@Override
	public boolean saveFile(File file, String relativelyPath) throws Exception {
		Assert.notNull(file, "file cannot be nil.");
		return getFileServiceInstance().saveFile(file, relativelyPath);
	}

	@Override
	public boolean saveFile(InputStream inputStream, String relativelyPath)
			throws Exception {
		return getFileServiceInstance().saveFile(inputStream, relativelyPath);
	}

	@Override
	public InputStream retrieveFileStream(String relativelyPath)
			throws Exception {
		return getFileServiceInstance().retrieveFileStream(relativelyPath);
	}

	@Override
	public boolean deleteFile(String relativelyPath) throws Exception {
		return getFileServiceInstance().deleteFile(relativelyPath);
	}

	@Override
	public String getWebUrl() {
		return getFileServiceInstance().getWebUrl();
	}

	@Override
	public boolean createDirecroty(String floderPath)
			throws IOException {
		return getFileServiceInstance().createDirecroty(floderPath);
	}

	@Override
	public List<FTPFile> getData(String remote, boolean isFloder) {
		return getFileServiceInstance().getData(remote, isFloder);
	}
}