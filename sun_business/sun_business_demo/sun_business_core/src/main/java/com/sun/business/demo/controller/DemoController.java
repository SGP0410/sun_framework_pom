package com.sun.business.demo.controller;

import com.sun.business.protocol.input.StudentInput;
import com.sun.common.core.result.Codes;
import com.sun.common.core.result.R;
import com.sun.common.core.utils.RUtils;
import com.sun.common.web.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author sungp
 * @date 2021年12月23日 15:01
 */

@RestController
@Slf4j
public class DemoController {

    @Value("${com.name}")
    private String name;

    @PostMapping("/insertStudentInput")
    public R<StudentInput> insertStudentInput(@Valid @RequestBody StudentInput studentInput) {
        System.out.println("----->" + studentInput);
        return RUtils.createSuccess(studentInput);
    }


    @GetMapping("/test")
    public R<String> test() {

        String user = null;
        Assert.notNull(user, "用户信息不能为空");

        throw new ServiceException(Codes.RESOURCES_NOT_FOUNT);

//        log.info("[DemoController] --- test");
//        return RUtils.createSuccess("test");
    }

}
