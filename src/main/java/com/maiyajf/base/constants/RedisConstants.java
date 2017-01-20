/**
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * Author:   LG
 * Date:     2016年7月29日 下午4:04:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.maiyajf.base.constants;

/**
 * redis常量类<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisConstants {

    /**
     * 10秒（毫秒单位）
     */
    public static final int SEC_10_IN_MILL = 10 * 1000;

    /**
     * 1分钟（毫秒单位）
     */
    public static final int MIN_IN_MILL = 60 * 1000;

    /**
     * 10分钟
     */
    public static final int TEN_IN_MIN = 10 * 60;

    /**
     * 1个小时
     */
    public static final int HOUR_1 = 1 * 60 * 60;

    /**
     * 3个小时
     */
    public static final int HOUR_3 = 3 * 60 * 60;

    /**
     * 一天的时间
     */
    public static final int DAYTIME = 24 * 60 * 60;
    /**
     * 一周的时间
     */
    public static final int WEEKTIME = 7 * 24 * 60 * 60;

    /**
     * 15天
     */
    public static final int HALF_MONTH_TIME = 15 * 24 * 60 * 60;

    /**
     * 一个月时间
     */
    public static final int MONTHTIME = 30 * 24 * 60 * 60;

    /**
     * 20年的时间
     */
    public static final int YEARTIME = 20 * 360 * 24 * 60 * 60;

    /**
     * 通用过期时间（1个小时）
     */
    public static final int COMMON_EXPIRE_TIME = 60 * 60;

    /**
     * key 匹配
     */
    public static final String KEYSPATTERN = "*";

    /**
     * 卡BIN Redis Key
     */
    public static final String CARDBIN_KEY = "zd:cardbin:";

    /**
     * 银行管理 Redis Key
     */
    public static final String CONF_BANK_KEY = "zd:bank:conf:";

    /**
     * 渠道银行管理 Redis Key
     */
    public static final String CHA_BANK_KEY = "zd:bank:coop:";

    /**
     * 银行支付渠道路由 Redis Key
     */
    public static final String BANK_CHANNEL_ROUTE_KEY = "zd:bank:rount:";

    /**
     * 字典表编码CODE Redis Key
     */
    public static final String DICT_CODE_KEY = "zd:code:";

    /**
     * 请求黑名单 Redis Key
     */
    public static final String REQUEST_BLACKLIST = "request:black:";

    /**
     * 请求白名单 Redis Key
     */
    public static final String REQUEST_WHITELIST = "request:white:";

    /**
     * 用户资料缓存KEY
     */
    public static final String KEY_MAIYA_USER_INFO = "maiya:user:info:";

    public RedisConstants() {

    }

}
