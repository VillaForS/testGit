package com.maiyajf.base.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源定位、搜索
 * 
 * @author ck
 *
 */
public class DataSourceLookup extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSouce();
	}

}
