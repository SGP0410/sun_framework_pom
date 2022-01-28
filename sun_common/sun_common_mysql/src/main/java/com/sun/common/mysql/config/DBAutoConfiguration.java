package com.sun.common.mysql.config;

import com.sun.common.mysql.annotation.Paging;
import com.sun.common.mysql.aop.PagingAop;
import com.sun.common.mysql.aop.WebPageAop;
import com.sun.common.mysql.plugins.PagePlugin;
import com.sun.common.mysql.plugins.SqlPlugins;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sungp
 * @date 2021年12月29日 17:11
 */
@Configuration
@MapperScan("com.sun.data.mapper")
@EnableTransactionManagement
public class DBAutoConfiguration {

    /**
     * value - 配置属性的名称
     * havingValue - 与配置文件中的值做比较 - 条件
     * matchIfMissing - 默认值
     * @author sungp
     * @date 2022/1/5 17:50
     */
    @Bean
    @ConditionalOnProperty(value = "sun.plugin.sql.enable" , havingValue = "true" , matchIfMissing = true)
    public SqlPlugins getSqlPlugins(){
        return new SqlPlugins();
    }

    /**
     * 分页插件
     * @author sungp
     * @date 2022/1/6 16:21
     */
    @Bean
    public PagePlugin getPagePlugin(){
        return new PagePlugin();
    }

    /**
     * web层拦截的aop
     * @author sungp
     * @date 2022/1/11 11:41
     */
    @Bean
    public WebPageAop getWebPageAop(){
        return new WebPageAop();
    }

    /**
     * 是否分页
     * @author sungp
     * @date 2022/1/11 14:00
     */
    @Bean
    public PagingAop getPagingAop(){
        return new PagingAop();
    }

}
