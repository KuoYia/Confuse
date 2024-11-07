package com.javaee.rest.controller;

import com.javaee.rest.po.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    // 1）添加学生，前端发送json格式学生数据
    @PostMapping("/")
    public Student addStudent(@RequestBody Student student) {
        System.out.println("Adding student: " + student);
        return new Student(); // 返回一个新实例
    }

    // 2）根据id删除学生
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        System.out.println("Deleting student with id: " + id);
        return "Deleted student with id: " + id; // 返回删除成功的消息
    }

    // 3）修改学生，前端发送json格式学生数据
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
        System.out.println("Updating student with id: " + id);
        return new Student(); // 返回一个新实例
    }

    // 4）根据id查询学生
    @GetMapping("/{id}")
    public Student queryById(@PathVariable int id) {
        System.out.println("Querying student with id: " + id);
        return new Student(); // 返回一个新实例
    }

    // 5）分页查询学生（参数请求页码和每页数据量）
    @GetMapping("/queryByPage")
    public List<Student> queryByPage(@RequestParam int page, @RequestParam int size) {
        System.out.println("Querying students by page: " + page + ", size: " + size);
        return List.of(new Student()); // 返回包含一个新实例的列表
    }

    // 6）模糊查询学生（参数请求页码和每页数据量，查询关键字）
    @GetMapping("/queryByKey")
    public List<Student> queryByKey(@RequestParam String key, @RequestParam int page, @RequestParam int size) {
        System.out.println("Querying students by key: " + key + ", page: " + page + ", size: " + size);
        return List.of(new Student()); // 返回包含一个新实例的列表
    }
}