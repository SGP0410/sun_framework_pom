package com.sun.common.web.exception;

import com.sun.common.core.result.Codes;
import lombok.Data;

/**
 * @author sungp
 * @date 2021年12月28日 14:41
 * 自定义业务异常
 */
@Data
public class ServiceException extends RuntimeException {

    private Integer code;
    private String msg;

    public ServiceException(Codes codes){
        super(codes.getMsg());
        this.code = codes.getCode();
        this.msg = codes.getMsg();
    }

    public ServiceException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(){}
}
