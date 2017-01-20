/**
 * 
 */
package com.maiyajf.base.utils.log;

import org.apache.log4j.Logger;

import com.maiyajf.base.utils.base.DateUtils;
import com.maiyajf.base.utils.log.appender.LogPropsReload;

/**
 * 系统日志，记录系统启动与关闭，线程调度等等行为
 * 
 * @author jun.wang
 * 
 */
public class SystemLogger {

	private static Logger logger = Logger.getLogger(SystemLogger.class);

	/**
	 * 
	 * @author jun.wang
	 * @Description:
	 * @date 2014-9-15 下午3:48:55
	 * 
	 * @param trace
	 *            调用者信息
	 * @param excuteNo
	 *            执行编号 excuteNo String 系统执行编号，每个系统自定义 Y
	 * @param executeName
	 *            执行名称 executeName String 某某功能或者某某线程等。 Y
	 * @param executeDate
	 *            执行时间 reqTime String yyyyMMddhhmmss（ms） N 执行描述 executeDesc
	 *            String 执行情况描述 N
	 * @param executeStatus
	 *            执行状态 status String N
	 * @param timeConsuming
	 *            耗时 timeConsuming int 单位毫秒 Y
	 */
	public static void info(String trace, String excuteNo, String executeName, String executeDate, String executeDesc,
			String executeStatus, int timeConsuming) {
		// 刷新参数
		LogPropsReload.refreshLogProperties();
		// 首先判断是否允许这个级别的日志记录
		if (LogPropsReload.isSystemEnabled) {
			StringBuilder info = new StringBuilder();
			info.append("<system>");
			info.append(trace);
			info.append("|");
			info.append(excuteNo);
			info.append("|");
			info.append(executeName);
			info.append("|");
			info.append(executeDate);
			info.append("|");
			info.append(executeDesc);
			info.append("|");
			info.append(executeStatus);
			info.append("|");
			info.append(timeConsuming);
			info.append("</system>");
			logger.info(info.toString());
		}
	}

	/**
	 * 
	 * @author jun.wang
	 * @Description:
	 * @date 2014-9-15 下午3:48:55
	 * 
	 * @param excuteNo
	 *            执行编号 excuteNo String 系统执行编号，每个系统自定义 Y
	 * @param executeName
	 *            执行名称 executeName String 某某功能或者某某线程等。 Y
	 * @param executeDate
	 *            执行时间 reqTime String yyyyMMddhhmmss（ms） N 执行描述 executeDesc
	 *            String 执行情况描述 N
	 * @param executeStatus
	 *            执行状态 status String N
	 * @param timeConsuming
	 *            耗时 timeConsuming int 单位毫秒 Y
	 */
	public static void info(String excuteNo, String executeName, String executeDate, String executeDesc,
			String executeStatus, int timeConsuming) {
		SystemLogger.info("", excuteNo, executeName, executeDate, executeDesc, executeStatus, timeConsuming);
	}

	/**
	 * 
	 * @author jun.wang
	 * @Description:
	 * @date 2014-9-15 下午4:02:45
	 * 
	 * @param trace
	 * @param executeDate
	 * @param executeDesc
	 * @param executeStatus
	 */
	public static void info(String trace, String executeDate, String executeDesc, String executeStatus) {
		SystemLogger.info(trace, "", "", executeDate, executeDesc, executeStatus, 0);
	}

	/**
	 * 
	 * @author jun.wang
	 * @Description:
	 * @date 2014-9-15 下午4:02:45
	 * @param executeDate
	 * @param executeDesc
	 * @param executeStatus
	 */
	public static void info(String executeDate, String executeDesc, String executeStatus) {
		SystemLogger.info("", "", executeDate, executeDesc, executeStatus, 0);
	}

	public static void info(String executeDesc) {
		SystemLogger.info("", "", DateUtils.getCurrentTimeNumber(), executeDesc, "", 0);
	}

}
