/**
 * 
 */
package com.maiyajf.base.utils.log;

import org.apache.log4j.Logger;

import com.maiyajf.base.utils.log.appender.LogPropsReload;

/**
 * 调试日志，用于开发调试，上线关闭
 * 
 * @author jun.wang
 * 
 */
public class DebugLogger {

	private static Logger logger = Logger.getLogger(DebugLogger.class);


	/**
	 * --------DebugLogger.debug(MyAppender.trace(),"xx");
	 * 
	 * @Title: LoggerDebug
	 * @Description: 开发调试使用，debug级别
	 * @param @param debugMsg 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void debug(String trace, Object msg) {
		// 刷新参数
		LogPropsReload.refreshLogProperties();
		if (LogPropsReload.isDebugEnabled) {
			logger.debug("<debug>" + trace + "|" + msg + "</debug>");
		}
	}

	/**
	 * 
	 * @author jun.wang
	 * @Description: 记录调试日志
	 * @date 2014-9-15 上午10:52:50
	 * 
	 * @param msg
	 */
	public static void debug(Object msg) {
		DebugLogger.debug("", msg);
	}

}
