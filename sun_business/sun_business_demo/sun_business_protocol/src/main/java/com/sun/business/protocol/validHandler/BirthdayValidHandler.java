package com.sun.business.protocol.validHandler;

import com.sun.business.protocol.input.StudentInput;
import com.sun.common.web.valid.annotation.SunValid;
import com.sun.common.web.valid.handler.SunValidHandler;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * @author sungp
 * @date 2021年12月29日 10:44
 */
@Component
public class BirthdayValidHandler implements SunValidHandler<StudentInput> {
    @Override
    public boolean valid(SunValid sunValid, StudentInput data) {
        System.out.println("-------> 自定义校验注解触发！");

        Integer age = data.getAge();
        Date birthday = data.getBirthday();

        if (age == null || birthday == null) {
            return true;
        }

        //当前时间
        Date now = new Date();
        //日历对象
        Calendar calendar = Calendar.getInstance();
        //当前年份
        calendar.setTime(now);
        int nowYear = calendar.get(Calendar.YEAR);
        //出生年份
        calendar.setTime(birthday);
        int birthdayYear = calendar.get(Calendar.YEAR);

        return (nowYear - birthdayYear) == age;
    }
}
