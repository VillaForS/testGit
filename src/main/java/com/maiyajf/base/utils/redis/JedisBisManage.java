/**
 * 
 */
package com.maiyajf.base.utils.redis;

import com.maiyajf.base.spring.SpringContextHolder;
import com.maiyajf.base.utils.log.ExceptionLogger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;


/**
 * @author Administrator
 * 
 */
public class JedisBisManage {
	
	/**
	 * 初始化redis连接池
	 */
	private static JedisPool pool = (JedisPool) SpringContextHolder
			.getBean("jedisPool");

	/**
	 * 获取redis连接实例
	 * @return
	 */
	public static Jedis getJedis() {
		Jedis jedis = null;
        try {
            jedis = pool.getResource();
        } catch (JedisException e) {
            ExceptionLogger.error(e);
            if(jedis!=null){
            	pool.returnBrokenResource(jedis);
            }
        }
        if(jedis==null){
        	ExceptionLogger.error(new Throwable("JedisPool is null，Please check redis status!"));
        }
        return jedis;
	}

	/**
	 * 扔回到连接池
	 * @param redis
	 */
	public static void returnResource(Jedis jedis) {
		if (jedis != null) {
			pool.returnResource(jedis);
		}
	}
}
