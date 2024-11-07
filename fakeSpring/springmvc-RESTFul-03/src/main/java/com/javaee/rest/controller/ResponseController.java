package com.javaee.rest.controller;

import com.javaee.rest.po.Student;
import com.javaee.rest.po.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

// 使用RestController注解，它整合了@Controller和@ResponseBody
@RestController
public class ResponseController {

    // 查询学生信息
    // 访问地址 http://localhost:8080/springmvc03/query/{id}
    //http://localhost:8080/springmvc03/query/1
    @GetMapping("/query/{id}")
    public Student queryById(@PathVariable int id) {
        System.out.println("模拟查询指定id的学生");
        Student student = new Student();
        student.setId(id);
        student.setNum("number");
        student.setName("username");
        student.setAge(19);
        student.setIntro("学生");
        return student;
    }

    // 登录功能
    // 访问地址 http://localhost:8080/springmvc03/login
    //http://localhost:8080/springmvc03/login?username=admin&password=12345
    @GetMapping("/login")
    public User login(@RequestParam(required = true,defaultValue ="admin") String username,
                      @RequestParam(defaultValue = "12345") String password,
                      HttpSession session) {
        System.out.println("模拟根据用户名和密码查询出对应的用户");
        User user = new User();
        user.setUid(1);
        user.setUsername(username);
        user.setPassword(password);
        //将用户保存到共享域
        session.setAttribute("loginUser",user);
        return user;
    }
}