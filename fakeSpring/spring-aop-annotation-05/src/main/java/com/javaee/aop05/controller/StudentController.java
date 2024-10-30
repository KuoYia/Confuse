package com.javaee.aop05.controller;

import com.javaee.aop05.po.Student;
import com.javaee.aop05.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    public int add(Student stu){
        System.out.println("Controller调用studentService的add方法添加数据");
        return studentService.add(stu);
    }

    public int delete(int id) {
        System.out.println("Controller调用studentService的delete方法删除数据");
        return studentService.delete(id);
    }

    public int update(Student stu) {
        System.out.println("Controller调用studentService的update方法修改数据");
        return studentService.update(stu);
    }

    public Student selectById(int id) {
        System.out.println("Controller调用studentService的selectById方法查询数据");
        return studentService.selectById(id);
    }

    public List<Student> selectAll() {
        System.out.println("Controller调用studentService的selectAll方法查询数据");
        return studentService.selectAll();
    }
}
