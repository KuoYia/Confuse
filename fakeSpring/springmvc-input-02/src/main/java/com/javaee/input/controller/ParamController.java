package com.javaee.input.controller;

import com.javaee.input.po.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/***
 * 1 接收param参数，即调用request.getParameter("key")得到的参数
 * 1.1方法形参和request的参数同名
 * 1.2方法形参和request的参数不同名，且对参数是否必须，默认值等做出限制
 * 1.3一名多值的接收
 * 1.4实体类接收
 * 任务：补充下面各方法形参
 */
@RequestMapping("param")
@Controller
public class ParamController {

    /*
     * 1.1 方法形参与request的参数同名
     * 前端请求: http://localhost:8080/param/sameName?id=1&name=lily
     * 出现乱码正常，json接收具体解决！！
     * @return 返回前端数据
     */
    @RequestMapping("sameName")
    @ResponseBody
    public String update(int id, String name){
        System.out.println("id = " + id + ", name = " + name);
        return "param/sameName";
    }

    /*
     * 1.2 方法形参与request的参数不同名，使用注解
     * 前端请求: http://localhost:8080/param/notSameName?snum=1001&sname=lily&sage=10
     * 使用@RequestParam注解标记handler方法的形参
     * 指定形参对应的请求参数@RequestParam(请求参数名称)
     */
    @RequestMapping("notSameName")
    @ResponseBody
    public String add(@RequestParam(value = "snum") int num,
                      @RequestParam(value = "sname") String name,
                      @RequestParam(value = "sage") int age){
        System.out.println("num = " + num + ", name = " + name + ", age = " + age);
        return "param/notSameName";
    }

    /*
     * 将参数设置非必须，并且设置默认值：
     * @RequestParam(value = "snum") 等价 @RequestParam(value = "snum",required = true)
     */
    @RequestMapping("notSameName1")
    @ResponseBody
    public String addWithDefault(@RequestParam(value = "snum",required = true) int num,
                                 @RequestParam(value = "sname") String name,
                                 @RequestParam(value = "sage", required = false, defaultValue = "18") int age){
        System.out.println("num = " + num + ", name = " + name + ", age = " + age);
        return "param/notSameName1";
    }

    /*
     * 1.3 一名多值
     * 前端请求: http://localhost:8080/param/arrayValue?ids=1&ids=3&ids=9
     * 多选框，提交的数据的时候一个key对应多个值(如批量删除)，即一名多值,
     * 一名多值可以使用集合或数组进行接收,但需要使用@RequestParam注解指定!
     */
    @RequestMapping("arrayValue")
    @ResponseBody
    public String delete(@RequestParam int[] ids){
        System.out.println("ids = " + Arrays.toString(ids));
        return "param/arrayValue";
    }

    /*
     * 1.4 实体类接收，类的属性与请求参数同名
     * http://localhost:8080/param/entityClass?id=1&number=1001&name=wanglwei&age=30
     * 在上述代码中，将请求参数id,number,name和age映射到实体类属性上！
     * 要求属性名必须等于参数名！否则无法映射！
     * 浏览器发出的都是get请求
     * 这里要求时post请求，需要使用postman工具，模拟post请求传递参数，进行测试
     */


    @ResponseBody
    @RequestMapping(value = "/entityClass", method = RequestMethod.POST)
    public String update(Student student){
        System.out.println("student = " + student);
        return "param/entityClass";
    }
}