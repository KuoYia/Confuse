package com.javaee.input.controller;

import com.javaee.input.po.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class JsonController {

    /*
     *1 添加json解析的依赖jackson-databind
     *2 添加json解析器 @EnableWebMvc
     *3 post请求
     *4 @RequestBody接收json格式的请求数据
     *  @ResponseBody 将返回值转换为json格式
     */
    /*json数据格式如下：
    {"number":"1002",
    "name":"lucy",
    "age":22,
    "intro":"sddffff"
    }
    */
    @ResponseBody
    @RequestMapping(value = "json/add",method = RequestMethod.POST)
    //等价的进阶注解
    //接收json数据，模拟添加到数据库后获取到了自增的id，像前端返回添加了id值的json数据
    public void addStudent() {
    }

}
