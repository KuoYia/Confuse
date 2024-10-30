package com.javaee.aop05.service.impl;

import com.javaee.aop05.dao.StudentDao;
import com.javaee.aop05.po.Student;
import com.javaee.aop05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public int add(Student stu){
        System.out.println("service调用dao类insert方法往数据库表插入数据");
        return studentDao.insert(stu);
    }

    @Override
    public int delete(int id) {
        System.out.println("service调用dao类delete方法从数据库删除数据");
        return studentDao.delete(id);
    }

    @Override
    public int update(Student stu) {
        System.out.println("service调用dao类update方法修改数据库数据");
        return studentDao.update(stu);
    }

    @Override
    public Student selectById(int id) {
        System.out.println("service调用dao类selectById方法查询数据");
        return studentDao.selectById(id);
    }

    @Override
    public List<Student> selectAll() {
        System.out.println("service调用dao类selectAll方法查询数据");
        return studentDao.selectAll();
    }
}
