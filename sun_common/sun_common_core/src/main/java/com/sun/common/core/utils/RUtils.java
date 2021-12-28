package com.sun.common.core.utils;

import com.sun.common.core.result.Codes;
import com.sun.common.core.result.R;

/**
 * @author sungp
 * @date 2021年12月28日 10:53
 * 快速构建R对象工具类
 */

public class RUtils {

    /**
     * 成功的R对象
     *
     * @param data 数据
     * @param <T>  数据泛型
     * @return 返回R对象
     * @author sungp
     * @date 2021/12/28 11:06
     */
    public static <T> R<T> createSuccess(T data) {
        return new R<T>(Codes.SUCCESS.getCode(), Codes.SUCCESS.getMsg(), data);
    }

    /**
     * 失败的R对象
     *
     * @param codes 响应结果
     * @return com.sun.common.core.result.R<T>
     * @author sungp
     * @date 2021/12/28 11:06
     */
    public static <T> R<T> createFail(Codes codes) {
        return new R<T>(codes.getCode(), codes.getMsg(), null);
    }

    public static <T> R<T> createFail(Integer code, String message) {
        return new R<T>(code, message, null);
    }

    /**
     * 构建常规R对象
     *
     * @param code    响应码
     * @param message 消息
     * @param data    数据
     * @return com.sun.common.core.result.R<T>
     * @author sungp
     * @date 2021/12/28 11:09
     */
    public static <T> R<T> create(Integer code, String message, T data) {
        return new R<T>(code, message, data);
    }

}
