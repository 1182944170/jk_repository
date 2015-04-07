package com.rpframework.utils;

import java.util.Arrays;
/**
 * @author <a href="mailto:rplees.i.ly@gmail.com">rplees</a>
 * date 2010-04-06
 * {@code} 一些常用的数组有关的帮助方法
 */
public class ArrayUtils {
	
	public static Number max(Number... ns) {
		Arrays.sort(ns) ;
		return ns[ns.length - 1] ;
	}
	public static Number min(Number... ns) {
		Arrays.sort(ns) ;
		return ns[0] ;
	}
}
