package com.javaee.input.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("path")
public class PathController {

    /*@PathVariable获取请求路径的参数
    @GetMapping  //对应查询
    @DeleteMapping  //对应删除
    @PutMapping  //对应修改
    @PostMapping  //对应添加
     */

    @GetMapping("/student/{id}")
    @ResponseBody
    public String query(){
        System.out.println("id = ");
        return "query by id";
    }

    @DeleteMapping("/student/{id}")
    @ResponseBody
    public String delete(){
        System.out.println("id = ");
        return "delete by id";
    }

    @PutMapping("/student/{id}/{sname}")
    @ResponseBody
    public String updateName() {
//        System.out.println("id = " + id + ", name = " + name);
        return "update student name";
    }

    @PostMapping("/student/{name}/{sage}")
    @ResponseBody
    public String add() {
//        System.out.println("name = " + name + ", age = " + age);
        return "add student";
    }
}
