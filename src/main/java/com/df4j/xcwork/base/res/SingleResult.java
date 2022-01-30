package com.df4j.xcwork.base.res;

/**
 * 单结果集类型
 * @param <T> 结果对象类型
 */
public class SingleResult<T> extends Result<T> {

    public SingleResult(T result) {
        super(result);
    }

    public SingleResult(int errorNo, String errorInfo, T result) {
        super(errorNo, errorInfo, false, result);
    }

}
