package com.df4j.xcwork.base.exception;


import com.df4j.xcwork.base.constants.ErrorCode;

/**
 * 业务异常
 */
public class BizException extends XcworkException{

    // 异常代码
    private int errorNo;

    public BizException(int errorNo) {
        this.errorNo = errorNo;
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.name());
        this.errorNo = errorCode.getErrorNo();
    }

    public BizException(int errorNo, String message) {
        super(message);
        this.errorNo = errorNo;
    }

    public BizException(int errorNo, String message, Throwable cause) {
        super(message, cause);
        this.errorNo = errorNo;
    }

    public BizException(int errorNo, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorNo = errorNo;
    }
}
