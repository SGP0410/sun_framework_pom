package com.sun.common.web.log;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.lang.NonNull;

/**
 * @author sungp
 * @date 2021年12月24日 15:27
 * SpringBoot事件的监听器
 * ApplicationEnvironmentPreparedEvent  --  应用环境准备事件
 */

public class LogMDCListener implements GenericApplicationListener {

    private static final String APPLICATION_CONFIG_NAME = "configurationProperties";
    private static final String APP_NAME = "spring.application.name";

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        //设置当前要监听什么样的事件
        //isAssignableFrom可以匹配当前类的子类
        assert resolvableType.getRawClass() != null;
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(resolvableType.getRawClass());
    }


    @Override
    public void onApplicationEvent(@NonNull ApplicationEvent applicationEvent) {
        //获取微服务的名称
        ApplicationEnvironmentPreparedEvent event = (ApplicationEnvironmentPreparedEvent) applicationEvent;
        //获得配置环境对象
        ConfigurableEnvironment configurableEnvironment = event.getEnvironment();
        //获取属性源集合 -SpringBoot各种配置属性来源
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();

        PropertySource<?> propertySource = propertySources.get(APPLICATION_CONFIG_NAME);
        assert propertySource != null;
        String appName = (String) propertySource.getProperty(APP_NAME);

        System.out.println("[LogMDCListener] 当前启动的服务: "+appName);
        //从log4j2.xml读取
        MDC.put("logName" , appName);
        MDC.put("logPath" , appName);

    }


    @Override
    public int getOrder() {
        //控制监听的顺序 ，值越小优先级越高
        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
