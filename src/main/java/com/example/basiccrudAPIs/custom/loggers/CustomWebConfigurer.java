package com.example.basiccrudAPIs.custom.loggers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class CustomWebConfigurer implements WebMvcConfigurer {

    @Autowired
    private InterceptLog logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor);
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
