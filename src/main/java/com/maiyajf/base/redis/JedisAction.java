/**
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * Author:   LG
 * Date:     2016年7月29日 下午4:04:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.maiyajf.base.redis;

import redis.clients.jedis.Jedis;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface JedisAction<T> {

    public T doAction(Jedis jedis);

}
