/**
 * 
 */
package com.maiyajf.base.utils.redis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import com.maiyajf.base.utils.SerializingUtil;
import com.maiyajf.base.utils.log.ExceptionLogger;

/**
 * @author Jinfm
 * 业务需求使用此REDIS UTIL
 */
@Component("base-JedisBisUtil")
public class JedisBisUtil{

	private static final String REDIS_HEADER = "bis:";

	/**
	 * 根据KEY获取对象
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public static Object get(String key) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				byte[] byteKey = getCacheKey(key).getBytes();
				byte[] byteValue = jedis.get(byteKey);
				if (byteValue != null) {
					return SerializingUtil.deserialize(byteValue);
				}
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return null;
	}
	
	/**
	 * 根据KEY获取对象（不进行序列化）
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public static Object getNoSerialize(String key) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				String value = jedis.get(getCacheKey(key));
				if (value != null) {
					return Integer.parseInt(value);
				}
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 增加
	 * @param key
	 * @param value
	 * @return
	 * @throws CacheException
	 */
	public static Object put(String key, Object value) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				if (key != null && value != null) {
					jedis.set(getCacheKey(key).getBytes(),
							SerializingUtil.serialize(value));
				}
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return value;
	}
	
	/**
	 * 自增
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public static Long incr(String key) throws CacheException {
		Long l=0l;
		Jedis jedis = JedisBisManage.getJedis();
		try {
			if (key != null) {
				l=jedis.incr(getCacheKey(key).getBytes());
			}
		} catch (JedisException e) {
			ExceptionLogger.error(e);
		} finally {
			JedisBisManage.returnResource(jedis);
		}
		return l;
	}
	
	/**
	 * 判断key存不存在
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public static boolean exists(String key) throws CacheException {
		Jedis jedis = JedisBisManage.getJedis();
		boolean has = true;
		try {
			if (key != null) {
				has = jedis.exists(getCacheKey(key).getBytes());
			}
		} catch (JedisException e) {
			ExceptionLogger.error(e);
			has = false;
		} finally {
			JedisBisManage.returnResource(jedis);
		}
		return has;
	}
	
	/**
	 * @Title: putExpire
	 * @Description: 增加并设置有效时间
	 * @return: Object
	 */
	public static Object putExpire(String key, int seconds, Object value) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				if (key != null && value != null) {
					jedis.setex(getCacheKey(key).getBytes(), seconds,
							SerializingUtil.serialize(value));
				}
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return value;
	}
	
	/**
	 * @Title: putExpireNoSerialize
	 * @Description: 增加并设置有效时间（不进行序列化）
	 * @return: Object
	 */
	public static Object putExpireNoSerialize(String key, int seconds, Integer value) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				if (key != null && value != null) {
					jedis.setex(getCacheKey(key), seconds, value.toString());
				}
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return value;
	}
	
	/**
	 * redis Set集合，新增当个值
	 * @param key
	 * @param value
	 * @return
	 * @throws CacheException
	 */
	public static Object sadd(String key, Object value) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				if (key != null && value != null) {
					jedis.sadd(getCacheKey(key).getBytes(), SerializingUtil.serialize(value));
				}
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return value;
	}
