package com.clover.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;


@Configuration

@ComponentScan(basePackages = "com.clover") // 确保包含 com.clover.service.impl
@PropertySource("classpath:jdbc.properties")
@MapperScan("com.clover.dao")

public class SpringConfig {

    @Bean
    public DataSource dataSource(
            @Value("${jdbc.driver}") String driver,
            @Value("${jdbc.url}") String url,
            @Value("${jdbc.username}") String username,
            @Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        sessionFactory.setDataSource(dataSource);

        // MyBatis会自动使用SLF4J与Logback的桥接，无需显式设置日志实现
        return sessionFactory.getObject();
    }
    /*SqlSessionFactory 本身是线程安全的，可以被多个线程共享。
    但是，SqlSessionFactory 通常会创建 SqlSession 实例，这些实例则是非线程安全的。
    在 MyBatis 中，SqlSession 是执行映射器操作的主要对象，每个线程都应该有自己的 SqlSession 实例。因此，通常的做法是：
    从 SqlSessionFactory 获取 SqlSession：您可以从 SqlSessionFactory 中获取 SqlSession，
    每个线程都应该使用自己的 SqlSession 实例。
    这通常是通过在 MyBatis 的配置中启用自动代理来完成的，这样每个数据访问对象（DAO）都会自动获得自己的 SqlSession。
    提交或关闭 SqlSession：在事务结束后，您应该提交或关闭 SqlSession。如果您使用 Spring 管理事务，这通常会自动处理。
    线程安全性：SqlSessionFactory 是线程安全的，但是每个 SqlSession 应该是线程局部的，不应该在多个线程间共享。*/


   /* SqlSessionFactoryBean：您在Spring配置中定义了一个SqlSessionFactoryBean，它负责创建和配置SqlSessionFactory。
   这个工厂是线程安全的，并且用于生成SqlSession。
    SqlSessionTemplate：虽然您没有显式定义一个SqlSessionTemplate，
    但Spring MyBatis集成会自动为您创建一个，如果您使用了@MapperScan注解来扫描Mapper接口。
    @MapperScan：您在配置类上使用了@MapperScan注解，这告诉Spring去哪里找Mapper接口，并为它们创建代理对象。
    这些代理对象会使用SqlSession来执行定义在Mapper接口中的方法。
    Mapper接口：您的PoetDao接口是一个Mapper接口，当Spring容器启动时，
    它会为这个接口创建一个代理实现。当您通过Spring容器注入PoetDao并调用其方法时，实际上是调用了代理对象的方法。
    SqlSession生命周期：在代理对象的方法中，MyBatis会从SqlSessionFactory获取一个SqlSession，
    执行相应的数据库操作，然后关闭SqlSession。
    如果Spring管理了事务，SqlSession的提交或回滚将由Spring的事务管理器控制。*/
}