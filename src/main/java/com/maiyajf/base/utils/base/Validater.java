/** 
 * Validater.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 数据合法性校验方法集类
 * 
 * @since version1.0
 */
public class Validater {

    /**
     * 校验是否为空
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isEmpty(String str) {

        if (str == null || str.trim().length() == 0)
            return true;
        else
            return false;
    }

    /**
     * 校验是否为整形
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isNumber(String str) {

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * 校验是否为单精度浮点型
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isFloat(String str) {

        try {
            Float.parseFloat(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验是否为双精度浮点型
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isDouble(String str) {

        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验是否为电子邮件格式
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isEmail(String str) {
        if (str.trim().length() != 0) {
            int first = str.indexOf("@");
            int last = str.lastIndexOf("@");

            if (first != last || first == -1 || str.endsWith("@") == true) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * 日期格式校验正则表达式
     */
    static String date_reg = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0[13578]|1[02])-(0[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0[13456789]|1[012])-(0[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-02-(0[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-02-29))$";

    /**
     * 校验是否日期型(标准格式:yy-MM-dd)
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isDate(String str) {

        return Pattern.compile(date_reg).matcher(str).matches();
    }

    static final String mobile_reg = "^((\\+{0,1}86){0,1})1[0-9]{10}$";

    /**
     * 校验是否手机(标准格式:13719352010)
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isMobile(String str) {

        return Pattern.compile(mobile_reg).matcher(str).matches();
    }
    // static final String mobile_reg_new="^((13[0-9])|(15[^4,\\D])|(177)|(18[0-9]))\\d{8}$";
    // static final String mobile_reg_new="^1[34578]\\d{9}$";

    static final String mobile_reg_new = "^((1[358][0-9])|(147)|(17[0135678]))\\d{8}$";

    public static void main(String[] args) {
        String b = "17965415215";
        System.out.println(Validater.isMobileNew(b));
    }

    /**
     * 校验是否手机(标准格式:13719352010)
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isMobileNew(String str) {

        return Pattern.compile(mobile_reg_new).matcher(str).matches();
    }

    /**
     * 日期时间格式校验正则表达式
     */
    static String datetime_reg = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0[13578]|1[02])-(0[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0[13456789]|1[012])-(0[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-02-(0[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-02-29)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

    /**
     * 校验是否日期型(标准格式:yy-MM-dd)
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isDatetime(String str) {

        return Pattern.compile(datetime_reg).matcher(str).matches();
    }

    /**
     * 用户名判断
     */
    static String username_reg = "^[a-zA-Z0-9_-]*$";

    public static final boolean usernameCheck(String str) {
        return Pattern.compile(username_reg).matcher(str).matches();
    }

    /**
     * 用户名判断
     */
    static String username_reg_new = "^[a-zA-Z][a-zA-Z0-9_]{4,20}$";

    public static final boolean usernameCheckNew(String str) {
        return Pattern.compile(username_reg_new).matcher(str).matches();
    }

    /**
     * 校验是否为布尔型
     * 
     * @param str
     * @return boolean
     */
    public static final boolean isBoolean(String str) {

        if (str.equals("0") || str.equals("1") || str.equals("true") || str.equals("false")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断前个日期是否小于后个日期
     * 
     * @param beginDate
     * @param endDate
     * @return boolean
     */
    public static final boolean compareDate(String beginDate, String endDate) {

        long begin = Integer
                .parseInt(beginDate.substring(0, 4) + beginDate.substring(5, 7) + beginDate.substring(8, 10));
        long end = Integer.parseInt(endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8, 10));
        if (begin >= end) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 将对象中的属性类型为日期型的（即属性名以d开头的属性，命名时必须严格按规范命名）且值为空的属性值置为null值
     * 
     * @param obj 处理对象
     * @return void
     */
    public static final void resetObjectDate(Object obj) {

        String sname = null;
        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            try {
                sname = fields[i].getName();
                String svalue = BeanUtils.getProperty(obj, sname);

                if (sname.substring(0, 1).equals("d") && StringUtils.isBlank(svalue)) {
                    BeanUtils.setProperty(obj, sname, null);
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    static final String password_reg = "^(?![^a-zA-Z]+$)(?!\\D+$).{6,16}$";

    /**
     * 验证密码6到16位数，是否包含字母
     * 
     * @param password
     * @return
     */
    public static final boolean vaildaterPassword(String password) {
        return Pattern.compile(password_reg).matcher(password).matches();
    }

    static final String password_reg_new = "^(?![0-9]+$)(?![_]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,16}$";

    /**
     * 验证密码6到16位数，是否包含字母数字下划线
     * 
     * @param password
     * @return
     */
    public static final boolean vaildaterPasswordNew(String password) {
        return Pattern.compile(password_reg_new).matcher(password).matches();
    }

    static final String card_reg = "^\\d{14,21}$";

    /**
     * 验证密码6到16位数，是否包含字母数字下划线
     * 
     * @param password
     * @return
     */
    public static final boolean vaildaterCard(String cardId) {
        return Pattern.compile(card_reg).matcher(cardId).matches();
    }
}
