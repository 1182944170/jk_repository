package com.rpframework.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.nodes.TextNode;
import org.htmlparser.util.ParserException;

/**
 * @author rplees
 *
 */
public class CodeUtils {
	
	public static String format(String s, Object...objects) {
		return format_f(s, "##", objects);
	}
	public static String format_f(String s, String f_flag, Object...objects) {
		if(StringUtils.isBlank(s) || objects == null)
			return s;
		
		StringBuffer b = new StringBuffer();
//		String[] split = StringUtils.split(s, f_flag);
		String[] split = s.split(f_flag);
		for (int i = 0; i < split.length; i++) {
			b.append(split[i]);
			if(objects.length > i) {
				b.append(objects[i]);
			} else {
				if(i != split.length - 1)
					b.append(f_flag);
			}
		}
		
		return b.toString();
	}
	
	/**
	 * 将编码为ISO-8859-1转成默认编码 UTF-8
	 * @param n
	 * @return
	 */
	public static String changeISO2GBK(String n) {
		if (StringUtils.isBlank(n))
			return n;
		byte[] bytes;
		try {
			bytes = n.getBytes("ISO-8859-1");
			String n2 = new String(bytes, "UTF-8");
			if(isMessyCode(n2)) {
				n2 = new String(bytes, "GB2312");
			}
			
			return n2;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(changeISO2GBK("1"));
		
		System.out.println(isMessyCode("*��JTP.jar�ļ����JTP�ļ���ȡ��ͼƬ��Դ"));
		System.out.println(isMessyCode("��ͼ�Ƽ�"));
	    System.out.println(isMessyCode("你好"));
	    
	    System.out.println(replaceKeyString("&nbsp;\r" + "</p>"));
	    
	    

		String s = "sdfjhsdkfh## ## ssdffd # ## # sfsdfds";
		
		System.out.println(
				format(s, 1,2,3)
				);
		
	
	}
	
	public static boolean isChinese(char c) {
	    Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	    if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	        || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	      return true;
	    }
	    return false;
	  }

	  public static boolean isMessyCode(String strName) {
	    Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
	    Matcher m = p.matcher(strName);
	    String after = m.replaceAll("");
	    String temp = after.replaceAll("\\p{P}", "");
	    char[] ch = temp.trim().toCharArray();
	    float chLength = ch.length;
	    float count = 0;
	    for (int i = 0; i < ch.length; i++) {
	      char c = ch[i];
	      if (!Character.isLetterOrDigit(c)) {

	        if (!isChinese(c)) {
	          count = count + 1;
//	          System.out.print(c);
	        }
	      }
	    }
	    float result = count / chLength;
	    if (result > 0.4) {
	      return true;
	    } else {
	      return false;
	    }

	  }
	  
	/**
    * 删除html字符串中的html格式 
    * @param html 
    * @return 
    */  
   public static String splitAndFilterString(String html) {  
       if (StringUtils.isBlank(html)) {  
           return "";  
       }  
       // 去掉所有html元素,  
       String str = html.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(  
               "<[^>]*>", "").replaceAll("[(/>)<]", "");  
       return str;  
   }
   
   
   /**
	 * 处理url
	 * 
	 * url为null返回null，url为空串或以http://或https://开头，则加上http://，其他情况返回原参数。
	 * 
	 * @param url
	 * @return
	 */
	public static String handelUrl(String url) {
		if (url == null) {
			return null;
		}
		url = url.trim();
		if (url.equals("") || url.startsWith("http://")
				|| url.startsWith("https://")) {
			return url;
		} else {
			return "http://" + url.trim();
		}
	}

