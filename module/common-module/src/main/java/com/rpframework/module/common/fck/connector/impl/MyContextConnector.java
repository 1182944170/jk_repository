package com.rpframework.module.common.fck.connector.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.fckeditor.connector.Connector;
import net.fckeditor.connector.exception.FolderAlreadyExistsException;
import net.fckeditor.connector.exception.InvalidCurrentFolderException;
import net.fckeditor.connector.exception.InvalidNewFolderNameException;
import net.fckeditor.connector.exception.WriteException;
import net.fckeditor.connector.impl.ContextConnector;
import net.fckeditor.handlers.ResourceType;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.SpringUtils;
import com.rpframework.module.common.utils.FCKUtils;
import com.rpframework.utils.CodeUtils;
import com.rpframework.utils.CollectionUtils;
import com.rpframework.utils.DateUtils;
import com.rpframework.utils.FileUtils;
import com.rpframework.utils.NumberUtils;

/**
 * 2010-03-17
 * 
 * @author rplees
 */
public class MyContextConnector extends ContextConnector {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public List<Map<String, Object>> getFiles(ResourceType type, String currentFolder) throws InvalidCurrentFolderException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		currentFolder = CodeUtils.changeISO2GBK(currentFolder);
		String remote = FileUtils.splicePaths(FCKUtils.getFCKUploadRoot(), type.getPath(), currentFolder);
		
		FileService fileService = SpringUtils.getBean(FileService.class);
		List<FTPFile> data = fileService.getData(remote, false);
		if(CollectionUtils.isNotEmpty(data)) {
			Map<String, Object> m = null;
			for (FTPFile ftpFile : data) {
				if(StringUtils.equals(ftpFile.getName(), ".") || StringUtils.equals(ftpFile.getName(), "..")) {
					continue;
				}
				m = new HashMap<String, Object>();
				m.put(Connector.KEY_NAME, ftpFile.getName());
				m.put(Connector.KEY_SIZE, ftpFile.getSize());
				m.put("timestamp", ftpFile.getTimestamp().getTimeInMillis());
				list.add(m);
			}
		}
		return list;
	}
	
	@Override
	public List<String> getFolders(ResourceType type, String currentFolder)
			throws InvalidCurrentFolderException {
		List<String> l = new ArrayList<String>();
		currentFolder = CodeUtils.changeISO2GBK(currentFolder);
		String remote = FileUtils.splicePaths(FCKUtils.getFCKUploadRoot(),  type.getPath(),currentFolder);
		
//		if(StringUtils.equals(currentFolder, "/")) {
//			remote = FileUtils.splicePaths(FCKUtils.getFCKUploadRoot(), type.getPath());
//		} else {
//			remote = FileUtils.splicePaths(FCKUtils.getFCKUploadRoot(),  type.getPath(),currentFolder);
//		}
		
		
		FileService fileService = SpringUtils.getBean(FileService.class);
		List<FTPFile> data = fileService.getData(remote, true);
		if(CollectionUtils.isNotEmpty(data)) {
			for (FTPFile ftpFile : data) {
				l.add(ftpFile.getName());
			}
		}
		return l;
	}
	
	@Override
	public String fileUpload(ResourceType type, String currentFolder, String fileName, InputStream inputStream)
			throws InvalidCurrentFolderException, WriteException {
		currentFolder = CodeUtils.changeISO2GBK(currentFolder);
		String nowTimeStr = DateUtils.nowDate(DateUtils.YYYYMMDDHHMMSS); // 保存当前时间
		String extension = "." + FilenameUtils.getExtension(fileName);
		fileName = nowTimeStr + ((Integer) NumberUtils.random()) + extension;
		
		String newFilePath = FileUtils.splicePaths(FCKUtils.getFCKUploadRoot(),type.getPath(), currentFolder, fileName);
		logger.info("fileUpload newFilePath:{}", newFilePath);
		
		FileService fileService = SpringUtils.getBean(FileService.class);
		try {
			fileService.saveFile(inputStream, newFilePath);
		} catch (Exception e) {
			logger.error("fileUpload fail.{}", e);
		}
		return fileName;
	}

	@Override
	public void createFolder(ResourceType type, String currentFolder, String newFolder) throws InvalidCurrentFolderException,
			InvalidNewFolderNameException, FolderAlreadyExistsException {
		
		newFolder = CodeUtils.changeISO2GBK(newFolder);
		currentFolder = CodeUtils.changeISO2GBK(currentFolder);		
		
		logger.info("createFolder>>currentFolder:{}, newFolder:{}",currentFolder, newFolder);
		
		String remote = FileUtils.splicePaths(FCKUtils.getFCKUploadRoot(), type.getPath(), currentFolder,newFolder);
		logger.info("createFolder will create floder:{}", remote);
		
		FileService fileService = SpringUtils.getBean(FileService.class);
		try {
			fileService.createDirecroty(remote);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		super.createFolder(type, currentFolder, newFolder);
	}
}
