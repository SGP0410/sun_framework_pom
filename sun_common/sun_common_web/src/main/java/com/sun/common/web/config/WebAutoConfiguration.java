package com.sun.common.web.config;

import com.sun.common.web.exception.GlobalException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author sungp
 * @date 2021年12月23日 15:10
 * Web模块的自动装配类 - springboot的自动装配功能
 */
//@Configuration
@ComponentScan("com.sun.business")
public class WebAutoConfiguration {

    @Bean
    public GlobalException getGlobalException(){
        return new GlobalException();
    }

}
