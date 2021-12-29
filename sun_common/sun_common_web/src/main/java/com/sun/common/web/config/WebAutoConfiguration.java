package com.sun.common.web.config;

import com.sun.common.web.exception.GlobalException;
import com.sun.common.web.utils.ApplicationContextUtils;
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

    /**
     * 全局异常处理器
     * @author sungp
     * @date 2021/12/29 10:05
     * @return com.sun.common.web.exception.GlobalException
     */
    @Bean
    public GlobalException getGlobalException(){
        return new GlobalException();
    }

    /**
     * Spring容器工具类
     * @author sungp
     * @date 2021/12/29 10:06
     * @return com.sun.common.web.utils.ApplicationContextUtils
     */
    @Bean
    public ApplicationContextUtils getApplicationContextUtils(){
        return new ApplicationContextUtils();
    }



}
