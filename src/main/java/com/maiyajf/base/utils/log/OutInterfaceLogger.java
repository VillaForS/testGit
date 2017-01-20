/**
 * 
 */
package com.maiyajf.base.utils.log;

import java.util.Date;

import org.apache.log4j.Logger;

import com.maiyajf.base.utils.base.DateUtils;
import com.maiyajf.base.utils.log.appender.LogPropsReload;

/**
 * 系统接口日志，线上稳定之后可以选择关闭
 * 
 * @author jun.wang
 * 
 */
public class OutInterfaceLogger {

	private static Logger logger = Logger.getLogger(OutInterfaceLogger.class);
	
	/**
	 * 
	 * @author jun.wang
	 * @Description: 访问外部接口日志
	 * @date 2014-9-15 下午5:23:34
	 * @param channelNo
	 *            渠道编号 channelNo String 外部系统编号 N
	 * @param interfaceName
	 *            接口名称 username String 如果用户登录记录用户名 N
	 * @param url
	 *            接口URL URL String 接口URL或者URI N
	 * @param reqTime
	 *            请求时间 reqTime String yyyyMMddhhmmss（ms） N
	 * @param reqParams
	 *            请求参数 reqParams String 隐私参数屏蔽，如密码，身份信息等。 Y
	 * @param resTime
	 *            响应时间 resTime String yyyyMMddhhmmss（ms） Y
	 * @param resStatus
	 *            响应状态 resStatus String 正确码与错误码 Y
	 * @param resParams
	 *            响应结果 resParams String 隐私参数屏蔽，如密码，身份信息等。可以关闭 Y
	 * @param timeConsuming
	 *            耗时 timeConsuming int 单位毫秒 Y
	 */
	public static void info(String channelNo, String interfaceName, String url,
			String reqTime, String reqParams, String resTime, String resStatus,
			String resParams, int timeConsuming) {
		//刷新参数
		LogPropsReload.refreshLogProperties();
		// 首先判断是否允许这个级别的日志记录
		if (LogPropsReload.isInterfaceEnabled) {
			StringBuilder info = new StringBuilder();
			info.append("<interface>");
			info.append(channelNo);
			info.append("|");
			info.append(interfaceName);
			info.append("|");
			info.append(url);
			info.append("|");
			info.append(reqTime);
			info.append("|");
			if (LogPropsReload.hideParams) {// 隐藏请求参数
				info.append("***");
			} else {
				info.append(reqParams);
			}
			info.append("|");
			info.append(resTime);
			info.append("|");
			info.append(resStatus);
			info.append("|");
			if (LogPropsReload.hideParams) {// 隐藏响应参数
				info.append("***");
			} else {
				info.append(resParams);
			}
			info.append("|");
			info.append(timeConsuming);
			info.append("</interface>");
			logger.info(info.toString());
		}
	}
	
	/**
	 * 
	 * @Description: 访问外部接口日志
	 * @param interfaceName
	 *            接口名称 username String 如果用户登录记录用户名 N
	 * @param url
	 *            接口URL URL String 接口URL或者URI N
	 * @param reqTime
	 *            请求时间 reqTime String yyyyMMddhhmmss（ms） N
	 * @param reqParams
	 *            请求参数 reqParams String 隐私参数屏蔽，如密码，身份信息等。 Y
	 * @param resTime
	 *            响应时间 resTime String yyyyMMddhhmmss（ms） Y
	 * @param resParams
	 *            响应结果 resParams String 隐私参数屏蔽，如密码，身份信息等。可以关闭 Y
	 */
	public static void info(String interfaceName, String url, Date reqTime, 
			String reqParams, Date resTime, String resParams) {
		String reqDate = null;
		String resDate = null;
		if (null != reqTime) {
			reqDate = DateUtils.dateParseString(reqTime,"yyyyMMddHHmmss");
		}
		if (null != resTime) {
			resDate = DateUtils.dateParseString(resTime,"yyyyMMddHHmmss");
		}
		// 首先判断是否允许这个级别的日志记录
		OutInterfaceLogger.info("", interfaceName, url, reqDate, reqParams, resDate,
				"", resParams, 0);
	}

	/**
	 * 
	 * @author jun.wang
	 * @Description: 访问外部接口日志
	 * @date 2014-9-15 下午5:23:34
	 * @param channelNo
	 *            渠道编号 channelNo String 外部系统编号 N
	 * @param interfaceName
	 *            接口名称 username String 如果用户登录记录用户名 N
	 * @param url
	 *            接口URL URL String 接口URL或者URI N
	 * @param reqTime
	 *            请求时间 reqTime String yyyyMMddhhmmss（ms） N
	 */
	public static void info(String channelNo, String interfaceName, String url,
			String reqTime) {
		// 首先判断是否允许这个级别的日志记录
		OutInterfaceLogger.info(channelNo, interfaceName, url, reqTime, "", "",
				"", "", 0);
	}

	
	/**
	 * 
	 * @author jun.wang
	 * @Description: 访问外部接口日志
	 * @date 2014-9-15 下午5:23:34
	 * @param interfaceName
	 *            接口名称 username String 如果用户登录记录用户名 N
	 * @param url
	 *            接口URL URL String 接口URL或者URI N
	 * @param reqTime
	 *            请求时间 reqTime Date yyyyMMddhhmmss（ms） N
	 */
	public static void info(String interfaceName, String url,
			Date reqTime) {
		String date = null;
		if (null != reqTime) {
			date = DateUtils.dateParseString(reqTime,"yyyyMMddHHmmss");
		}
		// 首先判断是否允许这个级别的日志记录
		OutInterfaceLogger.info("", interfaceName, url, date, "", "",
				"", "", 0);
	}
	
	/**
	 * 
	 * @author jun.wang
	 * @Description: 访问外部接口日志
	 * @date 2014-9-15 下午5:23:34
	 * @param interfaceName
	 *            接口名称 username String 如果用户登录记录用户名 N
	 * @param url
	 *            接口URL URL String 接口URL或者URI N
	 * @param reqTime
	 *            请求时间 reqTime Date yyyyMMddhhmmss（ms） N
	 * @param reqParams
	 *            请求参数 reqParams String 隐私参数屏蔽，如密码，身份信息等。 Y
	 */
	public static void info(String interfaceName, String url,
			Date reqTime, String reqParams) {
		String date = null;
		if (null != reqTime) {
			date = DateUtils.dateParseString(reqTime,"yyyyMMddHHmmss");
		}
		// 首先判断是否允许这个级别的日志记录
		OutInterfaceLogger.info("", interfaceName, url, date, "", reqParams,
				"", "", 0);
	}
}
