/**
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * Author:   LG
 * Date:     2016年7月29日 下午4:04:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.maiyajf.base.redis.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maiyajf.base.redis.JedisAction;
import com.maiyajf.base.redis.JedisClient;
import com.maiyajf.base.utils.redis.JedisBisManage;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JedisClientImpl implements JedisClient {

    private static final Logger logger = LoggerFactory.getLogger(JedisClientImpl.class);

    @Override
    public void destroy() {

    }

    @Override
    public <T> T execute(JedisAction<T> action) {
        // 获取业务层面调用execute的类
        T result = null;
        try {
            result = executeAction(action);
        } catch (Exception e) {
            throw new RedisClientException(e);
        }
        return result;
    }

    /**
     * 功能描述: 执行<br>
     * 〈功能详细描述〉
     *
     * @param action
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private <T> T executeAction(JedisAction<T> action) {
        Jedis jedis = null;
        try {
            jedis = JedisBisManage.getJedis();
            return action.doAction(jedis);
        } catch (JedisConnectionException jex) {
            if (jedis != null) {
                try {
                    JedisBisManage.returnResource(jedis);
                } catch (Exception ex) {
                    logger.warn("Can not return broken resource.", ex);
                }
                jedis = null;
            }
            throw jex;
        } finally {
            if (jedis != null) {
                try {
                    JedisBisManage.returnResource(jedis);
                } catch (Exception ex) {
                    logger.warn("Can not return resource.", ex);
                }
            }
        }
    }
}
