package com.javaee.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class StudentController {
    //1.编写方法
    @RequestMapping("student/hello") //2 配置web访问该方法的url
    @ResponseBody  //3 代表向浏览器直接返回数据
    public String hello(){
        String message="hello this is my first springmvc program";
        System.out.println("message = " + message);
        return message;
    }
}