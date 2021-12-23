package com.sun.business.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sungp
 * @date 2021年12月23日 15:01
 */

@RestController
public class DemoController {

    @GetMapping("/test")
    public String test(){
        return "test.........";
    }

}
