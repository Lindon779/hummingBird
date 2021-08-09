package com.example.demo.WebAppConfigurer;


import com.example.demo.config.CheckHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckHandlerInterceptor()).addPathPatterns("/**")
                                .excludePathPatterns("/getCode", "/login", "/getTime", "/loginPageCheck");
    }
}
