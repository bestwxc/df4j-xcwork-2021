package com.df4j.xcwork.web.utils;

import com.df4j.xcwork.base.res.MultipleResult;
import com.df4j.xcwork.base.res.PageResult;
import com.df4j.xcwork.base.res.Result;
import com.df4j.xcwork.base.res.SingleResult;

import java.util.List;

/**
 * 结果集装备工具类
 */
public class ResultUtils {

    /**
     * 组装成功的返回结果集
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T t) {
        if (t instanceof List) {
            return new MultipleResult((List) t);
        }
        return new SingleResult(t);
    }

    /**
     * 组装错误结果
     *
     * @param errorNo
     * @param errorInfo
     * @return
     */
    public static Result error(int errorNo, String errorInfo) {
        return new SingleResult(errorNo, errorInfo, null);
    }

    /**
     * 组装错误结果
     *
     * @param errorNo
     * @param errorInfo
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(int errorNo, String errorInfo, T t) {
        if (t instanceof List) {
            return new MultipleResult(errorNo, errorInfo, (List) t);
        }
        return new SingleResult<>(errorNo, errorInfo, t);
    }

    /**
     * 组装分页结果
     *
     * @param list
     * @param total
     * @param pageNum
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> Result<List<T>> page(List<T> list, int total, int pageNum, int pageSize) {
        return new PageResult(list, total, pageNum, pageSize);
    }
}
