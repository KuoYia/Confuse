package com.javaee.aop05;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy //开启aspectj注解的支持
@ComponentScan(basePackages = {"com.javaee.aop05"})
@Configuration
public class SpringConfig {
}
