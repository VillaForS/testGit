/**
 * 
 */
package com.maiyajf.base.utils.log;

import org.apache.log4j.Logger;

import com.maiyajf.base.utils.base.DateUtils;
import com.maiyajf.base.utils.log.appender.LogPropsReload;

/**
 * 
 * 针对系统异常与重点业务异常
 * 
 * @author jun.wang
 * 
 */
public class ExceptionLogger {

	private static Logger logger = Logger.getLogger(ExceptionLogger.class);

	/**
	 * 
	 * @author jun.wang
	 * @Description: 记录异常
	 * @date 2014-9-15 下午4:27:53
	 * 
	 * @param exceptionNo
	 * @param exceptionDesc
	 * @param exceptionTime
	 * @param statckTrace
	 */
	public static void error(String exceptionNo, String exceptionDesc, String exceptionTime, Throwable t) {
		// 刷新参数
		LogPropsReload.refreshLogProperties();
		if (LogPropsReload.isExceptionEnabled) {
			StringBuilder info = new StringBuilder();
			info.append("<exception>");
			info.append(exceptionNo);
			info.append("|");
			info.append(exceptionDesc);
			info.append("|");
			info.append(exceptionTime);
			info.append("</exception>");
			logger.error(info.toString(), t);
		}
	}

	public static void error(String exceptionNo, String exceptionDesc, Throwable t) {
		// 刷新参数
		LogPropsReload.refreshLogProperties();
		if (LogPropsReload.isExceptionEnabled) {
			StringBuilder info = new StringBuilder();
			info.append("<exception>");
			info.append(exceptionNo);
			info.append("|");
			info.append(exceptionDesc);
			info.append("|");
			info.append(DateUtils.getCurrentTimeNumber());
			info.append("</exception>");
			logger.error(info.toString(), t);
		}
	}

	/**
	 * 
	 * @Title: error @Description: (记录其他异常信息) @param @param logger @param @param
	 * t 设定文件 @return void 返回类型 @throws
	 */
	public static void error(Throwable t) {
		ExceptionLogger.error("", "", "", t);
	}
	
	/**
	 * @param exceptionNo
	 * @param exceptionDesc
	 */
	public static void error(String exceptionNo, String exceptionDesc) {
		// 刷新参数
		LogPropsReload.refreshLogProperties();
		if (LogPropsReload.isExceptionEnabled) {
			StringBuilder info = new StringBuilder();
			info.append("<exception>");
			info.append(exceptionNo);
			info.append("|");
			info.append(exceptionDesc);
			info.append("|");
			info.append(DateUtils.getCurrentTimeNumber());
			info.append("</exception>");
			logger.error(info.toString());
		}
	}

}
