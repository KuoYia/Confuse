package com.javaee.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMVC配置类 第2种方式
 * 替换之前的配置类MvcConfig
 */
@EnableWebMvc //1 启用json转换器
@Configuration
@ComponentScan("com.javaee.rest.controller")
public class SpringMvcConfig implements WebMvcConfigurer {

    //开启静态资源处理
    @Override
    //当前这个方法继承自接口或父类
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
