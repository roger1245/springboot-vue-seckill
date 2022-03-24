package com.hhj.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;


@Configuration
public class PrepartConfig {

    @Bean
    public HashMap<String,Long> setMap(){
        return new HashMap<>();
    }
}
