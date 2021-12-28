package com.sun.common.web.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sungp
 * @date 2021年12月28日 14:35
 */

public class HttpUtils {

    /**
     * 获取当前请求
     *
     * @return javax.servlet.http.HttpServletRequest
     * @author sungp
     * @date 2021/12/28 14:36
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获取当前响应
     *
     * @return javax.servlet.http.HttpServletRequest
     * @author sungp
     * @date 2021/12/28 14:36
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getResponse();
    }

}
