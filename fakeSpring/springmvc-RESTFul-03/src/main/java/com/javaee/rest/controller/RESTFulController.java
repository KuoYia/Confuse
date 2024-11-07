package com.javaee.rest.controller;

import com.javaee.rest.po.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@RestController
//@RequestMapping("/student")
public class   RESTFulController {
//1）添加学生，前端发送json格式学生数据
//   /student  post请求  json
    @PostMapping
    public int addStudent(@RequestBody Student student){
        System.out.println("addStudent = " + student);
        return 1;
    }

//2）根据id删除学生
//   /student/1   delete请求    path
    @DeleteMapping("/{id}")
    public int deleteStudent(@PathVariable  int id){
        System.out.println("deleteStudent id = " + id);
        return 1;
    }

//3）修改学生，前端发送json格式学生数据
//   /student/  put请求 json
    @PutMapping
    public int updateStudent(@RequestBody Student student){
        System.out.println("updateStudent = " + student);
        return 1;
    }

//4）根据id查询学生
//  /student/1  get请求
    @GetMapping("{id}")
    public Student detail(@PathVariable int id){
        System.out.println("detail id = " + id);
        return null;
    }

//5）分页查询学生（参数请求页码和每页数据量）
//  /student/queryByPage?page=2&size=10 get请求  param
//  不传则使用默认值page=1&size=5
    @GetMapping("/queryByPage")
    public List<Student> queryByPage(@RequestParam(required = false,defaultValue = "1") int page,
                                     @RequestParam(required = false,defaultValue = "5") int size){
        System.out.println("queryByPage page = " + page + ", size = " + size);
        return null;
    }

//6）模糊查询学生（参数请求页码和每页数据量，查询关键字）
//   /student/queryByKey?key=value&page=1&pageSize=10  get请求
//    不传则使用默认值page=1&size=5
    @GetMapping("/queryByKey")
    public List<Student> queryByKey(@RequestParam(required = false) String key,
                                    @RequestParam(required = false,defaultValue = "1") int page,
                                    @RequestParam(required = false,defaultValue = "5") int size){
        System.out.println("key = " + key + ", page = " + page + ", size = " + size);
        return null;
    }
}
