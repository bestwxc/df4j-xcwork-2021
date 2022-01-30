package com.df4j.xcwork.base.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 常用错误类型
 */
public enum ErrorCode {
    // 成功
    SUCCESS(0),
    // 未捕获的系统异常
    UNCAUGHT_SYSTEM_ERROR(-99),
    // 未捕获的运行异常
    UNCAUGHT_RUNTIME_EXCEPTION(-90),
    // 未捕获的业务异常
    UNCAUGHT_BIZ_EXCEPTION(-80),
    // 未登陆
    UNLOGIN(-999),
    // 拒绝访问
    PERMISSION_DENIED(-900),
    // 参数非法
    INVALID_PARAMETER(-800),
    // 未知的错误号
    UNKNOWN_ERROR_CODE(-9999);

    private int errorNo;

    private static Map<Integer, ErrorCode> errorCodeMap = new HashMap<>();

    private ErrorCode(int errorNo) {
        this.errorNo = errorNo;
    }

    public int getErrorNo() {
        return errorNo;
    }

    /**
     * 通过错误号获取错误类型
     *
     * @param errorNo
     * @return
     */
    public ErrorCode getErrorCode(int errorNo) {
        synchronized (ErrorCode.class) {
            if (errorCodeMap == null || errorCodeMap.isEmpty()) {
                Arrays.stream(ErrorCode.values()).map(x -> errorCodeMap.put(x.getErrorNo(), x));
            }
        }
        ErrorCode errorCode = errorCodeMap.get(errorNo);
        if (errorCode == null) {
            errorCode = UNKNOWN_ERROR_CODE;
        }
        return errorCode;
    }

    /**
     * 通过错误代码获取错误类型
     * @param strErrorCode
     * @return
     */
    public ErrorCode getErrorCode(String strErrorCode) {
        ErrorCode errorCode = null;
        try {
            errorCode = ErrorCode.valueOf(strErrorCode);
            if (errorCode == null) {
                errorCode = UNKNOWN_ERROR_CODE;
            }
        } catch (Exception e) {
            errorCode = UNKNOWN_ERROR_CODE;
        }
        return errorCode;
    }
}
