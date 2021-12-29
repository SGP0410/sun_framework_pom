package com.sun.data.entity.demo;

import com.sun.common.core.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.UUID;

/**
 * 学生表(Student)表实体类
 *
 * @author sungp
 * @since 2021-12-29 15:01:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends BaseEntity {

    //姓名
    private String name;
    //性别
    private String sex;
    //邮箱
    private String email;

    public Student(){
        long now = Instant.now().toEpochMilli();
        this.id = UUID.randomUUID().toString();
        this.createTime = now;
        this.updateTime = now;
        this.status = 0;
        this.delFlag = 1;
    }


}

