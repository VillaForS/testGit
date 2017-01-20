/*
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * FileName: ListConverter.java
 * Author:   LG
 * Date:     2016年8月17日 上午11:20:07
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.maiyajf.base.utils.base;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ListConverter<Z, T> {

    /**
     * 功能描述: 转化接口<br>
     * 〈功能详细描述〉
     *
     * @param t
     * @return
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Z convert(T t) throws Exception;
}
