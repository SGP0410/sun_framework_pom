package com.sun.data.mapper.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.data.entity.demo.Student;

import java.util.List;

/**
 * 学生表(Student)表数据库访问层
 *
 * @author sungp
 * @since 2021-12-29 15:41:02
 */
public interface StudentDao extends BaseMapper<Student> {

    List<Student> findAll();

}

