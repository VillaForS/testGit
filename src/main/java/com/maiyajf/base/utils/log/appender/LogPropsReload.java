/**
 * 
 */
package com.maiyajf.base.utils.log.appender;

import java.util.Date;

import com.maiyajf.base.spring.Configuration;
import com.maiyajf.base.utils.log.ExceptionLogger;

/**
 * @author jun.wang 日志配置刷新
 */
public class LogPropsReload {
	
	private static final String LOG4J_HIDEPARAMS = "log4j.hideParams";
	private static final String LOG4J_RELOAD_INTERVAL = "log4j.reload.interval";
	private static final String LOG4J_DEBUGLOGGER_ISOPEN = "log4j.DebugLogger.isopen";
	private static final String LOG4J_ACCESSLOGGER_ISOPEN = "log4j.AccessLogger.isopen";
	private static final String LOG4J_OUTINTERFACELOGGER_ISOPEN = "log4j.OutInterfaceLogger.isopen";
	private static final String LOG4J_EXCEPTIONLOGGER_ISOPEN = "log4j.ExceptionLogger.isopen";
	private static final String LOG4J_SYSTEMLOGGER_ISOPEN = "log4j.SystemLogger.isopen";
	// 刷新间隔
	private static long interval = -1l;
	// 出入参隐藏
	public static boolean hideParams = true;
	/**
	 * 上次修改时间
	 */
	public static Date lastUpdateDate = new Date();
	
	//调试日志开关
	public static boolean isDebugEnabled = false;
	//访问日志开关
	public static boolean isAccessEnabled = false;
	//外部访问日志开关
	public static boolean isInterfaceEnabled = false;
	//异常日志开关
	public static boolean isExceptionEnabled = false;
	//系统日志开关
	public static boolean isSystemEnabled = false;
	//服务层日志开关
	public static boolean isServiceEnabled = false;

	/**
	 * 
	 * @author jun.wang
	 * @Description: 整点刷新
	 * @date 2014-9-16 上午11:28:25
	 * 
	 */
	public static void refreshLogProperties() {
		try {
			// 间隔时间
			if (new Date().getTime() - lastUpdateDate.getTime() > interval) {
				Configuration config = new Configuration("log.properties");
				// 重新加载时间间隔
				interval = Long.parseLong(config
						.getValue(LOG4J_RELOAD_INTERVAL));
				// 参数
				hideParams = Boolean.parseBoolean(config
						.getValue(LOG4J_HIDEPARAMS));
				// 调试日志开关
				try {
					isDebugEnabled = Boolean.parseBoolean(config
							.getValue(LOG4J_DEBUGLOGGER_ISOPEN));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 访问日志开关
				try {
					isAccessEnabled = Boolean.parseBoolean(config
							.getValue(LOG4J_ACCESSLOGGER_ISOPEN));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 接口日志
				try {
					isInterfaceEnabled = Boolean.parseBoolean(config
							.getValue(LOG4J_OUTINTERFACELOGGER_ISOPEN));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 异常日志
				try {
					isExceptionEnabled = Boolean.parseBoolean(config
							.getValue(LOG4J_EXCEPTIONLOGGER_ISOPEN));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 系统日志
				try {
					isSystemEnabled = Boolean.parseBoolean(config
							.getValue(LOG4J_SYSTEMLOGGER_ISOPEN));
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 更新时间
				lastUpdateDate = new Date();
				config = null;
			}
		} catch (NumberFormatException e) {
			ExceptionLogger.error(e);
		}
	}
}
