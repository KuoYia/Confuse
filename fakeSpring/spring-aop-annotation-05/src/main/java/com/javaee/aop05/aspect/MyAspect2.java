package com.javaee.aop05.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
@Aspect
@Component
public class MyAspect2 {

    //2.记录dao类增删改方法出现的异常信息：异常通知的使用
    //2.1 应该选择异常通知，会在程序出现异常的时刻，织入代码   对应注解@AfterThrowing
    //2.2 通知可绑定多个切入点
    //2.3 获取异常信息：1）方法声明异常类型的形参 2）通知中配置throwing属性，值必须等于形参名
    @AfterThrowing(value = "MyAspect.daoInsertPoint()||MyAspect.daoDeletePoint()||MyAspect.daoUpdatePoint()"
            ,throwing="e")
    public void ExceptionLog(JoinPoint joinPoint,Exception e){
        System.out.println("----异常通知 开始----");
        //记录哪个类的方法哪个时刻出现异常，异常信息
        long currentTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className+"."+methodName+"执行时间："+simpleDateFormat.format(currentTime));
        System.out.println("异常信息："+e.getMessage());
        System.out.println("----异常通知 结束----");
    }
    //3.dao类中关闭sqlSession：最终final通知
    //不管程序执行成功还是出现异常，都会织入代码
    @After(value= "MyAspect.daoAllMethodPoint()")
    public void closeSqlSession(JoinPoint joinPoint){
        System.out.println("----最终final通知 开始----");

        long currentTime = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println(className+"."+methodName+"执行时间："+simpleDateFormat.format(currentTime));
        System.out.println("模拟关闭sqlsession对象");

        System.out.println("----最终final通知 结束----");
    }

    //4.记录service类中查询方法的返回值：返回通知@AfterReturning
    //只有成功执行才会织入代码
    //获取目标方法的返回值：1）方法声明Object类型的形参 2）通知中配置returning属性，值必须等于形参名
    @AfterReturning(value="MyAspect.serviceQueryPoint()",returning="queryResult")
    public void queryResultLog(JoinPoint joinPoint,Object queryResult){
        System.out.println("----返回通知 开始----");
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        System.out.println(className+"."+methodName+"返回值："+queryResult);
        System.out.println("----返回通知 结束----");
    }

    //5.模拟检查权限，有权限则执行controller类里的方法
    //自行完成
    // 5.模拟检查权限，有权限则执行controller类里的方法：前置通知@Before
    // 只有当权限检查通过时，才会执行目标方法
    @Before("MyAspect.controllerAllMethodPoint()")
    public void checkPermission(JoinPoint joinPoint) {
        System.out.println("----前置通知 开始----");
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        // 模拟权限检查逻辑
        boolean hasPermission = checkUserPermission(); // 假设这是检查用户权限的方法

        if (hasPermission) {
            System.out.println(className + "." + methodName + "权限检查通过，执行方法");
        } else {
            System.out.println(className + "." + methodName + "权限检查失败，阻止方法执行");
            // 可以在这里抛出异常或者进行其他处理来阻止方法执行
            throw new SecurityException("权限不足，无法执行方法：" + methodName);
        }

        System.out.println("----前置通知 结束----");
    }

    // 假设这是检查用户权限的方法，实际开发中需要根据实际情况实现
    private boolean checkUserPermission() {
        // 这里应该是实际的权限检查逻辑，例如查询用户的角色和权限等
        // 为了示例，这里默认返回true，表示用户有权限
        return true;
    }
}