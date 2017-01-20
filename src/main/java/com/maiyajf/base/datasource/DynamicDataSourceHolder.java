package com.maiyajf.base.datasource;

/**
 * 数据源上下文切换操作
 * 
 * @author ck
 *
 */
public class DynamicDataSourceHolder {
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();

	public static void putDataSource(String name) {
		holder.set(name);
	}

	public static String getDataSouce() {
		return holder.get();
	}
}
