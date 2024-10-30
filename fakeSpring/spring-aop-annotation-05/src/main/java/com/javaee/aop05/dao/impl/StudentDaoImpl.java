package com.javaee.aop05.dao.impl;

import com.javaee.aop05.dao.StudentDao;
import com.javaee.aop05.po.Student;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Override
    public int insert(Student stu){
        System.out.println("StudentDaoImpl模拟往数据库表插入数据");
        return 1;
    }

    @Override
    public int delete(int id) {
        System.out.println("StudentDaoImpl模拟从数据表删除数据");
        return 1;
    }

    @Override
    public int update(Student stu) {
        if(stu==null)
            throw new RuntimeException("未传参需要修改的数据");
        System.out.println("StudentDaoImpl模拟修改数据库表数据");
        return 1;
    }

    @Override
    public Student selectById(int id) {
        System.out.println("StudentDaoImpl模拟根据id从数据库表查询数据");
        try {
            //模拟查询耗时
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Student stu = null;
        if(id==1) {
            stu = new Student();
            stu.setId(1);
            stu.setNumber("22302091010");
            stu.setName("徐杰");
            stu.setAge(20);
            stu.setIntro("2022级软件工程1班学生");
        }
        return stu;
    }

    @Override
    public List<Student> selectAll() {
        System.out.println("StudentDaoImpl模拟从数据库表查询全部数据");
        try {
            //模拟查询耗时
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
