/**
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * Author:   LG
 * Date:     2016年9月14日 上午9:03:20
 * Description: //模块目的、功能描述      
 */
package com.maiyajf.base.utils.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MapTransformUtils extends MapUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapTransformUtils.class);

    /**
     * 功能描述: 将Map转换为Object<br>
     * 〈功能详细描述〉
     *
     * @param clazz 目标对象的类
     * @param map 待转换Map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T, V> T toObject(Map<String, V> map, Class<T> clazz) {
        return toObject(map, clazz, false);
    }

    /**
     * 功能描述: 将Map转换为Object<br>
     * 〈功能详细描述〉
     *
     * @param clazz 目标对象的类
     * @param map 待转换Map
     * @param toCamelCase 是否去掉下划线
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T, V> T toObject(Map<String, V> map, Class<T> clazz, boolean toCamelCase) {
        if (map == null) {
            return null;
        }
        try {
            T target = clazz.newInstance();
            if (toCamelCase) {
                map = toCamelCaseMap(map);
            }
            BeanUtils.populate(target, map);
            return target;
        } catch (Exception ex) {
            LOGGER.error("MapUtils toObject error.", ex);
            return null;
        }
    }

    /**
     * 功能描述: 将集合Map转换为List<Object><br>
     * 〈功能详细描述〉
     *
     * @param maps
     * @param clazz
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T, V> List<T> toObjectList(List<Map<String, V>> maps, Class<T> clazz) {
        return toObjectList(maps, clazz, false);
    }

    /**
     * 功能描述: 将集合Map转换为List<Object><br>
     * 〈功能详细描述〉
     *
     * @param maps
     * @param clazz
     * @param toCamelCase
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T, V> List<T> toObjectList(List<Map<String, V>> maps, Class<T> clazz, boolean toCamelCase) {
        List<T> list = null;
        if (maps != null && maps.size() > 0) {
            list = Lists.newArrayList();
            Map<String, V> map = null;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                T bean = toObject(map, clazz, toCamelCase);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 功能描述: 对象转Map<br>
     * 〈功能详细描述〉
     *
     * @param object 目标对象
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Map<String, String> toMap(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return BeanUtils.describe(object);
        } catch (Exception ex) {
            LOGGER.error("MapUtils toMap error.", ex);
            return null;
        }
    }

    /**
     * 功能描述: 转换为Collection<Map<K, V>><br>
     * 〈功能详细描述〉
     *
     * @param collection 待转换对象集合
     * @return 转换后的Collection<Map<K, V>>
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> Collection<Map<String, String>> toMapList(Collection<T> collection) {
        List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        if (collection != null && !collection.isEmpty()) {
            for (Object object : collection) {
                Map<String, String> map = toMap(object);
                retList.add(map);
            }
        }
        return retList;
    }

    /**
     * 功能描述: 转换为Collection,同时为字段做驼峰转换<Map<K, V>><br>
     * 〈功能详细描述〉
     *
     * @param collection
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <T> Collection<Map<String, String>> toMapListForFlat(Collection<T> collection) {
        List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
        if (collection != null && !collection.isEmpty()) {
            for (Object object : collection) {
                Map<String, String> map = toMapForFlat(object);
                retList.add(map);
            }
        }
        return retList;
    }

    /**
     * 功能描述: 转换成Map并提供字段命名驼峰转平行<br>
     * 〈功能详细描述〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Map<String, String> toMapForFlat(Object object) {
        Map<String, String> map = toMap(object);
        return toUnderlineStringMap(map);
    }

    /**
     * 功能描述: 将Map的Keys去下划线<br>
     * 〈例:branch_no -> branchNo〉
     *
     * @param map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <V> Map<String, V> toCamelCaseMap(Map<String, V> map) {
        Map<String, V> newMap = new HashMap<String, V>();
        for (String key : map.keySet()) {
            safeAddToMap(newMap, JavaBeanUtil.toCamelCaseString(key), map.get(key));
        }
        return newMap;
    }

    /**
     * 功能描述: 将Map的Keys转译成下划线格式的<br>
     * 〈例:branchNo -> branch_no〉
     *
     * @param map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static <V> Map<String, V> toUnderlineStringMap(Map<String, V> map) {
        Map<String, V> newMap = new HashMap<String, V>();
        for (String key : map.keySet()) {
            newMap.put(JavaBeanUtil.toUnderlineString(key), map.get(key));
        }
        return newMap;
    }
}
