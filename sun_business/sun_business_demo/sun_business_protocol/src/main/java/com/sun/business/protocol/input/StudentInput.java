package com.sun.business.protocol.input;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.business.protocol.validHandler.BirthdayValidHandler;
import com.sun.common.web.valid.annotation.SunValid;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * @author sungp
 * @date 2021年12月28日 15:44
 */
@Data
@ToString
@SunValid(message = "年龄和生日不匹配" , handler = BirthdayValidHandler.class)
public class StudentInput {

    @NotNull(message = "姓名不能为空")
    private String name;
    @Min(value = 1, message = "年龄不能小于1岁")
    @Max(value = 20, message = "年龄不能大于20岁")
    private Integer age;
    @NotNull(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    @NotNull(message = "性别不能为空")
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @NotNull(message = "生日不能为空")
    @Past(message = "生日范围不正确")
    private Date birthday;

}
