package com.javaee.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect    //标识该类为切面类
@Component //将该类声明为Spring组件
public class OperateLogAspect {

    //数据库层Reposite


    //定义一个切入点，这里使用的是execution来定义匹配所有com.javaee.aop.service.impl包下所有类的所有方法
    @Pointcut("execution(public * com.javaee.aop.service.impl.*.*(..))")
    public void serviceLogPoint() {
        // 该方法没有内容，它仅作为一个切入点的声明
    }

    //在切入点方法上添加@Before注解，表示在切入点方法执行前执行此方法
    //value属性指定了切入点表达式，即serviceLogPoint()，表示该通知方法适用于serviceLogPoint()定义的切入点
    @Before(value="serviceLogPoint()")
    public void saveServiceMethodInvoke(JoinPoint joinPoint) {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println("调用时间：" + currentTimeMillis);

        //获取目标方法的签名，并从中获取方法名、修饰符和声明类名
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        System.out.println("methodName = " + methodName);

        int modifiers = signature.getModifiers();
        System.out.println("modifiers = " + modifiers);

        String declaringTypeName = signature.getDeclaringTypeName();
        System.out.println("declaringTypeName = " + declaringTypeName);

        //获取外界调用目标方法时传入的实参列表
        Object[] args = joinPoint.getArgs();
        //将实参数组转换为List集合，以便更好地打印输出
        List<Object> argList = Arrays.asList(args);

        //打印方法参数列表
        System.out.println("[AOP前置通知] " + methodName + "方法开始了，参数列表：" + argList);
    }
}