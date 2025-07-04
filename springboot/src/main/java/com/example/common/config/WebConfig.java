package com.example.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements  WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    // 禁用JWT拦截器
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
    //             .excludePathPatterns("/")
    //             .excludePathPatterns("/login")
    //             .excludePathPatterns("/register")
    //             .excludePathPatterns("/files/**")
    //             .excludePathPatterns("/type/**")
    //             .excludePathPatterns("/notice/selectAll")
    //             .excludePathPatterns("/goods/**")
    //             .excludePathPatterns("/comment/selectByGoodsId/**");
    // }
}