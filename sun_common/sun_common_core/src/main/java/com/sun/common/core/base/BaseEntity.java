package com.sun.common.core.base;

import java.io.Serializable;

/**
 * @author sungp
 * @date 2021年12月29日 15:09
 */

public class BaseEntity implements Serializable {

    //id
    protected String id;
    //创建时间
    protected Long createTime;
    //更新时间
    protected Long updateTime;
    //状态
    protected Integer status;
    //删除标识 0删除 1未删除
    protected Integer delFlag;

}
