package com.df4j.xcwork.base.utils;

import com.df4j.xcwork.base.exception.XcworkException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {

    // 日期
    public static String DATE = "yyyy-MM-dd";
    // 时间
    public static String TIME_PATTERN = "HH:mm:ss";
    // 日期时间
    public static String DATETIME = "yyyy-MM-dd HH:mm:ss";
    // 日期数字
    public static String DATE_NUM_PATTERN = "yyyyMMdd";
    // 时间数字
    public static String TIME_NUM_PATTERN = "HHmmss";
    // 日期时间数字
    public static String DATETIME_NUM_PATTERN = "yyyyMMddHHmmss";

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 解析日期字符串
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(str);
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 格式化当前字符串
     *
     * @param pattern
     * @return
     */
    public static String now(String pattern) {
        Date date = new Date();
        return format(date, pattern);
    }
}
