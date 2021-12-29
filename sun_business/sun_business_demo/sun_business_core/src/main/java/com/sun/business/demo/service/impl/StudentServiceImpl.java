package com.sun.business.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.business.demo.service.StudentService;
import com.sun.data.entity.demo.Student;
import com.sun.data.mapper.demo.dao.StudentDao;
import org.springframework.stereotype.Service;

/**
 * 学生表(Student)表服务实现类
 *
 * @author sungp
 * @since 2021-12-29 15:55:42
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

}

