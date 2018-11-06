package com.proasecal.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.proasecal.web.interceptor.AclInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
  
       // This interceptor apply to URL like /admin/*
       // Exclude /admin/oldLogin
       registry.addInterceptor(new AclInterceptor())//
             .addPathPatterns("/*")//
             .excludePathPatterns("/login","/")
             .excludePathPatterns("/error");
    }

}
