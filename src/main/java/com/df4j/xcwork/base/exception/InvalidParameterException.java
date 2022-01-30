package com.df4j.xcwork.base.exception;

import com.df4j.xcwork.base.constants.ErrorCode;


/**
 * 参数不合法的异常
 */
public class InvalidParameterException extends BizException {

    // 参数key
    private String fieldName;

    // 说明
    private String fieldErrorType;

    public InvalidParameterException(String fieldName, String fieldErrorType) {
        super(ErrorCode.INVALID_PARAMETER);
        this.fieldName = fieldName;
        this.fieldErrorType = fieldErrorType;
    }

    @Override
    public String getMessage() {
        String tpl = "InvalidParameter,[%s][%s]";
        return String.format(tpl, fieldName, fieldErrorType);
    }
}
