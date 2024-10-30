package com.javaee.aop05.service;

import com.javaee.aop05.po.Student;
import java.util.List;

public interface StudentService {
    public int add(Student stu);
    public int delete(int id);
    public int update(Student stu);
    public Student selectById(int id);
    public List<Student> selectAll();
}
