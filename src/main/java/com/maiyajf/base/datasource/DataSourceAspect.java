package com.maiyajf.base.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 数据源切面拦截，动态切换
 * 
 * @author ck
 *
 */
public class DataSourceAspect {

	public void before(JoinPoint point) {
		Object target = point.getTarget();
		String method = point.getSignature().getName();

		Class<?>[] classz = target.getClass().getInterfaces();

		Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
		try {
			Method m = classz[0].getMethod(method, parameterTypes);
			if (m != null) {
				if (m.isAnnotationPresent(DataSourceAnno.class)) {
					// 方法上注解已经指定数据源
					DataSourceAnno data = m.getAnnotation(DataSourceAnno.class);
					DynamicDataSourceHolder.putDataSource(data.value());
					// System.out.println(data.value());
				} else {
					// 方法上注解未指定数据源
					DynamicDataSourceHolder.putDataSource(null);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
