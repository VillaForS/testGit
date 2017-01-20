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

public class RedisClientException extends RuntimeException {
    private static final long serialVersionUID = 7460934076911268418L;

    /**
     * 构造异常对象
     * 
     * @param msg
     */
    public RedisClientException(String msg) {
        super(msg);
    }

    /**
     * RedisClientException
     * 
     * @param exception
     */
    public RedisClientException(Throwable exception) {
        super(exception);
    }

    /**
     * RedisClientException
     * 
     * @param mag
     * @param exception
     */
    public RedisClientException(String mag, Exception exception) {
        super(mag, exception);
    }
}
