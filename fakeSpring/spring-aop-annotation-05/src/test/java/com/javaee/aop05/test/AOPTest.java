package com.javaee.aop05.test;

import com.javaee.aop05.SpringConfig;
import com.javaee.aop05.controller.StudentController;
import com.javaee.aop05.po.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.List;

@SpringJUnitConfig(SpringConfig.class) //spring-test
public class AOPTest {

    @Autowired
    private StudentController studentController;

    @Test
    public void testSelectById(){
        //1 测试环绕通知,查询耗时时间
        //2 测试返回通知，获取返回值
        //3 测试最终final通知,dao类中关闭sqlSession
        Student student = studentController.selectById(1);
        System.out.println("student = " + student);
    }

    @Test
    public void testUpdate(){
        Student stu = new Student(); //测试数据
        stu.setId(1);
        stu.setNumber("22302091010");
        stu.setName("徐杰");
        stu.setAge(20);
        stu.setIntro("2022级软件工程1班学生");

        //1测试异常通知，参数为空会抛出异常 （增删改方法出现的异常信息）
        //int update = studentController.update(null);
        //1测试异常通知，参数不为空，没有异常
        int update = studentController.update(stu);

        //2 测试最终final通知,dao类中关闭sqlSession
        System.out.println("update = " + update);
    }

    @Test
    public void testSelectAll(){
        //1 测试前置通知,模拟检查权限，有权限则执行controller类里的方法
        //2 测试环绕通知,查询耗时时间
        //3 测试最终final通知,dao类中关闭sqlSession
        List<Student> students = studentController.selectAll();
        System.out.println("students = " + students);
    }
}
