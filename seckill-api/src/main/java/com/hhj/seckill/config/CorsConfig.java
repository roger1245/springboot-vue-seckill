package com.hhj.seckill.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 这里要用allowedOriginPatterns，不能用allowedOrigin，不然会报错
//                .allowedOriginPatterns("*")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .allowCredentials(true)
                .exposedHeaders("uuid")
                .maxAge(3600)
                .allowedHeaders("*");
    }





}