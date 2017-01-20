/** 
 * StringHelper.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.maiyajf.base.utils.log.DebugLogger;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 字符串值格式化工具类
 * 
 * @since version1.0
 */
public class StringHelper {

    public StringHelper() {
        super();
    }

    private static Object initLock = new Object(); // 用于初始化一个生成随机数的空间对象

    private static MessageDigest digest = null; // 用于创建MD5加密对象

    private static Random randGen = null; // 随机数对象

    private static char numbersAndLetters[] = null; // 随机数生成的字符范围

    /**
     * 字符串转为时间类型
     * 
     * @param dateStr <String>
     * @return Date
     */
    public static final Date parseDate(String dateStr) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把String[] 转成 List<String[]>
     * 
     * @param list
     * @param pageSize ,每个数组的大小
     * @return
     */
    public static List<String[]> splitArray(String[] strs, int pageSize) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < strs.length; i++) {
            list.add(strs[i]);
        }

        List<String[]> sList = new ArrayList<String[]>();
        int count = list.size();
        int size;
        if (count % pageSize == 0) {
            size = count / pageSize;
        } else {
            size = count / pageSize + 1;
        }
        for (int i = 0; i < size; i++) {
            List<String> tempList;
            if (i == size - 1) {
                tempList = list.subList(i * pageSize, list.size());
            } else {
                tempList = list.subList(i * pageSize, (i + 1) * pageSize);
            }
            String[] mobiles = new String[tempList.size()];
            for (int j = 0; j < mobiles.length; j++) {
                mobiles[j] = tempList.get(j);
            }
            sList.add(mobiles);
        }
        return sList;
    }

    /**
     * 对指定的字符串做替换功能(大小写区别)
     * 
     * @param s <String>(指定的字符串),s1<String>(要替换的字符串),s2<String>(新的字符串)
     * @return String
     */
    public static final String replace(String s, String s1, String s2) {

        if (s == null)
            return null;
        int i = 0;
        if ((i = s.indexOf(s1, i)) >= 0) {
            char ac[] = s.toCharArray();
            char ac1[] = s2.toCharArray();
            int j = s1.length();
            StringBuffer stringbuffer = new StringBuffer(ac.length);
            stringbuffer.append(ac, 0, i).append(ac1);
            i += j;
            int k;
            for (k = i; (i = s.indexOf(s1, i)) > 0; k = i) {
                stringbuffer.append(ac, k, i - k).append(ac1);
                i += j;
            }

            stringbuffer.append(ac, k, ac.length - k);
            return stringbuffer.toString();
        } else {
            return s;
        }
    }

    /**
     * 对指定的字符串做替换功能(大小写不区别)
     * 
     * @param s <String>(指定的字符串),s1<String>(要替换的字符串),s2<String>(新的字符串)
     * @return String
     */
    public static final String replaceIgnoreCase(String s, String s1, String s2) {

        if (s == null)
            return null;
        String s3 = s.toLowerCase();
        String s4 = s1.toLowerCase();
        int i = 0;
        if ((i = s3.indexOf(s4, i)) >= 0) {
            char ac[] = s.toCharArray();
            char ac1[] = s2.toCharArray();
            int j = s1.length();
            StringBuffer stringbuffer = new StringBuffer(ac.length);
            stringbuffer.append(ac, 0, i).append(ac1);
            i += j;
            int k;
            for (k = i; (i = s3.indexOf(s4, i)) > 0; k = i) {
                stringbuffer.append(ac, k, i - k).append(ac1);
                i += j;
            }

            stringbuffer.append(ac, k, ac.length - k);
            return stringbuffer.toString();
        } else {
            return s;
        }
    }

    /**
     * 将字符中的"<"和">"替换为html中的标签格式"&lt;"和"&gt;"
     * 
     * @param s <String>(指定的字符串)
     * @return String
     */
    public static final String escapeHTMLTags(String s) {

        if (s == null || s.length() == 0)
            return s;
        StringBuffer stringbuffer = new StringBuffer(s.length());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<')
                stringbuffer.append("&lt;");
            else if (c == '>')
                stringbuffer.append("&gt;");
            else
                stringbuffer.append(c);
        }

        return stringbuffer.toString();
    }

    /**
     * 将字符中转换为数字类型(int)
     * 
     * @param str <String>(指定的字符串)
     * @return int
     */
    public static final int toNumber(final String str) {

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 将字符中转换为数字类型(int),可以设置缺省值
     * 
     * @param str <String>(指定的字符串)，defaultValue<int>(缺省值)
     * @return int
     */
    public static final int toNumber(final String str, int defaultValue) {

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 格式化小数对象
    private static DecimalFormat df = new DecimalFormat("##,###.00");

    // 格式化整数为默认格式对象
    private static NumberFormat nf = NumberFormat.getInstance();

    /**
     * 格式化浮点型的值的小数点后为两位小数
     * 
     * @param d <double>（浮点型值）
     * @return String（格式化后的字符串）
     */
    public static String formatDouble(double d) {

        return df.format(d);
    }

    /**
     * 格式化整数为默认格式
     * 
     * @param d <int>（整型值）
     * @return String（格式化后的字符串）
     */
    public static String formatInt(int d) {

        return nf.format(d);
    }

    /**
     * 格式化整数为默认格式
     * 
     * @param d <double>（浮点型值）
     * @return String（格式化后的字符串）
     */
    public static String formatInt(double d) {

        return nf.format(d);
    }

    /**
     * 将字符中转换为小写的数组
     * 
     * @param s <String>(指定的字符串)
     * @return String[]
     */
    public static final String[] toLowerCaseWordArray(String s) {

        if (s == null || s.length() == 0)
            return new String[0];
        StringTokenizer stringtokenizer = new StringTokenizer(s, " ,\r\n.:/\\+");
        String as[] = new String[stringtokenizer.countTokens()];
        for (int i = 0; i < as.length; i++)
            as[i] = stringtokenizer.nextToken().toLowerCase();

        return as;
    }

    /**
     * 天安保险流水号生成
     * 
     * @param i
     * 
     * @return
     */
    public static final String TianAnBXWaterNo() {
        String currDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return currDate + randomNum(5);
    }

    /**
     * 读取随机数字
     * 
     * @param i <int>(取值个数)
     * @return String
     */
    public static final String randomString(int i) {

        if (i < 1)
            return null;
        if (randGen == null)
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                            .toCharArray();
                }
            }
        char ac[] = new char[i];
        for (int j = 0; j < ac.length; j++)
            ac[j] = numbersAndLetters[randGen.nextInt(71)];

        return new String(ac);
    }

    /**
     * 读取随机数字
     * 
     * @param i <int>(取值个数)
     * @return String
     */
    public static final String randomNum(int i) {
        if (i < 1)
            return null;
        if (randGen == null)
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    numbersAndLetters = "0123456789".toCharArray();
                }
            }
        char ac[] = new char[i];
        for (int j = 0; j < ac.length; j++)
            ac[j] = numbersAndLetters[randGen.nextInt(9)];
        return new String(ac);
    }

    /**
     * 读取日期时间及随机数组成的字符串
     * 
     * @return String
     */
    public static String getDateTimeRandomStr() {
        StringBuffer str = new StringBuffer();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());

        str.append(calendar.get(Calendar.YEAR));
        str.append(((String.valueOf(calendar.get(Calendar.MONTH)).length() < 2)
                ? "0" + String.valueOf(calendar.get(Calendar.MONTH)) : String.valueOf(calendar.get(Calendar.MONTH))));
        str.append(((String.valueOf(calendar.get(Calendar.DATE)).length() < 2)
                ? "0" + String.valueOf(calendar.get(Calendar.DATE)) : String.valueOf(calendar.get(Calendar.DATE))));
        str.append(((String.valueOf(calendar.get(Calendar.HOUR)).length() < 2)
                ? "0" + String.valueOf(calendar.get(Calendar.HOUR)) : String.valueOf(calendar.get(Calendar.HOUR))));
        str.append(((String.valueOf(calendar.get(Calendar.MINUTE)).length() < 2)
                ? "0" + String.valueOf(calendar.get(Calendar.MINUTE)) : String.valueOf(calendar.get(Calendar.MINUTE))));
        str.append(((String.valueOf(calendar.get(Calendar.SECOND)).length() < 2)
                ? "0" + String.valueOf(calendar.get(Calendar.SECOND)) : String.valueOf(calendar.get(Calendar.SECOND))));
        str.append((new Random()).nextFloat());
        return str.toString();
    }

    /**
     * 格式化日期时间
     * 
     * @param datetime <String>
     * @param flag <int>（返回值标识：1是返回日期；2是返回时间）
     * @return String
     */
    public static String FormatDateTime(String datetime, int flag) {

        String result = null;
        String temp[] = null;

        if (datetime.equals(""))
            return null;
        if (flag == 0)
            flag = 1;

        temp = datetime.split(" ");

        if (flag == 1) // 返回日期
        {
            if (temp.length > 0)
                result = temp[0];
            else
                result = null;

        } else if (flag == 2) // 返回时间
        {
            if (temp.length > 2)
                result = temp[1];
            else
                result = null;
        }

        return result;
    }

    /**
     * 由当前时间返回yyyy-mm-dd格式的日期字符串
     * 
     * @return String
     */
    public static String getStringOfTodayDate() {

        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(d);
    }

    /**
     * 由当前时间返回yyyy-MM-dd hh:mm:ss格式的日期时间字符串
     * 
     * @return String
     */
    public static String getStringOfTodayDatetime() {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    /**
     * 获取当天日期（如：2006年09月05日）
     * 
     * @return String
     */

    public static final String getStringOfChinaTodayDate() {

        String date = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        String[] datevar = date.split("-");
        date = datevar[0] + "年" + datevar[1] + "月" + datevar[2] + "日";
        return date;
    }

    /**
     * 将String 类型的转化为日期格式(2006-09-17 5:20:5)
     * 
     * @param String
     * @return Date
     */
    public static Date stringToDate(String dateStr) {

        try {
            if (dateStr != null)
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Date对象类型转化为日期(2006-09-17 5:20:5)的字符串
     * 
     * @param Date
     * @return String
     */
    public static String dateToString(Date date) {

        try {
            if (date != null)
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Date对象类型转化为指定的格式字符串
     * 
     * @param date <Date>日期
     * @param format <String>格式
     * @return String
     */
    public static String dateToString(Date date, String format) {

        try {
            if (date != null)
                return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取两个日期间的间隔天数
     * 
     * @param date1 <String>(第一个日期),date2<String>(第二个日期)
     * @return int
     */
    public static int betweenBothDay(String date1, String date2) {

        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            return betweenBothDay(dateFormat.parse(date1), dateFormat.parse(date2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 获取两个日期间的间隔天数
     * 
     * @param date1 <String>(第一个日期),date2<String>(第二个日期)
     * @return int
     */
    public static int betweenBothDay(Date date1, Date date2) {
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            date1 = dateFormat.parse(dateFormat.format(date1));
            date2 = dateFormat.parse(dateFormat.format(date2));
        } catch (ParseException e) {

        }
        int rtn = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
        return rtn;

    }

    /**
     * 比较时间大小
     * 
     * @param str1 ,str2(格式必须为：00:00:00)
     * @return String
     */

    public static final String compareDateTime(String str1, String str2) {
        String arrtime1[] = str1.split(" ");
        String arrtime2[] = str2.split(" ");

        try {
            String arrtimetemp1[] = arrtime1[1].split(":");
            String arrtimetemp2[] = arrtime2[1].split(":");

            long strtemp1 = Integer.parseInt(arrtimetemp1[0] + arrtimetemp1[1] + arrtimetemp1[2]);
            long strtemp2 = Integer.parseInt(arrtimetemp2[0] + arrtimetemp2[1] + arrtimetemp1[2]);

            if (strtemp1 > strtemp2) {
                return ">";
            } else {
                return "<";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取两日期间为周末的日期
     * 
     * @param year <int>,startMonth<int>,endMonth<int>
     * @return List
     */
    public static List getWeeks(int year, int startMonth, int endMonth) {
        Calendar startCalendar = new GregorianCalendar(Locale.CHINESE);
        Calendar endCalendar = new GregorianCalendar(Locale.CHINESE);
        List dateList = new ArrayList();
        startCalendar.clear();
        endCalendar.clear();
        startCalendar.set(year, startMonth, 1);
        endCalendar.set(year, endMonth, 1);
        long time = System.currentTimeMillis();
        while (startCalendar.get(Calendar.YEAR) == year && endCalendar.get(Calendar.YEAR) == year
                && startCalendar.get(Calendar.MONTH) >= startMonth && startCalendar.get(Calendar.MONTH) <= endMonth) {
            if (startCalendar.get(Calendar.YEAR) == year && endCalendar.get(Calendar.YEAR) == year
                    && startCalendar.get(Calendar.MONTH) >= startMonth
                    && startCalendar.get(Calendar.MONTH) <= endMonth) {
                switch (startCalendar.get(Calendar.DAY_OF_WEEK)) {
                    case Calendar.SATURDAY:
                        dateList.add(year + "-" + (startCalendar.get(Calendar.MONTH) + 1) + "-"
                                + startCalendar.get(Calendar.DAY_OF_MONTH));
                        break;
                    case Calendar.SUNDAY:
                        dateList.add(year + "-" + (startCalendar.get(Calendar.MONTH) + 1) + "-"
                                + startCalendar.get(Calendar.DAY_OF_MONTH));
                        break;
                }
            }
            startCalendar.add(Calendar.DATE, 1);
        }

        return dateList;
    }

    /**
     * 判断某日期是否为周末
     * 
     * @param year <int>,startMonth<int>,endMonth<int>
     * @return Boolean true(周末返回true),周一到周五放回false
     */
    public boolean isWeeks(String theDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = simpleDateFormat.parse(theDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SATURDAY: {
                    return true;// 周六返回 true
                }
                case Calendar.SUNDAY: {
                    return true;// 周日返回 true
                }
                default: {
                    return false;// 周一到周五返回 false
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断某日期是否为周末
     * 
     * @param startDateStr <String>(开始日期),startMonth<String>(结束日期)
     * @return List 周末日期集合
     */
    public static List getWeeksInBetweenDay(String startDateStr, String endDateStr) {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = simpleDateFormat.parse(startDateStr);
            Date endDate = simpleDateFormat.parse(endDateStr);

            Calendar startCalendar = new GregorianCalendar(Locale.CHINESE);
            Calendar endCalendar = new GregorianCalendar(Locale.CHINESE);

            List dateList = new ArrayList();

            startCalendar.clear();
            endCalendar.clear();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);

            while (startCalendar.getTime().before(endCalendar.getTime())) {
                switch (startCalendar.get(Calendar.DAY_OF_WEEK)) {
                    case Calendar.SATURDAY:
                        dateList.add(startCalendar.get(Calendar.YEAR) + "-" + (startCalendar.get(Calendar.MONTH) + 1)
                                + "-" + startCalendar.get(Calendar.DAY_OF_MONTH));
                        break;
                    case Calendar.SUNDAY:
                        dateList.add(startCalendar.get(Calendar.YEAR) + "-" + (startCalendar.get(Calendar.MONTH) + 1)
                                + "-" + startCalendar.get(Calendar.DAY_OF_MONTH));
                        break;
                }
                startCalendar.add(Calendar.DATE, 1);
            }
            return dateList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList();
    }

    /**
     * 由当前时间返回先前或后退几天 的 yyyy-mm-dd格式的日期字符串
     * 
     * @param addOrLessDate <int>如果为整数则时间向前,为负数时间向后退
     * @return String
     */
    public static String getAheadOrBackOffDate(String startDateStr, int addOrLessDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = simpleDateFormat.parse(startDateStr);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            startCalendar.add(startCalendar.DATE, addOrLessDate);

            String returnDate = simpleDateFormat.format(simpleDateFormat.parse(startCalendar.get(Calendar.YEAR) + "-"
                    + (startCalendar.get(Calendar.MONTH) + 1) + "-" + startCalendar.get(Calendar.DAY_OF_MONTH)));
            return returnDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 由当前时间返回先前或后退几月 的 yyyy-mm-dd格式的日期字符串
     * 
     * @param addOrLessDate <int>如果为整数则时间向前,为负数时间向后退
     * @return String
     */
    public static String getAheadOrBackOffMonth(String startDateStr, int addOrLessDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = simpleDateFormat.parse(startDateStr);
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(startDate);
            startCalendar.add(startCalendar.MONTH, addOrLessDate);

            String returnDate = simpleDateFormat.format(simpleDateFormat.parse(startCalendar.get(Calendar.YEAR) + "-"
                    + (startCalendar.get(Calendar.MONTH) + 1) + "-" + startCalendar.get(Calendar.DAY_OF_MONTH)));
            return returnDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将gb格式的字符转换为utf-8格式字符
     * 
     * @param src <String>
     * @return String
     */
    public static String gbToUtf8(String src) {

        byte b[] = src.getBytes();
        char c[] = new char[b.length];
        for (int i = 0; i < b.length; i++)
            c[i] = (char) (b[i] & 0xff);
        return new String(c);
    }

    /**
     * 将字符串转换为GBK格式字符串
     * 
     * @param s <String>
     * @return String
     */
    public static final String ConvertGBK(String s) {

        String s1 = "";
        String s2;
        if (s == null || s.trim().length() == 0)
            return "";
        try {
            s1 = new String(s.getBytes("ISO-8859-1"), "GBK");
        } catch (Exception exception) {
            DebugLogger.debug("ConvertGBK():ex=" + exception.toString());
        }
        return s1;
    }

    /**
     * 将null转换为""空字符串
     * 
     * @param src <String>
     * @return String
     */
    public static final String NULLToSpace(String s) {

        if (s == null)
            return "";
        else
            return s.trim();
    }

    /**
     * 验证字符串是否为空
     * 
     * @param str
     * @return boolean
     */
    public final static boolean isEmpty(String str) {

        if (str == null || str.trim().length() == 0)
            return true;
        else
            return false;
    }

    /**
     * 验证是否为整数
     * 
     * @param str
     * @return boolean
     */
    public final boolean isNumber(String str) {

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * 验证是否为Float类型
     * 
     * @param str
     * @return boolean
     */
    public final boolean isFloat(String str) {

        try {
            Float.parseFloat(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证是否为Double类型
     * 
     * @param str
     * @return boolean
     */
    public final boolean isDouble(String str) {

        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 验证是否为email
     * 
     * @param str
     * @return boolean
     */
    public final boolean isEmail(String str) {
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
     * 验证是否为日期字符串(格式:yyMMdd)
     * 
     * @param str
     * @return boolean
     */
    public static boolean isDate(String str) {

        boolean bValid = true;
        boolean bl = false;

        if (str != null) {
            try {
                if (str.length() == 10) {
                    for (int i = 0; i < 10; i++) {
                        String sTem = Integer.toString(i);
                        if (str.endsWith(sTem)) {
                            bl = true;
                        }
                    }

                    if (bl) {
                        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
                        formatter.setLenient(false);
                        formatter.parse(str);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } catch (ParseException e) {
                bValid = false;
            }
        } else {
            bValid = false;
        }

        return bValid;
    }

    /**
     * 比较两日期(字符)的大小,日期格式为yyMMdd.
     * 
     * @param beginDate
     * @param endDate
     * @return boolean
     */
    public final boolean compareDate(String beginDate, String endDate) {

        long begin = Integer
                .parseInt(beginDate.substring(0, 4) + beginDate.substring(5, 7) + beginDate.substring(8, 10));
        long end = Integer.parseInt(endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8, 10));
        if (begin > end) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * todaysteel字符串加密算法
     * 
     * @param sConst 原字符串
     * @param key 密钥
     * @return String
     */
    public static String encryptWord(String sConst, int key) {

        char r[] = new char[sConst.length()];
        char a[] = sConst.toCharArray();

        for (int i = 0; i < sConst.length(); i++) {
            r[i] = (char) (a[i] ^ key >> 8);
            key = (byte) r[i] + key;
        }
        return new String(r);
    }

    /**
     * 采用MD5加密算法，加密字符串
     * 
     * @param s <String>（被加密源字符串）
     * @return String（加密后的字符串）
     */
    public static final synchronized String hash(String s) {

        if (digest == null)
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
                System.err.println("Failed to load the MD5 MessageDigest. Jive will be unable to function normally.");
                nosuchalgorithmexception.printStackTrace();
            }
        digest.update(s.getBytes());
        return toHex(digest.digest());
    }

    /**
     * 将byte类型的数据转为字符串
     * 
     * @param abyte0 []<byte>
     * @return String（转换后的字符串）
     */
    public static final String toHex(byte abyte0[]) {

        StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
        for (int i = 0; i < abyte0.length; i++) {
            if ((abyte0[i] & 0xff) < 16)
                stringbuffer.append("0");
            stringbuffer.append(Long.toString(abyte0[i] & 0xff, 16));
        }

        return stringbuffer.toString();
    }

    /**
     * 获取字符串内的所有汉字的汉语拼音并大写每个字的首字母 注意：传入参数必须全部为中文，数字或者字母会被忽略 汉字拼音的首字母大写，不管多音字，取第一个
     * 
     * @param chinese
     * @return
     * @author liuwei
     */
    private static String getFullSpellStr(String chinese) {
        if (chinese == null) {
            return null;
        }
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调
        format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chinese.length(); i++) {
                String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
                if (array == null || array.length == 0) {
                    continue;
                }
                String s = array[0];// 不管多音字,只取第一个
                String pinyin = String.valueOf(s.charAt(0)).toUpperCase().concat(s.substring(1));
                sb.append(pinyin);
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取字符串内的所有汉字的汉语拼音并大写或者小写 注意：isUpperCase 为true 全部大写，false全部小写
     * 
     * @param chinese <String>
     * @param isUpperCase <boolean>
     * @return
     * @author liuwei
     */
    public static String getFullSpell(final String chinese, final boolean isUpperCase) {
        String str = null;
        if (isUpperCase)
            str = getFullSpellStr(chinese).toUpperCase();
        else
            str = getFullSpellStr(chinese).toLowerCase();
        return str;
    }

    /**
     * 获取字符串内的所有汉字的汉语拼音并大写每个字的首字母 注意：传入参数必须全部为中文，数字或者字母会被忽略 汉字拼音的首字母大写，不管多音字，取第一个
     * 
     * @param chinese
     * @return
     * @author liuwei
     */
    public static String getFullSpell(final String chinese) {
        return getFullSpellStr(chinese);
    }

    /**
     * 获取字符串内的所有汉字的汉语拼音的首字母并大写 注意：传入参数必须全部为中文，数字或者字母会被忽略 汉字拼音的首字母并大写，不管多音字，取第一个
     * 
     * @param chinese
     * @return
     * @author liuwei
     */
    public static String getFirstSpell(String chinese) {
        if (chinese == null) {
            return null;
        }
        try {
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调
            format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chinese.length(); i++) {
                String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
                if (array == null || array.length == 0) {
                    continue;
                }
                String s = array[0];// 不管多音字,只取第一个
                String pinyin = String.valueOf(s.charAt(0)).toUpperCase();
                sb.append(pinyin);
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 判断字符串中是否包含中文 boolean
     * 
     * @author liuwei
     */
    public static boolean hasCn(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        } else {
            if (str.getBytes().length > str.length())
                return true;
            else
                return false;
        }
    }

    public static String numToChinese(int number) {
        String[] chinese = new String[] { "", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        return chinese[number];
    }

    /**
     * 格式化收款银行帐号
     * 
     * @param sreceiveaccount 收款帐号字符串(id:name@id:name@)
     * @return
     */
    public static String formatBankAccount(String sreceiveaccount) {
        String bankAccount = "";
        if (StringUtils.isNotBlank(sreceiveaccount)) {
            String[] sreceiveaccounts = sreceiveaccount.substring(0, sreceiveaccount.length() - 1).split("@");
            for (int v = 0; v < sreceiveaccounts.length; v++) {
                String[] temp = sreceiveaccounts[v].split(":");
                bankAccount += temp[1] + "\n";
            }
        }
        return bankAccount;
    }

    /**
     * 取得收款银行帐号的ID
     * 
     * @param serceiveraccount 收款帐号字符串(id:name@id:name@)
     * @return
     */
    public static List<String> getBankAccountId(String serceiveraccount) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(serceiveraccount)) {
            String[] temp = serceiveraccount.split("@");
            if (null != temp && temp.length > 0) {
                for (String tmp : temp) {
                    list.add(tmp.split(":")[0]);
                }
            }
        }
        return list;
    }

    /**
     * 按给定字节数取字串
     * 
     * @param str 原串
     * @startIndex 起始位置
     * @param byteNum 要取的字节数
     * @return
     * @throws Exception
     */
    public static String getSubStr(String str, int startIndex, int byteNum) throws Exception {
        try {
            if (str == null) {
                return "";
            }
            byte[] b = str.getBytes("GBK");
            if (startIndex >= b.length) {
                return "";
            }
            int index = 0;
            int n = 0;// 用于记录取了多少个字节
            for (; n < byteNum && index < b.length; index++) {
                if (b[index] != 0) {
                    n++;
                }
            }

            // 避免出现最后是半个汉字而乱码
            // if (b[index] < 0) {//
            // 说明最后为汉字，这里可以自己定义，如果最后半个汉字不要则index--,如果补全则index++
            // index++;
            // }
            return new String(b, startIndex, index, "GBK");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 根据传入的分割符号和分隔符出现的次数获取需要截取的字节数/字符数，仅仅限于处理字母和数字组合
     * 
     * @param msg , 待处理的字符串
     * @param split , 分隔符
     * @param count , 出现的次数
     * @return
     */
    public static int getBodySplitLength(String msg, String split, int count) {
        int length = 0;
        for (int i = 0; i < count; i++) {
            length += msg.indexOf(split) + 1;
            msg = msg.substring(msg.indexOf(split) + 1);
        }
        return length;
    }

    /**
     * 如果字段为空，则返回空的字符串
     * 
     * @param da
     * @return
     */
    public static String getEmptyIfBlank(String da) {
        return StringUtils.isNotBlank(da) ? da : "";
    }

    /**
     * 获取一个20位随机流水号
     * 
     * @return
     */
    public static String getRandomSerialNumber() {
        String sequence = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
        return sequence + RandomStringUtils.randomNumeric(5);
    }

    /**
     * 判断是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumberAndInteger(String str) {
        Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$|-\\d+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isPositiveNumber(String str) {
        Pattern pattern = Pattern.compile("^\\+?[1-9]\\d*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 获取日期格式为yyMMdd的字符串
     * 
     * @return
     */
    public static String getTodayDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 提供字符数组转换，只针对于数据库字段解析
     * 
     * @param array
     * @return 类似('1','2','3')的结果
     */
    public static String arrayConvertion(String[] array) {
        String singular = "";
        for (String str : array) {
            singular += "'" + str + "',";
        }
        singular = singular.substring(0, singular.lastIndexOf(","));
        return singular;
    }

    public static String spitNumber(int start, int end, String str) {
        return str.substring(0, start) + "**" + str.substring(str.length() - end);
    }

    // 隐藏身份证号
    public static String hideIDNumber(String IDNumber) {
        if (StringUtils.isBlank(IDNumber) || IDNumber.length() <= 10) {
            return IDNumber;
        }
        return IDNumber.substring(0, 6) + "****" + IDNumber.substring(IDNumber.length() - 4);
    }

    // 隐藏卡号
    public static String hideCardNumber(String CardNumber) {
        if (StringUtils.isBlank(CardNumber) || CardNumber.length() <= 8) {
            return CardNumber;
        }
        return CardNumber.substring(0, 4) + "****" + CardNumber.substring(CardNumber.length() - 4);
    }

}