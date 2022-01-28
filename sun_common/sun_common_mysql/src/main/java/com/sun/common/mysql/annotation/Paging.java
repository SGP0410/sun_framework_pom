package com.sun.common.mysql.annotation;

import java.lang.annotation.*;

/**
 * 分页标识
 * @author sungp
 * @date 2022/1/11 12:57
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Paging {
}
