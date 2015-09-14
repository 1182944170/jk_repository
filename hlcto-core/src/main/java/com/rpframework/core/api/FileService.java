package com.rpframework.core.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.net.ftp.FTPFile;

/**
 * 
 * title: FileService.java 
 * <pre>
 * 文件Service
 * 会根据字典表的 fileService.type 选择哪种方式保存文件，其中
 * location -- 本地绝对目录保存的方式，该情况需要配置 fileService.location.path 设置根目录，相见<code>LocationFileServiceImpl</code>
 * location_abs -- (默认) 会保存在本web程序部署的根目录下，相见<code>LocationABSFileServiceImpl</code>
 * ftp -- 保存到ftp, 需要配置字典
 *  fileService.ftp.host	192.168.0.68	map	1	0	0
 *	fileService.ftp.overwrite	true	map	1	0	0
 *	fileService.ftp.password	root	map	1	0	0
 *	fileService.ftp.port	21	map	1	0	0
 *	fileService.ftp.uploadfolder	/	map	1	0	0
 *	fileService.ftp.username	root	map	1	0	0
 *	fileService.location.path	/Users/rplees/Desktop/temp	list	1	0	0
 *	fileService.type	location	list	0	0	0
 * 
 * 相见<code>FTPFileServiceImpl</code>
 * 
 * 使用demo：
 * FileService fileService = SpringUtils.getBean(FileService.class);
 * File file = new File("/Users/rplees/Desktop/maven");
 * try {
 * 		fileService.saveFile(file, "file/saveFile/maven.txt");
 * 	} catch (Exception e) {
 * 		e.printStackTrace();
 * 	}
 * </pre>
 *
 * @author rplees
 * @email rplees.i.ly@gmail.com
 * @version 1.0  
 * @created 2015年4月13日 下午2:41:02
 */
public abstract class FileService {
	
	protected static final String ROOT = "fileService";
	protected static final String TYPE_LOCATION = "location";
	protected static final String TYPE_LOCATION_ABS = "location_abs";
	protected static final String TYPE_FTP = "ftp";
	protected static final String WEB_URL_KEY = ROOT + ".webUrl";
	
	public abstract boolean saveFile(File file, String relativelyPath) throws Exception ;
	
	public abstract boolean saveFile(InputStream inputStream, String relativelyPath) throws Exception;
	
	public abstract InputStream retrieveFileStream(String relativelyPath) throws Exception;
	
	public abstract boolean deleteFile(String relativelyPath) throws Exception;
	
	public abstract String getWebUrl();

	public abstract boolean createDirecroty(String floderPath) throws IOException;

	public abstract List<FTPFile> getData(String remote, boolean isFloder);
}
