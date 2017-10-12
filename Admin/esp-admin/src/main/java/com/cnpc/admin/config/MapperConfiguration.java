package com.cnpc.admin.config;


import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;


@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MapperConfiguration implements EnvironmentAware {

    private RelaxedPropertyResolver propertyResolver;

    private String basePackage;

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment){

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(basePackage);

        Properties properties=new Properties();
        properties.setProperty("mapper",propertyResolver.getProperty("mybatis.mappers"));
        properties.setProperty("IDENTITY",propertyResolver.getProperty("mybatis.identity"));
        properties.setProperty("ORDER",propertyResolver.getProperty("mybatis.order"));
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }


    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, null);
        this.basePackage = propertyResolver.getProperty("mybatis.basepackage");

    }

}