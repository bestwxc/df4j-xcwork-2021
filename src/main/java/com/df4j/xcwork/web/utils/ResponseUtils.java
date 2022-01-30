package com.df4j.xcwork.web.utils;

import com.df4j.xcwork.base.exception.XcworkException;
import com.df4j.xcwork.base.utils.IOUtils;
import com.df4j.xcwork.base.utils.JsonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * 响应工具类
 */
public class ResponseUtils {

    /**
     * 响应json
     *
     * @param response
     * @param resultObject
     */
    public static void json(HttpServletResponse response, Object resultObject) {
        Writer writer = null;
        Throwable t = null;
        try {
            String json = JsonUtils.stringfy(resultObject);
            writer = response.getWriter();
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            t = e;
            throw XcworkException.repack(e);
        } finally {
            IOUtils.close(writer, t);
        }
    }
}
