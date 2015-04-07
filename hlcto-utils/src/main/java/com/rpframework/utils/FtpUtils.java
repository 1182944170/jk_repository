package com.rpframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Utilities for FTP Operation
 * 
 * @author rplees
 */
public class FtpUtils {

	/**
	 * upload local file to FTP
	 * 
	 * @param server
	 *            FTP server name or IP, such as "192.168.1.103"
	 * @param username
	 *            username for FTP server, such as "abcd"
	 * @param password
	 *            password for FTP server, such as "abcdabcd"
	 * @param local
	 *            local file name (full path, or relative path to this
	 *            application / user.dir), such as "C:/upload/jpeg/test.jpg", "./upload/jpeg/test.jpg"
	 * @param ftpFolder
	 *            remote FTP folder (absolute FTP virtual path), such as "/jpeg"
	 * @param remote
	 *            remote FTP file name, such as "test.jpg"
	 * @throws IOException
	 * @throws SocketException
	 */
	public static void upload(String server, String username, String password,
			String local, String ftpFolder, String remote) throws Exception {
		File file = new File(local);
		if (!file.exists()) {
			throw new Exception("local file not found! " + local);
		}
		upload(server, username, password, new FileInputStream(file), ftpFolder, remote);

	}
	/**
	 * upload local InputStream/FileInputStream to FTP
	 * 
	 * @param server
	 *            FTP server name or IP, such as "192.168.1.103"
	 * @param username
	 *            username for FTP server, such as "abcd"
	 * @param password
	 *            password for FTP server, such as "abcdabcd"
	 * @param input
	 *            local InputStream, or FileInputStream
	 * @param ftpFolder
	 *            remote FTP folder (absolute FTP virtual path), such as "/jpeg"
	 * @param remote
	 *            remote FTP file name, such as "test.jpg"
	 * @throws IOException
	 * @throws SocketException
	 */
	public static void upload(String server, String username, String password,
			InputStream input, String ftpFolder, String remote) throws Exception {
		
		FTPClient ftp = null;
		try {
			ftp = new FTPClient();
			
			ftp.connect(server);
			
			int reply = ftp.getReplyCode();
			
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				throw new Exception("FTP server refused connection.");
			}

			if (!ftp.login(username, password)) {
				ftp.logout();
				throw new Exception("FTP server login failed.");
			}

			ftp.enterLocalPassiveMode();
			ftp.makeDirectory(ftpFolder);
			ftp.changeWorkingDirectory(ftpFolder);

			if(!ftp.storeFile(remote, input)){
				throw new Exception("upload to ftp failed!");
			}
			input.close();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException f) {
				}
			}
		}
	}
}
