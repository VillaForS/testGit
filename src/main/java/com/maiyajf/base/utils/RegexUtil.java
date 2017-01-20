package com.maiyajf.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author 冯星星
 *
 */
public class RegexUtil {
	
	/**
	 * 从字符串中过滤出与正则表达式匹配的子串
	 * @param regex 正则表达式
	 * @param str 匹配子串
	 * @return
	 */
	public static String matcher(String regex, String str) {
		if (regex == null || "".equals(regex) || str == null || "".equals(str)) {
			return null;
		}

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return str.substring(matcher.start(), matcher.end());
		} else {
			return null;
		}
	}
	
	/**
	 * 从字符串中过滤出与正则表达式匹配的子串
	 * @param regex 正则表达式
	 * @param str 匹配子串
	 * @param group 分组
	 * @return
	 */
	public static String matcher(String regex, String str, int group) {
		if (regex == null || "".equals(regex) || str == null || "".equals(str)) {
			return null;
		}
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return matcher.group(group);
		} else {
			return null;
		}
	}
	
	/**
	 * 检查字符串是否匹配
	 * 注意：整个字符串匹配检查 加^符号开头  和  $符号结束
	 * 例如：
	 * isMatcher("^123$", "1234") false
	 * isMatcher("^123$", "123") true
	 * isMatcher("123", "01234") true
	 * @param regex 正则表达式
	 * @param str 匹配子串
	 * @return
	 */
	public static boolean isMatcher(String regex, String str) {
		if(regex == null || str == null) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
	
}
