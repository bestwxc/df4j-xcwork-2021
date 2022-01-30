package com.df4j.xcwork.base.exception;

/**
 * 参数不能为空抛出的异常
 */
public class NullParameterException extends InvalidParameterException {

    public NullParameterException(String fieldName) {
        super(fieldName, "Parameter should not be null.");
    }
}
