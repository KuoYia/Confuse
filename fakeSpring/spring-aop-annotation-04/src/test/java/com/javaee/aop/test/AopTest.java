package com.javaee.aop.test;


import com.javaee.aop.config.SpringConfig;
import com.javaee.aop.po.Student;
import com.javaee.aop.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value ={SpringConfig.class} )
public class AopTest {

    @Autowired
    @Qualifier("studentServiceImpl")
    private StudentService studentService;

    @Test
    public void testInsertStudent(){
        studentService.insertStudent(stu);
    }

    private Student stu;
    @BeforeEach
    public void before(){
        stu=new Student(); //测试数据
        stu.setNumber("22302091010");
        stu.setName("徐杰");
        stu.setAge(20);
        stu.setIntro("2022级软件工程1班学生");
    }
}
