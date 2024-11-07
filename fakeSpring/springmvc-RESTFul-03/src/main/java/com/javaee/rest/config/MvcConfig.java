package com.javaee.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * SpringMVC配置类 第1种方式
 */
@EnableWebMvc  //1 启用json转换器
@Configuration
@ComponentScan("com.javaee.rest.controller")
public class MvcConfig {
    @Bean
    public  RequestMappingHandlerMapping handlerMapping(){
        return new RequestMappingHandlerMapping();
    }
    @Bean
    public RequestMappingHandlerAdapter handlerAdapter(){
        return new RequestMappingHandlerAdapter();
    }
}