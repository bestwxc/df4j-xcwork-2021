package com.df4j.xcwork.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * IO操作相关的工具类
 */
public class IOUtils {

    private static Logger logger = LoggerFactory.getLogger(IOUtils.class);

    /**
     * 关闭操作
     *
     * @param closeable
     * @param throwable
     */
    public static void close(AutoCloseable closeable, Throwable throwable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable t) {
                if (throwable != null) {
                    throwable.addSuppressed(t);
                } else {
                    logger.warn("关闭资源时，捕获到异常", t);
                }
            }
        }
    }

    /**
     * 关闭
     *
     * @param closeable
     */
    public static void close(AutoCloseable closeable) {
        close(closeable, null);
    }


}
