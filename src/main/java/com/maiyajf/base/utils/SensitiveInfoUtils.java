package com.maiyajf.base.utils;

import org.apache.commons.lang.StringUtils;

/**
 * 用户敏感信息隐位工具类
 *
 * @author hf
 * @ClassName: SensitiveInfoUtils
 * @Description: TODO
 * @date 2016年9月14日 上午11:32:49
 */
public class SensitiveInfoUtils {

    /**
     * 姓名隐藏    保留最后一个字   如：赵凯  -》  *凯
     *
     * @param userName
     * @return
     */
    public static String maskUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            return "";
        }
        String name = StringUtils.right(userName, 1);
        return StringUtils.leftPad(name, StringUtils.length(userName), "*");
    }

    /**
     * 身份证号隐藏后4位
     *
     * @param idCardNum 共计18位或者15位
     * @return
     */
    public static String maskIdCard(String idCardNum) {
        if (StringUtils.isBlank(idCardNum)) {
            return "";
        }
        return StringUtils.left(idCardNum, StringUtils.length(idCardNum) - 4).concat("****");
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     *
     * @param num
     * @return
     */
    public static String maskFixedPhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*");
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     *
     * @param num
     * @return
     */
    public static String maskMobilePhone(String num) {
        if (StringUtils.isBlank(num)) {
            return "";
        }
        return StringUtils.left(num, 3).concat(
                StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"),
                        "***"));
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****>
     *
     * @param address
     * @param sensitiveSize 敏感信息长度
     * @return
     */
    public static String maskAddress(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        }
        int length = StringUtils.length(address);
        return StringUtils.rightPad(StringUtils.left(address, length - sensitiveSize), length, "*");
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1)
            return email;
        else
            return StringUtils.rightPad(StringUtils.left(email, 1), index, "*").concat(
                    StringUtils.mid(email, index, StringUtils.length(email)));
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     *
     * @param cardNum
     * @return
     */
    public static String maskBankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        cardNum = StringUtils.trim(cardNum);
        return StringUtils
                .left(cardNum, 6)
                .concat(StringUtils.removeStart(
                        StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "******"));
    }

    /**
     * [公司开户银行联号] 公司开户银行联行号,显示前两位，其他用星号隐藏，每位1个星号<例子:12********>
     *
     * @param code
     * @return
     */
    public static String maskCnapsCode(String code) {
        if (StringUtils.isBlank(code)) {
            return "";
        }
        return StringUtils.rightPad(StringUtils.left(code, 2), StringUtils.length(code), "*");
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.left("aaa", -10) + "222");
        System.out.println(SensitiveInfoUtils.maskUserName("赵凯的"));
        System.out.println(SensitiveInfoUtils.maskUserName("赵凯"));
        System.out.println(SensitiveInfoUtils.maskUserName("罗纳尔多"));

        System.out.println(SensitiveInfoUtils.maskIdCard("320829198909120919"));
        System.out.println(SensitiveInfoUtils.maskIdCard("320829198909120"));

        System.out.println(SensitiveInfoUtils.maskMobilePhone("18812345678"));

        System.out.println(SensitiveInfoUtils.maskBankCard("6214830258746951 "));

        System.out.println(SensitiveInfoUtils.maskBankCard("6214830258746951 "));
    }
}
