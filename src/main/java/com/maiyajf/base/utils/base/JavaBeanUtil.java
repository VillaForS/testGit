/**
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * Author:   LG
 * Date:     2016年9月14日 上午9:08:13
 * Description: //模块目的、功能描述      
 */
package com.maiyajf.base.utils.base;

import java.util.Locale;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class JavaBeanUtil {
    private static final char SEPARATOR = '_';

    /**
     * 功能描述: 将属性样式字符串转成驼峰样式字符串<br>
     * 〈例:branchNo -> branch_no〉
     *
     * @param inputString
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String toUnderlineString(String inputString) {
        if (inputString == null)
            return null;
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            boolean nextUpperCase = true;
            if (i < (inputString.length() - 1)) {
                nextUpperCase = Character.isUpperCase(inputString.charAt(i + 1));
            }
            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0)
                        sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 功能描述: 将驼峰字段转成属性字符串<br>
     * 〈例:branch_no -> branchNo〉
     *
     * @param inputString
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String toCamelCaseString(String inputString) {
        return toCamelCaseString(inputString, false);
    }

    /**
     * 功能描述: 将驼峰字段转成属性字符串<br>
     * 〈例:branch_no -> branchNo〉
     *
     * @param inputString 输入字符串
     * @param firstCharacterUppercase 是否首字母大写
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String toCamelCaseString(String inputString, boolean firstCharacterUppercase) {
        if (inputString == null)
            return null;
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            switch (c) {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0) {
                        nextUpperCase = true;
                    }
                    break;
                default:
                    if (nextUpperCase) {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    } else {
                        sb.append(Character.toLowerCase(c));
                    }
                    break;
            }
        }
        if (firstCharacterUppercase) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
        return sb.toString();
    }

    /**
     * 功能描述: 得到标准字段名称<br>
     * 〈功能详细描述〉
     *
     * @param inputString
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getValidPropertyName(String inputString) {
        String answer;
        if (inputString == null) {
            answer = null;
        } else if (inputString.length() < 2) {
            answer = inputString.toLowerCase(Locale.US);
        } else {
            if (Character.isUpperCase(inputString.charAt(0)) && !Character.isUpperCase(inputString.charAt(1))) {
                answer = inputString.substring(0, 1).toLowerCase(Locale.US) + inputString.substring(1);
            } else {
                answer = inputString;
            }
        }
        return answer;
    }

    /**
     * 功能描述: 将属性转换成标准set方法名字符串<br>
     * 〈功能详细描述〉
     *
     * @param property
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getSetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "set");
        return sb.toString();
    }

    /**
     * 功能描述: 将属性转换成标准get方法名字符串<br>
     * 〈功能详细描述〉
     *
     * @param property
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String getGetterMethodName(String property) {
        StringBuilder sb = new StringBuilder();
        sb.append(property);
        if (Character.isLowerCase(sb.charAt(0))) {
            if (sb.length() == 1 || !Character.isUpperCase(sb.charAt(1))) {
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
            }
        }
        sb.insert(0, "get");
        return sb.toString();
    }

}
