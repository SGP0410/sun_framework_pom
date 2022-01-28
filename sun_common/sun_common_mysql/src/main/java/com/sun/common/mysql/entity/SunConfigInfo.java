package com.sun.common.mysql.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sungp
 * @date 2022年01月05日 17:58
 */
@Component
@ConfigurationProperties(prefix = "sun.plugin.sql")
@Data
public class SunConfigInfo {

    private boolean enable;

}
