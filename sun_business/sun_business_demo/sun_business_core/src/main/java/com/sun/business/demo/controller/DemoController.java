package com.sun.business.demo.controller;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sungp
 * @date 2021年12月23日 15:01
 */

@RestController
public class DemoController {

    @Value("${com.name}")
    private String name;

    @GetMapping("/test")
    public String test(){
        return "test........."+name;
    }

}
