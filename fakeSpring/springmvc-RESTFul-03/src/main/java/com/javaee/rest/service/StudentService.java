package com.javaee.rest.service;

import com.javaee.rest.po.Student;

public interface StudentService {
    public int addStudent(Student student);
    public Student queryById(int id);
}
