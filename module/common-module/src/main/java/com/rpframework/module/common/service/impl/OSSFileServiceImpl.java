package com.rpframework.module.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.rpframework.core.api.FileService;
import com.rpframework.core.utils.DictionarySettingUtils;

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
public class OSSFileServiceImpl extends FileService {
	
	String accessKeyId = null;
	String accessKeySecret = null;
	String bucketName = null;
	String endpoint = null;
	public OSSFileServiceImpl(String accessKeyId, String accessKeySecret, String bucketName, String endpoint) {
		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
	}

	private OSSClient getOSSClient(){
		 OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		 return client;
	}
	
	@Override
	public boolean saveFile(MultipartFile multipartFile, String relativelyPath) throws Exception {
		OSSClient client = getOSSClient();
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(multipartFile.getSize());
        meta.setContentType(multipartFile.getContentType());
        
		// 上传Object.
        client.putObject(bucketName , relativelyPath, multipartFile.getInputStream(), meta);
		return true;
	}
	
	@Override
	public boolean saveFile(File file, String relativelyPath)
			 throws Exception {
		 // 初始化一个OSSClient
        OSSClient client = getOSSClient();
        
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(file.length());
        meta.setContentType(new MimetypesFileTypeMap().getContentType(file));
        
		// 上传Object.
        client.putObject(bucketName , relativelyPath, new FileInputStream(file), meta);
		return true;
	}

	@Override
	public boolean saveFile(InputStream inputStream, String relativelyPath)  throws Exception {
		 // 初始化一个OSSClient
        OSSClient client = getOSSClient();
        
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        
        // 必须设置ContentLength
        meta.setContentLength(inputStream.available());
        meta.setContentType(new MimetypesFileTypeMap().getContentType(relativelyPath));
//        meta.setContentType("image/jpeg");
        
		// 上传Object.
        client.putObject(bucketName , relativelyPath, inputStream, meta);
        
		return true;
	}

	@Override
	public InputStream retrieveFileStream(String relativelyPath)  throws Exception{
		OSSClient client = getOSSClient();
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, relativelyPath);
		OSSObject object = client.getObject(getObjectRequest);
		
		return object.getObjectContent();
	}

	@Override
	public boolean deleteFile(String relativelyPath)  throws Exception{
		OSSClient client = getOSSClient();
		client.deleteObject(bucketName, relativelyPath);
		return true;
	}
	
	@Override
	public String getWebUrl() {
		String webUrl = DictionarySettingUtils.getParameterValue(WEB_URL_KEY);
		Assert.notNull(webUrl, "webUrl cannot be nil.");
		return webUrl;
	}

	@Override
	public List<FTPFile> getData(String remote, boolean isFloder) {
		throw new IllegalArgumentException("OSS getData 方法还未实现.");
	}

	@Override
	public boolean createDirecroty(String floderPath) throws IOException {
		throw new IllegalArgumentException("OSS createDirecroty 方法还未实现.");
	}
}
