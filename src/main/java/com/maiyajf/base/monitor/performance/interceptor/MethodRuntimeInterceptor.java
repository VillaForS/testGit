package com.maiyajf.base.monitor.performance.interceptor;

import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.maiyajf.base.utils.log.AccessLogger;
import com.maiyajf.base.utils.log.DebugLogger;
import com.maiyajf.base.utils.log.ExceptionLogger;

/**
 * 监控各业务方法的执行时间
 * 
 * @author ck
 *
 */
@Component("base-MethodRuntimeInterceptor")
@Aspect
public class MethodRuntimeInterceptor {

	public static final String expression = "execution(public * com.maiyajf..*.*(..))";

	@Pointcut(expression)
	public void pointCutController() {
	}

	private static ConcurrentHashMap<String, MethodStats> methodStats = new ConcurrentHashMap<String, MethodStats>();
	private static long statLogFrequency = 20;// 每20次统计一次
	private static long methodWarningThreshold_WARNING = 1000;// 超过一秒统计一次
	private static long methodWarningThreshold_FATAL = 10000;// 超过十秒统计一次

	@Around("pointCutController()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		String methodName = "";
		try {
			methodName = pjp.getSignature().toString();
		} catch (Exception e) {
			ExceptionLogger.error("", "【doAround】【获取被监控方法名异常】！！ProceedingJoinPoint：" + pjp, e);
		}
		try {
			return pjp.proceed();
		} catch (Exception e) {
			ExceptionLogger.error("", "【doAround】【监控到方法执行异常】！！方法：" + methodName, e);
			throw e;
		} finally {
			try {
				updateStats(methodName, (System.currentTimeMillis() - start));
			} catch (Exception e2) {
				ExceptionLogger.error("", "【doAround】【统计方法执行时长异常】！！方法：" + methodName, e2);
			}
		}
	}

	private void updateStats(String methodName, long elapsedTime) {
		MethodStats stats = methodStats.get(methodName);
		if (stats == null) {
			stats = new MethodStats(methodName);
			methodStats.put(methodName, stats);
		}
		stats.count++;
		stats.totalTime += elapsedTime;
		if (elapsedTime > stats.maxTime) {
			stats.maxTime = elapsedTime;
		}

		long avgTime = stats.totalTime / stats.count;
		long runningAvg = (stats.totalTime - stats.lastTotalTime) / statLogFrequency;

		if (elapsedTime > methodWarningThreshold_FATAL) {
			AccessLogger.info(null, null,
					"[严重!!!!][告警！执行时间过长！]" + getCalTimeStr(methodName, stats, elapsedTime, avgTime));
		} else if (elapsedTime > methodWarningThreshold_WARNING) {
			AccessLogger.info(null, null,
					"[警告!!][告警！执行时间过长！]" + getCalTimeStr(methodName, stats, elapsedTime, avgTime));
		}

		if (stats.count % statLogFrequency == 0) {
			DebugLogger.debug("[性能监控：频率打印][方法名称：" + methodName + "] [调用次数:" + stats.count + "] [执行耗时(ms)：" + elapsedTime
					+ "] [平均耗时(ms)：" + avgTime + "] [最长一次耗时(ms)：" + stats.maxTime + "]");

			// reset the last total time
			stats.lastTotalTime = stats.totalTime;
		}
	}

	private String getCalTimeStr(String methodName, MethodStats stats, long elapsedTime, long avgTime) {
		return "[性能监控] [方法名称：" + methodName + "] [调用次数:" + stats.count + "] [执行耗时(ms)：" + elapsedTime + "] [平均耗时(ms)："
				+ avgTime + "] [最长一次耗时(ms)：" + stats.maxTime + "]";
	}

	class MethodStats {
		public String methodName;
		public long count;
		public long totalTime;
		public long lastTotalTime;
		public long maxTime;

		public MethodStats(String methodName) {
			this.methodName = methodName;
		}
	}
}
