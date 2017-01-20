/** 
 * Validate.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;

/**
 * ActionForm对象数据合法性校验工具类
 * @since version1.0
 */
public class Validate {

	/**
	 * 验证单个对象数据是否合法(非集合对象)
	 * 
	 * @param o
	 *            待验证的对象实例
	 * @param notNullStr
	 *            不能为空值的属性名称组成的字符串, 比如: "id,name,grade"
	 */
	public static ValidateResult validateSingleObj(Object o, String notNullStr) {
		ValidateResult result = new ValidateResult();
		if (o == null)
			return result;
		validateObj(o, notNullStr, result);
		return result;
	}

	/**
	 * 验证对象数据是否合法(集合对象)
	 * 
	 * @param o
	 *            待验证的对象实例
	 * @param notNullStr
	 *            不能为空值的属性名称组成的字符串, 比如: "id,name,grade"
	 */
	public static ValidateResult validateCollectionObj(Collection o,
			String notNullStr) {
		ValidateResult result = new ValidateResult();
		if (o == null)
			return result;
		Iterator ite = o.iterator();
		while (ite.hasNext()) {
			validateObj(ite.next(), notNullStr, result);
		}
		return result;
	}

	/**
	 * 验证对象数据是否合法(非集合对象)
	 * 
	 * Note: 验证失败，此方法将封装当前属性，属性值和错误提示到一个字符串数组中。
	 * 
	 * @param o
	 *            待验证的对象实例
	 * @param notNullStr
	 *            不能为空值的属性名称组成的字符串, 比如: "id,name,grade"
	 */
	private static void validateObj(Object o, String notNullStr,
			ValidateResult result) {
		try {
			
			Field[] fields = o.getClass().getSuperclass().getDeclaredFields();

			boolean isBug = false;
			String prompt = null;
			List properties = new ArrayList();

			for (int i = 0; i < fields.length; i++) {
				String sname = fields[i].getName();
				String svalue = BeanUtils.getProperty(o, fields[i].getName());

				if (isContains(notNullStr, sname)
						&& StringUtils.isBlank(svalue)) {
					isBug = true;
					prompt = "当前属性的值不能为空!";
				} else {
					if (StringUtils.isNotBlank(svalue)
							&& sname.substring(0, 1).equals("i")
							&& !sname.equals("id")) {
						if (!GenericValidator.isInt(svalue)) {
							isBug = true;
							prompt = "当前属性的值必须为整数!";
						}
					} else if (StringUtils.isNotBlank(svalue)
							&& sname.substring(0, 1).equals("l")) {
						if (!GenericValidator.isLong(svalue)) {
							isBug = true;
							prompt = "当前属性的值必须为长整数!";
						}
					} else if (StringUtils.isNotBlank(svalue)
							&& sname.substring(0, 1).equals("f")) {
						if (!GenericValidator.isDouble(svalue)) {
							isBug = true;
							prompt = "当前属性的值必须为浮点数!";
						}
					} else if (StringUtils.isNotBlank(svalue)
							&& sname.substring(0, 1).equals("b")) {
						if (!Validater.isBoolean(svalue)) {
							isBug = true;
							prompt = "当前属性的值必须为布尔型（如0、1、true、false）!";
						}
					} else if (StringUtils.isNotBlank(svalue)
							&& sname.substring(0, 1).equals("d")) {
						if (!Validater.isDate(svalue)) {
							isBug = true;
							prompt = "当前属性的值必须为日期类型!";
						}
					} else if (StringUtils.isNotBlank(svalue)
							&& sname.substring(0, 1).equals("t")) {
						if (!Validater.isDatetime(svalue)) {
							isBug = true;
							prompt = "当前属性的值必须为日期时间类型!";
						}
					} else if (StringUtils.isBlank(svalue)
							&& sname.substring(0, 1).equals("d")) {
						// 如果该属性值为日期型且允许为空，则如果该属性值为空时赋予null值
						BeanUtils.setProperty(o, sname, null);
					} else if (StringUtils.isBlank(svalue)
							&& sname.substring(0, 1).equals("t")) {
						// 如果该属性值为日期时间型且允许为空，则如果该属性值为空时赋予null值
						BeanUtils.setProperty(o, sname, null);
					} else if (StringUtils.isBlank(svalue)
							&& sname.substring(0, 1).equals("i")
							&& !sname.equals("id")) {
						// 如果该属性值为整型且允许为空，则如果该属性值为空时赋予null值
						BeanUtils.setProperty(o, sname, null);
					} else if (StringUtils.isBlank(svalue)
							&& sname.substring(0, 1).equals("l")) {
						// 如果该属性值为长整型且允许为空，则如果该属性值为空时赋予null值
						BeanUtils.setProperty(o, sname, null);
					} else if (StringUtils.isBlank(svalue)
							&& sname.substring(0, 1).equals("f")) {
						// 如果该属性值为浮点型且允许为空，则如果该属性值为空时赋予null值
						BeanUtils.setProperty(o, sname, null);
					}
				}
				if (isBug) {
					isBug = false;
					properties.add(new String[] { sname, svalue, prompt });
				}
			}

			if (properties.size() > 0) {
				result.setFailure(true);
				result.getObjs().put(o, properties);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isContains(String sourceStr, String str) {
		if (sourceStr == null || str == null)
			return false;
		sourceStr = "," + sourceStr + ",";
		return sourceStr.contains("," + str + ",");
	}
}