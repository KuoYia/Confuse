package com.javaee.rest.controller;

import com.javaee.rest.po.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share")
public class ShareController {

    // ServletContext共享域对象的使用
    @Autowired
    private ServletContext servletContext;

    // 1 登录时，将用户保存到共享域session
    @GetMapping("/login")
    public User login(HttpSession session, String username, String password) {
        System.out.println("模拟根据用户名和密码查询出对应的用户");
        User user = new User();
        user.setUid(1);
        user.setUsername(username);
        user.setPassword(password);
        // 将用户保存到共享域session
        session.setAttribute("loginUser", user);
        return user;
    }

    // 2 删除操作，打印哪个用户执行删除操作
    // 访问地址 http://localhost:8080/springmvc03/share/delete/1
    @DeleteMapping("/delete/{id}")
   // @PostMapping("/delete/{id}")
    public int delete(@PathVariable int id, HttpSession session) {
        // 从session获取用户，并打印用户在执行删除操作
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            System.out.println("用户 " + user.getUsername() + " 正在执行删除操作");
        } else {
            System.out.println("未登录用户正在执行删除操作");
        }
        return 1;
    }


//访问地址 http://localhost:8080/springmvc03/queryBypage?page=1&size=10
//    @GetMapping("/queryBypage")
//    @ResponseBody
//    public int queryBypage(int page,int size){
//        System.out.println("page = " + page + ", size = " + size);
//        int count = 0;
//        if(servletContext.getAttribute("queryBypageCount")!=null){
//            count = (int)servletContext.getAttribute("queryBypageCount");
//        }
//        System.out.println("count = " + count);
//        servletContext.setAttribute("queryBypageCount",count++);
//        return count;
//    }



}
