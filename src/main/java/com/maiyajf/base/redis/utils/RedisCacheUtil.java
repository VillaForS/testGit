/**
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * Author:   LG
 * Date:     2016年7月29日 下午4:04:01
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.maiyajf.base.redis.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.maiyajf.base.redis.ShardedJedisAction;
import com.maiyajf.base.redis.ShardedJedisClient;

import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.Tuple;

/**
 * redis访问工具类
 *
 * 
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisCacheUtil {

    private ShardedJedisClient shardedJedisClient;

    public void setShardedJedisClient(ShardedJedisClient shardedJedisClient) {
        this.shardedJedisClient = shardedJedisClient;
    }

    public ShardedJedisClient getShardedJedisClient() {
        return shardedJedisClient;
    }

    /*
     * Sets 集合
     */
    public Long sadd(final String key, final String... values) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.sadd(key, values);
            }
        });
    }

    public long srem(final String key, final String value) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.srem(key, value);
            }
        });
    }

    public Set<String> smembers(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<Set<String>>() {
            @Override
            public Set<String> doAction(ShardedJedis shardedJedis) {
                return shardedJedis.smembers(key);
            }
        });
    }

    public Boolean sismember(final String key, final String value) {
        return shardedJedisClient.execute(new ShardedJedisAction<Boolean>() {
            @Override
            public Boolean doAction(ShardedJedis shardedJedis) {
                return shardedJedis.sismember(key, value);
            }
        });
    }

    public List<Boolean> batchSismember(final String key, final List<String> values) {
        return shardedJedisClient.execute(new ShardedJedisAction<List<Boolean>>() {
            @Override
            public List<Boolean> doAction(ShardedJedis shardedJedis) {
                ShardedJedisPipeline pipeline = shardedJedis.pipelined();
                for (String value : values) {
                    pipeline.sismember(key, value);
                }
                List<Object> list = pipeline.syncAndReturnAll();
                List<Boolean> result = new ArrayList<Boolean>();
                for (Object item : list) {
                    result.add((Boolean) item);
                }
                return result;
            }
        });
    }

    /*
     * Hashs 哈希
     */
    public String hget(final String key, final String field) {
        return shardedJedisClient.execute(new ShardedJedisAction<String>() {
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hget(key, field);
            }
        });
    }

    public <T> T hgetBin(final String key, final String field, final Class<T> type) {
        String result = shardedJedisClient.execute(new ShardedJedisAction<String>() {
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hget(key, field);
            }
        });
        return jsonDecode(result, type);
    }

    public void hset(final String key, final String field, final String value) {
        shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hset(key, field, value);
            }
        });
    }

    public void hsetBin(final String key, final String field, final Object object) {
        shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hset(key, field, jsonEncode(object));
            }
        });
    }

    public Long hdel(final String key, final String field) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hdel(key, field);
            }
        });
    }

    public <T> Map<String, T> hgetAllBin(final String key, final Class<T> type) {
        Map<String, String> result = shardedJedisClient.execute(new ShardedJedisAction<Map<String, String>>() {
            public Map<String, String> doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hgetAll(key);
            }
        });
        Map<String, T> resultList = new LinkedHashMap<String, T>();
        Iterator<Map.Entry<String, String>> it = result.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            resultList.put(entry.getKey(), jsonDecode(entry.getValue(), type));
        }
        return resultList;
    }

    public <T> List<T> hvalsBin(final String key, final Class<T> type) {
        return shardedJedisClient.execute(new ShardedJedisAction<List<T>>() {
            public List<T> doAction(ShardedJedis shardedJedis) {
                List<T> resultList = new ArrayList<T>();
                List<String> list = shardedJedis.hvals(key);
                for (String s : list) {
                    resultList.add(jsonDecode(s, type));
                }
                return resultList;
            }
        });
    }

    public long hlen(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hlen(key);
            }
        });
    }

    public void hmsetBin(final String key, final Map<String, Object> hash) {
        shardedJedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis shardedJedis) {
                Map<String, String> values = new HashMap<String, String>();
                for (Map.Entry<String, Object> entry : hash.entrySet()) {
                    values.put(entry.getKey(), jsonEncode(entry.getValue()));
                }
                return shardedJedis.hmset(key, values);
            }
        });
    }

    public <T extends Serializable> List<T> hmgetBin(final String key, final Class<T> type, final String... fields) {
        if (fields == null || fields.length == 0) {
            return new ArrayList<T>();
        }
        return shardedJedisClient.execute(new ShardedJedisAction<List<T>>() {
            @Override
            public List<T> doAction(ShardedJedis shardedJedis) {
                List<String> values = shardedJedis.hmget(key, fields);
                List<T> result = new ArrayList<T>(values.size());
                for (String value : values) {
                    result.add(jsonDecode(value, type));
                }
                return result;
            }
        });
    }

    public String hmset(final String key, final Map<String, String> hash) {
        return shardedJedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hmset(key, hash);
            }
        });
    }

    public List<String> hmget(final String key, final String... fields) {
        if (fields == null || fields.length == 0) {
            return new ArrayList<String>();
        }
        return shardedJedisClient.execute(new ShardedJedisAction<List<String>>() {

            @Override
            public List<String> doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hmget(key, fields);
            }
        });
    }

    public Long hincrby(final String key, final String field, final long value) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.hincrBy(key, field, value);
            }
        });
    }

    public Long hincrby(final String key, final String field, final int time, final long value) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis shardedJedis) {
                boolean exist = true;
                long leftTime = shardedJedis.ttl(key);
                if (leftTime < 0) {
                    // 过期时间小于0说明key不存在
                    exist = false;
                }
                long num = shardedJedis.hincrBy(key, field, value);
                if (!exist) {
                    // 有效时间不为0且key为新插入则设置业务缓存的有效时间
                    shardedJedis.expire(key, time);
                }
                return num;
            }

        });
    }

    /* String 字符串 */
    public Long incr(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.incr(key);
            }
        });
    }

    /**
     * 将 key 中储存的数字值加一。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 incr操作
     * 
     * @param key 缓存key
     * @param time 缓存时间(秒)
     */
    public long incr(final String key, final int time) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                boolean exist = true;
                long leftTime = shardedJedis.ttl(key);
                if (leftTime < 0) {
                    // 过期时间小于0说明key不存在
                    exist = false;
                }
                long num = shardedJedis.incr(key);
                if (!exist) {
                    // 有效时间不为0且key为新插入则设置业务缓存的有效时间
                    shardedJedis.expire(key, time);
                }
                return num;
            }
        });
    }

    /**
     * 将 key 中储存的数字值加一。 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 incr操作
     * 
     * @param key 缓存key
     * @param time 缓存时间(秒)
     */
    public long incrBy(final String key, final int time, final Long cnt) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                boolean exist = true;
                long leftTime = shardedJedis.ttl(key);
                if (leftTime < 0) {
                    // 过期时间小于0说明key不存在
                    exist = false;
                }
                long num = shardedJedis.incrBy(key, cnt);
                if (!exist) {
                    // 有效时间不为0且key为新插入则设置业务缓存的有效时间
                    shardedJedis.expire(key, time);
                }
                return num;
            }
        });
    }

    public Long decr(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.decr(key);
            }
        });
    }

    /**
     * 同redis的decr命令,如果是第一次插入值，则同时设置在多少秒后失效
     * 
     * @param key 键
     * @param time 秒
     * @return 值
     */
    public long decr(final String key, final int time) {
        boolean exist = true;
        long leftTime = ttl(key);
        if (leftTime < 0) {
            exist = false;
        }
        Long ret = decr(key);
        if (!exist) {
            expire(key, time);
        }
        return ret;
    }

    public Long expire(final String key, final int seconds) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key, seconds);
            }
        });
    }

    public long ttl(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.ttl(key);
            }
        });
    }

    public boolean exists(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<Boolean>() {

            @Override
            public Boolean doAction(ShardedJedis shardedJedis) {
                return shardedJedis.exists(key);
            }
        });
    }

    public long del(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    public void setex(final String key, final String value, final int time) {
        shardedJedisClient.execute(new ShardedJedisAction<String>() {
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.setex(key, time, value);
            }
        });
    }

    public void setexBin(final String key, final Object object, final int time) {
        shardedJedisClient.execute(new ShardedJedisAction<String>() {
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.setex(key, time, jsonEncode(object));
            }
        });
    }

    public void set(final String key, final String value) {
        shardedJedisClient.execute(new ShardedJedisAction<String>() {
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }

    public void setBin(final String key, final Object object) {
        shardedJedisClient.execute(new ShardedJedisAction<String>() {
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, jsonEncode(object));
            }
        });
    }

    public String get(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<String>() {
            public String doAction(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }

    public <T> T getBin(final String key, final Class<T> type) {
        return shardedJedisClient.execute(new ShardedJedisAction<T>() {
            public T doAction(ShardedJedis shardedJedis) {
                return jsonDecode(shardedJedis.get(key), type);
            }
        });
    }

    public <T extends Serializable> List<T> mgetBin(final Class<T> type, final String... keys) {
        if (keys == null || keys.length == 0) {
            return new ArrayList<T>();
        }
        return shardedJedisClient.execute(new ShardedJedisAction<List<T>>() {
            @Override
            public List<T> doAction(ShardedJedis shardedJedis) {
                ShardedJedisPipeline pipeline = shardedJedis.pipelined();
                for (String key : keys) {
                    pipeline.get(key);
                }
                List<Object> responses = pipeline.syncAndReturnAll();
                List<T> result = new ArrayList<T>(responses.size());
                for (Object response : responses) {
                    String resp = (String) response;
                    result.add(resp == null ? null : jsonDecode(resp, type));
                }
                return result;
            }
        });
    }

    public void msetBin(final Map<String, Object> hash, final int time) {
        shardedJedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis shardedJedis) {
                ShardedJedisPipeline pipeline = shardedJedis.pipelined();
                for (Map.Entry<String, Object> entry : hash.entrySet()) {
                    pipeline.setex(entry.getKey(), time, jsonEncode(entry.getValue()));
                }
                pipeline.sync();
                return null;
            }
        });
    }

    /*
     * Lists 列表
     */
    public <T> List<T> lrangeBin(final String key, final Class<T> type, final long start, final long end) {
        return shardedJedisClient.execute(new ShardedJedisAction<List<T>>() {
            @Override
            public List<T> doAction(ShardedJedis shardedJedis) {
                List<T> resultList = new ArrayList<T>();
                List<String> results = shardedJedis.lrange(key, start, end);
                for (String s : results) {
                    resultList.add(jsonDecode(s, type));
                }
                return resultList;
            }
        });
    }

    public Long lpushBin(final String key, final int time, final Object... objects) {
        if (objects == null || objects.length == 0) {
            return 0L;
        }
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                final String[] jsonArray = objectsToJsonArray(objects);
                Long result = shardedJedis.lpush(key, jsonArray);
                shardedJedis.expire(key, time);
                return result;
            }
        });
    }

    /*-----------------------------------------------------------
    特殊实现队列。支持查询队列是否存在的功能。
    ------------------------------------------------------------- */

    /**
     * 获取队列数据，如果队列不存在返回null， 如果队列为空，返回空list
     * 
     * @param key key
     * @param start 开始位置
     * @param end 结束位置
     * @return 列表数据
     */
    public List<String> listRange(final String key, final long start, final long end) {
        return shardedJedisClient.execute(new ShardedJedisAction<List<String>>() {
            @Override
            public List<String> doAction(ShardedJedis jedis) {
                List<String> list = jedis.lrange(key, start, end);
                return fromRangList(list);
            }
        });
    }

    public List<List<String>> batchListRange(final List<String> keys, final long start, final long end) {
        return shardedJedisClient.execute(new ShardedJedisAction<List<List<String>>>() {
            @SuppressWarnings("unchecked")
            @Override
            public List<List<String>> doAction(ShardedJedis jedis) {
                ShardedJedisPipeline pipeline = jedis.pipelined();
                for (String key : keys) {
                    pipeline.lrange(key, start, end);
                }
                List<Object> list = pipeline.syncAndReturnAll();
                List<List<String>> result = new ArrayList<List<String>>();
                for (Object item : list) {
                    result.add(fromRangList((List<String>) item));
                }
                return result;
            }
        });
    }

    public <T> List<T> listRangeBin(final String key, final Class<T> type, final long start, final long end) {
        return shardedJedisClient.execute(new ShardedJedisAction<List<T>>() {
            @Override
            public List<T> doAction(ShardedJedis jedis) {
                List<String> list = jedis.lrange(key, start, end);
                return fromRangListBin(list, type);
            }
        });
    }

    public <T> List<List<T>> batchListRangeBin(final List<String> keys, final Class<T> type, final long start,
            final long end) {
        return shardedJedisClient.execute(new ShardedJedisAction<List<List<T>>>() {
            @Override
            public List<List<T>> doAction(ShardedJedis jedis) {
                ShardedJedisPipeline pipeline = jedis.pipelined();
                List<Response<List<String>>> resps = new ArrayList<Response<List<String>>>();
                for (String key : keys) {
                    Response<List<String>> resp = pipeline.lrange(key, start, end);
                    resps.add(resp);
                }
                pipeline.sync();
                List<List<T>> result = new ArrayList<List<T>>();
                for (Response<List<String>> resp : resps) {
                    List<T> binList = fromRangListBin(resp.get(), type);
                    result.add(binList);
                }
                return result;
            }
        });
    }

    public Long listDel(final String key, final String value) {
        if (value == null || value.isEmpty()) {
            return 0L;
        }
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.lrem(key, 1, value);
            }
        });
    }

    public Long listDelBin(final String key, final Object value) {
        if (value == null) {
            return 0L;
        }
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                return jedis.lrem(key, 1, jsonEncode(value));
            }
        });
    }

    public String listPop(final String key) {
        return shardedJedisClient.execute(new ShardedJedisAction<String>() {
            @Override
            public String doAction(ShardedJedis jedis) {
                return jedis.lpop(key);
            }
        });
    }

    /**
     * 创建空的队列
     * 
     * @param key key
     * @param time 超时时间
     */
    public void listCreate(final String key, final int time) {
        shardedJedisClient.execute(new ShardedJedisAction<Object>() {
            @Override
            public Object doAction(ShardedJedis jedis) {
                jedis.lpush(key, StringUtils.EMPTY);
                jedis.expire(key, time);
                return null;
            }
        });
    }

    public Long listAdd(final String key, final int time, final int maxSize, final String... objects) {
        if (objects == null || objects.length == 0) {
            return 0L;
        }
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                ArrayUtils.reverse(objects);
                Long count = jedis.lpush(key, objects);
                if (maxSize > 0) {
                    jedis.ltrim(key, 0, maxSize - 1);
                }
                jedis.expire(key, time);
                return count;
            }
        });
    }

    public Long listAddBin(final String key, final int time, final int maxSize, final Object... objects) {
        if (objects == null || objects.length == 0) {
            return 0L;
        }
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis jedis) {
                final String[] jsonStringArray = objectsToJsonArrayReverse(objects);
                Long count = jedis.lpush(key, jsonStringArray);
                if (maxSize > 0) {
                    jedis.ltrim(key, 0, maxSize - 1);
                }
                jedis.expire(key, time);
                return count;
            }
        });
    }

    // null有特殊含义，标识缓存为空
    @SuppressWarnings("all")
    private List<String> fromRangList(List<String> list) {
        List<String> result = new ArrayList<String>();
        int size = list.size();
        if (size == 0) {
            return null;
        }
        if (list.get(0).isEmpty()) {
            return result;
        }
        for (String s : list) {
            if (!s.isEmpty()) {
                result.add(s);
            }
        }
        return result;
    }

    // null有特殊含义，标识缓存为空
    @SuppressWarnings("all")
    private <T> List<T> fromRangListBin(List<String> list, Class<T> type) {
        List<T> result = new ArrayList<T>();
        int size = list.size();
        if (size == 0) {
            return null;
        }
        if (list.get(0).isEmpty()) {
            return result;
        }
        for (String s : list) {
            if (!s.isEmpty()) {
                result.add(jsonDecode(s, type));
            }
        }
        return result;
    }

    /*
     * 有序集合（Sorted Sets）
     */
    public Long zadd(final String key, final double score, final Object member) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.zadd(key, score, jsonEncode(member));
            }
        });
    }

    public Long zcount(final String key, final double min, final double max) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.zcount(key, min, max);
            }
        });
    }

    public <T> Set<T> zrangeByScore(final String key, final Class<T> type, final double min, final double max,
            final int offset, final int count) {
        return shardedJedisClient.execute(new ShardedJedisAction<Set<T>>() {
            @Override
            public Set<T> doAction(ShardedJedis shardedJedis) {
                Set<T> resultList = new LinkedHashSet<T>();
                Set<String> results = shardedJedis.zrangeByScore(key, min, max, offset, count);
                for (String result : results) {
                    resultList.add(jsonDecode(result, type));
                }
                return resultList;
            }
        });
    }

    public <T> Set<T> zrevrangeByScore(final String key, final Class<T> type, final double max, final double min,
            final int offset, final int count) {
        return shardedJedisClient.execute(new ShardedJedisAction<Set<T>>() {
            @Override
            public Set<T> doAction(ShardedJedis shardedJedis) {
                Set<T> resultList = new LinkedHashSet<T>();
                Set<String> results = shardedJedis.zrevrangeByScore(key, max, min, offset, count);
                for (String result : results) {
                    resultList.add(jsonDecode(result, type));
                }
                return resultList;
            }
        });
    }

    public Long zremrangeByScore(final String key, final double start, final double end) {
        return shardedJedisClient.execute(new ShardedJedisAction<Long>() {
            @Override
            public Long doAction(ShardedJedis shardedJedis) {
                return shardedJedis.zremrangeByScore(key, start, end);
            }
        });
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 add by 15090780
     * 
     * @param key
     * @param start
     * @param end
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Set<String> zrevrange(final String key, final long start, final long end) {
        return shardedJedisClient.execute(new ShardedJedisAction<Set<String>>() {
            @Override
            public Set<String> doAction(ShardedJedis shardedJedis) {
                return shardedJedis.zrevrange(key, start, end);
            }
        });
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 added by 15090780
     * 
     * @param key
     * @param start
     * @param end
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
        return shardedJedisClient.execute(new ShardedJedisAction<Set<Tuple>>() {
            @Override
            public Set<Tuple> doAction(ShardedJedis shardedJedis) {
                return shardedJedis.zrevrangeWithScores(key, start, end);
            }
        });
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 added by 15090780
     * 
     * @param key
     * @param member
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Double zscore(final String key, final String member) {
        return shardedJedisClient.execute(new ShardedJedisAction<Double>() {
            @Override
            public Double doAction(ShardedJedis shardedJedis) {

                return shardedJedis.zscore(key, member);

            }
        });
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉 add by 15090780
     * 
     * @param key
     * @param score
     * @param member
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public double zincrby(final String key, final double score, final String member) {
        return shardedJedisClient.execute(new ShardedJedisAction<Double>() {
            @Override
            public Double doAction(ShardedJedis shardedJedis) {
                return shardedJedis.zincrby(key, score, member);
            }
        });
    }

    public String jsonEncode(Object object) {
        if (object == null) {
            return null;
        }
        return JSON.toJSONString(object);
    }

    public <T> T jsonDecode(String input, Class<T> type) {
        if (input == null || input.length() == 0) {
            return null;
        }
        return JSON.parseObject(input, type);
    }

    public <T> List<T> jsonDecodeArray(String input, Class<T> type) {
        if (input == null || input.length() == 0) {
            return Collections.emptyList();
        }
        return JSON.parseArray(input, type);
    }

    /**
     * list to array
     * 
     * @param objects serial list
     * @return byte[][]
     */
    private String[] objectsToJsonArray(Object... objects) {
        int length = objects.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = jsonEncode(objects[i]);
        }
        return result;
    }

    private String[] objectsToJsonArrayReverse(Object... objects) {
        int length = objects.length;
        String[] result = new String[length];
        for (int i = 0; i < length; i++) {
            result[i] = jsonEncode(objects[length - i - 1]);
        }
        return result;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param flagId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String key(String key, String flagId) {
        StringBuilder sb = new StringBuilder();
        return sb.append(key).append(flagId).toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param flagId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String key(String key, String flagId, String flagId2) {
        StringBuilder sb = new StringBuilder();
        return sb.append(key).append(flagId).append(":").append(flagId2).toString();
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param key
     * @param uId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String keyByDay(String key, String uId) {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder();
        return sb.append(key).append(day).append(uId).toString();
    }
}