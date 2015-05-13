package com.rpframework.utils;

public class HtmlHelper {

	/**
	 * 去除某段文字中的标签,
	 * ...<pre>...</pre>... 传入 pre即可
	 * @param context
	 * @param tag
	 * @return
	 */
	
	public static String parseHtmltag(String context , String tag){
		
		if(null != context && null != tag){
			
			String _tmp = context.replaceAll("<"+tag+">", "");
			
			_tmp = _tmp.replaceAll("</"+tag+">", "");
			
			return _tmp;
		}
		return context;
	}
}
