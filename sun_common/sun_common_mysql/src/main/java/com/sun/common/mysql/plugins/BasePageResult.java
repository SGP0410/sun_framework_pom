package com.sun.common.mysql.plugins;

import com.sun.common.mysql.page.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sungp
 * @date 2022年01月11日 11:53
 */
@Data
public class BasePageResult implements Serializable {

    private Page page;

}
