/** 
 * WebserviceUtil.java
 * create on 2011-1-21
 * Copyright 2015 Todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * WebService工具类
 * @author <a href="mailto:service@todaysteel.com">wangjianglin</a>
 * @since version1.0
 */
public class WebserviceUtil {

	/**
	 * 通用正则表达式校验
	 * @param reg  正则表达式
	 * @param string  要校验的数据
	 * @return
	 */
	public static final boolean doExpCheck(String reg, String string) {
		boolean tem = false;
		try {
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(string);
			tem = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return tem;
	}
	
	/**
	 * 分析证书dn 字符串 ，并放置于 HashMap 中。
	 * 
	 * @param dnStr
	 *            String
	 * @return HashMap
	 */
	public static HashMap<String,String> parseDN(String dnStr) {
		if (dnStr == null)
			return null;
		StringTokenizer token = new StringTokenizer(dnStr, ",");
		HashMap<String,String> result = new HashMap<String,String>();
		while (token.hasMoreElements()) {
			String str = (String) token.nextElement();
			String[] s = str.split("=");
			if (s != null && s.length == 2 && s[0] != null && s[1] != null)
				result.put(s[0].trim(), s[1].trim());
		}
		return result;
	}
	
}
