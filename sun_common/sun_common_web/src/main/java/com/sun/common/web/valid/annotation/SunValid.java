package com.sun.common.web.valid.annotation;

import com.sun.common.web.valid.constraint.SunValidConstraint;
import com.sun.common.web.valid.handler.SunValidHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义参数校验注解
 *
 * @author sungp
 * @date 2021/12/28 17:32
 */
@Target({ElementType.FIELD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SunValidConstraint.class)
public @interface SunValid {

    /**
     * 校验失败后的提示
     * @author sungp
     * @date 2021/12/28 17:36
     * @return java.lang.String
     */
    String message() default "校验失败";

    /**
     * 校验的分组
     * @author sungp
     * @date 2021/12/28 17:38
     * @return java.lang.Class<?>[]
     */
    Class<?>[] groups() default {};

    /**
     * 校验的负载
     * @author sungp
     * @date 2021/12/28 17:40
     * @return java.lang.Class<? extends javax.validation.Payload>[]
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 实际校验处理的class对象
     * @author sungp
     * @date 2021/12/29 9:32
     * @return java.lang.Class<? extends com.sun.common.web.valid.handler.SunValidHandler>
     */
    Class<? extends SunValidHandler<?>> handler();

}
