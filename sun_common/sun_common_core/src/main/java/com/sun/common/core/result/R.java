package com.sun.common.core.result;

import com.sun.common.mysql.plugins.BasePageResult;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sungp
 * @date 2021年12月28日 10:33
 * 微服务的统一返回对象
 */
@Data
public class R<T> extends BasePageResult {

    //响应码
    private Integer code;

    //响应消息
    private String msg;

    //数据
    private T data;

    /**
     * 全参构造
     *
     * @param code    响应码
     * @param message 消息
     * @param data    数据
     * @author sungp
     * @date 2021/12/28 10:45
     */
    public R(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    /**
     * 数据为空时
     *
     * @param code    响应码
     * @param message 消息
     * @author sungp
     * @date 2021/12/28 10:47
     */
    public R(Integer code, String message) {
        this.code = code;
        this.msg = message;
        this.data = null;
    }

    public R() {

    }
}
