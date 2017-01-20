/*
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * FileName: CommonUtils.java
 * Author:   LG
 * Date:     2016年8月17日 上午11:05:43
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.maiyajf.base.utils.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maiyajf.base.constants.NumberConstants;

/**
 * 对象、集合转化工具类<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CommonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 功能描述: 复制对象,常用于po转dto，适合于深度复制<br>
     * 〈功能详细描述〉
     *
     * @param source
     * @param tClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <Z> Z transform(Object source, Class<Z> tClass) {
        if (source == null) {
            return null;
        }
        try {
            Z target = tClass.newInstance();
            BeanUtils.copyProperties(target, source);
            return target;
        } catch (Exception ex) {
            LOGGER.error("transform error.", ex);
            return null;
        }
    }

    /**
     * 功能描述: 复制集合，常用于List<po>转List<dto>,适用于深度复制<br>
     * 〈功能详细描述〉
     *
     * @param sources
     * @param tClass
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <Z, T> List<Z> transformList(Collection<T> sources, Class<Z> tClass) {
        try {
            List<Z> result = new ArrayList<Z>();
            if (sources == null) {
                return result;
            }
            for (T source : sources) {
                if (source == null) {
                    continue;
                }
                Z target = tClass.newInstance();
                BeanUtils.copyProperties(target, source);
                result.add(target);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("transformList error.", ex);
            return Collections.emptyList();
        }
    }

    /**
     * 功能描述: 复制集合，常用于不同属性对象之间，适用于浅复制<br>
     * 〈功能详细描述〉
     *
     * @param sources
     * @param converter
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <Z, T> List<Z> transformList(Collection<T> sources, ListConverter<Z, T> converter) {
        try {
            List<Z> result = new ArrayList<Z>();
            for (T source : sources) {
                result.add(converter.convert(source));
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("transformList error.", ex);
            return Collections.emptyList();
        }
    }

    /**
     * 功能描述: 复制集合，常用于不同属性对象之间，适用于浅复制，且增加相应的业务逻辑处理<br>
     * 〈功能详细描述〉
     *
     * @param sources
     * @param converter
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <Z, T> List<Z> transformListRemoveNone(Collection<T> sources, ListConverter<Z, T> converter) {
        try {
            List<Z> result = new ArrayList<Z>();
            for (T source : sources) {
                Z z = converter.convert(source);
                if (z == null) {
                    continue;
                }
                result.add(z);
            }
            return result;
        } catch (Exception ex) {
            LOGGER.error("transformListRemoveNone error.", ex);
            return Collections.emptyList();
        }
    }

    /**
     * 功能描述: 拼接字符串<br>
     * 〈功能详细描述〉
     *
     * @param append
     * @param str
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String concatStr(char append, String... str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String string : str) {
            if (string == null) {
                string = StringUtils.EMPTY;
            }
            sb.append(string).append(append);
        }
        return sb.substring(NumberConstants.NUM_ZERO, sb.length() - NumberConstants.NUM_ONE).toString();
    }

}
