package com.javaee.input.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1. @RequestMapping的作用：
 * 1).配置url
 * 2).配置请求方式（如get请求method = RequestMethod.GET、post请求等）
 * 3).加在方法上，也可以加在类上
 * 2.进阶注解：
 * 3.务必保证唯一：url和请求方式唯一
 */
@RequestMapping("/mvc")//提取handler公共部分

@Controller
public class RequestMappingController {

   /* @RequestMapping(value = "/hello",method = RequestMethod.GET)*/
    //简化写法（进阶注解）
    @GetMapping("/hello")

    @ResponseBody
    public String hello(){
        return "hello";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query(){
        return "query";
    }


}
