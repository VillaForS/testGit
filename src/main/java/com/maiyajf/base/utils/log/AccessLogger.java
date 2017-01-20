/**
 * 
 */
package com.maiyajf.base.utils.log;

import java.util.Date;

import org.apache.log4j.Logger;

import com.maiyajf.base.utils.base.DateUtils;
import com.maiyajf.base.utils.log.appender.LogPropsReload;

/**
 * 访问日志，记录用户访问轨迹与错误
 * 
 * @author jun.wang
 * 
 */
public class AccessLogger {

	private static Logger logger = Logger.getLogger(AccessLogger.class);

	/**
	 * 
	 * @author jun.wang
	 * @Description: 访问日志记录
	 * @date 2014-9-15 下午4:52:46
	 * @param user
	 *            用户名 username String 如果用户登录记录用户名 Y
	 * @param token
	 *            Session session String 会话ID或者Token N
	 * @param url
	 *            请求URL String N
	 * @param accessSource
	 *            请求来源 String web，android，IOS或者其它第三方渠道 N
	 * @param ip
	 *            请求IP ip String Y
	 * @param function
	 *            功能分类 function String XXYY：XX表示一级功能，YY表示二级功能 Y
	 * @param reqTime
	 *            请求时间 reqTime String yyyyMMddhhmmss（ms） N
	 * @param reqParams
	 *            请求参数 reqParams String 隐私参数屏蔽，如密码，身份信息等。可以关闭为空 Y
	 * @param resTime
	 *            响应时间 resTime String yyyyMMddhhmmss（ms） Y
	 * @param resStatus
	 *            响应状态 resStatus String 正确码与错误码 Y
	 * @param resParams
	 *            响应结果 resParams String 隐私参数屏蔽，如密码，身份信息等。可以关闭 Y
	 * @param timeConsuming
	 *            耗时 timeConsuming int 单位毫秒 Y
	 */
	public static void info(String user, String token, String url, String accessSource, String ip, String function,
			String reqTime, String reqParams, String resTime, String resStatus, String resParams, int timeConsuming) {
		// 刷新参数
		LogPropsReload.refreshLogProperties();
		// 首先判断是否允许这个级别的日志记录
		if (LogPropsReload.isAccessEnabled) {
			StringBuilder info = new StringBuilder();
			info.append("<access>");
			info.append(user);
			info.append("|");
			info.append(token);
			info.append("|");
			info.append(url);
			info.append("|");
			info.append(accessSource);
			info.append("|");
			info.append(ip);
			info.append("|");
			info.append(function);
			info.append("|");
			info.append(reqTime);
			info.append("|请求参数：");
			if (LogPropsReload.hideParams) {// 隐藏请求参数
				info.append("***");
			} else {
				info.append(reqParams);
			}
			info.append("|");
			info.append(resTime);
			info.append("|");
			info.append(resStatus);
			info.append("|响应参数：");
			if (LogPropsReload.hideParams) {// 隐藏响应参数
				info.append("***");
			} else {
				info.append(resParams);
			}
			info.append("|耗时：");
			info.append(timeConsuming);
			info.append("（毫秒）</access>");
			logger.info(info.toString());
		}
	}

	/**
	 * 
	 * @author jun.wang
	 * @Description: 访问日志记录
	 * @date 2014-9-15 下午4:52:46
	 * @param token
	 *            Session session String 会话ID或者Token N
	 * @param url
	 *            请求URL String N
	 * @param accessSource
	 *            请求来源 String web，android，IOS或者其它第三方渠道 N
	 * @param reqTime
	 */
	public static void info(String token, String url, String accessSource, String reqTime) {
		AccessLogger.info("", token, url, accessSource, "", "", reqTime, "", "", "", "", 0);
	}

	public static void info(String token, String url, String accessSource) {
		AccessLogger.info("", token, url, accessSource, "", "", new Date().toLocaleString(), "", "", "", "", 0);
	}
	
	public static void info(String user, String token, String accessSource, Date reqTime) {
		String date = DateUtils.dateToString(reqTime);
		AccessLogger.info(user, token, "", accessSource, "", "", date, "", "", "", "", 0);
	}
	
	public static void info(String user, String token, String url, String accessSource, Date reqTime) {
		String date = DateUtils.dateToString(reqTime);
		AccessLogger.info(user, token, url, accessSource, "", "", date, "", "", "", "", 0);
	}
	
	public static void info(String accessDesc, String url, String reqParams, String resParams, int timeConsuming) {
		AccessLogger.info(accessDesc, "", url, "", "", "", "", reqParams, "", "", resParams, timeConsuming);
	}
}
