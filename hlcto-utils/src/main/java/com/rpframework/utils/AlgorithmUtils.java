package com.rpframework.utils;

import java.security.MessageDigest;
import java.security.Security;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.sun.crypto.provider.SunJCE;

/**
 * @author <a href="mailto:rplees.i.ly@gmail.com">rplees</a>
 * date 2010-04-07
 * {@code} 一些常用的算法，加密
 */
public class AlgorithmUtils {
	private final static Log log = LogFactory.getLog(AlgorithmUtils.class);
	
	private static final String algorithm = "DESede"; // 定义 加密算法,可用 DES,DESede,Blowfish

	private static DESedeKeySpec spec;
	private static SecretKeyFactory keyFactory;
	private static SecretKey theKey;
	private static Cipher cipher;
	private static IvParameterSpec ivParameters;
	static {
		try {
			try {
				Cipher.getInstance(algorithm);
			} catch (Exception e) {
				log.info("Installling SunJCE provider.");
				Security.addProvider(new SunJCE());
			}
			String strDefaultKey = "WERTYUOPWERTYUOPWERTYUOP";
			/*try {
				strDefaultKey = PropertiesLoader.getProperty("info.jd.secretkey");
			} catch (Exception e) {
				log.warn("get info.jd.secretkey from properties error.");
			}
			if (strDefaultKey == null || strDefaultKey.length() < 25) {
				strDefaultKey = "WERTYUOPWERTYUOPWERTYUOP";
			}
			log.info("default encrypt key: " + strDefaultKey);*/
			spec = new DESedeKeySpec(strDefaultKey.getBytes());
			keyFactory = SecretKeyFactory.getInstance(algorithm);
			theKey = keyFactory.generateSecret(spec);
			cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			ivParameters = new IvParameterSpec(new byte[] { 12, 34, 56, 78, 90,	87, 65, 43 });
		} catch (Exception e) {
			log.error("Installling provider error.", e);
		}
	}

	/**
	 * 
	 * 加密算法（双向）
	 * 
	 * @param password
	 *            未加密的密码
	 * 
	 * @return 加密后的密码
	 */
	public static String encrypt(String password) {
		byte[] encryptedPassword = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, theKey, ivParameters);
			encryptedPassword = cipher.doFinal(password.getBytes());
		} catch (Exception e) {
			log.error("encrypt error.", e);
			return password;
		}
		StringBuffer sb = new StringBuffer();
		for (byte ep: encryptedPassword) {
			sb.append(ep + ",");
		}
		return enBase64(sb.toString());
	}
	/**
	 * 
	 * 解密算法（双向）
	 * 
	 * @param password
	 *            加过密的密码
	 * 
	 * @return 解密后的密码
	 */
	public static String decrypt(String spassword) {
		String[] ss = deBase64(spassword).split(",");
		byte[] decryptedPassword = new byte[ss.length];
		for (int i = 0; i < ss.length; i++) {
			decryptedPassword[i] = (byte) (new Integer(ss[i]).intValue());
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, theKey, ivParameters);
			return new String(cipher.doFinal(decryptedPassword));
		} catch (Exception e) {
			log.error("decrypt error.", e);
		}
		return null;
	}

	/**
	 * BASE64 编码
	 * @param s
	 * @return
	 */
	public static String enBase64(String s) {
		return new BASE64Encoder().encode(s.getBytes());
	}

	/**
	 * BASE64 解码
	 * @param s
	 * @return
	 */
	public static String deBase64(String s) {
		try {
			return new String(new BASE64Decoder().decodeBuffer(s));
		} catch (Exception e) {
			log.error("deBase64 error.", e);
		}
		return null;
	}

    /**
     * 加密方法（单向）
     * @param password 原始密码
     * @param algorithm 加密算法类型，如：SHA
     * @return String 加密后的值
     */
    public static String encodePassword(String password, AlgorithmEnum algorithm) {
        byte[] unencodedPassword = password.getBytes();
        MessageDigest md = null;
        String algorithmString = "";
        if(algorithm == AlgorithmEnum.MD5) {
        	algorithmString = "MD5";
        } else if(algorithm == AlgorithmEnum.SHA) {
        	algorithmString = "SHA";
        }
        try {
            // first create an instance, given the provider
            md = MessageDigest.getInstance( algorithmString );
        } catch (Exception e) {
            log.error("错误，获得加密算法！Exception: " + e);
            return password;
        }
        md.reset();
        md.update(unencodedPassword);

        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }
    
    public static void main(String[] args) {
    	System.out.println(new Date().getTime());
		System.out.println(encodePassword("111111", AlgorithmEnum.MD5));
		
		JsonArray arr = new JsonParser().parse("['dddd','gggg']").getAsJsonArray();
		arr.add(new JsonPrimitive("hhhh"));
		System.out.println(arr.toString());
	}
}
