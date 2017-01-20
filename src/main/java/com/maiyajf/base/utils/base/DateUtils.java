/** 
 * DateUtils.java
 * create on 2011-01-01
 * Copyright 2015 todaysteel All Rights Reserved.
 */
package com.maiyajf.base.utils.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.maiyajf.base.constants.NumberConstants;

/**
 * 日期工具类
 * 
 * @since version1.0
 */
public abstract class DateUtils extends org.apache.commons.lang.time.DateUtils {

    /**
     * 返回当前时间
     * 
     * @return 返回当前时间
     */
    public static Date getCurrentDateTime() {
        java.util.Calendar calNow = java.util.Calendar.getInstance();
        java.util.Date dtNow = calNow.getTime();
        return dtNow;
    }

    /**
     * 返回当前时间的数字
     * 
     * @return 返回当前时间的数字
     */
    public static String getCurrentTimeNumber() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
    }

    public static String getCurrentTimeNum() {
        return new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
    }

    /**
     * 当月第一天
     * 
     * @return
     */
    public static String getFirstDay(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        return day_first;
    }

    /**
     * 当月最后一天
     * 
     * @return
     */
    public static String getLastDay(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        return s;
    }

    public static int getCurrentUnixTime() throws Exception {
        long epoch = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/01/1970 00:00:00").getTime()
                / 1000;
        return Integer.parseInt(Long.toString(System.currentTimeMillis() / 1000 - epoch));
    }

    /**
     * @Title: getSubDay
     * @Description: 获得天数（入参日期-当前日期）
     * @return: long
     */
    public static long getSubDay(Date date2) {
        Date d1 = convertToDate(dateParseShortString(date2));
        Date d2 = convertToDate(dateParseShortString(new Date()));
        long day = (d1.getTime() - d2.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * @Title: getSubDays
     * @Description: 获得与当前时间的天数差（当前日期-入参日期）
     * @return: long
     */
    public static long getSubDays(Date date2) {
        Date d1 = convertToDateTime(dateParseString(date2));
        Date d2 = convertToDateTime(dateParseString(new Date()));
        long day = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * @Title: getDoubleSubDays
     * @Description: 获得时间的天数差精确到小数点
     * @return: double
     */
    public static double getDoubleSubDays(Date date1, Date date2) {
        double sub = date2.getTime() - date1.getTime();
        double day = sub / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * @Title: getSubHours
     * @Description: 获得与当前时间的小时差（当前时间-入参时间）
     * @return: long
     */
    public static long getSubHours(Date date2) {
        Date d1 = convertToDateTime(dateParseString(date2));
        Date d2 = convertToDateTime(dateParseString(new Date()));
        long day = (d2.getTime() - d1.getTime()) / (60 * 60 * 1000);
        return day;
    }

    /**
     * 该方法仅用于定期宝计算倒计时使用
     * 
     * @param date2
     * @return
     */
    public static long getSubSecond(Date date2) {
        Date d2 = convertToDateTime(dateParseString(new Date()));
        Date d1 = convertToDateTime(dateParseString(date2));
        long second = (d1.getTime() + (30 * 1000) - d2.getTime()) / 1000;
        return second;
    }

    /**
     * @return 返回今天日期，时间部分为0。例如：2006-4-8 00:00:00
     */
    public static Date getToday() {
        return truncate(new Date(), Calendar.DATE);
    }

    /**
     * @return 返回今天日期，秒部分为0。例如：2006-4-8 23:59:00
     */
    public static Date getTodayM() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        return stringToDate(s.format(new Date()));
    }

    /**
     * @return 返回今天日期，时间部分为23:59:59.999。例如：2006-4-19 23:59:59.999
     */
    public static Date getTodayEnd() {
        return getDayEnd(new Date());
    }

    /**
     * 将字符串转换为日期（不包括时间）
     * 
     * @param dateString "yyyy-MM-dd"格式的日期字符串
     * @return 日期
     */
    public static Date convertToDate(String dateString) {
        try {
            DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);
            return DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 检查字符串是否为日期格式yyyy-MM-dd
     * 
     * @param dateString
     * @return true=是；false=否
     */
    public static boolean checkDateString(String dateString) {
        return (convertToDate(dateString) != null);
    }

    /**
     * 将字符串转换为日期（包括时间）
     * 
     * @param dateString "yyyy-MM-dd HH:mm:ss"格式的日期字符串
     * @return 日期时间
     */
    public static Date convertToDateTime(String dateTimeString) {
        try {
            DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.CHINA);
            return DATE_TIME_FORMAT.parse(dateTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertToTime(String dateTimeString) {
        try {
            DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss", java.util.Locale.CHINA);
            return TIME_FORMAT.parse(dateTimeString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将字符串转换为日期（包括时间）
     * 
     * @param dateString "dd/MM/yyyy HH:mm"格式的日期字符串
     * @return 日期
     */
    public static Date convertSimpleToDateTime(String dateString) {
        try {
            return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 检查字符串是否为日期时间格式yyyy-MM-dd HH:mm:ss
     * 
     * @param dateString
     * @return true=是；false=否
     */
    public static boolean checkDateTimeString(String dateTimeString) {
        return (convertToDateTime(dateTimeString) != null);
    }

    /**
     * 获取月底
     * 
     * @param year 年 4位年度
     * @param month 月 1~12
     * @return 月底的Date对象。例如：2006-3-31 23:59:59.999
     */
    public static Date getMonthEnd(int year, int month) {
        StringBuffer sb = new StringBuffer(10);
        Date date;
        if (month < 12) {
            sb.append(Integer.toString(year));
            sb.append("-");
            sb.append(month + 1);
            sb.append("-1");
            date = convertToDate(sb.toString());
        } else {
            sb.append(Integer.toString(year + 1));
            sb.append("-1-1");
            date = convertToDate(sb.toString());
        }
        date.setTime(date.getTime() - 1);
        return date;
    }

    /**
     * 获取月底
     * 
     * @param when 要计算月底的日期
     * @return 月底的Date对象。例如：2006-3-31 23:59:59.999
     */
    public static Date getMonthEnd(Date when) {
        Assert.notNull(when, "date must not be null !");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return getMonthEnd(year, month);
    }

    /**
     * 日期格式转换为字符串
     * 
     * @param date
     * @return
     */
    public static String dateParseString(Date date) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 日期格式转换为字符串
     * 
     * @param date
     * @return
     */
    public static String dateParseString(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        java.text.DateFormat df = new java.text.SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 日期格式转换为字符串
     * 
     * @param date
     * @return
     */
    public static String dateParseShortString(Date date) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    /**
     * 活动专用
     * 
     * @param per 期次
     * @return
     */
    public static String getFormatDateString(int per) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy年MM月dd日");
        return df.format(DateUtils.addMonths(new Date(), per));
    }

    public static String dateParseShortStringSecend(Date date) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
        return df.format(date);
    }

    public static String dateParseShortStringHour(Date date) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm");
        return df.format(date);
    }

    /**
     * 老带新用了下这个
     * 
     * @return
     */
    public static String getThisTimeStr() {
        java.text.DateFormat df = new java.text.SimpleDateFormat("MM-dd HH:mm");
        return df.format(new Date());
    }

    /**
     * 获取给定日的最后一刻。
     * 
     * @param when 给定日
     * @return 最后一刻。例如：2006-4-19 23:59:59.999
     */
    public static Date getDayEnd(Date when) {
        Date date = truncate(when, Calendar.DATE);
        date = addDays(date, 1);
        date.setTime(date.getTime() - 1);
        return date;
    }

    /**
     * 获取给定日的起始时刻。
     * 
     * @param when 给定日
     * @return 最后一刻。例如：2006-4-19 00:00:00
     */
    public static Date getDayBegin(Date when) {
        Date date = convertToDateTime(dateParseShortString(when) + " 00:00:00");
        return date;
    }

    /**
     * 获取给定日的第一刻。
     * 
     * @param when 给定日
     * @return 最后一刻。例如：2006-4-19 23:59:59.999
     */
    public static Date getDay(Date when) {
        Date date = truncate(when, Calendar.DATE);
        date = addDays(date, -1);
        date.setTime(date.getTime() + 1);
        return date;
    }

    /**
     * 日期加法
     * 
     * @param when 被计算的日期
     * @param field the time field. 在Calendar中定义的常数，例如Calendar.DATE
     * @param amount 加数
     * @return 计算后的日期
     */
    public static Date add(Date when, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(when);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 计算给定的日期加上给定的天数。
     * 
     * @param when 给定的日期
     * @param amount 给定的天数
     * @return 计算后的日期
     */
    public static Date addDays(Date when, int amount) {

        return add(when, Calendar.DAY_OF_YEAR, amount);
    }

    /**
     * 计算给定的日期加上给定的分钟数。
     * 
     * @param when 给定的日期
     * @param amount 给定的分钟数
     * @return 计算后的日期
     */
    public static Date addMinutes(Date when, int amount) {

        return add(when, Calendar.MINUTE, amount);
    }

    /**
     * 计算给定的日期加上给定的秒数。
     * 
     * @param when 给定的日期
     * @param amount 给定的秒数
     * @return 计算后的日期
     */
    public static Date addSeconds(Date when, int amount) {

        return add(when, Calendar.SECOND, amount);
    }

    /**
     * 计算给定的日期加上给定的月数。
     * 
     * @param when 给定的日期
     * @param amount 给定的月数
     * @return 计算后的日期
     */
    public static Date addMonths(Date when, int amount) {
        return add(when, Calendar.MONTH, amount);
    }

    /**
     * 获取当前时段：早上、上午、下午、晚上、凌晨
     * 
     * @return 当前时段
     */
    @SuppressWarnings("deprecation")
    public static String getTimePeriod() {
        String period = "";
        Date now = getCurrentDateTime();
        int hour = now.getHours();
        if (hour >= 0 && hour < 6) {
            period = "凌晨";
        } else if (hour >= 6 && hour < 8) {
            period = "早上";
        } else if (hour >= 8 && hour < 12) {
            period = "上午";
        } else if (hour >= 12 && hour < 18) {
            period = "下午";
        } else if (hour >= 18) {
            period = "晚上";
        }
        return period;
    }

    /**
     * 
     * @param date
     * @param n
     * @return
     */
    public static Date getTimePriTenDay(Date date, int n) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return convertToDateTime(df.format(new Date(date.getTime() + n * 24 * 60 * 60 * 1000)));
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
     * 判断当前日期是否在两个日期之间
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isTodayIn(String startDate, String endDate) {
        Date today = DateUtils.parseDate(DateUtils.dateParseShortString(new Date()));
        Date sDate = DateUtils.parseDate(startDate);
        Date eDate = DateUtils.parseDate(endDate);
        if (today.getTime() >= sDate.getTime() && today.getTime() <= eDate.getTime())
            return true;
        return false;
    }

    /**
     * 日期比较
     * 
     * @param date1 date1>date2 true
     * @param date2
     * @return
     */
    public static boolean dateCompare(Date date1, String date2) {
        Date date1_s = DateUtils.parseDate(DateUtils.dateParseShortString(date1));
        Date date2_s = DateUtils.parseDate(date2);
        if (date1_s.getTime() >= date2_s.getTime())
            return true;
        return false;

    }

    public static boolean dateCompare2(Date date1, String date2) {
        Date date1_s = DateUtils.parseDate(DateUtils.dateParseShortString(date1));
        Date date2_s = DateUtils.parseDate(date2);
        if (date1_s.getTime() > date2_s.getTime())
            return true;
        return false;

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

    public static final Date parseDateByFormat(String dateStr, String format) {

        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 得到具体时间如：几天前；几小时前；几分钟前；几秒钟前
     * 
     * @param time 传入一个Date类型的日期
     * @return 根据当天当时当秒解析出距离当天当时当秒的字符串 String
     */
    public static String getTimeInterval(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Long dateDiff = sdf.parse(sdf.format(new Date())).getTime() - sdf.parse(sdf.format(time)).getTime();

            Long day = dateDiff / (24 * 60 * 60 * 1000);

            if (day > 0) {
                return day + "天前";
            }

            Long hour = dateDiff / (60 * 60 * 1000);

            if (hour > 0) {
                return hour + "小时前";
            }

            Long minute = dateDiff / (60 * 1000);

            if (minute > 0) {
                return minute + "分钟前";
            }

            Long second = dateDiff / 1000;

            return second + "秒前";
        } catch (Exception e) {
            e.printStackTrace();
            return "未知";
        }
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
     * 
     * 
     * @return String
     */
    public static String getPzStartDate() {

        Date d = DateUtils.addDays(new Date(), 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(d);
    }

    /**
     * 
     * 
     * @return String
     */
    public static String getPzEndDate(int per) {
        Date d = DateUtils.add(DateUtils.addDays(new Date(), 1), Calendar.MONTH, per);
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(d);
    }

    /**
     * 
     * @return String yyyy年MM月dd日
     */
    public static String getPzDate() {
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        return df.format(d);
    }

    /**
     * 
     * @return String yyyy年MM月dd日HH时mm分
     */
    public static String getPzDateTime() {
        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        return df.format(d);
    }

    /**
     * 比较两日期(字符)的大小,日期格式为yyMMdd.
     * 
     * @param beginDate
     * @param endDate
     * @return boolean
     */
    public static final boolean compareDate(String beginDate, String endDate) {

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
     * 系统日期减去传入日期得到天数
     * 
     * @param date1 传入日期
     * @return 天数 long
     */
    public static long sub(Date date1) {
        Date d2 = convertToDate(dateParseShortString(new Date()));
        Date d1 = convertToDate(dateParseShortString(date1));
        long day = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 系统日期减去传入日期得到秒数
     * 
     * @param date1 传入日期
     * @return 天数 long
     */
    public static long subSeconds(Date date1) {
        return Math.abs((date1.getTime() - new Date().getTime()) / 1000);
    }

    /**
     * 两个日期相减得到天数
     * 
     * @param date1 传入日期
     * @param date2 传入日期
     * @return 天数 long
     */
    public static long sub(Date date1, Date date2) {
        Date d1 = convertToDate(dateParseShortString(date1));
        Date d2 = convertToDate(dateParseShortString(date2));
        long day = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 日期相减得到年数
     * 
     * @param date1 相减的日期
     * @return
     */
    public static long subDateGetYear(Date date1) {
        Date d2 = convertToDate(dateParseShortString(new Date()));
        Date d1 = convertToDate(dateParseShortString(date1));
        long day = (d2.getYear() - d1.getYear());
        return day + 1;
    }

    /**
     * 计算两个日期间的月份
     * 
     * @param date1 起始日期
     * @param date2结束日期
     * @return
     */
    public static long subDateGetMonth(Date date1, Date date2) {
        DateCalculate culate = DateCalculate.calculate(date1, date2);
        return culate.getDifferenceOfMonths();
    }

    /**
     * 计算两个日期间的月份,不满一个月按一个月计算
     * 
     * @param date1 起始日期
     * @param date2结束日期
     * @return
     */
    public static long subDateGetFullMonth(Date date1, Date date2) {
        DateCalculate culate = DateCalculate.calculate(date1, date2);
        long month = culate.getDifferenceOfMonths();
        if (culate.getDifferenceOfDays() > 0)
            month++;
        return month;
    }

    /**
     * @Title: timeStamp2Date
     * @Description: Unix时间戳转换成日期
     * @return: Date
     */
    public static Date timeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString);
        Date date = new java.util.Date(timestamp);
        return date;
    }

    /**
     * @Title: getSubMonths
     * @Description: 获取两个日期相减的自然月
     * @return: int
     */
    public static int getSubMonths(Date beginDay, Date endDay) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(beginDay);
        end.setTime(endDay);
        int beginM = start.get(Calendar.MONTH), endM = end.get(Calendar.MONTH);
        return endM - beginM;
    }

    /**
     * 将String 类型的转化为日期格式(20060917 5:20:5)
     * 
     * @param String
     * @return Date
     */
    public static Date stringToDateyyyymmdd(String dateStr) {

        try {
            if (dateStr != null)
                return new SimpleDateFormat("yyyyMMdd").parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能描述: 返回当日剩余秒数<br>
     * 〈功能详细描述〉
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static int getOffsetTime() {
        long leftToday = NumberConstants.DAY_IN_MILL
                - DateUtils.getFragmentInMilliseconds(Calendar.getInstance(), Calendar.DATE);
        return (int) (leftToday / NumberConstants.NUM_1000);
    }
    
    /**
     * 获取日期的一年后的日期
     * 
     * @param Date
     * @return String
     */
    public static Date getDateNextYear(String dateSource) {

    	Calendar curr = new GregorianCalendar();
		try {
			curr.setTime(new SimpleDateFormat("yyyyMMdd").parse(dateSource));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		curr.add(Calendar.YEAR,1);
		Date date=curr.getTime();
		return date;
    }

}
