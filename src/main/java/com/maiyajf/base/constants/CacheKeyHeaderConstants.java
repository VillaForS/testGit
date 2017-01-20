package com.maiyajf.base.constants;


/**
 * @ClassName: CacheKeyHeaderConstants
 * @Description: 缓存key的头部常量
 * @author: yunlei.hua
 * @date: 2016年9月13日 上午11:23:42
 */
public interface CacheKeyHeaderConstants {

	/**
	 * 卡BIN Redis Key
	 */
	public static final String CARDBIN_KEY = "DATA:ZD_CARDBIN:";

	/**
	 * 渠道银行管理 Redis Key
	 */
	public static final String CHA_BANK_KEY = "DATA:ZD_CHABANK:";

	/**
	 * 字典表编码CODE Redis Key
	 */
	public static final String DICT_CODE_KEY = "DATA:ZD_CODE:";
	
	/**
	 * 银行支付渠道路由 Redis Key
	 */
	public static final String BANK_CHANNEL_ROUTE_KEY = "DATA:BANK_CHANNEL_ROUTE:";
}
