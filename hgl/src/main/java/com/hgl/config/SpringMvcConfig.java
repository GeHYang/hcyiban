package com.hgl.config;

import com.hgl.web.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan("com.hgl.web")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectInterceptor projectInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>(); // 放行
        excludePath.add("/user");
        excludePath.add("/page/js/*");
        excludePath.add("/page/font/*");
        excludePath.add("/page/css/*");
        excludePath.add("/page/leave/*");
        excludePath.add("/static/**");
        excludePath.add("/view/**");
        excludePath.add("/auth/{*}");
        registry.addInterceptor(projectInterceptor).addPathPatterns("/**").excludePathPatterns(excludePath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/page/js/**").addResourceLocations("/WEB-INF/page/js/");
        registry.addResourceHandler("/page/font/**").addResourceLocations("/WEB-INF/page/font/");
        registry.addResourceHandler("/page/css/**").addResourceLocations("/WEB-INF/page/css/");
        registry.addResourceHandler("/page/leave/**").addResourceLocations("/WEB-INF/page/leave/");
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        WebMvcConfigurer.super.configureContentNegotiation(configurer);
    }
}
