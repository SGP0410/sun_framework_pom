package com.sun.common.web.valid.handler;

import com.sun.common.web.valid.annotation.SunValid;

/**
 * @author sungp
 * @date 2021年12月28日 17:50
 * 拓展接口 - 实现该接口实现自定义校验规则
 */

public interface SunValidHandler<T> {

    /**
     * 实际的校验方法
     * @author sungp
     * @date 2021/12/28 17:52
     * @param data 校验的数据
     * @return boolean 返回值
     */
    boolean valid(SunValid sunValid , T data);

}
