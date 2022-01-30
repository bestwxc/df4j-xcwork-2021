package com.df4j.xcwork.base.exception;

/**
 * 标准异常类型
 */
public class XcworkException extends RuntimeException {

    public XcworkException() {
    }

    public XcworkException(String message) {
        super(message);
    }

    public XcworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public XcworkException(Throwable cause) {
        super(cause);
    }

    public XcworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * 重新包装异常
     *
     * @param t
     * @return
     */
    public static XcworkException repack(Throwable t) {
        if (t instanceof XcworkException) {
            return (XcworkException) t;
        } else {
            return new XcworkException(t);
        }
    }
}
