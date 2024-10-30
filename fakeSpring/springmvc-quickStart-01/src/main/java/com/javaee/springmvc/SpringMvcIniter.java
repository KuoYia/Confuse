package com.javaee.springmvc;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class SpringMvcIniter extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //1 配置springmvc的配置类
        return new Class[]{MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        /*2 配置前端控制器DS接收的请求url
        功能类似web.xml配置
        	<servlet-mapping>
        		<servlet-name>ds</servlet-name>
        		<url-pattern>/</url-pattern>
        	</servlet-mapping>
        */
        return new String[]{"/"};
    }
}