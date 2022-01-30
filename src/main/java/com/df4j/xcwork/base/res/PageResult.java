package com.df4j.xcwork.base.res;

import java.util.List;

/**
 * 分页结果集
 *
 * @param <T> 列表元素类型
 */
public class PageResult<T> extends MultipleResult<T> {

    // 结果数量
    private int total;

    // 分页编号
    private int pageNum;

    // 分页大小
    private int pageSize;

    public PageResult(List<T> result, int total, int pageNum, int pageSize) {
        super(result, true);
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageResult(int errorNo, String errorInfo, List<T> result, int total, int pageNum, int pageSize) {
        super(errorNo, errorInfo, result, true);
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 是否还有剩余分页
     * @return
     */
    boolean hasMore() {
        return pageNum * pageSize < total;
    }
}
