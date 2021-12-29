package com.sun.common.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @author sungp
 * @date 2021年12月29日 9:52
 * Spring容器的工具方法 -- 手动从spring中获取bean对象
 */

public class ApplicationContextUtils {

    @Autowired
    private ApplicationContext applicationContext;

    private static ApplicationContext staticApplicationContext;

    /**
     * 初始化方法
     * @author sungp
     * @date 2021/12/29 9:57
     */
    @PostConstruct
    public void init(){
        ApplicationContextUtils.staticApplicationContext = applicationContext;
    }

    /**
     * 根据class类型获取spring容器中的对象
     * @author sungp
     * @date 2021/12/29 9:58
     * @param clazz 要获取类的类型
     * @return T 返回的对象
     */
    public static <T> T getBean(Class<T> clazz){
        if (staticApplicationContext != null)
            return staticApplicationContext.getBean(clazz);
        return null;
    }

}
