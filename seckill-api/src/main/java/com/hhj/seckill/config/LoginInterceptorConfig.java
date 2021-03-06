package com.hhj.seckill.config;

import com.hhj.seckill.common.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register","/seckill", "/findUserName", "/images/**", "/public/**", "/resources/**", "/good/**", "/secproduct/**");

    }
}