//	/**
//	 * redis Set 无序集合新增
//	 * @param key
//	 * @param values
//	 * @return
//	 * @throws CacheException
//	 */
//	public static Object sadds(String key, List<?> values) throws CacheException {
//		if (StringUtils.isBlank(key)) {
//			return null;
//		} else {
//			Jedis jedis = JedisBisManage.getJedis();
//			try {
//				if (key != null && values != null) {
//					for(Object value:values){
//						jedis.sadd(getCacheKey(key).getBytes(), SerializingUtil.serialize(value));
//					}
//				}
//			} catch (JedisException e) {
//				ExceptionLogger.error(e);
//			} finally {
//				JedisBisManage.returnResource(jedis);
//			}
//		}
//		return key;
//	}
//	
//	/**
//	 * 根据Set KEY获取对象集合
//	 * @param key
//	 * @return
//	 * @throws CacheException
//	 */
//	public static List<?> smembers(String key) throws CacheException {
//		if (StringUtils.isBlank(key)) {
//			return null;
//		} else {
//			Jedis jedis = JedisBisManage.getJedis();
//			try {
//				byte[] byteKey = getCacheKey(key).getBytes();
//				Set<byte[]> sets = jedis.smembers(byteKey);
//				List<Object> setValues=new ArrayList<Object>();
//				for(Iterator<byte[]>it=sets.iterator();it.hasNext();){
//					byte[] byteValue=	it.next();
//					Object o= SerializingUtil.deserialize(byteValue);
//					setValues.add(o);
//				}
//				return setValues;	
//			} catch (JedisException e) {
//				ExceptionLogger.error(e);
//			} finally {
//				JedisBisManage.returnResource(jedis);
//			}
//		}
//		return null;
//	}
/**
 * 
 * @param key
 * @param values
 * @return
 * @throws CacheException
 */
	public static Object zadd(String key, List<?> values) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				if (key != null && values != null) {
					Double i=0.0d;
					Map<Double, byte[]> map=new HashMap<Double, byte[]>();
					for(Object value:values){
						map.put(i++, SerializingUtil.serialize(value));
						if(i%50==0){
							jedis.zadd(getCacheKey(key).getBytes(), map);
							map.clear();
						}
						//jedis.zadd(getCacheKey(key).getBytes(), i++,  SerializingUtil.serialize(value));
					}
					if(map.size()>0){
						jedis.zadd(getCacheKey(key).getBytes(), map);
					}
				}
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return key;
	}
	
	/**
	 * 构建有序Redis查询
	 * @param key 
	 * @param start 开始记录数
	 * @param end  结束记录数
	 * @return
	 * @throws CacheException
	 */
	public static Object zrange(String key,int start,int end ) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return null;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				byte[] byteKey = getCacheKey(key).getBytes();
				if(end==0){
					end=-1;
				}
				Set<byte[]> lists = jedis.zrange(byteKey, start, end);
				List<Object> setValues=new ArrayList<Object>();
				for(Iterator<byte[]>it=lists.iterator();it.hasNext();){
					byte[] byteValue=	it.next();
					Object o= SerializingUtil.deserialize(byteValue);
					setValues.add(o);
				}
				return setValues;	
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return null;
	}

	/**
	 * 删除
	 * @param key
	 * @return
	 * @throws CacheException
	 */
	public static Object remove(String key) throws CacheException {
		Object previos = get(key);
		Jedis jedis = JedisBisManage.getJedis();
		try {
			jedis.del(getCacheKey(key).getBytes());
		} catch (JedisException e) {
			ExceptionLogger.error(e);
		} finally {
			JedisBisManage.returnResource(jedis);
		}

		return previos;
	}

	public static Object removeByString(String key) throws CacheException {
		Object previos = get(key);
		Jedis jedis = JedisBisManage.getJedis();
		try {
			jedis.del(key);
		} catch (JedisException e) {
			ExceptionLogger.error(e);
		} finally {
			JedisBisManage.returnResource(jedis);
		}

		return previos;
	}
	/**
	 * 清空
	 * @throws CacheException
	 */
	public void clear(String catalog) throws CacheException {
		byte[] keysPattern;
		if(StringUtils.isNotBlank(catalog)){
			 keysPattern = (REDIS_HEADER+catalog+ "*").getBytes();
		}else{
			 keysPattern = (REDIS_HEADER+ "*").getBytes();
		}
		Jedis jedis = JedisBisManage.getJedis();
		try {
			jedis.del(keysPattern);
		} catch (JedisException e) {
			ExceptionLogger.error(e);
		} finally {
			JedisBisManage.returnResource(jedis);
		}
	}
	/**
	 * 设置Redis失效时间
	 * @throws CacheException
	 */
	public static void expire(String key, int time) throws CacheException {
		if (StringUtils.isBlank(key)) {
			return ;
		} 
		Jedis jedis = JedisBisManage.getJedis();
		try {
			jedis.expire(getCacheKey(key).getBytes(), time);
		} catch (JedisException e) {
			ExceptionLogger.error(e);
		} finally {
			JedisBisManage.returnResource(jedis);
		}
	}
	/**
	 * 获取redis中的数据量
	 * @return
	 */
	public static int size() {
		if (keys(null) == null)
			return 0;
		return keys(null).size();
	}

	/**
	 * 获取key集合
	 * @return
	 */
	public static Set<String> keys(String catalog) {
		Jedis jedis = JedisBisManage.getJedis();
		Set<byte[]> byteSet;
		if(StringUtils.isNotBlank(catalog)){
			 byteSet = jedis.keys((REDIS_HEADER +catalog+ "*").getBytes());
		}else{
			 byteSet = jedis.keys((REDIS_HEADER + "*").getBytes());
		}
		Set<String> keys = new HashSet<String>();
		for (byte[] bs : byteSet) {
			keys.add((String)SerializingUtil.deserialize(bs));
		}
		return keys;
	}
	
	/**
	 * 获取key集合
	 * @return
	 */
	public static Set<String> keysByString(String catalog) {
		Jedis jedis = JedisBisManage.getJedis();
		Set<String> byteSet;
		if(StringUtils.isNotBlank(catalog)){
			 byteSet = jedis.keys((REDIS_HEADER +catalog+ "*"));
		}else{
			 byteSet = jedis.keys((REDIS_HEADER + "*"));
		}
		Set<String> keys = new HashSet<String>();
		for (String bs : byteSet) {
			keys.add(bs);
		}
		return keys;
	}

	/**
	 * 获取所有的值
	 * @return
	 */
	public static Collection<Object> values(String catalog) {
		Jedis jedis = JedisBisManage.getJedis();
		Set<byte[]> byteSet;
		if(StringUtils.isNotBlank(catalog)){
			 byteSet = jedis.keys((REDIS_HEADER+catalog+ "*").getBytes());
		}else{
			 byteSet = jedis.keys((REDIS_HEADER + "*").getBytes());
		}
		List<Object> result = new LinkedList<Object>();
		for (byte[] bs : byteSet) {
			result.add( SerializingUtil.deserialize(jedis.get(bs)));
		}
		return result;
	}

	/**
	 * 获取key
	 * @param key
	 * @return
	 */
	private static String getCacheKey(Object key) {
		return REDIS_HEADER + key;
	}
	
	/**
	 * @Title: getSurplusExpire
	 * @Description: 获得Redis剩余失效时间
	 * @return: long
	 */
	public static long getSurplusExpire(String key) {
		if (StringUtils.isBlank(key)) {
			return 0;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				byte[] byteKey = getCacheKey(key).getBytes();
				Long time = jedis.ttl(byteKey);
				if (null == time)
					return 0;
				return time;
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return 0;
	}

	/**
	 * 从左边添加内容
	 * @param key
	 * @param string
	 */
	public static void lpush(String key, String s) {
		if (StringUtils.isBlank(key)) {
			return;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				jedis.lpush(key,s);
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
	}

	/**
	 * 从左边，获取对应的索引的内容
	 * @param key
	 * @param i
	 * @return
	 */
	public static String lindex(String key, int i) {
		String result="";
		if (StringUtils.isBlank(key)) {
			return result;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				result=jedis.lindex(key,i);
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return result;
	}

	/**
	 * 截key对应的内容
	 * @param key
	 * @param i--开始位置
	 * @param l--结束位置
	 */
	public static void ltrim(String key, int b, long e) {
		if (StringUtils.isBlank(key)) {
			return;
		} else {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				jedis.ltrim(key,b,e);
			} catch (JedisException ex) {
				ExceptionLogger.error(ex);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
	}

	public static Long llen(String key) {
		Long l=0l;
		if (!StringUtils.isBlank(key)) {
			Jedis jedis = JedisBisManage.getJedis();
			try {
				l=jedis.llen(key.getBytes());
			} catch (JedisException e) {
				ExceptionLogger.error(e);
			} finally {
				JedisBisManage.returnResource(jedis);
			}
		}
		return l;
	}
	
}
