package com.df4j.xcwork.base.res;


/**
 * 标准结果集 默认下与但结果集相同
 * @param <T> 结果对象类型
 */
public class Result<T> {

    // 结果代码
    private int errorNo = 0;

    // 结果信息
    private String errorInfo = "成功";

    // 是否是多结果集
    private boolean multiple = false;

    // 结果
    private T result;

    public Result(T result) {
        this.result = result;
    }

    public Result(int errorNo, String errorInfo, T result) {
        this(errorNo, errorInfo, false, result);
    }

    protected Result(boolean multiple, T result) {
        this.multiple = multiple;
        this.result = result;
    }

    protected Result(int errorNo, String errorInfo, boolean multiple, T result) {
        this.errorNo = errorNo;
        this.errorInfo = errorInfo;
        this.multiple = multiple;
        this.result = result;
    }

    public int getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(int errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
