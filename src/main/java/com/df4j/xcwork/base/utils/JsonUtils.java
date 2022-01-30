package com.df4j.xcwork.base.utils;

import com.df4j.xcwork.base.exception.XcworkException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json操作工具类
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将对象转换为json字符串
     *
     * @param object
     * @return
     */
    public static String stringfy(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }

    /**
     * 将json字符串转换为指定对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw XcworkException.repack(e);
        }
    }
}