	/**
	 * 分割并且去除空格
	 * 
	 * @param str
	 *            待分割字符串
	 * @param sep
	 *            分割符
	 * @param sep2
	 *            第二个分隔符
	 * @return 如果str为空，则返回null。
	 */
	public static String[] splitAndTrim(String str, String sep, String sep2) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		if (!StringUtils.isBlank(sep2)) {
			str = StringUtils.replace(str, sep2, sep);
		}
		String[] arr = StringUtils.split(str, sep);
		// trim
		for (int i = 0, len = arr.length; i < len; i++) {
			arr[i] = arr[i].trim();
		}
		return arr;
	}

	/**
	 * 文本转html
	 * 
	 * @param txt
	 * @return
	 */
	public static String txt2htm(String txt) {
		if (StringUtils.isBlank(txt)) {
			return txt;
		}
		StringBuilder sb = new StringBuilder((int) (txt.length() * 1.2));
		char c;
		boolean doub = false;
		for (int i = 0; i < txt.length(); i++) {
			c = txt.charAt(i);
			if (c == ' ') {
				if (doub) {
					sb.append(' ');
					doub = false;
				} else {
					sb.append("&nbsp;");
					doub = true;
				}
			} else {
				doub = false;
				switch (c) {
				case '&':
					sb.append("&amp;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '"':
					sb.append("&quot;");
					break;
				case '\n':
					sb.append("<br/>");
					break;
				default:
					sb.append(c);
					break;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 剪切文本。如果进行了剪切，则在文本后加上"..."
	 * 
	 * @param s
	 *            剪切对象。
	 * @param len
	 *            编码小于256的作为一个字符，大于256的作为两个字符。
	 * @return
	 */
	public static String textCut(String s, int len, String append) {
		if (s == null) {
			return null;
		}
		int slen = s.length();
		if (slen <= len) {
			return s;
		}
		// 最大计数（如果全是英文）
		int maxCount = len * 2;
		int count = 0;
		int i = 0;
		for (; count < maxCount && i < slen; i++) {
			if (s.codePointAt(i) < 256) {
				count++;
			} else {
				count += 2;
			}
		}
		if (i < slen) {
			if (count > maxCount) {
				i--;
			}
			if (!StringUtils.isBlank(append)) {
				if (s.codePointAt(i - 1) < 256) {
					i -= 2;
				} else {
					i--;
				}
				return s.substring(0, i) + append;
			} else {
				return s.substring(0, i);
			}
		} else {
			return s;
		}
	}

	public static String htmlCut(String s, int len, String append) {
		String text = html2Text(s, len * 2);
		return textCut(text, len, append);
	}

	public static String html2Text(String html, int len) {
		try {
			Lexer lexer = new Lexer(html);
			Node node;
			StringBuilder sb = new StringBuilder(html.length());
			while ((node = lexer.nextNode()) != null) {
				if (node instanceof TextNode) {
					sb.append(node.toHtml());
				}
				if (sb.length() > len) {
					break;
				}
			}
			return sb.toString();
		} catch (ParserException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 检查字符串中是否包含被搜索的字符串。被搜索的字符串可以使用通配符'*'。
	 * 
	 * @param str
	 * @param search
	 * @return
	 */
	public static boolean contains(String str, String search) {
		if (StringUtils.isBlank(str) || StringUtils.isBlank(search)) {
			return false;
		}
		String reg = StringUtils.replace(search, "*", ".*");
		Pattern p = Pattern.compile(reg);
		return p.matcher(str).matches();
	}

	public static boolean containsKeyString(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		if (str.contains("'") || str.contains("\"") || str.contains("\r")
				|| str.contains("\n") || str.contains("\t")
				|| str.contains("\b") || str.contains("\f")) {
			return true;
		}
		return false;
	}

	// 将""和'转义
	public static String replaceKeyString(String str) {
		if (containsKeyString(str)) {
			return str.replace("'", "\\'").replace("\"", "\\\"").replace("\r",
					"\\r").replace("\n", "\\n").replace("\t", "\\t").replace(
					"\b", "\\b").replace("\f", "\\f");
		} else {
			return str;
		}
	}

}
