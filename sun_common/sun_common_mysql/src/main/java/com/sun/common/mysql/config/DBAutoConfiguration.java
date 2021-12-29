package com.sun.common.mysql.config;

import org.mybatis.spring.annotation.MapperScan;
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

}
