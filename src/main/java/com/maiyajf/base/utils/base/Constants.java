/** 
 * Constants.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.maiyajf.base.spring.Configuration;

/**
 * 常量/系统参数组件类。
 * 可以在这里添加常量/系统参数, 也可以通过功能维护模块在配置文件中添加。
 * 提示：配置文件中最好不要设置中文内容。
 * @since version1.0
 */
public class Constants {
	
	public static final String SUCCESS = "success";    //成功标识
	public static final String FAIL = "fail";		   //失败标识
	
	public static final String InstitutionID="001064";//快捷支付机构号码
	public static final String cardType="10";//快捷支付银行卡类型储蓄卡
	/**
	 * 系统参数文件
	 */
	private static final String CONFIGURATION_PATH = "todaysteel.properties";
	/**
	 * 前置配置文件
	 * */
	public static final String APPLICATION_PROPERTIES = "application.properties";
	public static final String FPAY_CORE_URL = "fpay.core.url";
	
	/**
	 * 首页是否缓存，添加开关设置
	 */
	public static final String FPAY_CACHE_SWITCH = new Configuration(
			Constants.APPLICATION_PROPERTIES).getValue("fpay.cache.switch");
	
	/**
	 * 配置参数缓存名
	 */	
	public static final String FPAY_CACHE_PARAMETER_TIME = new Configuration(
			Constants.APPLICATION_PROPERTIES).getValue("fpay.cache.parameter.time");
	/**
	 * 首页动态文章缓存时间
	 */
	public static final String FPAY_CACHE_ARTICLE_TIME = new Configuration(
			Constants.APPLICATION_PROPERTIES).getValue("fpay.cache.article.time");

	/**
	 * 首页项目列表缓存时间
	 */
	public static final String FPAY_CACHE_VWPROJECTCHANNEL_TIME = new Configuration(
			Constants.APPLICATION_PROPERTIES).getValue("fpay.cache.vwProjectChannel.time");

	/**
	 * 系统日志
	 */
	protected static Logger logger = Logger.getLogger("SystemLog");

	/**
	 * 全局系统参数值缓存集合
	 */
	private static Properties prop = null;
	
	/**
	 * 全局密匙
	 */
	public static final int ENCRYPTION_KEY = 505;

	static {
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(CONFIGURATION_PATH));//getSysParameterFilePath()
			prop = new Properties();
			prop.load(in);
		} catch (IOException e) {
			logger.error("系统参数文件——todaysteel.properties，初始化出错！");
		}
	}
	
	/**
	 * 获取系统参数文件的绝对路径
	 * 
	 * @return String
	 */
	public static final String getSysParameterFilePath() {
		return getRootPath() + "../config/" + CONFIGURATION_PATH;
	}

	/**
	 * 传入参数Key,返回相应的值
	 * 
	 * @param key<String>系统参数Key
	 * @return String
	 */
	public static final String getPropValue(String key) {
		return (String) prop.get(key);
	}

	/**
	 * 获取全局系统参数值缓存集合，其中包含了所有配置文件中的key/value组合。
	 * 
	 * @return Properties
	 */
	public static final Properties getProp() {
		return Constants.prop;
	}

	/**
	 * 返回维护模块的每页最大记录数
	 * 
	 * @return int
	 */
	public static final int getResultsPerPage() {
		return new Integer((String) Constants.prop.get("resultsPerPage"));
	}
	
	/**
	 * 返回查询或查询统计模块的每页最大记录数
	 * 
	 * @return String
	 */
	public static final String getResultsPerPageForQuery() {
		return (String) Constants.prop.get("resultsPerPageForQuery");
	}

	/**
	 * 返回系统绝对根路径,
	 * 比如:E:/Project/WebRoot/WEB-INF/classes/
	 * 
	 * @return String
	 */
	public static final String getRootPath() {
		String path = Constants.class.getResource("/").toString();
		return path.substring(6, path.length()).replace("%20", " ");
	}
}
