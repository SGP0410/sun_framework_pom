package com.sun.common.web.valid.constraint;

import com.sun.common.web.utils.ApplicationContextUtils;
import com.sun.common.web.valid.annotation.SunValid;
import com.sun.common.web.valid.handler.SunValidHandler;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

/**
 * @author sungp
 * @date 2021年12月28日 17:43
 * 自定义参数校验类
 */

public class SunValidConstraint implements ConstraintValidator<SunValid , Object> {

    private SunValid sunValid;

    @Override
    public void initialize(SunValid sunValid) {
        this.sunValid = sunValid;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        //判断非空
        if (value != null){
            Class<? extends SunValidHandler> handler = sunValid.handler();

            //交给SunValidHandler实现类处理
            SunValidHandler sunValidHandler = ApplicationContextUtils.getBean(handler);

            return Optional.ofNullable(sunValidHandler)
                            .map(sunValidHandler1 -> {
                                return sunValidHandler.valid(sunValid , value);
                            }).orElse(false);

        }
        //数据为空则通过校验，校验非空由@NotNull来做
        return true;
    }
}
