package com.hgl.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({JdbcConfig.class, MybatisConfig.class, UploadConfig.class})
@ComponentScan({"com.hgl.service", "com.hgl.mapper"})
@MapperScan("com.hgl.mapper")
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement// 开启事务注解
public class SpringConfig {
}
