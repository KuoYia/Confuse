package com.javaee.aop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.javaee.aop")
@EnableAspectJAutoProxy  //开启 Aspectj注解支持


@PropertySource("classpath:jdbc.properties")



public class SpringConfig {


}
