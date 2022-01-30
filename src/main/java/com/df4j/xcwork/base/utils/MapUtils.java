package com.df4j.xcwork.base.utils;

import com.df4j.xcwork.base.exception.NullParameterException;
import com.df4j.xcwork.base.exception.XcworkException;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 从Map获取参数的工具类
 */
public class MapUtils {

    /**
     * 从Map中获取String
     *
     * @param map
     * @param fieldName
     * @param defaultValue
     * @return
     */
    public static String getString(Map map, String fieldName, String defaultValue) {
        Object obj = map.get(fieldName);
        if (obj != null) {
            String strObj = String.valueOf(obj);
            if ("null".equalsIgnoreCase(strObj)) {
                obj = null;
            }
            if (!StringUtils.hasText(strObj)) {
                obj = null;
            }
            if (obj != null) {
                return strObj;
            }
        }
        return defaultValue;
    }

    /**
     * 从Map中获取String
     *
     * @param map
     * @param fieldName
     * @return
     */
    public static String getString(Map map, String fieldName) {
        String str = getString(map, fieldName, null);
        if (str == null) {
            throw new NullParameterException(fieldName);
        }
        return str;
    }


    /**
     * 从Map中获取指定的
     *
     * @param map
     * @param fieldName
     * @param defaultValue
     * @param function
     * @param <R>
     * @return
     */
    public static <R> R get(Map map, String fieldName, R defaultValue, Function<String, R> function) {
        String strValue = getString(map, fieldName, null);
        if (strValue == null) {
            return defaultValue;
        } else {
            try {
                return function.apply(strValue);
            } catch (Exception e) {
                throw XcworkException.repack(e);
            }
        }
    }

    /**
     * 判断结果，如果为空，则抛出异常
     *
     * @param fieldName
     * @param supplier
     * @param <R>
     * @return
     */
    public static <R> R get(String fieldName, Supplier<R> supplier) {
        R res = supplier.get();
        if (res == null) {
            throw new NullParameterException(fieldName);
        }
        return res;
    }

    /**
     * 从Map 中获取integer
     *
     * @param map
     * @param fieldName
     * @param defaultValue
     * @return
     */
    public static Integer getInteger(Map map, String fieldName, Integer defaultValue) {
        return get(map, fieldName, defaultValue, Integer::valueOf);
    }

    /**
     * 从Map 中获取integer
     *
     * @param map
     * @param fieldName
     * @return
     */
    public static Integer getInteger(Map map, String fieldName) {
        return get(fieldName, () -> getInteger(map, fieldName, null));
    }

    /**
     * 从Map中获取Long
     *
     * @param map
     * @param fieldName
     * @param defaultValue
     * @return
     */
    public static Long getLong(Map map, String fieldName, Long defaultValue) {
        return get(map, fieldName, defaultValue, Long::valueOf);
    }

    /**
     * 从Map中获取Long
     *
     * @param map
     * @param fieldName
     * @return
     */
    public static Long getLong(Map map, String fieldName) {
        return get(fieldName, () -> getLong(map, fieldName, null));
    }

    /**
     * 从Map中获取Boolean
     *
     * @param map
     * @param fieldName
     * @param defaultValue
     * @return
     */
    public static Boolean getBoolean(Map map, String fieldName, Boolean defaultValue) {
        return get(map, fieldName, defaultValue, Boolean::valueOf);
    }

    /**
     * 从Map中获取Boolean
     *
     * @param map
     * @param fieldName
     * @return
     */
    public static Boolean getBoolean(Map map, String fieldName) {
        return get(fieldName, () -> getBoolean(map, fieldName, null));
    }

    /**
     * 从Map中获取Date参数
     *
     * @param map
     * @param fieldName
     * @param pattern
     * @param defaultValue
     * @return
     */
    public static Date getDate(Map map, String fieldName, String pattern, Date defaultValue) {
        return get(map, fieldName, defaultValue, x -> DateUtils.parse(x, pattern));
    }

    /**
     * 从Map中获取Date参数
     *
     * @param map
     * @param fieldName
     * @param pattern
     * @return
     */
    public static Date getDate(Map map, String fieldName, String pattern) {
        return get(fieldName, () -> getDate(map, fieldName, pattern, null));
    }

    /**
     * 从Map中获取BigDecimal参数
     *
     * @param map
     * @param fieldName
     * @param defaultValue
     * @return
     */
    public static BigDecimal getBigDecimal(Map map, String fieldName, BigDecimal defaultValue) {
        return get(map, fieldName, defaultValue, BigDecimal::new);
    }

    /**
     * 从Map中获取BigDecimal参数
     * @param map
     * @param fieldName
     * @return
     */
    public static BigDecimal getBigDecimal(Map map, String fieldName) {
        return get(fieldName, () -> getBigDecimal(map, fieldName, null));
    }
}
