package com.javaee.aop05.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class MyAspect {

    //@Pointcut标记切入点，切入点的id即方法名，execution()是切入点表达式的固定写法
    //1.记录dao类查询方法的耗时时间
    @Pointcut(value = "execution(* com.javaee.aop05.dao.impl.*.select*(..))")
    public void daoQueryPoint(){}

    //2.记录dao类增删改方法出现的异常信息
    //第一个*为返回值类型，
    @Pointcut(value = "execution(* com.javaee.aop05.dao.impl.*.insert*(..))")
    public void daoInsertPoint(){}
    @Pointcut(value = "execution(* com.javaee.aop05.dao.impl.*.delete*(..))")
    public void daoDeletePoint(){}
    @Pointcut(value = "execution(* com.javaee.aop05.dao.impl.*.update*(..))")
    public void daoUpdatePoint(){}

    //3.dao类中关闭sqlSession
    @Pointcut(value = "execution(* com.javaee.aop05.dao.impl.*.*(..))")
    public void daoAllMethodPoint(){}

    //4.记录service类中查询方法的返回值
    @Pointcut(value = "execution(* com.javaee.aop05.dao.impl.*.select*(..))")
    public void serviceQueryPoint(){}

    //5.模拟检查权限，有权限则执行controller类里的方法
    @Pointcut(value = "execution(* com.javaee.aop05.controller.*.*(..))")
    public void controllerAllMethodPoint(){}

    //1.记录dao类查询方法的耗时时间：环绕通知的使用
    //引用其他类中的切入点：类的全限定名.方法名
    //@Around(value = "com.javaee.aop05.aspect.MyAspect.daoQueryPoint()")
    //@Around(value = "daoQueryPoint()")
    @Around(value = "execution(* com.javaee.aop05.dao.impl.*.select*(..))")
    public Object saveQureyTime(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("----环绕通知 开始----");
        Object returnValue=null;
        long startTime = System.currentTimeMillis();
        try {
            //ProceedingJoinPoint是为环绕通知量身定制的！
            Object[] args = proceedingJoinPoint.getArgs();//可以不传
            //执行目标方法，即本任务中的dao类查询方法
            returnValue = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println(className+"."+methodName+"执行耗时："+(endTime-startTime)+"毫秒");
        System.out.println("----环绕通知 结束----");
        return returnValue; //必须要返回，否则目标方法执行后没有返回值
    }
}
