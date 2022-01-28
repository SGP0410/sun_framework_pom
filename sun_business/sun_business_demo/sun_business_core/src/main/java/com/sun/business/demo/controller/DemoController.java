package com.sun.business.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.business.demo.service.StudentService;
import com.sun.business.protocol.input.StudentInput;
import com.sun.common.core.result.R;
import com.sun.common.core.utils.RUtils;
import com.sun.common.mysql.annotation.Paging;
import com.sun.data.entity.demo.Student;
import com.sun.data.mapper.demo.dao.StudentDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentDao studentDao;

//    @Value("${com.name}")
//    private String name;

    @PostMapping("/insertStudentInput")
    public R<Boolean> insertStudentInput(@Valid @RequestBody StudentInput studentInput) {
        System.out.println("----->" + studentInput);

        Student student = new Student();
        BeanUtils.copyProperties(studentInput, student);

        boolean save = studentService.save(student);

        return RUtils.createSuccess(save);
    }

    @GetMapping("/selectStudent")
    @Paging
    public R selectStudent() {

        return RUtils.createSuccess(studentDao.findAll());
//        return null;
    }


//    @GetMapping("/test")
//    public R<String> test() {
//
//        String user = null;
//        Assert.notNull(user, "用户信息不能为空");
//
//        throw new ServiceException(Codes.RESOURCES_NOT_FOUNT);

//        log.info("[DemoController] --- test");
//        return RUtils.createSuccess("test");
//    }

}
