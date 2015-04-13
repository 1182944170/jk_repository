package com.rpframework.module.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rpframework.module.common.service.FileService;
import com.rpframework.utils.CodeUtils;

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
	private FTPClient ftpClient;
	private String host;
	private int port;
	private String username;
	private String password;
	private String uploadfolder;	//remote FTP folder (absolute FTP virtual path), such as "/jpeg"
	private boolean overwrite;
	
	public FTPFileServiceImpl(String host, int port, String username, String password, String uploadfolder, boolean overwrite) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.uploadfolder = uploadfolder;
		this.overwrite = overwrite;
		
		int retry = 0;
		while (retry++ < FTP_CONNECTION_RETRY_COUNT){
			try {
				initFtpClient();
				break;
			} catch (Exception e) {
			}
		}

		if (retry > 2) {
			ftpClient = null;
			logger.error("init Ftp Server error!");
		}
	}
	
	private void initFtpClient() throws SocketException, IOException {
		ftpClient = new FTPClient();
		if (port > 0 && port < 65535 && port != 21) {	//default FTP port is 21
			ftpClient.setDefaultPort(port);
		}

		ftpClient.connect(host);

		int reply = ftpClient.getReplyCode();

		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			throw new SocketException("FTP server refused connection.");
		}

		if (!ftpClient.login(username, password)) {
			ftpClient.logout();
			throw new SocketException("FTP server login failed.");
		}

		ftpClient.enterLocalPassiveMode();
		ftpClient.makeDirectory(uploadfolder);
		ftpClient.changeWorkingDirectory(uploadfolder);
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);  // 使用二进制流上传文件
	}
	
	@Override
	public boolean saveFile(File file, String relativelyPath)
			 throws Exception {
		transferFile(new FileInputStream(file), relativelyPath);
		return true;
	}

	@Override
	public boolean saveFile(InputStream inputStream, String relativelyPath)  throws Exception{
		transferFile(inputStream, relativelyPath);
		return true;
	}

	/**
	 * transfer File to FTP server
	 * 
	 * @param input
	 *            local InputStream, or FileInputStream
	 * @param remote
	 *            remote FTP file name, such as "test.jpg"
	 * @throws IOException 
	 * @throws IOException
	 * @throws Exception
	 */
	public void transferFile(InputStream input, String remote) throws IOException {
		remote = StringUtils.replace(remote, "//", "/");
		ifNeedCreateDirecroty(remote);
		ftpClient.enterLocalPassiveMode();
		try {
			if (!ftpClient.isConnected()) {		//if the FTP session is timeout, login and connect again.
				initFtpClient();
			}
			
			int index = remote.lastIndexOf('/');
			if (index < 0) {
				index = remote.lastIndexOf('\\');
			}
			if (index > 0) {
				try {
					ftpClient.makeDirectory(remote.substring(0, index));
				} catch (Exception e) {
					logger.error("Ftp Server make Directory error", e);
				}
			}
			
			boolean result = false;
			if (overwrite) {
				result = ftpClient.storeFile(remote, input);
			} else {
				result = ftpClient.storeUniqueFile(remote, input);
			}
			if(!result){
				throw new IOException("upload to ftp failed! maybe remote file exists!");
			}
		} finally {
			try {
				input.close();
				ftpClient.disconnect();
			} catch (Exception e) {
				logger.error("transfer File finally", e);
			}
		}
	}
	@Override
	public InputStream retrieveFileStream(String relativelyPath)  throws Exception{
		if (!ftpClient.isConnected()) {		//if the FTP session is timeout, login and connect again.
			initFtpClient();
		}
    	ftpClient.enterLocalPassiveMode();
    	return ftpClient.retrieveFileStream(relativelyPath);
	}

	public boolean ifNeedCreateDirecroty(String relativelyPath) throws IOException{
		relativelyPath = StringUtils.replace(relativelyPath, "//", "/");
		String directory = StringUtils.substring(relativelyPath, 0, relativelyPath.lastIndexOf("/") + 1);
		if(StringUtils.isBlank(directory)) {
			return true;
		}
		
		if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(CodeUtils.changeISO2GBK(directory))) {  
            //如果远程目录不存在，则递归创建远程服务器目录   
            int start=0;   
            int end = 0;   
            if(directory.startsWith("/")){   
                start = 1;   
            }else{   
                start = 0;   
            }   
            end = directory.indexOf("/",start);   
            while(true){   
                String subDirectory = CodeUtils.changeISO2GBK(relativelyPath.substring(start,end));   
                if(!ftpClient.changeWorkingDirectory(subDirectory)){   
                    if(ftpClient.makeDirectory(subDirectory)){   
                        ftpClient.changeWorkingDirectory(subDirectory);   
                    }else {   
                        logger.info("创建目录失败, subDirectory:{}, relativelyPath:{}", subDirectory,relativelyPath);   
                        return false;   
                    }   
                }   
                   
                start = end + 1;   
                end = directory.indexOf("/",start);   
                   
                //检查所有目录是否创建完毕   
                if(end <= start){   
                    break;   
                }   
            }   
        }   
        return true;   
	}
	
	@Override
	public boolean deleteFile(String relativelyPath)  throws Exception{
		if (StringUtils.isBlank(relativelyPath)) {
			return false;
		}
		if (!ftpClient.isConnected()) {		//if the FTP session is timeout, login and connect again.
			initFtpClient();
		}
    	ftpClient.deleteFile(relativelyPath);
	    int status = ftpClient.getReplyCode();
	    return status == 250 ;
	}
}
