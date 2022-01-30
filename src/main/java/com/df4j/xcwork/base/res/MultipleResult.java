package com.df4j.xcwork.base.res;

import java.util.List;

/**
 * 多结果集类型
 * @param <T> 列表元素类型
 */
public class MultipleResult<T> extends Result<List<T>> {

    // 是否是分页结果集
    private boolean page = false;

    public MultipleResult(List<T> result) {
        super(true, result);
    }

    public MultipleResult(int errorNo, String errorInfo, List<T> result) {
        super(errorNo, errorInfo, true, result);
    }

    protected MultipleResult(List<T> result, boolean page) {
        super(true, result);
        this.page = page;
    }

    protected MultipleResult(int errorNo, String errorInfo, List<T> result, boolean page) {
        super(errorNo, errorInfo, true, result);
        this.page = page;
    }
}
