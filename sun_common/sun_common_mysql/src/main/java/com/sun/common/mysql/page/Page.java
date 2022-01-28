package com.sun.common.mysql.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sungp
 * @date 2022年01月06日 13:46
 * 分页信息对象
 */
@Data
public class Page implements Serializable {

    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 总页码
     */
    private Integer pageTotal;
    /**
     * 总条数
     */
    private Integer pageCount;

    /**
     * 是否允许分页
     */
    private boolean isEnable;

    public Page() {
    }

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Page(Integer pageNum, Integer pageSize, Integer pageTotal, Integer pageCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageTotal = pageTotal;
        this.pageCount = pageCount;
    }

    @JsonIgnore
    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
